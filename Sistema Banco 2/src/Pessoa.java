import java.util.ArrayList;
import java.util.List;

public class Pessoa {
	private int id;
	private String nome;
	private String endereco;
	List<Conta> contas = new ArrayList();
	
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
	
	public void adicionarConta(Conta conta) {
		contas.add(contas.size(), conta);
	}
	
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereco: " + this.getEndereco() + "\n";
	}

	public void setIdentificador(String ident) {
		
	}
	
	public String getIdentificador() {
		return null;
	}
}