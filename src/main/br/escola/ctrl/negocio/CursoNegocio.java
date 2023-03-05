package main.br.escola.ctrl.negocio;

import main.br.escola.ctrl.exception.CursoException;
import main.br.escola.model.dao.CursoDAO;
import main.br.escola.model.entities.Curso;

import java.util.List;

public class CursoNegocio {
    CursoDAO dao = new CursoDAO();

    public Curso inserir(Curso curso) throws CursoException {
        this.validarCurso(curso);

        return dao.inserir(curso);
    }

    public List<Curso> buscaTodos() throws CursoException {
        return dao.buscaTodos();
    }

    public Curso buscaPorId(Integer id) throws CursoException {
        return dao.buscaPorId(id);
    }

    public Curso alterar(Curso curso) throws CursoException {
        this.validarCurso(curso);

        return dao.alterar(curso);
    }

    public void excluir(Integer id) throws CursoException {
        dao.excluir(id);
    }

    private void validarCurso(Curso curso) throws CursoException {
        if (curso.getNmCurso() == null || curso.getNmCurso().trim().isEmpty()) {
            throw new CursoException("É necessário cadastrar o curso.");
        }

        List<Curso> cursos = dao.buscarPorNome(curso.getNmCurso());
        if (!cursos.isEmpty()) {
            throw new CursoException("Curso já cadastrado");
        }
    }

}
