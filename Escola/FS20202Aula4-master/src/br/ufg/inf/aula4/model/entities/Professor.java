package br.ufg.inf.aula4.model.entities;

import br.ufg.inf.aula4.model.enums.Escolaridade;

public class Professor {

	private Integer idProfessor;
	private Pessoa pessoa;
	private Escolaridade escolaridade;

	
	
	public Professor() {
		super();
	}

	public Professor(Integer idProfessor, Pessoa pessoa, Escolaridade escolaridade) {
		super();
		this.idProfessor = idProfessor;
		this.pessoa = pessoa;
		this.escolaridade = escolaridade;
	}

	public Integer getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Integer idProfessor) {
		this.idProfessor = idProfessor;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	@Override
	public String toString() {
		return "Professor [idProfessor=" + idProfessor + ", pessoa=" + pessoa + ", escolaridade="
				+ this.getEscolaridade() + "]";
	}

}
