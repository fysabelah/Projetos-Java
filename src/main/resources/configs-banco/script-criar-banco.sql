CREATE DATABASE escola
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE tb_curso (
  id_curso SERIAL PRIMARY KEY,
  nm_curso varchar(45) NOT NULL
);

CREATE TABLE tb_disciplina (
  id_disciplina SERIAL PRIMARY KEY,
  nm_disciplina varchar(45) NOT NULL,
  carga_horaria int DEFAULT NULL
);

CREATE TABLE tb_pessoa (
  id_pessoa SERIAL PRIMARY KEY,
  nm_pessoa varchar(45) DEFAULT NULL,
  cpf bigint DEFAULT NULL,
  dt_nascimento date DEFAULT NULL
);

CREATE TABLE tb_aluno (
  id_aluno SERIAL PRIMARY KEY,
  dt_inicio date DEFAULT NULL,
  ativo smallint DEFAULT '1',
  id_pessoa int DEFAULT NULL,
  id_curso int DEFAULT NULL,
  CONSTRAINT aluno_curso FOREIGN KEY (id_curso) REFERENCES tb_curso (id_curso),
  CONSTRAINT aluno_pessoa FOREIGN KEY (id_pessoa) REFERENCES tb_pessoa (id_pessoa)
);

CREATE TABLE tb_professor (
  id_professor SERIAL PRIMARY KEY,
  escolaridade int DEFAULT NULL,
  id_pessoa int DEFAULT NULL,
  CONSTRAINT professor_pessoa FOREIGN KEY (id_pessoa) REFERENCES tb_pessoa (id_pessoa)
);

COMMENT ON COLUMN tb_professor.escolaridade IS '1 - Médio\n2 - Graduação\n3 - Especialização\n4 - Mestrado\n5 - Doutorado';

CREATE TABLE tb_oferta (
  id_oferta SERIAL PRIMARY KEY,
  id_professor int DEFAULT NULL,
  id_disciplina int DEFAULT NULL,
  dt_inicio date DEFAULT NULL,
  dt_fim date DEFAULT NULL,
  dia int DEFAULT NULL,
  hora varchar(45) DEFAULT NULL,
  CONSTRAINT oferta_disciplina FOREIGN KEY (id_disciplina) REFERENCES tb_disciplina (id_disciplina),
  CONSTRAINT oferta_professor FOREIGN KEY (id_professor) REFERENCES tb_professor (id_professor)
);

COMMENT ON COLUMN tb_oferta.dia IS '1 - Domingo\n2 - Segunda-feira\n3 - Terça-feira\n4 - Quarta-feira\n5 - Quinta-feira\n6 - Sexta-feira\n7 - Sábado';

CREATE TABLE tb_matricula (
  id_matricula SERIAL PRIMARY KEY,
  id_aluno int DEFAULT NULL,
  id_oferta int DEFAULT NULL,
  CONSTRAINT matricula_aluno FOREIGN KEY (id_aluno) REFERENCES tb_aluno (id_aluno),
  CONSTRAINT matricula_oferta FOREIGN KEY (id_oferta) REFERENCES tb_oferta (id_oferta)
);