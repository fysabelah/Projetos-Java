package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.model.entities.Disciplina;
import br.ufg.inf.aula4.model.entities.Oferta;
import br.ufg.inf.aula4.model.entities.Professor;
import br.ufg.inf.aula4.model.enums.Dia;

public class OfertaDAO {

	// CREATE
	public Oferta inserir(Oferta oferta) throws OfertaExection {

		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
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

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					oferta.setIdOferta(id);
				}
			}
		} catch (SQLException e) {
			throw new OfertaExection(e.getMessage());
		}

		return oferta;
	}

	// READ
	public List<Oferta> buscaTodos() throws OfertaExection {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Oferta> ofertas = new ArrayList<Oferta>();
		try {
			Connection conn = DB.getConnection();

			String query = "SELECT id_oferta, id_professor, id_disciplina, dt_inicio, dt_fim, dia, hora FROM tb_oferta ORDER BY id_oferta ";
			st = conn.prepareStatement(query);

			rs = st.executeQuery();

			while (rs.next()) {
				ofertas.add(this.vo(rs));
			}

		} catch (SQLException e) {
			throw new OfertaExection(e.getMessage());

		}

		return ofertas;
	}

	private Oferta vo(ResultSet rs) throws SQLException {
		Oferta oferta = new Oferta();
		oferta.setIdOferta(rs.getInt("id_oferta"));
		oferta.setProfessor(new Professor(rs.getInt("id_professor"), null, null));
		oferta.setDisciplina(new Disciplina(rs.getInt("id_disciplina"), null, null));
		oferta.setDtInicio(rs.getDate("dt_inicio"));
		oferta.setDtFim(rs.getDate("dt_fim"));
		oferta.setDia(Dia.get(rs.getInt("dia")));
		oferta.setHora(rs.getString("hora"));
		return oferta;
	}

	public Oferta buscaPorId(Integer id) throws OfertaExection {
		Oferta oferta = null;

		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			
			String query = "SELECT id_oferta, id_professor, id_disciplina, dt_inicio, dt_fim, dia, hora FROM tb_oferta WHERE id_oferta = ? ";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				oferta = this.vo(rs);
			}

		} catch (SQLException e) {
			throw new OfertaExection(e.getMessage());

		}

		return oferta;
	}

	// UPDATE

	public Oferta alterar(Oferta oferta) throws OfertaExection {

		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();

			//id_oferta, id_professor, id_disciplina, dt_inicio, dt_fim, dia, hora
			String query = "UPDATE tb_oferta SET id_professor = ?, id_disciplina = ?, dt_inicio = ?, dt_fim = ?, dia= ?, hora = ? WHERE id_oferta = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
			throw new OfertaExection(e.getMessage());
		}

		return oferta;
	}

	// DELETE

	public void excluir(Integer id) throws OfertaExection {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();

			String query = " DELETE FROM tb_oferta WHERE id_oferta = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new OfertaExection(e.getMessage());
		}

	}
}
