
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

	protected boolean temSaldo() {
		double saldo = super.getSaldo();
		if(saldo >= 0) return(true);
		else {
			if(saldo*-1 <= limite) return(true);
			else return(false); //Atingiu o limite especial
		}
	}

	@Override
	public String toString() {
		return super.toString() +"Limite da conta: R$" + this.getLimite() + "\n";
	}
}
