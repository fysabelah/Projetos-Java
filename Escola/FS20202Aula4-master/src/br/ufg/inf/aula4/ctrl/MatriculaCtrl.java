package br.ufg.inf.aula4.ctrl;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.ctrl.exception.PessoaExection;
import br.ufg.inf.aula4.ctrl.negocio.MatriculaNegocio;
import br.ufg.inf.aula4.model.entities.Matricula;

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

	public List<Matricula> buscaTodos() throws AlunoException, CursoException, PessoaExection, OfertaExection{
		List<Matricula> matricula = null;
		try {
			matricula = negocio.buscaTodos();
		} catch (MatriculaException e) {
			System.out.println("Erro tentar buscar matrículas cadastradas.");
			System.out.println(e.getMessage());
		}
		return(matricula);
	}

	public Matricula buscaPorId(Integer id) throws AlunoException, CursoException, PessoaExection, OfertaExection {
		Matricula matricula = null;
		try {
			matricula = negocio.buscaPorId(id);
		} catch (MatriculaException e) {
			System.out.println("Erro tentar buscar matrícula de ID: " + id + ".");
			System.out.println(e.getMessage());
		}
		return(matricula);
	}

	public Matricula alterar(Matricula matricula) {
		try {
			matricula = negocio.alterar(matricula);
			System.out.println("Matrícula alterada com sucesso: " + matricula);
		} catch (MatriculaException e) {
			System.out.println("Erro ao tentar alterar matrícula com ID: " + matricula.getIdMatricula() + ".");
			System.out.println(e.getMessage());
		}
		return(matricula);
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
