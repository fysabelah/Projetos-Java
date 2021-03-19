public class ContaPoupanca extends Conta{
	private double txCorrecao;
	
	public ContaPoupanca(int nrConta, double saldo, double txCorrecao) {
		super(nrConta, saldo);
		this.txCorrecao = txCorrecao;
	}

	public double getTxCorrecao() {
		return txCorrecao;
	}

	public void setTxCorrecao(double txCorrecao) {
		this.txCorrecao = txCorrecao;
	}

	public void atualizaSaldoRendimento() {
		double auxiliar = super.getSaldo();
		super.setSaldo(auxiliar + auxiliar * this.txCorrecao);
	}

	@Override
	public String toString() {
		return super.toString() + "Correção: " + getTxCorrecao() + "%\n";
	}
	
}