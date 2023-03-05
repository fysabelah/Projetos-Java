package main.br.escola.model.dao;

import main.br.escola.app.DatabaseFactory;
import main.br.escola.ctrl.exception.DisciplinaException;
import main.br.escola.ctrl.exception.OfertaException;
import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.model.entities.Oferta;
import main.br.escola.model.enums.Dia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfertaDAO {

    public Oferta inserir(Oferta oferta) throws OfertaException {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO tb_oferta (id_professor, id_disciplina, dt_inicio, dt_fim, dia, hora) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, oferta.getProfessor().getIdProfessor());
            st.setLong(2, oferta.getDisciplina().getIdDisciplina());
            st.setDate(3, new Date(oferta.getDtInicio().getTime()));
            st.setDate(4, new Date(oferta.getDtFim().getTime()));
            st.setInt(5, oferta.getDia().getId());
            st.setString(6, oferta.getHora());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    oferta.setIdOferta(id);
                }
            }
        } catch (SQLException e) {
            throw new OfertaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return oferta;
    }

    public List<Oferta> buscaTodos() throws OfertaException {
        ResultSet rs = null;
        PreparedStatement st = null;

        List<Oferta> ofertas = new ArrayList<>();

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_oferta, id_professor, id_disciplina, dt_inicio, dt_fim, dia, hora FROM tb_oferta ORDER BY id_oferta ";
            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                ofertas.add(this.vo(rs));
            }

        } catch (SQLException | ProfessorException | DisciplinaException e) {
            throw new OfertaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return ofertas;
    }

    private Oferta vo(ResultSet rs) throws SQLException, ProfessorException, DisciplinaException {
        Oferta oferta = new Oferta();

        oferta.setIdOferta(rs.getInt("id_oferta"));

        ProfessorDAO professorDAO = new ProfessorDAO();
        oferta.setProfessor(professorDAO.buscaPorId(rs.getInt("id_professor")));

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        oferta.setDisciplina(disciplinaDAO.buscaPorId(rs.getInt("id_disciplina")));

        oferta.setDtInicio(rs.getDate("dt_inicio"));
        oferta.setDtFim(rs.getDate("dt_fim"));
        oferta.setDia(Dia.get(rs.getInt("dia")));
        oferta.setHora(rs.getString("hora"));

        return oferta;
    }

    public Oferta buscaPorId(Integer id) throws OfertaException, ProfessorException, DisciplinaException {
        Oferta oferta = null;

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "SELECT id_oferta, id_professor, id_disciplina, dt_inicio, dt_fim, dia, hora FROM tb_oferta WHERE id_oferta = ?";

            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                oferta = this.vo(rs);
            }

        } catch (SQLException e) {
            throw new OfertaException(e.getMessage());
        } finally {
            DatabaseFactory.closeResultSet(rs);
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return oferta;
    }

    public Oferta alterar(Oferta oferta) throws OfertaException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = "UPDATE tb_oferta SET id_professor = ?, id_disciplina = ?, dt_inicio = ?, dt_fim = ?, dia= ?, hora = ? WHERE id_oferta = ?";

            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, oferta.getProfessor().getIdProfessor());
            st.setLong(2, oferta.getDisciplina().getIdDisciplina());
            st.setDate(3, new Date(oferta.getDtInicio().getTime()));
            st.setDate(4, new Date(oferta.getDtFim().getTime()));
            st.setInt(5, oferta.getDia().getId());
            st.setString(6, oferta.getHora());
            st.setInt(7, oferta.getIdOferta());

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);

        } catch (SQLException e) {
            throw new OfertaException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }

        return oferta;
    }

    public void excluir(Integer id) throws OfertaException {
        PreparedStatement st = null;

        try {
            Connection conn = DatabaseFactory.getConnection();

            String query = " DELETE FROM tb_oferta WHERE id_oferta = ?";

            st = conn.prepareStatement(query);
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas alteradas: " + rowsAffected);
        } catch (SQLException e) {
            throw new OfertaException(e.getMessage());
        } finally {
            DatabaseFactory.closeStatment(st);
            DatabaseFactory.closeConnectoin();
        }
    }
}
