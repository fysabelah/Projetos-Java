package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.DisciplinaExection;
import br.ufg.inf.aula4.model.entities.Disciplina;


public class DisciplinaDAO {

	// CREATE
	public Disciplina inserir(Disciplina disciplina) throws DisciplinaExection {
		
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO tb_disciplina " + "(nm_disciplina, carga_horaria)" + "VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, disciplina.getNmDisciplina());
			st.setInt(2, disciplina.getCargaHoraria());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					disciplina.setIdDisciplina(id);
				}
			}
		} catch (SQLException e) {
			throw new DisciplinaExection(e.getMessage());
		}

		return disciplina;
	}

	// READ
	public List<Disciplina> buscaTodos() throws DisciplinaExection {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		try {
			Connection conn = DB.getConnection();
			st = conn.prepareStatement("SELECT id_disciplina, nm_disciplina, carga_horaria" + " FROM tb_disciplina "
					+ " ORDER BY nm_disciplina ");

			rs = st.executeQuery();

			while (rs.next()) {
				Disciplina disciplina = new Disciplina();

				disciplina.setIdDisciplina(rs.getInt("id_disciplina"));
				disciplina.setNmDisciplina(rs.getString("nm_disciplina"));
				disciplina.setCargaHoraria(rs.getInt("carga_horaria"));

				disciplinas.add(disciplina);
			}

		} catch (SQLException e) {
			throw new DisciplinaExection(e.getMessage());

		}

		return disciplinas;
	}

	public Disciplina buscaPorId(Integer id) throws DisciplinaExection {
		Disciplina disciplina = new Disciplina();

		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = conn.prepareStatement("SELECT id_disciplina, nm_disciplina, carga_horaria" + " FROM tb_disciplina "
					+ " WHERE id_disciplina = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				disciplina.setIdDisciplina(rs.getInt("id_disciplina"));
				disciplina.setNmDisciplina(rs.getString("nm_disciplina"));
				disciplina.setCargaHoraria(rs.getInt("carga_horaria"));


			}

		} catch (SQLException e) {
			throw new DisciplinaExection(e.getMessage());

		}


		return disciplina;
	}

	// UPDATE

	public Disciplina alterar(Disciplina disciplina) throws DisciplinaExection {

		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			
			String query = "UPDATE tb_disciplina "
					+ " SET "
					+ " nm_disciplina = ? , "
					+ " carga_horaria = ? "
					+ " WHERE id_disciplina = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, disciplina.getNmDisciplina());
			st.setInt(2, disciplina.getCargaHoraria());
			st.setInt(3, disciplina.getIdDisciplina());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new DisciplinaExection(e.getMessage());
		}
		
		return disciplina;
	}

	// DELETE

	public void excluir(Integer id) throws DisciplinaExection {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			
			String query = " DELETE FROM tb_disciplina WHERE id_disciplina = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new DisciplinaExection(e.getMessage());
		}
		
	}
}
