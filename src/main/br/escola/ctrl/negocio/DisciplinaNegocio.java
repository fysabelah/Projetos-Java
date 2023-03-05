package main.br.escola.ctrl.negocio;

import main.br.escola.ctrl.exception.DisciplinaException;
import main.br.escola.model.dao.DisciplinaDAO;
import main.br.escola.model.entities.Disciplina;

import java.util.List;

public class DisciplinaNegocio {

    DisciplinaDAO dao = new DisciplinaDAO();

    public Disciplina inserir(Disciplina disciplina) throws DisciplinaException {
        this.validarDisciplina(disciplina);

        Disciplina cadastrada = dao.buscarPorNome(disciplina.getNmDisciplina());

        if (cadastrada != null) {
            throw new DisciplinaException("Disciplina já cadastrada!");
        }

        return dao.inserir(disciplina);
    }

    public List<Disciplina> buscaTodos() throws DisciplinaException {
        return dao.buscaTodos();
    }

    public Disciplina buscaPorId(Integer id) throws DisciplinaException {
        return dao.buscaPorId(id);
    }


    public Disciplina alterar(Disciplina disciplina) throws DisciplinaException {
        this.validarDisciplina(disciplina);

        Disciplina cadastrada = dao.buscarPorNome(disciplina.getNmDisciplina());
        if (cadastrada != null && !disciplina.getIdDisciplina().equals(cadastrada.getIdDisciplina())) {
            throw new DisciplinaException("Existe outra disciplina cadastrada com este nome!");
        }

        return dao.alterar(disciplina);
    }

    public void excluir(Integer id) throws DisciplinaException {
        dao.excluir(id);
    }

    private void validarDisciplina(Disciplina disciplina) throws DisciplinaException {
        if (disciplina.getCargaHoraria() <= 0) {
            throw new DisciplinaException("Carga horária deve ter maior que 0.");
        }

        if (disciplina.getNmDisciplina() == null || disciplina.getNmDisciplina().trim().isEmpty()) {
            throw new DisciplinaException("Nome da disciplina é obrigatório.");
        }
    }
}
