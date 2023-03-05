package main.br.escola.app;

import main.br.escola.ctrl.*;

public class Application {

    public static void main(String[] args) {

        System.out.println("Inicializando as transações");

        TesteApp testeApp = new TesteApp();

        testeApp.testeCrudPessoa(new PessoaCtrl());
        testeApp.testeCrudCurso(new CursoCtrl());
        testeApp.testeCrudDisciplina(new DisciplinaCtrl());
        testeApp.testeCrudProfessor(new ProfessorCtrl());
        testeApp.testeCrudAluno(new AlunoCtrl());
        testeApp.testeCrudOferta(new OfertaCtrl(), new DisciplinaCtrl(), new ProfessorCtrl());
        testeApp.testeCrudMatricula(new MatriculaCtrl());

        System.out.println("Finalizadas");
    }
}
