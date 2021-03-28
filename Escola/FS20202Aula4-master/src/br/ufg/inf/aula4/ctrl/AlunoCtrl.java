package br.ufg.inf.aula4.ctrl;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.PessoaExection;
import br.ufg.inf.aula4.ctrl.negocio.AlunoNegocio;
import br.ufg.inf.aula4.model.entities.Aluno;

public class AlunoCtrl {
	AlunoNegocio negocio = new AlunoNegocio();

	public Aluno inserir(Aluno aluno) {
		try {
			aluno = negocio.inserir(aluno);
			System.out.println("Aluno(a) cadastrado(a) com sucesso: " + aluno);
		} catch (AlunoException e) {
			System.out.println("Erro ao tentar cadastrar aluno(a).");
			System.out.println(e.getMessage());
		}
		return(aluno);
	}

	public List<Aluno> buscaTodos() throws CursoException, PessoaExection {
		List<Aluno> aluno = null;
		try {
			aluno = negocio.buscaTodos();
		} catch (AlunoException e) {
			System.out.println("Erro tentar buscar o(a)s aluno(a)s cadastrado(a)s.");
			System.out.println(e.getMessage());
		}
		return(aluno);
	}

	public Aluno buscaPorId(Integer id) throws CursoException, PessoaExection {
		Aluno aluno = null;
		try {
			aluno = negocio.buscaPorId(id);
		} catch (AlunoException e) {
			System.out.println("Erro tentar buscar aluno(a) de ID: " + id + ".");
			System.out.println(e.getMessage());
		}
		return(aluno);
	}

	public Aluno alterar(Aluno aluno) {
		try {
			aluno = negocio.alterar(aluno);
			System.out.println("Aluno atualizado com sucesso: " + aluno);
		} catch (AlunoException e) {
			System.out.println("Erro ao tentar atualizar o(a) aluno(a) com ID: " + aluno.getIdAluno() + ".");
			System.out.println(e.getMessage());
		}
		return(aluno);
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
