package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.ProfessorExection;
import br.ufg.inf.aula4.model.entities.Pessoa;
import br.ufg.inf.aula4.model.entities.Professor;
import br.ufg.inf.aula4.model.enums.Escolaridade;

public class ProfessorDAO {

	public Professor inserir(Professor professor) throws ProfessorExection {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO tb_professor " + "(escolaridade, id_pessoa)" + "VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, professor.getEscolaridade().getId());
			st.setInt(2, professor.getPessoa().getIdPessoa());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					professor.setIdProfessor(id);
				}
			}
		} catch (SQLException e) {
			throw new ProfessorExection(e.getMessage());
		}
		return professor;
	}

	public List<Professor> buscaTodos() throws ProfessorExection {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Professor> professors = new ArrayList<Professor>();
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_professor, escolaridade, id_pessoa FROM tb_professor ORDER BY id_professor ";
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				professors.add(this.vo(rs));
			}
		} catch (SQLException e) {
			throw new ProfessorExection(e.getMessage());
		}
		return professors;
	}

	private Professor vo(ResultSet rs) throws SQLException {
		Professor professor = new Professor();
		professor.setIdProfessor(rs.getInt("id_professor"));
		professor.setEscolaridade(Escolaridade.get(rs.getInt("escolaridade")));
		professor.setPessoa(new Pessoa(rs.getInt("id_pessoa"), null, null, null));
		return professor;
	}

	public Professor buscaPorId(Integer id) throws ProfessorExection {
		Professor professor = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_professor, escolaridade, id_pessoa FROM tb_professor WHERE id_professor = ? ";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				professor = this.vo(rs);
			}
		} catch (SQLException e) {
			throw new ProfessorExection(e.getMessage());
		}
		return professor;
	}

	public Professor alterar(Professor professor) throws ProfessorExection {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = "UPDATE tb_professor SET escolaridade = ?, id_pessoa = ? WHERE id_professor = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, professor.getEscolaridade().getId());
			st.setInt(2, professor.getPessoa().getIdPessoa());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
		} catch (SQLException e) {
			throw new ProfessorExection(e.getMessage());
		}
		return professor;
	}

	public void excluir(Integer id) throws ProfessorExection {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = " DELETE FROM tb_professor WHERE id_professor = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new ProfessorExection(e.getMessage());
		}
	}
}
