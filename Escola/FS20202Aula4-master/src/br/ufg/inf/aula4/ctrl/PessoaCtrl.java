package br.ufg.inf.aula4.ctrl;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.PessoaExection;
import br.ufg.inf.aula4.ctrl.negocio.PessoaNegocio;
import br.ufg.inf.aula4.model.entities.Pessoa;

public class PessoaCtrl {

	PessoaNegocio negocio = new PessoaNegocio();

	public Pessoa inserir(Pessoa pessoa) {
		try {
			pessoa = negocio.inserir(pessoa);
			System.out.println("Pessoa cadastrada com sucesso: " + pessoa);
		} catch (PessoaExection e) {
			System.out.println("Erro ao tentar cadastrar pessoa.");
			System.out.println(e.getMessage());
		}
		return pessoa;
	}

	public List<Pessoa> buscaTodos() {
		List<Pessoa> pessoas = null;
		try {
			pessoas = negocio.buscaTodos();
		} catch (PessoaExection e) {
			System.out.println("Erro tentar buscar as pessoas cadastradas.");
			System.out.println(e.getMessage());
		}
		return pessoas;
	}

	public Pessoa buscaPorId(Integer id) {
		Pessoa pessoa = null;
		try {
			pessoa = negocio.buscaPorId(id);
		} catch (PessoaExection e) {
			System.out.println("Erro tentar buscar pessoa do ID: " + id + ".");
			System.out.println(e.getMessage());
		}
		return pessoa;
	}

	public Pessoa alterar(Pessoa pessoa) {
		try {
			pessoa = negocio.alterar(pessoa);
			System.out.println("Pessoa alterada com sucesso: " + pessoa);
		} catch (PessoaExection e) {
			System.out.println("Erro ao tentar alterar pessoa com ID: " + pessoa.getIdPessoa() + ".");
			System.out.println(e.getMessage());
		}
		return pessoa;
	}

	public void excluir(Integer id) {
		try {
			negocio.excluir(id);
			System.out.println("Pessoa excluída com sucesso.");
		} catch (PessoaExection e) {
			System.out.println("Erro ao tentar excluir a pessoa");
			System.out.println(e.getMessage());
		}
	}
}
