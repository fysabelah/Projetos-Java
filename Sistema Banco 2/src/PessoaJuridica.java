public class PessoaJuridica extends Pessoa{
	private String cnpj;
	private String atividade;

	public PessoaJuridica(int id, String nome, String endereco, String cnpj, String atividade) {
		super(id, nome, endereco);
		this.cnpj = cnpj;
		this.atividade = atividade;
	}
	
	@Override
	public String getIdentificador() {
		return cnpj;
	}
	
	@Override
	public void setIdentificador(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	@Override
	public String toString() {
		return super.toString() +"CNPJ: " + getIdentificador() + "\nAtividade: " + getAtividade() + "\n";
	}
}