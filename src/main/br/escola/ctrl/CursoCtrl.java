package main.br.escola.ctrl;

import main.br.escola.ctrl.exception.CursoException;
import main.br.escola.ctrl.negocio.CursoNegocio;
import main.br.escola.model.entities.Curso;

import java.util.List;

public class CursoCtrl {
    CursoNegocio negocio = new CursoNegocio();

    public Curso inserir(Curso curso) {
        try {
            curso = negocio.inserir(curso);
            System.out.println("Curso cadastrado com sucesso: " + curso);
        } catch (CursoException e) {
            System.out.println("Erro ao tentar cadastrar curso.");
            System.out.println(e.getMessage());
        }

        return curso;
    }

    public List<Curso> buscaTodos() {
        List<Curso> curso = null;

        try {
            curso = negocio.buscaTodos();
        } catch (CursoException e) {
            System.out.println("Erro tentar buscar os cursos cadastrados.");
            System.out.println(e.getMessage());
        }

        return curso;
    }

    public Curso buscaPorId(Integer id) {
        Curso curso = null;

        try {
            curso = negocio.buscaPorId(id);
        } catch (CursoException e) {
            System.out.println("Erro tentar buscar curso do ID: " + id + ".");
            System.out.println(e.getMessage());
        }

        return curso;
    }

    public Curso alterar(Curso curso) {
        try {
            curso = negocio.alterar(curso);
            System.out.println("Curso alterada com sucesso: " + curso);
        } catch (CursoException e) {
            System.out.println("Erro ao tentar alterar curso com ID: " + curso.getIdCurso() + ".");
            System.out.println(e.getMessage());
        }

        return curso;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            System.out.println("Curso excluída com sucesso.");
        } catch (CursoException e) {
            System.out.println("Erro ao tentar excluir o curso");
            System.out.println(e.getMessage());
        }
    }
}
