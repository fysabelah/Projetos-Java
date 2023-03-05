package main.br.escola.ctrl;

import main.br.escola.ctrl.exception.MatriculaException;
import main.br.escola.ctrl.negocio.MatriculaNegocio;
import main.br.escola.model.entities.Matricula;

import java.util.List;

public class MatriculaCtrl {
    MatriculaNegocio negocio = new MatriculaNegocio();

    public Matricula inserir(Matricula matricula) {
        try {
            matricula = negocio.inserir(matricula);
            System.out.println("Matrícula cadastrada com sucesso: " + matricula);
        } catch (MatriculaException e) {
            System.out.println("Erro ao tentar cadastrar matrícula.");
            System.out.println(e.getMessage());
        }

        return matricula;
    }

    public List<Matricula> buscaTodos() {
        List<Matricula> matricula = null;

        try {
            matricula = negocio.buscaTodos();
        } catch (Exception e) {
            System.out.println("Erro tentar buscar matrículas cadastradas.");
            System.out.println(e.getMessage());
        }

        return matricula;
    }

    public Matricula buscaPorId(Integer id) {
        Matricula matricula = null;

        try {
            matricula = negocio.buscaPorId(id);
        } catch (Exception e) {
            System.out.println("Erro tentar buscar matrícula de ID: " + id + ".");
            System.out.println(e.getMessage());
        }

        return matricula;
    }

    public Matricula alterar(Matricula matricula) {
        try {
            matricula = negocio.alterar(matricula);
            System.out.println("Matrícula alterada com sucesso: " + matricula);
        } catch (MatriculaException e) {
            System.out.println("Erro ao tentar alterar matrícula com ID: " + matricula.getIdMatricula() + ".");
            System.out.println(e.getMessage());
        }

        return matricula;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            System.out.println("Matrícula excluída com sucesso.");
        } catch (MatriculaException e) {
            System.out.println("Erro ao tentar excluir a matrícula.");
            System.out.println(e.getMessage());
        }
    }
}
