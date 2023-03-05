package main.br.escola.ctrl;

import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.ctrl.negocio.ProfessorNegocio;
import main.br.escola.model.entities.Professor;

import java.util.List;

public class ProfessorCtrl {

    ProfessorNegocio negocio = new ProfessorNegocio();

    public Professor inserir(Professor professor) {
        try {
            professor = negocio.inserir(professor);
            System.out.println("Professor cadastrado com sucesso: " + professor);
        } catch (ProfessorException e) {
            System.out.println("Erro ao tentar cadastrar professor.");
            System.out.println(e.getMessage());
        }

        return professor;
    }

    public List<Professor> buscaTodos() {
        List<Professor> professors = null;

        try {
            professors = negocio.buscaTodos();
        } catch (ProfessorException e) {
            System.out.println("Erro tentar buscar as professores cadastrados.");
            System.out.println(e.getMessage());
        }

        return professors;
    }

    public Professor buscaPorId(Integer id) {
        Professor professor = null;

        try {
            professor = negocio.buscaPorId(id);
        } catch (ProfessorException e) {
            System.out.println("Erro tentar buscar professor do ID: " + id + ".");
            System.out.println(e.getMessage());
        }

        return professor;
    }

    public Professor alterar(Professor professor) {
        try {
            professor = negocio.alterar(professor);
            System.out.println("Professor alterado com sucesso: " + professor);
        } catch (ProfessorException e) {
            System.out.println("Erro ao tentar alterar professor com ID: " + professor.getIdProfessor() + ".");
            System.out.println(e.getMessage());
        }

        return professor;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            System.out.println("Professor excluído com sucesso.");
        } catch (ProfessorException e) {
            System.out.println("Erro ao tentar excluir o professor");
            System.out.println(e.getMessage());
        }
    }
}
