package br.ufg.inf.aula4.model.entities;

import java.util.Date;

import br.ufg.inf.aula4.model.enums.Dia;

public class Oferta {

	private Integer idOferta;
	private Professor professor;
	private Disciplina disciplina;
	private Date dtInicio;
	private Date dtFim;
	private Dia dia;
	private String hora;
	
	public Oferta() {
		super();
	}
	
	public Oferta(Integer idOferta, Professor professor, Disciplina disciplina, Date dtInicio, Date dtFim, Dia dia,
			String hora) {
		super();
		this.idOferta = idOferta;
		this.professor = professor;
		this.disciplina = disciplina;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.dia = dia;
		this.hora = hora;
	}

	public Integer getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Integer idOferta) {
		this.idOferta = idOferta;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Oferta [idOferta=" + idOferta + ", professor=" + professor + ", disciplina=" + disciplina
				+ ", dtInicio=" + dtInicio + ", dtFim=" + dtFim + ", dia=" + dia + ", hora=" + hora + "]";
	}
	
}
