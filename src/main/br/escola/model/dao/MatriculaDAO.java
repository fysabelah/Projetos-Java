package main.br.escola.model.dao;

import main.br.escola.app.DatabaseFactory;
import main.br.escola.ctrl.exception.*;
import main.br.escola.model.entities.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public Matricula inserir(Matricula matricula) throws MatriculaException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO tb_matricula " + "(id_aluno, id_oferta)" + "VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, matricula.getAluno().getIdAluno());
            st.setInt(2, matricula.getOferta().getIdOferta());

            int rowsAffected = st.executeUpdate();

            System.out.println("Linhas alteradas: " + rowsAffected);

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    matricula.setIdMatricula(id);
                }
            }
        } catch (SQLException e) {
            throw new MatriculaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return matricula;
    }

    public List<Matricula> buscaTodos() throws MatriculaException, AlunoException, CursoException, PessoaException, OfertaException, DisciplinaException, ProfessorException {
        ResultSet rs = null;
        PreparedStatement st = null;

        List<Matricula> matricula = new ArrayList<>();

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula ORDER BY id_matricula ";
            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                matricula.add(this.vo(rs));
            }
        } catch (SQLException e) {
            throw new MatriculaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return (matricula);
    }

    private Matricula vo(ResultSet rs) throws SQLException, AlunoException, CursoException, PessoaException, OfertaException, ProfessorException, DisciplinaException {
        Matricula matricula = new Matricula();
        AlunoDAO alunoDAO = new AlunoDAO();
        OfertaDAO ofertaDAO = new OfertaDAO();

        matricula.setAluno(alunoDAO.buscaPorId(rs.getInt("id_aluno")));
        matricula.setIdMatricula(rs.getInt("id_matricula"));
        matricula.setOferta(ofertaDAO.buscaPorId(rs.getInt("id_oferta")));

        return (matricula);
    }

    public Matricula buscaPorId(Integer id) throws MatriculaException, AlunoException, CursoException, PessoaException, OfertaException, ProfessorException, DisciplinaException {
        Matricula matricula = null;

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula WHERE id_matricula = ?";

            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                matricula = this.vo(rs);
            }
        } catch (SQLException e) {
            throw new MatriculaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return (matricula);
    }

    public Matricula alterar(Matricula matricula) throws MatriculaException {
        PreparedStatement st = null;

        try {

            Connection conn = DatabaseFactory.getConnection();

            String query = "UPDATE tb_matricula SET id_aluno = ?, id_oferta = ? WHERE id_matricula = ?";

            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, matricula.getAluno().getIdAluno());
            st.setInt(2, matricula.getOferta().getIdOferta());
            st.setInt(3, matricula.getIdMatricula());

            int rowsAffected = st.executeUpdate();

            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new MatriculaException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return matricula;
    }

    public void excluir(Integer id) throws MatriculaException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = " DELETE FROM tb_matricula WHERE id_matricula = ?";

            st = conn.prepareStatement(query);
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new MatriculaException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }
    }
}
