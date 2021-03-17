public class Pessoa {
	private int id;
	private String nome;
	private String endereco;
	
	public Pessoa(int id, String nome, String endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Identificador: " + getId() + "\nNome: " + getNome() + "\nEndreço: " + getEndereco() + "\n";
	}
	
	public void setIdentificador(String ident) {
		
	}
	
	public String getIdentificador() {
		return null;
	}
}