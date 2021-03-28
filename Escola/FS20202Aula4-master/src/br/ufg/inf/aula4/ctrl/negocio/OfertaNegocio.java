package br.ufg.inf.aula4.ctrl.negocio;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.DisciplinaExection;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.ctrl.exception.ProfessorExection;
import br.ufg.inf.aula4.model.dao.OfertaDAO;
import br.ufg.inf.aula4.model.entities.Oferta;

public class OfertaNegocio {


		OfertaDAO dao = new OfertaDAO();
		ProfessorNegocio professorNegocio = new ProfessorNegocio();
		DisciplinaNegocio disciplinaNegocio = new DisciplinaNegocio();
	
		public Oferta inserir(Oferta oferta) throws OfertaExection {
			this.validarOferta(oferta);
			dao.inserir(oferta);
			return oferta;
		}
		
		// READ
		public List<Oferta> buscaTodos() throws OfertaExection, ProfessorExection, DisciplinaExection{
			List<Oferta> ofertas =  new ArrayList<Oferta>();
			for(Oferta o : dao.buscaTodos()) {
				o.setProfessor(professorNegocio.buscaPorId(o.getProfessor().getIdProfessor()));
				o.setDisciplina(disciplinaNegocio.buscaPorId(o.getDisciplina().getIdDisciplina()));
			}
			return ofertas;	
		}
		
		public Oferta buscaPorId(Integer id) throws OfertaExection {
			Oferta oferta = null;
			
			try {
				oferta = dao.buscaPorId(id);
			}catch(OfertaExection e){
				System.out.println("Erro ao buscar oferta de ID: " + id);
				System.out.println(e.getMessage());
			}
			
			return oferta;
		}
		
		
		// UPDATE
		
		public Oferta alterar(Oferta oferta) throws OfertaExection {		
			this.validarOferta(oferta);
			return dao.alterar(oferta);
		}
		
		// DELETE
		
		public void excluir(Integer id) throws OfertaExection {
			dao.excluir(id);
		}
		
		private void validarOferta(Oferta oferta) throws OfertaExection {
			if(oferta.getDisciplina() == null || oferta.getProfessor() == null) {
				throw new OfertaExection("Dado(s) pendente(s) para disciplina e/ou professor(a).");
			}

		}
}
