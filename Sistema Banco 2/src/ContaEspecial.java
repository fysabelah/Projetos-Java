public class ContaEspecial extends Conta{
	private double limite;

	public ContaEspecial(int nrConta, double saldo, double limite) {
		super(nrConta, saldo);
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public boolean sacar(double valorSaque) {
		if(this.temSaldo()) {
			if(valorSaque < this.limite) {
				//Trantando conta especial
				super.debitar(valorSaque);
				return(true);
			}
			else return(false);
		}
		else return(false);
	}
	
	protected boolean temSaldo() {
		if(super.getSaldo() > 0) return(true);
		else {
			if((super.getSaldo()*-1) < this.limite) {
				return(true);
			}
			
			return(false);
		}
	}

	@Override
	public String toString() {
		return super.toString() +"Limite da conta: R$" + this.getLimite() + "\n";
	}
}