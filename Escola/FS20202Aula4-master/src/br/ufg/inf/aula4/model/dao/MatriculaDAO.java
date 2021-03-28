package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.ctrl.exception.PessoaExection;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaDAO {
	//Create
	public Matricula inserir(Matricula matricula) throws MatriculaException{
		PreparedStatement st = null;
		
		try {
			Connection conn = DB.getConnection();
			
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO tb_matricula " + "(id_aluno, id_oferta)" + "VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, matricula.getAluno().getIdAluno());
			st.setInt(2, matricula.getOferta().getIdOferta());
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Linhas alteradas: " + rowsAffected);
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					matricula.setIdMatricula(id);
				}
				
			}
		}catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		
		return(matricula);
	}
	
	//Read
	public List<Matricula> buscaTodos() throws  MatriculaException, AlunoException, CursoException, PessoaExection, OfertaExection{
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Matricula> matricula = new ArrayList<Matricula>();
		
		try {
			Connection conn = DB.getConnection();
			
			String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula ORDER BY id_matricula ";
			st = conn.prepareStatement(query);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				matricula.add(this.vo(rs));
			}
		}catch(SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		
		return(matricula);
	}
	
	private Matricula vo(ResultSet rs) throws SQLException, AlunoException, CursoException, PessoaExection, OfertaExection{
		Matricula matricula = new Matricula();
		AlunoDAO aluno = new AlunoDAO();
		OfertaDAO oferta = new OfertaDAO();
		
		matricula.setAluno(aluno.buscaPorId(rs.getInt("id_aluno")));
		matricula.setIdMatricula(rs.getInt("id_matricula"));
		matricula.setOferta(oferta.buscaPorId(rs.getInt("id_oferta")));
		
		return(matricula);
	}
	
	public Matricula buscaPorId(Integer id) throws MatriculaException, AlunoException, CursoException, PessoaExection, OfertaExection{
		Matricula matricula = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		
		try {
			Connection conn = DB.getConnection();
			
			String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula WHERE id_matricula = ? ";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				matricula = this.vo(rs);
			}
		}catch(SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		
		return(matricula);
	}
	
	//Update
	public Matricula alterar(Matricula matricula) throws MatriculaException{
		PreparedStatement st = null;
		
		try {
			
			Connection conn = DB.getConnection();
			
			String query = "UPDATE tb_matricula SET id_aluno = ?, id_oferta = ? WHERE id_matricula = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, matricula.getAluno().getIdAluno());
			st.setInt(2, matricula.getOferta().getIdOferta());
			st.setInt(3, matricula.getIdMatricula());
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Linhas alteradas: " + rowsAffected);
		}catch(SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		
		return(matricula);
	}
	
	//Delete
	public void excluir(Integer id) throws MatriculaException{
		PreparedStatement st = null;
		
		try {
			Connection conn = DB.getConnection();
			
			String query = " DELETE FROM tb_matricula WHERE id_matricula = ? ;  ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Linhas alteradas: " + rowsAffected);
		}catch(SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
	}
}
