package main.br.escola.ctrl.negocio;

import main.br.escola.ctrl.exception.*;
import main.br.escola.model.dao.MatriculaDAO;
import main.br.escola.model.entities.Matricula;

import java.util.List;

public class MatriculaNegocio {
    MatriculaDAO dao = new MatriculaDAO();

    public Matricula inserir(Matricula matricula) throws MatriculaException {
        this.validarMatricula(matricula);


        return dao.inserir(matricula);
    }

    public List<Matricula> buscaTodos() throws AlunoException, MatriculaException, PessoaException, OfertaException, CursoException, ProfessorException, DisciplinaException {
        return dao.buscaTodos();
    }

    public Matricula buscaPorId(Integer id) throws AlunoException, MatriculaException, PessoaException, OfertaException, CursoException, ProfessorException, DisciplinaException {
        return dao.buscaPorId(id);
    }

    public Matricula alterar(Matricula matricula) throws MatriculaException {
        this.validarMatricula(matricula);

        return dao.alterar(matricula);
    }

    public void excluir(Integer id) throws MatriculaException {
        dao.excluir(id);
    }

    private void validarMatricula(Matricula matricula) throws MatriculaException {
        if (matricula.getAluno() == null || matricula.getOferta() == null) {
            throw new MatriculaException("Cadastro de Aluno e/ou Matrícula pendente(s).");
        }
    }
}
