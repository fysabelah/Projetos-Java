package main.br.escola.ctrl;

import main.br.escola.ctrl.exception.DisciplinaException;
import main.br.escola.ctrl.negocio.DisciplinaNegocio;
import main.br.escola.model.entities.Disciplina;

import java.util.List;

public class DisciplinaCtrl {

    DisciplinaNegocio negocio = new DisciplinaNegocio();

    public Disciplina inserir(Disciplina disciplina) {
        try {
            disciplina = negocio.inserir(disciplina);
            System.out.println("Disciplina cadastrada com sucesso: " + disciplina);
        } catch (DisciplinaException e) {
            System.out.println("Erro ao tentar cadastrar disciplina.");
            System.out.println(e.getMessage());
        }

        return disciplina;
    }

    public List<Disciplina> buscaTodos() {
        List<Disciplina> disciplinas = null;

        try {
            disciplinas = negocio.buscaTodos();
        } catch (DisciplinaException e) {
            System.out.println("Erro tentar buscar as disciplinas cadastradas.");
            System.out.println(e.getMessage());
        }

        return disciplinas;
    }

    public Disciplina buscaPorId(Integer id) {
        Disciplina disciplina = null;

        try {
            disciplina = negocio.buscaPorId(id);
        } catch (DisciplinaException e) {
            System.out.println("Erro tentar buscar disciplina do ID: " + id + ".");
            System.out.println(e.getMessage());
        }

        return disciplina;
    }

    public Disciplina alterar(Disciplina disciplina) {
        try {
            disciplina = negocio.alterar(disciplina);
            System.out.println("Disciplina alterada com sucesso: " + disciplina);
        } catch (DisciplinaException e) {
            System.out.println("Erro ao tentar alterar disciplina com ID: " + disciplina.getIdDisciplina() + ".");
            System.out.println(e.getMessage());
        }

        return disciplina;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            System.out.println("Disciplina excluída com sucesso.");
        } catch (DisciplinaException e) {
            System.out.println("Erro ao tentar excluir a disciplina");
            System.out.println(e.getMessage());
        }
    }
}
