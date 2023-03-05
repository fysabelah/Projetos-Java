package main.br.escola.model.dao;

import main.br.escola.app.DatabaseFactory;
import main.br.escola.ctrl.exception.CursoException;
import main.br.escola.model.entities.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public Curso inserir(Curso curso) throws CursoException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO tb_curso " + "(nm_curso)" + "VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, curso.getNmCurso());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    curso.setIdCurso(id);
                }

            }
        } catch (SQLException e) {
            throw new CursoException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return curso;
    }

    public List<Curso> buscaTodos() throws CursoException {
        ResultSet rs = null;
        PreparedStatement st = null;

        List<Curso> curso = new ArrayList<>();

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_curso, nm_curso FROM tb_curso ORDER BY nm_curso ";
            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                curso.add(this.vo(rs));
            }
        } catch (SQLException e) {
            throw new CursoException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return curso;
    }

    private Curso vo(ResultSet rs) throws SQLException {
        Curso curso = new Curso();

        curso.setIdCurso(rs.getInt("id_curso"));
        curso.setNmCurso(rs.getString("nm_curso"));

        return curso;
    }

    public Curso buscaPorId(Integer id) throws CursoException {
        Curso curso = null;

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_curso, nm_curso FROM tb_curso WHERE id_curso = ? ";

            st = conn.prepareStatement(query);
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                curso = this.vo(rs);
            }
        } catch (SQLException e) {
            throw new CursoException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return curso;
    }

    public List<Curso> buscarPorNome(String curso) throws CursoException {
        List<Curso> cursos = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_curso, nm_curso FROM tb_curso WHERE nm_curso LIKE ?";

            st = conn.prepareStatement(query);
            st.setString(1, '%' + curso + '%');

            rs = st.executeQuery();

            if (rs.next()) {
                cursos.add(this.vo(rs));
            }
        } catch (SQLException e) {
            throw new CursoException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return cursos;
    }

    public Curso alterar(Curso curso) throws CursoException {
        PreparedStatement st = null;

        try {

            Connection conn = DatabaseFactory.getConnection();

            String query = "UPDATE tb_curso SET nm_curso = ? WHERE id_curso = ?";

            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, curso.getNmCurso());
            st.setInt(2, curso.getIdCurso());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new CursoException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return curso;
    }

    public void excluir(Integer id) throws CursoException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = " DELETE FROM tb_curso WHERE id_curso = ?";

            st = conn.prepareStatement(query);
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new CursoException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }
    }
}
