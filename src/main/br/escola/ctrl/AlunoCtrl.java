package main.br.escola.ctrl;

import main.br.escola.ctrl.exception.AlunoException;
import main.br.escola.ctrl.exception.CursoException;
import main.br.escola.ctrl.exception.PessoaException;
import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.ctrl.negocio.AlunoNegocio;
import main.br.escola.model.entities.Aluno;

import java.util.List;

public class AlunoCtrl {
    AlunoNegocio negocio = new AlunoNegocio();

    public Aluno inserir(Aluno aluno) {
        try {
            aluno = negocio.inserir(aluno);
            System.out.println("Aluno(a) cadastrado(a) com sucesso: " + aluno);
        } catch (AlunoException | ProfessorException | CursoException e) {
            System.out.println("Erro ao tentar cadastrar aluno(a).");
            System.out.println(e.getMessage());
        }

        return aluno;
    }

    public List<Aluno> buscaTodos() {
        List<Aluno> aluno = null;

        try {
            aluno = negocio.buscaTodos();
        } catch (AlunoException | CursoException | PessoaException e) {
            System.out.println("Erro tentar buscar o(a)s aluno(a)s cadastrado(a)s.");
            System.out.println(e.getMessage());
        }

        return aluno;
    }

    public Aluno buscaPorId(Integer id) {
        Aluno aluno = null;

        try {
            aluno = negocio.buscaPorId(id);
        } catch (AlunoException | CursoException | PessoaException e) {
            System.out.println("Erro tentar buscar aluno(a) de ID: " + id + ".");
            System.out.println(e.getMessage());
        }

        return aluno;
    }

    public Aluno alterar(Aluno aluno) {
        try {
            aluno = negocio.alterar(aluno);
            System.out.println("Aluno atualizado com sucesso: " + aluno);
        } catch (AlunoException e) {
            System.out.println("Erro ao tentar atualizar o(a) aluno(a) com ID: " + aluno.getIdAluno() + ".");
            System.out.println(e.getMessage());
        }

        return aluno;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            System.out.println("Aluno(a) excluído(a) com sucesso.");
        } catch (AlunoException e) {
            System.out.println("Erro ao tentar excluir o(a) aluno(a).");
            System.out.println(e.getMessage());
        }
    }
}
