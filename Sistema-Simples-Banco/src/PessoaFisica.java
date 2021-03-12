public class PessoaFisica extends Pessoa{
	private String cpf;
	private String dtNascimento;
	private String genero;

	public PessoaFisica(int id, String nome, String endereco, String cpf, String dtNascimento, String genero) {
		super(id, nome, endereco);
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.genero = genero;
	}
	
	@Override
	public String getIdentificador() {
		return cpf;
	}

	@Override
	public void setIdentificador(String cpf) {
		this.cpf = cpf;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return super.toString() + "CPF: " + getIdentificador() + "\nData de Nascimento:" + getDtNascimento() + "\n Genêro:"
				+ getGenero() + "\n";
	}
}
