package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.DisciplinaExection;
import br.ufg.inf.aula4.model.dao.DisciplinaDAO;
import br.ufg.inf.aula4.model.entities.Disciplina;

public class DisciplinaNegocio {


		DisciplinaDAO dao = new DisciplinaDAO();
	
		public Disciplina inserir(Disciplina disciplina) throws DisciplinaExection {
			this.validarDisciplina(disciplina);
			dao.inserir(disciplina);
			return disciplina;
		}
		
		// READ
		public List<Disciplina> buscaTodos() throws DisciplinaExection{
			return dao.buscaTodos();	
		}
		
		public Disciplina buscaPorId(Integer id) throws DisciplinaExection {
			
			return dao.buscaPorId(id);
		}
		
		
		// UPDATE
		
		public Disciplina alterar(Disciplina disciplina) throws DisciplinaExection {		
			this.validarDisciplina(disciplina);
			return dao.alterar(disciplina);
		}
		
		// DELETE
		
		public void excluir(Integer id) throws DisciplinaExection {
			dao.excluir(id);
		}
		
		private void validarDisciplina(Disciplina disciplina) throws DisciplinaExection {
			if (disciplina.getCargaHoraria() <= 0) {
				throw new DisciplinaExection("Carga horária deve ter maior que 0.");
			}

			if (disciplina.getNmDisciplina() == null || disciplina.getNmDisciplina().length() == 0) {
				throw new DisciplinaExection("Nome da disciplina é obrigatório.");
			}
		}
}
