package main.br.escola.ctrl;

import main.br.escola.ctrl.exception.DisciplinaException;
import main.br.escola.ctrl.exception.OfertaException;
import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.ctrl.negocio.OfertaNegocio;
import main.br.escola.model.entities.Oferta;

import java.util.List;

public class OfertaCtrl {

    OfertaNegocio negocio = new OfertaNegocio();

    public Oferta inserir(Oferta oferta) {
        try {
            oferta = negocio.inserir(oferta);
            System.out.println("Oferta cadastrada com sucesso: " + oferta);
        } catch (OfertaException e) {
            System.out.println("Erro ao tentar cadastrar oferta.");
            System.out.println(e.getMessage());
        }

        return oferta;
    }

    public List<Oferta> buscaTodos() {
        List<Oferta> ofertas = null;

        try {
            ofertas = negocio.buscaTodos();
        } catch (OfertaException | DisciplinaException | ProfessorException e) {
            System.out.println("Erro tentar buscar as ofertas cadastrados.");
            System.out.println(e.getMessage());
        }

        return ofertas;
    }

    public Oferta buscaPorId(Integer id) {
        return negocio.buscaPorId(id);
    }

    public Oferta alterar(Oferta oferta) {
        try {
            oferta = negocio.alterar(oferta);
            System.out.println("Oferta alterado com sucesso: " + oferta);
        } catch (OfertaException e) {
            System.out.println("Erro ao tentar alterar oferta com ID: " + oferta.getIdOferta() + ".");
            System.out.println(e.getMessage());
        }

        return oferta;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            System.out.println("Oferta excluído com sucesso.");
        } catch (OfertaException e) {
            System.out.println("Erro ao tentar excluir o oferta");
            System.out.println(e.getMessage());
        }
    }
}
