import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

class Transacoes{
	static List<Conta> contas = new ArrayList();
	
	public static void saque() {
		Conta cliente = operacoesAuxiliares.buscarCliente(contas, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			jOptions.dialogError("Este cliente não possui conta", "Saque");
		}
		else {
			double valor = Double.parseDouble(jOptions.inputDialog("Informe o valor do saque", "Saque"));
			
			if(cliente.sacar(valor)) {
				jOptions.dialogInformation("Saque realizado com sucesso\nO saldo atual é de R$ " + cliente.getSaldo(), "Saque");
			}
			else {
				jOptions.dialogError("Conta sem saldo", "Saque");
			}
		}
		
		Menu.menu();
	}
	
	public static void depositar() {
		Conta cliente = operacoesAuxiliares.buscarCliente(contas, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			jOptions.dialogError("Este cliente não possui conta", "Depósito");
		}
		else {
			double valor = Double.parseDouble(jOptions.inputDialog("Informe o valor do depósito", "Depósito"));
			
			cliente.depositar(valor);
			
			jOptions.dialogInformation("Depósito realizado com sucesso\nO saldo atual é de R$ " + cliente.getSaldo(), "Depósito");
		}
		
		Menu.menu();
	}

	public static void transferir() {
		Conta clienteS = operacoesAuxiliares.buscarCliente(contas, "Informe o CPF/CNPJ da conta A SACAR");
		double valor = Double.parseDouble(jOptions.inputDialog("Informe o valor do saque", "Saque"));
		Conta clienteR = operacoesAuxiliares.buscarCliente(contas, "Informe o CPF/CNPJ da conta A RECEBER");
		
		if(clienteS.transferir(clienteR, valor)) {
			jOptions.dialogInformation("Transferência realizado com sucesso", "Transferência");
		}
		else {
			jOptions.dialogInformation("Conta A SACAR sem saldo disponível", "Transferência");
		}
		
		Menu.menu();
	}
	
	public static void visualizar() {
		Conta cliente = operacoesAuxiliares.buscarCliente(contas, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			jOptions.dialogError("Este cliente não cadastrado", "Dados");
		}
		else {
			jOptions.dialogInformation(cliente.toString(), "Dados");
		}
		
		Menu.menu();
	}
	
	public static void cadastrar() {
		String[] op1 = {"Especial", "Poupança"};
		
		int tipo = jOptions.optionDialog(op1, "Cadastro de conta", "Tipo");
		
		Pessoa cliente = operacoesAuxiliares.cadastrarCliente();
		
		switch(tipo) {
			case 0:
				ContaEspecial ce = new ContaEspecial(
						cliente,
						operacoesAuxiliares.identificadores(),
						0,
						1000);
				contas.add(contas.size(), ce);
				break;
			case 1:
				ContaPoupanca cp = new ContaPoupanca(
						cliente,
						operacoesAuxiliares.identificadores(),
						operacoesAuxiliares.saldoInicial(),
						0.002);
				contas.add(contas.size(), cp);
				break;
		}
		
		Menu.menu();
	}
	
}

class Menu{
	public static void menu(){
        String[] op1 = {"Gerenciar Conta", "Gerenciar Cliente", "Sair"};
        
        int input = jOptions.optionDialog(op1, "Escolha a opção desejada", "Menu Principal");
        
        switch(input) {
	    	case 0:
	    		menuConta();
	    		break;
	    	case 1:
	    		menuCliente();
	    		break;
	    	case 2:
	    		operacoesAuxiliares.confirmaSair();
	    		break;
	    }
    }
	
	public static void  menuConta(){
        String[] op1 = {"Saque", "Depósito", "Transferência", "Sair"};
        
        int input = jOptions.optionDialog(op1, "Escolha a opção desejada", "Menu Contas");

       switch(input) {
        	case 0:
        		Transacoes.saque();
        		break;
        	case 1:
        		Transacoes.depositar();
        		break;
        	case 2:
        		Transacoes.transferir();
        		break;
        	case 3:
        		operacoesAuxiliares.confirmaSair();
        		break;
        
        }
    }
	
	public static void menuCliente(){
        String[] op1 = {"Cadastrar", "Visualizar", "Sair"};
        
        int input = jOptions.optionDialog(op1, "Escolha a opção desejada", "Menu de Clientes");
        
        switch(input) {
	    	case 0:
	    		Transacoes.cadastrar();
	    		break;
	    	case 1:
	    		Transacoes.visualizar();
	    		break;
	    	case 2:
	    		operacoesAuxiliares.confirmaSair();
	    		break;
	    
	    }
    }
}

