package br.ufg.inf.aula4.app;

import br.ufg.inf.aula4.ctrl.AlunoCtrl;
import br.ufg.inf.aula4.ctrl.CursoCtrl;
import br.ufg.inf.aula4.ctrl.DisciplinaCtrl;
import br.ufg.inf.aula4.ctrl.MatriculaCtrl;
import br.ufg.inf.aula4.ctrl.OfertaCtrl;
import br.ufg.inf.aula4.ctrl.PessoaCtrl;
import br.ufg.inf.aula4.ctrl.ProfessorCtrl;
import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.ctrl.exception.PessoaExection;

public class aplication {

	public static void main(String[] args) throws CursoException, PessoaExection, AlunoException, OfertaExection {

		System.out.println("Começando por aqui");

		TesteApp testeApp = new TesteApp();
		

		testeApp.testeCrudPessoa(new PessoaCtrl());
		testeApp.testeCrudDisciplina(new DisciplinaCtrl());
		testeApp.testeCrudCurso(new CursoCtrl());
		testeApp.testeCrudAluno(new AlunoCtrl());
		testeApp.testeCrudProfessor(new ProfessorCtrl());
		testeApp.testeCrudOferta(new OfertaCtrl(), new DisciplinaCtrl(), new ProfessorCtrl());
		testeApp.testeCrudMatricula(new MatriculaCtrl());
	}
}
