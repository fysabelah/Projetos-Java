package main.br.escola.model.dao;

import main.br.escola.app.DatabaseFactory;
import main.br.escola.ctrl.exception.DisciplinaException;
import main.br.escola.model.entities.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DisciplinaDAO {

    public Disciplina inserir(Disciplina disciplina) throws DisciplinaException {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO tb_disciplina " + "(nm_disciplina, carga_horaria)" + "VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, disciplina.getNmDisciplina());
            st.setInt(2, disciplina.getCargaHoraria());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    disciplina.setIdDisciplina(id);
                }
            }
        } catch (SQLException e) {
            throw new DisciplinaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return disciplina;
    }

    public Disciplina buscarPorNome(String nome) throws DisciplinaException {
        Disciplina disciplina = null;

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_disciplina, nm_disciplina, carga_horaria FROM tb_disciplina WHERE nm_disciplina LIKE ?";

            st = conn.prepareStatement(query);
            st.setString(1, '%' + nome + '%');

            rs = st.executeQuery();

            if (rs.next()) {
                disciplina = vo(rs);
            }
        } catch (SQLException e) {
            throw new DisciplinaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return disciplina;
    }

    public List<Disciplina> buscaTodos() throws DisciplinaException {
        ResultSet rs = null;
        PreparedStatement st = null;

        List<Disciplina> disciplinas = new ArrayList<>();

        try {
            Connection conn = DatabaseFactory.getConnection();

            st = conn.prepareStatement("SELECT id_disciplina, nm_disciplina, carga_horaria" + " FROM tb_disciplina "
                    + " ORDER BY nm_disciplina ");

            rs = st.executeQuery();

            while (rs.next()) {
                disciplinas.add(vo(rs));
            }

        } catch (SQLException e) {
            throw new DisciplinaException(e.getMessage());

        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return disciplinas;
    }

    private Disciplina vo(ResultSet rs) throws SQLException {
        Disciplina disciplina = new Disciplina();

        disciplina.setIdDisciplina(rs.getInt("id_disciplina"));
        disciplina.setNmDisciplina(rs.getString("nm_disciplina"));
        disciplina.setCargaHoraria(rs.getInt("carga_horaria"));

        return disciplina;
    }

    public Disciplina buscaPorId(Integer id) throws DisciplinaException {
        Disciplina disciplina = new Disciplina();

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            st = conn.prepareStatement("SELECT id_disciplina, nm_disciplina, carga_horaria" + " FROM tb_disciplina "
                    + " WHERE id_disciplina = ? ");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                disciplina = vo(rs);
            }

        } catch (SQLException e) {
            throw new DisciplinaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return disciplina;
    }

    public Disciplina alterar(Disciplina disciplina) throws DisciplinaException {

        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "UPDATE tb_disciplina "
                    + " SET "
                    + " nm_disciplina = ? , "
                    + " carga_horaria = ? "
                    + " WHERE id_disciplina = ?";
            st = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, disciplina.getNmDisciplina());
            st.setInt(2, disciplina.getCargaHoraria());
            st.setInt(3, disciplina.getIdDisciplina());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new DisciplinaException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return disciplina;
    }

    public void excluir(Integer id) throws DisciplinaException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = " DELETE FROM tb_disciplina WHERE id_disciplina = ?";
            st = conn.prepareStatement(query);
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

        } catch (SQLException e) {
            throw new DisciplinaException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }
    }
}
