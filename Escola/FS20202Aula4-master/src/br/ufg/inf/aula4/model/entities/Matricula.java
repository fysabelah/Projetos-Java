package br.ufg.inf.aula4.model.entities;

public class Matricula {

	private Integer idMatricula;
	private Aluno aluno;
	private Oferta oferta;

	public Matricula(Integer idMatricula, Aluno aluno, Oferta oferta) {
		super();
		this.idMatricula = idMatricula;
		this.aluno = aluno;
		this.oferta = oferta;
	}

	public Matricula() {
		super();
	}

	public Integer getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	@Override
	public String toString() {
		return "Matricula [idMatricula=" + idMatricula + ", aluno=" + aluno + ", oferta=" + oferta + "]";
	}

}
