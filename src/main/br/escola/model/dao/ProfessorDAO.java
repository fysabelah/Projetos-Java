package main.br.escola.model.dao;

import main.br.escola.app.DatabaseFactory;
import main.br.escola.ctrl.exception.PessoaException;
import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.model.entities.Professor;
import main.br.escola.model.enums.Escolaridade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public Professor inserir(Professor professor) throws ProfessorException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO tb_professor " + "(escolaridade, id_pessoa)" + "VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, professor.getEscolaridade().getId());
            st.setInt(2, professor.getPessoa().getIdPessoa());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    professor.setIdProfessor(id);
                }
            }
        } catch (SQLException e) {
            throw new ProfessorException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return professor;
    }

    public Professor buscarPorCpf(Long cpf) throws ProfessorException {
        Professor professor = null;

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = new StringBuilder()
                    .append("SELECT professor.* ")
                    .append("FROM tb_professor AS professor ")
                    .append("INNER JOIN tb_pessoa AS pessoa ON professor.id_pessoa = pessoa.id_pessoa ")
                    .append("WHERE pessoa.cpf = ?")
                    .toString();

            st = conn.prepareStatement(query);
            st.setLong(1, cpf);

            rs = st.executeQuery();

            if (rs.next()) {
                professor = this.vo(rs);
            }
        } catch (SQLException | PessoaException e) {
            throw new ProfessorException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return professor;
    }

    public List<Professor> buscaTodos() throws ProfessorException {
        ResultSet rs = null;
        PreparedStatement st = null;

        List<Professor> professors = new ArrayList<>();

        try {
            Connection conn = DatabaseFactory.getConnection();
            String query = "SELECT id_professor, escolaridade, id_pessoa FROM tb_professor ORDER BY id_professor ";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                professors.add(this.vo(rs));
            }
        } catch (SQLException | PessoaException e) {
            throw new ProfessorException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return professors;
    }

    private Professor vo(ResultSet rs) throws SQLException, PessoaException {
        Professor professor = new Professor();

        professor.setIdProfessor(rs.getInt("id_professor"));
        professor.setEscolaridade(Escolaridade.get(rs.getInt("escolaridade")));

        Integer idPessoa = rs.getInt("id_pessoa");
        PessoaDAO pessoaDAO = new PessoaDAO();
        professor.setPessoa(pessoaDAO.buscaPorId(idPessoa));

        return professor;
    }

    public Professor buscaPorId(Integer id) throws ProfessorException {
        Professor professor = null;

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_professor, escolaridade, id_pessoa FROM tb_professor WHERE id_professor = ? ";
            st = conn.prepareStatement(query);
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                professor = this.vo(rs);
            }
        } catch (SQLException | PessoaException e) {
            throw new ProfessorException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return professor;
    }

    public Professor alterar(Professor professor) throws ProfessorException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();
            String query = "UPDATE tb_professor SET escolaridade = ?, id_pessoa = ? WHERE id_professor = ?";

            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, professor.getEscolaridade().getId());
            st.setInt(2, professor.getPessoa().getIdPessoa());
            st.setInt(3, professor.getIdProfessor());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new ProfessorException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return professor;
    }

    public void excluir(Integer id) throws ProfessorException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = " DELETE FROM tb_professor WHERE id_professor = ?";
            st = conn.prepareStatement(query);
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

        } catch (SQLException e) {
            throw new ProfessorException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }
    }
}
