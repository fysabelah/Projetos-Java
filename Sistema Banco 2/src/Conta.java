public class Conta {
	private int nrConta;
	private double saldo;
	
	public int getNrConta() {
		return nrConta;
	}
	
	public void setNrConta(int nrConta) {
		this.nrConta = nrConta;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Conta(int nrConta, double saldo) {
		this.nrConta = nrConta;
		this.saldo = saldo;
	}
	 
	protected boolean temSaldo() {
		if(this.saldo > 0) 
			return(true);
		return(false);//Verificar se não influenciou
	}
	
	public boolean sacar(double valorSaque) {
		if(this.temSaldo()) {
			if(this.getSaldo() >= valorSaque) {
				//Trantando conta especial
				debitar(valorSaque);
				return(true);
			}
			else return(false);
		}
		else return(false);
	}
	
	public void debitar(double valor) {
		this.saldo -= valor;
	}
	
	public void depositar(double valorDepositar) {
		this.saldo += valorDepositar;
	}
	
	public boolean transferir(Conta c2, double valor) {
		if(this.sacar(valor)) {
			c2.depositar(valor);
			return(true);
		}

		return(false);
	}

	@Override
	public String toString() {
		return "Numero da Conta: " + this.getNrConta() + "\nSaldo: R$ " + this.getSaldo() + "\n";
	}
}