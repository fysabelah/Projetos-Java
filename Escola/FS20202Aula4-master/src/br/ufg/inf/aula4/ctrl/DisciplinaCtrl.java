package br.ufg.inf.aula4.ctrl;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.DisciplinaExection;
import br.ufg.inf.aula4.ctrl.negocio.DisciplinaNegocio;
import br.ufg.inf.aula4.model.entities.Disciplina;

public class DisciplinaCtrl {

	DisciplinaNegocio negocio = new DisciplinaNegocio();

	public Disciplina inserir(Disciplina disciplina) {
		try {
			disciplina = negocio.inserir(disciplina);
			System.out.println("Disciplina cadastrada com sucesso: " + disciplina);
		} catch (DisciplinaExection e) {
			System.out.println("Erro ao tentar cadastrar disciplina.");
			System.out.println(e.getMessage());
		}
		return disciplina;
	}

	public List<Disciplina> buscaTodos() {
		List<Disciplina> disciplinas = null;
		try {
			disciplinas = negocio.buscaTodos();
		} catch (DisciplinaExection e) {
			System.out.println("Erro tentar buscar as disciplinas cadastradas.");
			System.out.println(e.getMessage());
		}
		return disciplinas;
	}

	public Disciplina buscaPorId(Integer id) {
		Disciplina disciplina = null;
		try {
			disciplina = negocio.buscaPorId(id);
		} catch (DisciplinaExection e) {
			System.out.println("Erro tentar buscar disciplina do ID: " + id + ".");
			System.out.println(e.getMessage());
		}
		return disciplina;
	}

	public Disciplina alterar(Disciplina disciplina) {
		try {
			disciplina = negocio.alterar(disciplina);
			System.out.println("Disciplina alterada com sucesso: " + disciplina);
		} catch (DisciplinaExection e) {
			System.out.println("Erro ao tentar alterar disciplina com ID: " + disciplina.getIdDisciplina() + ".");
			System.out.println(e.getMessage());
		}
		return disciplina;
	}

	public void excluir(Integer id) {
		try {
			negocio.excluir(id);
			System.out.println("Disciplina excluída com sucesso.");
		} catch (DisciplinaExection e) {
			System.out.println("Erro ao tentar excluir a disciplina");
			System.out.println(e.getMessage());
		}
	}
}
