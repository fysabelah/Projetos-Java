package main.br.escola.ctrl.negocio;

import main.br.escola.ctrl.exception.DisciplinaException;
import main.br.escola.ctrl.exception.OfertaException;
import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.model.dao.OfertaDAO;
import main.br.escola.model.entities.Oferta;

import java.util.ArrayList;
import java.util.List;

public class OfertaNegocio {

    OfertaDAO dao = new OfertaDAO();

    ProfessorNegocio professorNegocio = new ProfessorNegocio();

    DisciplinaNegocio disciplinaNegocio = new DisciplinaNegocio();

    public Oferta inserir(Oferta oferta) throws OfertaException {
        this.validarOferta(oferta);

        return dao.inserir(oferta);
    }

    public List<Oferta> buscaTodos() throws DisciplinaException, ProfessorException, OfertaException {
        List<Oferta> ofertas = new ArrayList<>();

        for (Oferta o : dao.buscaTodos()) {
            o.setProfessor(professorNegocio.buscaPorId(o.getProfessor().getIdProfessor()));
            o.setDisciplina(disciplinaNegocio.buscaPorId(o.getDisciplina().getIdDisciplina()));
        }

        return ofertas;
    }

    public Oferta buscaPorId(Integer id) {
        Oferta oferta = null;

        try {
            oferta = dao.buscaPorId(id);
        } catch (OfertaException | ProfessorException | DisciplinaException e) {
            System.out.println("Erro ao buscar oferta de ID: " + id);
            System.out.println(e.getMessage());
        }

        return oferta;
    }

    public Oferta alterar(Oferta oferta) throws OfertaException {
        this.validarOferta(oferta);

        return dao.alterar(oferta);
    }

    public void excluir(Integer id) throws OfertaException {
        dao.excluir(id);
    }

    private void validarOferta(Oferta oferta) throws OfertaException {
        if (oferta.getDisciplina() == null || oferta.getProfessor() == null) {
            throw new OfertaException("Dado(s) pendente(s) para disciplina e/ou professor(a).");
        }
    }
}
