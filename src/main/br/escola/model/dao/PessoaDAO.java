package main.br.escola.model.dao;

import main.br.escola.app.DatabaseFactory;
import main.br.escola.ctrl.exception.PessoaException;
import main.br.escola.model.entities.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public Pessoa inserir(Pessoa pessoa) throws PessoaException {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection conn = DatabaseFactory.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO tb_pessoa " + "(nm_pessoa, cpf, dt_nascimento)" + "VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, pessoa.getNmPessoa());
            st.setLong(2, pessoa.getCpf());
            st.setDate(3, new Date(pessoa.getDtNascimento().getTime()));

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    pessoa.setIdPessoa(id);
                }
            }
        } catch (SQLException e) {
            throw new PessoaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return pessoa;
    }

    public List<Pessoa> buscaTodos() throws PessoaException {
        ResultSet rs = null;
        PreparedStatement st = null;

        List<Pessoa> pessoas = new ArrayList<>();

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_pessoa, nm_pessoa, cpf, dt_nascimento FROM tb_pessoa ORDER BY nm_pessoa ";
            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                pessoas.add(this.vo(rs));
            }

        } catch (SQLException e) {
            throw new PessoaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return pessoas;
    }

    private Pessoa vo(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();

        pessoa.setIdPessoa(rs.getInt("id_pessoa"));
        pessoa.setNmPessoa(rs.getString("nm_pessoa"));
        pessoa.setCpf(rs.getLong("cpf"));
        pessoa.setDtNascimento(rs.getDate("dt_nascimento"));

        return pessoa;
    }

    public List<Pessoa> buscarPorCpf(Long cpf) throws PessoaException {
        List<Pessoa> pessoas = new ArrayList<>();

        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            Connection connection = DatabaseFactory.getConnection();
            String query = "SELECT id_pessoa, nm_pessoa, cpf, dt_nascimento FROM tb_pessoa WHERE cpf = ?";

            statement = connection.prepareStatement(query);
            statement.setLong(1, cpf);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                pessoas.add(this.vo(resultSet));
            }

        } catch (SQLException e) {
            throw new PessoaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(resultSet);
            DatabaseFactory.closeStatment(statement);
            DatabaseFactory.closeConnectoin();
        }

        return pessoas;
    }

    public Pessoa buscaPorId(Integer id) throws PessoaException {
        Pessoa pessoa = null;

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_pessoa, nm_pessoa, cpf, dt_nascimento FROM tb_pessoa WHERE id_pessoa = ? ";
            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                pessoa = this.vo(rs);
            }
        } catch (SQLException e) {
            throw new PessoaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return pessoa;
    }

    public Pessoa alterar(Pessoa pessoa) throws PessoaException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "UPDATE tb_pessoa SET nm_pessoa = ?, dt_nascimento = ? WHERE id_pessoa = ?";
            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, pessoa.getNmPessoa());
            st.setDate(2, new Date(pessoa.getDtNascimento().getTime()));
            st.setInt(3, pessoa.getIdPessoa());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

        } catch (SQLException e) {
            throw new PessoaException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return pessoa;
    }

    public void excluir(Integer id) throws PessoaException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = " DELETE FROM tb_pessoa WHERE id_pessoa = ?";
            st = conn.prepareStatement(query);

            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();

            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new PessoaException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }
    }
}
