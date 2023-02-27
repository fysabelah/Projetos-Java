package main.br.escola.app;

import main.br.escola.ctrl.*;
import main.br.escola.model.entities.*;
import main.br.escola.model.enums.Dia;
import main.br.escola.model.enums.Escolaridade;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TesteApp {

    private final static String LINE = "--------------------------------------------------";

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public void testeCrudDisciplina(DisciplinaCtrl ctrl) {
        List<Disciplina> disciplinas = ctrl.buscaTodos();
        System.out.println(LINE);
        System.out.println("Buscar Todos");
        if (disciplinas != null && !disciplinas.isEmpty()) {
            for (Disciplina dis : ctrl.buscaTodos()) {
                System.out.println(dis);
            }
        }

        System.out.println(LINE);

        Disciplina disc1 = new Disciplina(null, "Des. FullStack", 64);
        Disciplina disc2 = new Disciplina(null, "LLP", 60);
        Disciplina disc3 = new Disciplina(null, "Matemática", 30);
        Disciplina disc4 = new Disciplina(1, "Inglês", 10);
        Disciplina disc5 = new Disciplina(2, "Lógica", 40);
        Disciplina disc6 = new Disciplina(3, "Matemática", 30);

        ctrl.inserir(disc1);
        ctrl.inserir(disc2);
        disc3 = ctrl.inserir(disc3);
        ctrl.inserir(disc4);
        ctrl.inserir(disc5);
        ctrl.inserir(disc6);

        System.out.println(LINE);
        System.out.println("Buscar Todos");
        disciplinas = ctrl.buscaTodos();
        if (disciplinas != null && !disciplinas.isEmpty()) {
            for (Disciplina dis : ctrl.buscaTodos()) {
                System.out.println(dis);
            }
        }

        System.out.println(LINE);
        System.out.println("Buscar pelo id 2: " + ctrl.buscaPorId(2));

        if (disc3 != null) {
            System.out.println(LINE);
            System.out.println("Alterar");
            disc3.setCargaHoraria(50);
            disc3.setNmDisciplina(disc3.getNmDisciplina() + " Discreta");
            ctrl.alterar(disc3);
        }

        System.out.println(LINE);
        System.out.println("Excluir");
        ctrl.excluir(5);

        System.out.println(LINE);
        System.out.println("Buscar Todos");
        disciplinas = ctrl.buscaTodos();
        if (disciplinas != null && !disciplinas.isEmpty()) {
            for (Disciplina dis : ctrl.buscaTodos()) {
                System.out.println(dis);
            }
        }
    }

    public void testeCrudPessoa(PessoaCtrl ctrl) {
        System.out.println(LINE);
        System.out.println("Pessoas Cadastradas");
        List<Pessoa> pessoas = ctrl.buscaTodos();
        if (pessoas != null && !pessoas.isEmpty()) {
            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }

        System.out.println(LINE);

        Pessoa pes1 = new Pessoa(null, "Luiz Martins", 12345678901l, new Date(1980, 1, 10));
        Pessoa pes2 = new Pessoa(null, "Fulano da Silva", 99999999999l, new Date(1985, 2, 5));
        Pessoa pes3 = new Pessoa(null, "Ciclano da Silva", 88888888888l, new Date(1980, 1, 10));
        Pessoa pes4 = new Pessoa(null, "Beltrano da Silva", 77777777777l, new Date(1980, 1, 10));

        ctrl.inserir(pes1);
        ctrl.inserir(pes2);
        ctrl.inserir(pes3);
        pes4 = ctrl.inserir(pes4);

        System.out.println(LINE);
        System.out.println("Pessoas Cadastradas");
        pessoas = ctrl.buscaTodos();
        if (pessoas != null && !pessoas.isEmpty()) {
            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }

        System.out.println(LINE);
        System.out.println("Buscar pelo id 1: " + ctrl.buscaPorId(1));

        if (pes4 != null) {
            System.out.println(LINE);
            System.out.println("Alterando");
            pes4.setCpf(11111111111l);
            pes4.setNmPessoa("José " + pes4.getNmPessoa());
            ctrl.alterar(pes4);
        }

        System.out.println(LINE);
        System.out.println("Excluindo");
        ctrl.excluir(5);

        System.out.println(LINE);
        System.out.println("Pessoas Cadastradas");
        pessoas = ctrl.buscaTodos();
        if (pessoas != null && !pessoas.isEmpty()) {
            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }
    }

    public void testeCrudProfessor(ProfessorCtrl ctrl) {
        PessoaCtrl pessoaCtrl = new PessoaCtrl();

        System.out.println("Professores Cadastrados");
        List<Professor> professors = ctrl.buscaTodos();
        if (professors != null && !professors.isEmpty()) {
            for (Professor dis : ctrl.buscaTodos()) {
                System.out.println(dis);
            }
        }

        System.out.println(LINE);
        Professor prof1 = new Professor(null, pessoaCtrl.buscaPorId(1), Escolaridade.get(4));
        Professor prof2 = new Professor(null, pessoaCtrl.buscaPorId(2), Escolaridade.get(2));

        ctrl.inserir(prof1);
        prof2 = ctrl.inserir(prof2);

        System.out.println(LINE);
        System.out.println("Professores Cadastrados");
        professors = ctrl.buscaTodos();
        if (professors != null && !professors.isEmpty()) {
            for (Professor dis : ctrl.buscaTodos()) {
                System.out.println(dis);
            }
        }

        System.out.println(LINE);
        Professor cadastrado = ctrl.buscaPorId(1);
        System.out.println("Buscar pelo id: 1 => " + cadastrado);

        System.out.println(LINE);
        System.out.println("Alterando");
        cadastrado.setEscolaridade(Escolaridade.get(3));
        ctrl.alterar(cadastrado);

        if (prof2.getIdProfessor() != null) {
            System.out.println(LINE);
            System.out.println("Excluindo");
            ctrl.excluir(prof2.getIdProfessor());
        }

        System.out.println(LINE);
        System.out.println("Professores Cadastrados");
        professors = ctrl.buscaTodos();
        if (professors != null && !professors.isEmpty()) {
            for (Professor dis : ctrl.buscaTodos()) {
                System.out.println(dis);
            }
        }
    }

    public void testeCrudOferta(OfertaCtrl ctrl, DisciplinaCtrl disciplinaCtrl, ProfessorCtrl professorCtrl) {
        Date dt1 = null;
        Date dt2 = null;

        try {
            dt1 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-3-10");
            dt2 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-6-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Oferta ofe1 = new Oferta(null, professorCtrl.buscaPorId(1), disciplinaCtrl.buscaPorId(1), dt1, dt2, Dia.get(2),
                "08:00");
        ctrl.inserir(ofe1);

        try {
            dt1 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-2-7");
            dt2 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-5-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Oferta ofe2 = new Oferta(null, professorCtrl.buscaPorId(2), disciplinaCtrl.buscaPorId(2), dt1, dt2, Dia.get(6),
                "19:00");
        ctrl.inserir(ofe2);

        System.out.println(LINE);

    }

    public void testeCrudCurso(CursoCtrl ctrl) {
        Curso curso1 = new Curso(null, "Ciência da Computação");
        Curso curso2 = new Curso(null, "Engenharia da Computação");
        Curso curso3 = new Curso(null, "Engenharia de Software");

        ctrl.inserir(curso1);
        curso2 = ctrl.inserir(curso2);
        ctrl.inserir(curso3);

        System.out.println(LINE);
        System.out.println("Cursos Cadastrados");
        List<Curso> cursos = ctrl.buscaTodos();
        if (cursos != null && !cursos.isEmpty()) {
            for (Curso curso : cursos) {
                System.out.println(curso);
            }
        }

        System.out.println(LINE);
        System.out.println("Curso de ID 1");
        System.out.println(ctrl.buscaPorId(1));

        if (curso2 != null) {
            System.out.println(LINE);
            System.out.println("Curso alterado");
            curso2.setNmCurso("Sistemas da Informação");
            ctrl.alterar(curso2);
        }

        ctrl.excluir(5);

        System.out.println(LINE);
        System.out.println("Cursos Cadastrados");
        cursos = ctrl.buscaTodos();
        if (cursos != null && !cursos.isEmpty()) {
            for (Curso curso : cursos) {
                System.out.println(curso);
            }
        }
    }

    public void testeCrudMatricula(MatriculaCtrl ctrl) {
        AlunoCtrl aluno = new AlunoCtrl();
        OfertaCtrl oferta = new OfertaCtrl();

        Matricula mat1 = new Matricula(null, aluno.buscaPorId(1), oferta.buscaPorId(1));
        Matricula mat2 = new Matricula(null, aluno.buscaPorId(1), oferta.buscaPorId(1));
        Matricula mat3 = new Matricula(null, aluno.buscaPorId(2), oferta.buscaPorId(2));
        Matricula mat4 = new Matricula(null, aluno.buscaPorId(2), oferta.buscaPorId(2));

        ctrl.inserir(mat1);
        ctrl.inserir(mat2);
        ctrl.inserir(mat3);
        mat4 = ctrl.inserir(mat4);

        List<Matricula> matriculas = ctrl.buscaTodos();
        if (matriculas != null && !matriculas.isEmpty()) {
            System.out.println(LINE);
            System.out.println("Matriculas Cadastrados");

            for (Matricula mat : matriculas) {
                System.out.println(mat);
            }
        }

        System.out.println(LINE);
        System.out.println("Matricula de ID 1");
        System.out.println(ctrl.buscaPorId(1));

        if (mat4 != null) {
            System.out.println(LINE);
            System.out.println("Matricula alterada");
            mat4.setAluno(aluno.buscaPorId(2));
            ctrl.alterar(mat4);
        }

        ctrl.excluir(5);

        matriculas = ctrl.buscaTodos();
        if (matriculas != null && !matriculas.isEmpty()) {
            System.out.println(LINE);
            System.out.println("Matriculas Cadastrados");

            for (Matricula mat : matriculas) {
                System.out.println(mat);
            }
        }
    }

    public void testeCrudAluno(AlunoCtrl ctrl) {
        PessoaCtrl pessoaCtrl = new PessoaCtrl();
        CursoCtrl curso = new CursoCtrl();

        Date dt1 = null;
        Date dt2 = null;
        Date dt3 = null;

        try {
            dt1 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-3-26");
            dt2 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-3-27");
            dt3 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-3-28");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Aluno aluno1 = new Aluno(null, dt1, false, pessoaCtrl.buscaPorId(1), curso.buscaPorId(1));
        Aluno aluno2 = new Aluno(null, dt2, true, pessoaCtrl.buscaPorId(2), curso.buscaPorId(3));
        Aluno aluno3 = new Aluno(null, dt3, true, pessoaCtrl.buscaPorId(4), curso.buscaPorId(3));

        aluno1 = ctrl.inserir(aluno1);
        aluno2 = ctrl.inserir(aluno2);
        aluno3 = ctrl.inserir(aluno3);

        System.out.println(LINE);
        System.out.println("Alunos Cadastrados");
        List<Aluno> alunos = ctrl.buscaTodos();
        if (alunos != null && !alunos.isEmpty()) {
            for (Aluno al : alunos) {
                System.out.println(al);
            }
        }

        System.out.println(LINE);
        System.out.println("Aluno de ID 1");
        System.out.println(ctrl.buscaPorId(1));

        if (aluno3 != null && aluno3.getIdAluno() != null) {
            System.out.println(LINE);
            System.out.println("Alunos Alterados");
            aluno3.setCurso(curso.buscaPorId(1));
            ctrl.alterar(aluno3);
        }

        ctrl.excluir(5);

        System.out.println(LINE);
        System.out.println("Alunos Cadastrados");
        alunos = ctrl.buscaTodos();
        if (alunos != null && !alunos.isEmpty()) {
            for (Aluno al : alunos) {
                System.out.println(al);
            }
        }
    }
}
