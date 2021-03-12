
public class ContaEspecial extends Conta{
	private double limite;

	public ContaEspecial(Pessoa cliente, int nrConta, double saldo, double limite) {
		super(cliente, nrConta, saldo);
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	protected int temSaldo() {
		//É permitido saldo negativo
		return(1);
	}

	@Override
	public String toString() {
		return super.toString() +"Limite da conta: R$" + this.getLimite() + "\n";
	}
}
