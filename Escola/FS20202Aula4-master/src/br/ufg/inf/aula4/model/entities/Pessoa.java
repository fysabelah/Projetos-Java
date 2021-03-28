package br.ufg.inf.aula4.model.entities;

import java.util.Date;

public class Pessoa {

	private Integer idPessoa;
	private String nmPessoa;
	private Long cpf;
	private Date dtNascimento;
	
	
	public Pessoa() {
		super();
	}

	public Pessoa(Integer idPessoa, String nmPessoa, Long cpf, Date dtNascimento) {
		this.idPessoa = idPessoa;
		this.nmPessoa = nmPessoa;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
	}
	
	public Integer getIdPessoa() {
		return idPessoa;
	}
	
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public String getNmPessoa() {
		return nmPessoa;
	}
	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}
	
	public Long getCpf() {
		return cpf;
	}
	
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nmPessoa=" + nmPessoa + ", cpf=" + cpf + ", dtNascimento="
				+ dtNascimento + "]";
	}
	
}
