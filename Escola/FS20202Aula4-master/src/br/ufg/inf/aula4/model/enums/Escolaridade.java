package br.ufg.inf.aula4.model.enums;

public enum Escolaridade {
	
	MEDIO(1, "Médio"),
	GRADUACAO(2, "Graduação"),
	ESPECIALIZACAO(3, "Especialização"),
	MESTRADO(4, "Mestrado"),
	DOUTORADO(5, "Doutorado");
		
	private int id;
	private String value;
	
	Escolaridade(int id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static Escolaridade get(int id) {
		Escolaridade res = null;
		for(Escolaridade esc : Escolaridade.values()) {
			if(esc.getId() == id) {
				res = esc;
				break;
			}
		}
		return res;
	}
	
}
