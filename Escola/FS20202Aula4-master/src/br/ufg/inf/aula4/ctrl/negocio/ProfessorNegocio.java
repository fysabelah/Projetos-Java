package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.ProfessorExection;
import br.ufg.inf.aula4.model.dao.ProfessorDAO;
import br.ufg.inf.aula4.model.entities.Professor;

public class ProfessorNegocio {


		ProfessorDAO dao = new ProfessorDAO();
		public Professor inserir(Professor professor) throws ProfessorExection {
			this.validarProfessor(professor);
			dao.inserir(professor);
			return professor;
		}
		
		public List<Professor> buscaTodos() throws ProfessorExection{
			return dao.buscaTodos();	
		}
		
		public Professor buscaPorId(Integer id) throws ProfessorExection {
			
			return dao.buscaPorId(id);
		}
		
		public Professor alterar(Professor professor) throws ProfessorExection {		
			this.validarProfessor(professor);
			return dao.alterar(professor);
		}
		
		public void excluir(Integer id) throws ProfessorExection {
			dao.excluir(id);
		}
		
		private void validarProfessor(Professor professor) throws ProfessorExection {
			if (professor.getPessoa() == null) {
				throw new ProfessorExection("É necessário vicular uma pessoa ao professor.");
			}
		}
}
