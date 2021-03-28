CREATE DATABASE `escola` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `escola`.`tb_curso` (
  `id_curso` int NOT NULL AUTO_INCREMENT,
  `nm_curso` varchar(45) NOT NULL,
  PRIMARY KEY (`id_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `escola`.`tb_disciplina` (
  `id_disciplina` int NOT NULL AUTO_INCREMENT,
  `nm_disciplina` varchar(45) NOT NULL,
  `carga_horaria` int DEFAULT NULL,
  PRIMARY KEY (`id_disciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `escola`.`tb_pessoa` (
  `id_pessoa` int NOT NULL AUTO_INCREMENT,
  `nm_pessoa` varchar(45) DEFAULT NULL,
  `cpf` bigint DEFAULT NULL,
  `dt_nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `escola`.`tb_aluno` (
  `id_aluno` int NOT NULL AUTO_INCREMENT,
  `dt_inicio` date DEFAULT NULL,
  `ativo` tinyint DEFAULT '1',
  `id_pessoa` int DEFAULT NULL,
  `id_curso` int DEFAULT NULL,
  PRIMARY KEY (`id_aluno`),
  KEY `aluno_pessoa_idx` (`id_pessoa`),
  KEY `aluno_curso_idx` (`id_curso`),
  CONSTRAINT `aluno_curso` FOREIGN KEY (`id_curso`) REFERENCES `tb_curso` (`id_curso`),
  CONSTRAINT `aluno_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `tb_pessoa` (`id_pessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `escola`.`tb_professor` (
  `id_professor` int NOT NULL AUTO_INCREMENT,
  `escolaridade` int DEFAULT NULL COMMENT '1 - Médio\n2 - Graduação\n3 - Especialização\n4 - Mestrado\n5 - Doutorado',
  `id_pessoa` int DEFAULT NULL,
  PRIMARY KEY (`id_professor`),
  KEY `professor_pessoa_idx` (`id_pessoa`),
  CONSTRAINT `professor_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `tb_pessoa` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `escola`.`tb_oferta` (
  `id_oferta` int NOT NULL AUTO_INCREMENT,
  `id_professor` int DEFAULT NULL,
  `id_disciplina` int DEFAULT NULL,
  `dt_inicio` date DEFAULT NULL,
  `dt_fim` date DEFAULT NULL,
  `dia` int DEFAULT NULL COMMENT '1 - Domingo\n2 - Segunda-feira\n3 - Terça-feira\n4 - Quarta-feira\n5 - Quinta-feira\n6 - Sexta-feira\n7 - Sábado',
  `hora` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_oferta`),
  KEY `oferta_disciplina_idx` (`id_disciplina`),
  KEY `oferta_professor_idx` (`id_professor`),
  CONSTRAINT `oferta_disciplina` FOREIGN KEY (`id_disciplina`) REFERENCES `tb_disciplina` (`id_disciplina`),
  CONSTRAINT `oferta_professor` FOREIGN KEY (`id_professor`) REFERENCES `tb_professor` (`id_professor`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `escola`.`tb_matricula` (
  `id_matricula` int NOT NULL AUTO_INCREMENT,
  `id_aluno` int DEFAULT NULL,
  `id_oferta` int DEFAULT NULL,
  PRIMARY KEY (`id_matricula`),
  KEY `oferta_aluno_idx` (`id_aluno`),
  KEY `matricula_oferta_idx` (`id_oferta`),
  CONSTRAINT `matricula_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `tb_aluno` (`id_aluno`),
  CONSTRAINT `matricula_oferta` FOREIGN KEY (`id_oferta`) REFERENCES `tb_oferta` (`id_oferta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;