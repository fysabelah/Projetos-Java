package br.ufg.inf.aula4.model.entities;

import java.util.Date;

public class Aluno {

	private Integer idAluno;
	private Date dtInicio;
	private Boolean ativo;
	private Pessoa pessoa;
	private Curso curso;

	public Aluno() {
		super();
	}

	public Aluno(Integer idAluno, Date dtInicio, Boolean ativo, Pessoa pessoa, Curso curso) {
		super();
		this.idAluno = idAluno;
		this.dtInicio = dtInicio;
		this.ativo = ativo;
		this.pessoa = pessoa;
		this.curso = curso;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Aluno [idAluno=" + idAluno + ", dtInicio=" + dtInicio + ", ativo=" + ativo + ", pessoa=" + pessoa
				+ ", curso=" + curso + "]";
	}

}
