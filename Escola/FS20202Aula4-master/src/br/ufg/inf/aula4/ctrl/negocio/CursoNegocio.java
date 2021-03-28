package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.model.dao.CursoDAO;
import br.ufg.inf.aula4.model.entities.Curso;

public class CursoNegocio {
	CursoDAO dao = new CursoDAO();
	
	public Curso inserir (Curso curso) throws CursoException{
		this.validarCurso(curso);
		dao.inserir(curso);
		return(curso);
	}
	
	public List<Curso> buscaTodos() throws CursoException{
		return(dao.buscaTodos());
	}
	
	public Curso buscaPorId(Integer id) throws CursoException{
		return(dao.buscaPorId(id));
	}
	
	public Curso alterar(Curso curso) throws CursoException{
		this.validarCurso(curso);
		return(dao.alterar(curso));
	}
	
	public void excluir(Integer id) throws CursoException{
		dao.excluir(id);
	}
	
	private void validarCurso(Curso curso) throws CursoException{
		if(curso.getNmCurso() == null || curso.getNmCurso().length() == 0) {
			throw new CursoException("É necessário cadastrar o curso.");
		}
	}
	
}
