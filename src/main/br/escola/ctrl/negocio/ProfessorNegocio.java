package main.br.escola.ctrl.negocio;

import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.model.dao.ProfessorDAO;
import main.br.escola.model.entities.Professor;

import java.util.List;

public class ProfessorNegocio {

    ProfessorDAO dao = new ProfessorDAO();

    public Professor inserir(Professor professor) throws ProfessorException {
        this.validarProfessor(professor);

        Professor cadastrado = dao.buscarPorCpf(professor.getPessoa().getCpf());
        if (cadastrado != null) {
            throw new ProfessorException("Já existe um professor cadastrado com este CPF");
        }

        return dao.inserir(professor);
    }

    public List<Professor> buscaTodos() throws ProfessorException {
        return dao.buscaTodos();
    }

    public Professor buscaPorId(Integer id) throws ProfessorException {
        return dao.buscaPorId(id);
    }

    public Professor alterar(Professor professor) throws ProfessorException {
        this.validarProfessor(professor);

        Professor cadastrado = dao.buscarPorCpf(professor.getPessoa().getCpf());
        if (cadastrado != null && cadastrado.getIdProfessor().compareTo(professor.getIdProfessor()) != 0) {
            throw new ProfessorException("Já existe um professor cadastrado com este CPF");
        }

        return dao.alterar(professor);
    }

    public void excluir(Integer id) throws ProfessorException {
        dao.excluir(id);
    }

    private void validarProfessor(Professor professor) throws ProfessorException {
        if (professor.getPessoa() == null) {
            throw new ProfessorException("É necessário vicular uma pessoa ao professor.");
        }
    }
}
