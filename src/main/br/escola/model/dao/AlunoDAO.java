package main.br.escola.model.dao;

import main.br.escola.app.DatabaseFactory;
import main.br.escola.ctrl.exception.AlunoException;
import main.br.escola.ctrl.exception.CursoException;
import main.br.escola.ctrl.exception.PessoaException;
import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.model.entities.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public Aluno inserir(Aluno aluno) throws AlunoException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO tb_aluno " + "(dt_inicio, ativo, id_pessoa, id_curso)" + "VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setDate(1, new Date(aluno.getDtInicio().getTime()));
            st.setInt(2, aluno.getAtivo() ? 1 : 0);
            st.setInt(3, aluno.getPessoa().getIdPessoa());
            st.setInt(4, aluno.getCurso().getIdCurso());

            int rowsAffected = st.executeUpdate();

            System.out.println("Linhas alteradas: " + rowsAffected);

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    aluno.setIdAluno(id);
                }
            }
        } catch (SQLException e) {
            throw new AlunoException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return aluno;
    }

    public Aluno buscarPorCpf(Long cpf) throws ProfessorException, CursoException {
        Aluno aluno = null;

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = new StringBuilder()
                    .append("SELECT aluno.* ")
                    .append("FROM tb_aluno AS aluno ")
                    .append("INNER JOIN tb_pessoa AS pessoa ON aluno.id_pessoa = pessoa.id_pessoa ")
                    .append("WHERE pessoa.cpf = ?")
                    .toString();

            st = conn.prepareStatement(query);
            st.setLong(1, cpf);

            rs = st.executeQuery();

            if (rs.next()) {
                aluno = this.vo(rs);
            }
        } catch (SQLException | PessoaException e) {
            throw new ProfessorException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return aluno;
    }

    public List<Aluno> buscaTodos() throws AlunoException, CursoException, PessoaException {
        ResultSet rs = null;
        PreparedStatement st = null;

        List<Aluno> aluno = new ArrayList<>();

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT * FROM tb_aluno ORDER BY id_aluno ";
            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                aluno.add(this.vo(rs));
            }
        } catch (SQLException e) {
            throw new AlunoException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return aluno;
    }

    private Aluno vo(ResultSet rs) throws SQLException, CursoException, PessoaException {
        Aluno aluno = new Aluno();

        aluno.setIdAluno(rs.getInt("id_aluno"));

        Integer id = rs.getInt("id_curso");
        CursoDAO curso = new CursoDAO();
        aluno.setCurso(curso.buscaPorId(id));

        id = rs.getInt("id_pessoa");
        PessoaDAO pessoa = new PessoaDAO();
        aluno.setPessoa(pessoa.buscaPorId(id));

        aluno.setAtivo(rs.getBoolean("ativo"));
        aluno.setDtInicio(rs.getDate("dt_inicio"));

        return aluno;
    }

    public Aluno buscaPorId(Integer id) throws AlunoException, CursoException, PessoaException {
        Aluno aluno = null;
        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT * FROM tb_aluno WHERE id_aluno = ?";

            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                aluno = this.vo(rs);
            }
        } catch (SQLException e) {
            throw new AlunoException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return aluno;
    }

    public Aluno alterar(Aluno aluno) throws AlunoException {
        PreparedStatement st = null;

        try {

            Connection conn = DatabaseFactory.getConnection();

            String query = "UPDATE tb_aluno SET dt_inicio = ?, ativo = ?, id_curso = ?, id_pessoa = ? WHERE id_aluno = ?";

            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, new Date(aluno.getDtInicio().getTime()));
            st.setInt(2, aluno.getAtivo() ? 1 : 0);
            st.setInt(3, aluno.getCurso().getIdCurso());
            st.setInt(4, aluno.getPessoa().getIdPessoa());
            st.setInt(5, aluno.getIdAluno());

            int rowsAffected = st.executeUpdate();

            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new AlunoException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return aluno;
    }

    public void excluir(Integer id) throws AlunoException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "DELETE FROM tb_aluno WHERE id_aluno = ?";

            st = conn.prepareStatement(query);
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new AlunoException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }
    }
}
