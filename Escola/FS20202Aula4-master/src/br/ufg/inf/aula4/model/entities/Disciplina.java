package br.ufg.inf.aula4.model.entities;

public class Disciplina {

	private Integer idDisciplina;
	private String nmDisciplina;
	private Integer cargaHoraria;

	public Disciplina() {
		super();
	}

	public Disciplina(Integer idDisciplina, String nmDisciplina, Integer cargaHoraria) {
		super();
		this.idDisciplina = idDisciplina;
		this.nmDisciplina = nmDisciplina;
		this.cargaHoraria = cargaHoraria;
	}

	public Integer getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNmDisciplina() {
		return nmDisciplina;
	}

	public void setNmDisciplina(String nmDisciplina) {
		this.nmDisciplina = nmDisciplina;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return "Disciplina [idDisciplina=" + idDisciplina + ", nmDisciplina=" + nmDisciplina + ", cargaHoraria="
				+ cargaHoraria + "]";
	}

}
