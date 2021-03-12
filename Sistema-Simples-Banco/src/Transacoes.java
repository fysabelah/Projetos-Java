import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Transacoes{
	AuxiliarTransacoes auxT = new AuxiliarTransacoes();
	List<Conta> contas = new ArrayList();
	int contaId = 1000;
	
	public void menu(){
        String[] op1 = {"Gerenciar Conta", "Gerenciar Cliente", "Sair"};
        
        int input = JOptionPane.showOptionDialog(null, "Escolha a opção desejada?", 
                "Menu Pricipal", 0, JOptionPane.PLAIN_MESSAGE, null, op1, op1[0]);
       
        switch(input) {
	    	case 0:
	    		menuConta();
	    		break;
	    	case 1:
	    		menuCliente();
	    		break;
	    	case 2:
	    		auxT.confirmaSair();
	    		break;
	    }
    }
	
	public void  menuConta(){
        String[] op3 = {"Saque", "Depósito", "Transferência", "Sair"};
        
        int input = JOptionPane.showOptionDialog(null, "Escolha a opção desejada?", 
                "Menu de Contas", 0, JOptionPane.INFORMATION_MESSAGE, null, op3, op3[0]);
        
        switch(input) {
        	case 0:
        		saque();
        		break;
        	case 1:
        		depositar();
        		break;
        	case 2:
        		transferir();
        		break;
        	case 3:
        		auxT.confirmaSair();
        		break;
        
        }
    }
	
	public void transferir() {
		Conta clienteS = auxT.buscarCliente(contas, "Informe o CPF/CNPJ da conta A SACAR");
		String aux = JOptionPane.showInputDialog(null,"Informe o valor do saque", 
	            "Saque",JOptionPane.PLAIN_MESSAGE);
		double valorS = Double.parseDouble(aux);
		Conta clienteR = auxT.buscarCliente(contas, "Informe o CPF/CNPJ da conta A RECEBER");
		
		if(clienteS == null || clienteR == null) {
			JOptionPane.showMessageDialog(null, "Operação abortada. Favor verificar contas",
	                "Transferência",JOptionPane.ERROR_MESSAGE);
		}
		else {
			if(clienteS.sacar(valorS) == 1) {
				clienteR.depositar(valorS, 0);
				
				JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso.",
						"Transferência", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Operação abortada por falta de saldo",
		                "Transferência",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		menu();
	}
	
	public void depositar() {
		Conta cliente = auxT.buscarCliente(contas, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			JOptionPane.showMessageDialog(null, "Este cliente não possui conta.",
	                "Saque",JOptionPane.ERROR_MESSAGE);
		}
		else {
			String aux = JOptionPane.showInputDialog(null,"Informe o valor do depósito", 
		            "Depósito",JOptionPane.PLAIN_MESSAGE);
			double valor = Double.parseDouble(aux);
			
			cliente.depositar(valor, 1);
			
			JOptionPane.showMessageDialog(null, "O saldo atual é no valor de R$ " + cliente.getSaldo() + ".",
					"Depósito", JOptionPane.INFORMATION_MESSAGE);
		}
		
		menu();
	}
	
	public void saque() {
		Conta cliente = auxT.buscarCliente(contas, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			JOptionPane.showMessageDialog(null, "Este cliente não possui conta.",
	                "Saque",JOptionPane.ERROR_MESSAGE);
		}
		else {
			String aux = JOptionPane.showInputDialog(null,"Informe o valor do saque", 
		            "Saque",JOptionPane.PLAIN_MESSAGE);
			double valor = Double.parseDouble(aux);
			
			cliente.sacar(valor);
		}
		
		menu();
	}
	
	public void menuCliente(){
        String[] op2 = {"Cadastrar", "Visualizar", "Sair"};
        int input = JOptionPane.showOptionDialog(null, "Escolha a opção desejada?", 
                "Menu de Clientes", 0, JOptionPane.PLAIN_MESSAGE, null, op2, op2[0]);
        
        switch(input) {
	    	case 0:
	    		cadastrar();
	    		break;
	    	case 1:
	    		visualizar();
	    		break;
	    	case 2:
	    		auxT.confirmaSair();
	    		break;
	    
	    }
    }
	
	public void visualizar() {
		Conta cliente = auxT.buscarCliente(contas, "Informe o CPF/CNPJ");

		if(cliente == null) {
			JOptionPane.showMessageDialog(null, "Este cliente não possui conta.",
	                "Buscar cliente",JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, cliente.toString(),
	                "Dados",JOptionPane.PLAIN_MESSAGE);
		}
		
		menu();
	}
	
	public void cadastrar() {
		String[] op1 = {"Especial", "Poupança"};
		int tipoConta = JOptionPane.showOptionDialog(null, "Cadastro de Conta", 
                "Tipo",0, JOptionPane.PLAIN_MESSAGE, null, op1, op1[0]);
        
		Pessoa p = auxT.cadastrarCliente();
        
		switch(tipoConta) {
        	case 0:
               ContaEspecial c = new ContaEspecial(p, contaId, auxT.saldoInicial() , 1000.0);
               contaId += 1;
               contas.add(contas.size(), c);
               break;
        	case 1:
                ContaPoupanca c1 = new ContaPoupanca(p, contaId, auxT.saldoInicial() , 0.001);
                contaId += 1;
                contas.add(contas.size(), c1);
                break;
        }
		
		JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso.",
                "Cadastro",JOptionPane.INFORMATION_MESSAGE);
		
		menu();
	}
}