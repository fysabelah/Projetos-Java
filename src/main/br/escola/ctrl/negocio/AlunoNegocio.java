package main.br.escola.ctrl.negocio;

import main.br.escola.ctrl.exception.AlunoException;
import main.br.escola.ctrl.exception.CursoException;
import main.br.escola.ctrl.exception.PessoaException;
import main.br.escola.ctrl.exception.ProfessorException;
import main.br.escola.model.dao.AlunoDAO;
import main.br.escola.model.entities.Aluno;

import java.util.List;

public class AlunoNegocio {
    AlunoDAO dao = new AlunoDAO();

    public Aluno inserir(Aluno aluno) throws AlunoException, ProfessorException, CursoException {
        this.validarAluno(aluno);

        Aluno cadastrado = dao.buscarPorCpf(aluno.getPessoa().getCpf());

        if (cadastrado != null) {
            throw new AlunoException("Já existe um aluno cadastrado com esse CPF!");
        }

        return dao.inserir(aluno);
    }

    public List<Aluno> buscaTodos() throws AlunoException, CursoException, PessoaException {
        return dao.buscaTodos();
    }

    public Aluno buscaPorId(Integer id) throws AlunoException, CursoException, PessoaException {
        return dao.buscaPorId(id);
    }

    public Aluno alterar(Aluno aluno) throws AlunoException {
        this.validarAluno(aluno);

        return dao.alterar(aluno);
    }

    public void excluir(Integer id) throws AlunoException {
        dao.excluir(id);
    }

    private void validarAluno(Aluno aluno) throws AlunoException {
        if (aluno.getPessoa() == null || aluno.getCurso() == null) {
            throw new AlunoException("É necessário a pessoa e/ou aluno.");
        }
    }
}