class operacoesAuxiliares{
	public static void confirmaSair() {
		int input = JOptionPane.showConfirmDialog(null, "Deseja continuar?", 
                "Sair", JOptionPane.YES_NO_OPTION);

        if(input == 0) System.exit(0);
        else Menu.menu();
	}
	
	public static Conta buscarCliente(List<Conta> contas, String texto) {
		String aux = jOptions.inputDialog(texto, "Buscar cliente");
		
		for(int i = 0; i < contas.size(); i++) {
			Conta c = contas.get(i);
			
			if(c.getCliente().getIdentificador().compareTo(aux) == 0) {
				return(c);
			}
		}
		
		return(null);
	}
	
	public static int identificadores() {
		Random gerador = new Random();
		return(gerador.nextInt(1000000));
	}
	
	public static Pessoa cadastrarCliente() {
		String[] op = {"Pessoa Física", "Pessoa Jurídica"};
		
		int tipo = jOptions.optionDialog(op, "Tipo", "Cadastro de conta");
		
		switch(tipo) {
			case 0:
				String[] ops = {"Femino", "Masculino", "Não binário"};
				
				PessoaFisica pf = new PessoaFisica(
						operacoesAuxiliares.identificadores(),
						jOptions.inputDialog("Nome", "Cadastro de Cliente"),
						jOptions.inputDialog("Endereço", "Cadastro de Cliente"),
						operacoesAuxiliares.obrigaTamanho(11, "CPF", jOptions.inputDialog("CPF", "Cadastro de Cliente")),
						jOptions.inputDialog("Data de Nascimento", "Cadastro de Cliente"),
						jOptions.inputOpcoes(ops,"Genêro", "Cadastro de Cliente"));
				return(pf);
			case 1:
				PessoaJuridica pj = new PessoaJuridica(
						operacoesAuxiliares.identificadores(),
						jOptions.inputDialog("Nome", "Cadastro de Cliente"),
						jOptions.inputDialog("Endereço", "Cadastro de Cliente"),
						operacoesAuxiliares.obrigaTamanho(14, "CPF", jOptions.inputDialog("CNPJ", "Cadastro de Cliente")),
						jOptions.inputDialog("Atividade", "Cadastro de Cliente"));
				return(pj);
		}
		
		return(null);
	}
	
	public static String obrigaTamanho(int tamanho, String text, String testar) {
		while(testar.length() < tamanho) {
			testar = jOptions.inputDialog("Digite novamente o " + text, "Corrigir");
		}
		
		return(testar);
	}
	
	public static double saldoInicial() {
		DecimalFormat formato = new DecimalFormat("#.###");
		double valor = 0;
		String inicial;
		
		do {
			inicial = jOptions.inputDialog("Depósito inicial de R$", "Cadastro de conta");
			inicial = inicial.replaceAll(",", ".");
			valor = Double.parseDouble(inicial);
			
			if(!inicial.contains(".")) {
				valor = Double.valueOf(formato.format(valor));
			}
		}while(valor < 50.000);
		
		return(valor);
	}
}

class jOptions{
	public static String inputOpcoes(String[] ops, String text1, String text2) {
		return((String)(JOptionPane.showInputDialog(null, text1, text2, JOptionPane.PLAIN_MESSAGE, null,
				ops, ops[0])));
	}
	
	public static String inputDialog(String text1, String text2) {
		return(JOptionPane.showInputDialog(null,text1, 
	            text2,JOptionPane.PLAIN_MESSAGE));
	}
	
	public static void dialogError(String text1, String text2) {
		JOptionPane.showMessageDialog(null, text1,
				text2, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void dialogInformation(String text1, String text2) {
		JOptionPane.showMessageDialog(null,  text1,
				text2, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void dialogWarning(String text1, String text2) {
		JOptionPane.showMessageDialog(null, text1,
				text2, JOptionPane.WARNING_MESSAGE);
	}
	
	public static int optionDialog(String[] ops, String text1, String text2) {
		return(JOptionPane.showOptionDialog(null, text1, 
                text2, 0, JOptionPane.PLAIN_MESSAGE, null, ops, ops[0]));
	}
}

public class Principal {
	public static void main(String[] args) {
		Menu.menu();
	}
}