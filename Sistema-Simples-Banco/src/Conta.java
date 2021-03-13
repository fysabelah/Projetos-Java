public class Conta {
	private Pessoa cliente;
	private int nrConta;
	private double saldo;
	
	public Pessoa getCliente() {
		return cliente;
	}
	
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	
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

	public Conta(Pessoa cliente, int nrConta, double saldo) {
		this.cliente = cliente;
		this.nrConta = nrConta;
		this.saldo = saldo;
	}
	 
	protected boolean temSaldo() {
		if(this.saldo <= 0) 
			return(false);
		return(true);
	}
	
	public boolean sacar(double valorSaque) {
		if(temSaldo()) {
			debitar(valorSaque);
			return(true);
		}
		
		return(false);
	}
	
	public boolean debitar(double valor) {
		if(valor <= this.saldo) {
			this.saldo -= valor;
			return(true);
		}

		return(false);
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
		return cliente.toString() + "\nNumero da Conta: " + nrConta + "\nSaldo: R$ " + saldo + "\n";
	}
}
