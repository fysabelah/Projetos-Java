import javax.swing.JOptionPane;

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
	 
	protected int temSaldo() {
		if(this.saldo <= 0) 
			return(0);
		return(1);
	}
	
	public int sacar(double valorSaque) {
		if(temSaldo() == 1) {
			debitar(valorSaque);
			return(1);
		}
		
		JOptionPane.showMessageDialog(null, "Conta sem saldo",
					"Saque", JOptionPane.ERROR_MESSAGE);
		return(0);
	}
	
	public void debitar(double valor) {
		if(valor <= this.saldo) {
			this.saldo -= valor;
			
			JOptionPane.showMessageDialog(null, "Saque de R$ " + valor + " realizado com sucesso\nO saldo atual é de R$ " + this.saldo + ".",
					"Saque", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "O valor do saque solicitado é maior que o valor do saldo",
					"Saque", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void depositar(double valorDepositar, int flag) {
		this.saldo += valorDepositar;
		
		/*if(flag == 1) {
			//JOptionPane.showMessageDialog(null, "O saldo atual é no valor de R$ " + this.saldo + ".", "Depósito", JOptionPane.INFORMATION_MESSAGE);
		}
		else JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso.",
				"Depósito", JOptionPane.INFORMATION_MESSAGE);*/
	}
	
	/*Já considero que a conta a receber existe. Logo trato em outra classe*/
	public void transferir(double valorTrans) {
		if(sacar(valorTrans) == 1) {
			depositar(valorTrans, 0);
		}
		else {
			JOptionPane.showMessageDialog(null, "Conta sem saldo para realização da transferência.",
					"Transferência", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public String toString() {
		return cliente.toString() + "\nNumero da Conta: " + nrConta + "\nSaldo: R$ " + saldo + "\n";
	}
}
