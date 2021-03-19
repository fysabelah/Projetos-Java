import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

class Transacoes{
	static List<Pessoa> clientes = new ArrayList();
	
	public static void saque() {
		Pessoa cliente = operacoesAuxiliares.buscarCliente(clientes, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			jOptions.dialogError("Cliente não cadastrado", "Saque");
		}
		else {
			Conta conta = operacoesAuxiliares.selecionaContaCliente(cliente, "Selecione a conta");
			
			if(conta == null) {
				jOptions.dialogError("Cliente não possui conta", "Saque");
			}
			else {
				double valor = Double.parseDouble(jOptions.inputDialog("Informe o valor do saque", "Saque"));
				
				if(conta.sacar(valor)) {
					jOptions.dialogInformation("Saque realizado com sucesso\nO saldo atual é de R$ " + conta.getSaldo(), "Saque");
				}
				else {
					jOptions.dialogError("Conta sem saldo", "Saque");
				}
			}
		}
		
		Menu.menu();
	}
	
	public static void depositar() {
		Pessoa cliente = operacoesAuxiliares.buscarCliente(clientes, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			jOptions.dialogError("Cliente não possui conta", "Depósito");
		}
		else {
			Conta conta = operacoesAuxiliares.selecionaContaCliente(cliente, "Selecione a conta");
			
			if(conta == null) {
				jOptions.dialogError("Cliente não possui conta", "Depósito");
			}
			else {
				double valor = Double.parseDouble(jOptions.inputDialog("Informe o valor do depósito", "Depósito"));
				
				conta.depositar(valor);
				
				jOptions.dialogInformation("Depósito realizado com sucesso\nO saldo atual é de R$ " + conta.getSaldo(), "Depósito");
			}
		}
		
		Menu.menu();
	}

	public static void transferir() {
		Pessoa clienteS = operacoesAuxiliares.buscarCliente(clientes, "Informe o CPF/CNPJ do cliente A SACAR");
		double valor = Double.parseDouble(jOptions.inputDialog("Informe o valor do saque", "Saque"));
		Pessoa clienteR = operacoesAuxiliares.buscarCliente(clientes, "Informe o CPF/CNPJ do cliente A RECEBER");
		
		if(clienteS == null || clienteR == null) {
			jOptions.dialogError("Transação não realizada. Verifique os dados.", "Transferência");
		}
		else {
			Conta contaS = operacoesAuxiliares.selecionaContaCliente(clienteS, "Selecione a conta A SACAR");
			Conta contaR = operacoesAuxiliares.selecionaContaCliente(clienteR, "Selecione a conta A RECEBER");
			
			if(contaS == null || contaR == null) {
				jOptions.dialogError("Transação não realizada. Verifique os dados.", "Transferência");
			}
			else {
				if(contaS.transferir(contaR, valor)) {
					jOptions.dialogInformation("Transferência realizado com sucesso", "Transferência");
				}
				else {
					jOptions.dialogInformation("Conta A SACAR sem saldo disponível", "Transferência");
				}
			}
		}
		
		Menu.menu();
	}
	
	public static void visualizar() {
		Pessoa cliente = operacoesAuxiliares.buscarCliente(clientes, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			jOptions.dialogError("Cliente não cadastrado", "Dados");
		}
		else {
			jOptions.dialogInformation(cliente.toString(), "Dados");
		}
		
		Menu.menu();
	}
	
	public static void contaAbrir() {
		Pessoa cliente = operacoesAuxiliares.buscarCliente(clientes, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			jOptions.dialogError("Cliente não possui cadastrado", "Cadastro de Conta");
		}
		else {
			String[] op1 = {"Especial", "Poupança"};
			
			int tipo = jOptions.optionDialog(op1, "Cadastro de conta", "Tipo");
			
			switch(tipo) {
				case 0:
					ContaEspecial ce = new ContaEspecial(operacoesAuxiliares.identificadores(),
							0,
							1000);
					cliente.adicionarConta(ce);
					break;
				case 1:
					ContaPoupanca cp = new ContaPoupanca(
							operacoesAuxiliares.identificadores(),
							operacoesAuxiliares.saldoInicial(),
							0.002);
					cliente.adicionarConta(cp);
					break;
			}
		}
		
		Menu.menu();
    }
		
	public static void cadastrarCliente() {
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
				clientes.add(clientes.size(), pf);
				break;
			case 1:
				PessoaJuridica pj = new PessoaJuridica(
						operacoesAuxiliares.identificadores(),
						jOptions.inputDialog("Nome", "Cadastro de Cliente"),
						jOptions.inputDialog("Endereço", "Cadastro de Cliente"),
						operacoesAuxiliares.obrigaTamanho(14, "CPF", jOptions.inputDialog("CNPJ", "Cadastro de Cliente")),
						jOptions.inputDialog("Atividade", "Cadastro de Cliente"));
				clientes.add(clientes.size(), pj);
				break;
		}
		
		Menu.menu();
	}
	
	public static void alterarDados() {
		Pessoa cliente = operacoesAuxiliares.buscarCliente(clientes, "Informe o CPF/CNPJ");
		
		if(cliente == null) {
			jOptions.dialogError("Cliente não encontrado", "Alterar Dados");
		}
		else {
			cliente.setEndereco(jOptions.inputDialog("Endereço", "Alterar Dados"));
		}
		
		Menu.menu();
	}
	
	public static void relatorios() {
		String[] op = {"Saldo das Contas", "Total das Contas"};
		
		int tipo = jOptions.optionDialog(op, "Tipo", "Gerar relatório");
		
		switch(tipo) {
			case 0:
				operacoesAuxiliares.relatorioSaldoClientes(clientes);
				break;
			case 1:
				operacoesAuxiliares.relatorioTotalClientes(clientes);
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
	    	default:
	    		operacoesAuxiliares.confirmaSair();
	    		break;
	    }
    }
	
	public static void  menuConta(){
       String[] op1 = {"Saque", "Depósito", "Transferência", "Abrir Conta","Relatórios", "Voltar", "Sair"};
        
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
        		Transacoes.contaAbrir();
        		break;
        	case 4:
        		Transacoes.relatorios();
        		break;
        	case 5:
        		Menu.menu();
        		break;
        	default:
        		operacoesAuxiliares.confirmaSair();
        		break;
        
        }
    }
	
	public static void menuCliente(){
        String[] op1 = {"Cadastrar", "Visualizar", "Alterar Dados", "Voltar", "Sair"};
        
        int input = jOptions.optionDialog(op1, "Escolha a opção desejada", "Menu de Clientes");
        
        switch(input) {
	    	case 0:
	    		Transacoes.cadastrarCliente();;
	    		break;
	    	case 1:
	    		Transacoes.visualizar();
	    		break;
	    	case 2:
	    		Transacoes.alterarDados();
	    		break;
	    	case 3: 
	    		Menu.menu();
	    		break;
	    	default:
	    		operacoesAuxiliares.confirmaSair();
	    		break;
	    
	    }
    }
}

class operacoesAuxiliares{
	public static void relatorioTotalClientes(List<Pessoa> clientes) {
		try {
			File arquivo = new File("SaldoCliente.txt");
			
			if(arquivo.exists()) {
				arquivo.delete();
				arquivo.createNewFile();
			}
			
			FileWriter arquivoSaldo = new FileWriter(arquivo);
			PrintWriter gravarArq = new PrintWriter(arquivoSaldo);
			
			gravarArq.println("-------------------- Relatório Saldo Total/Cliente --------------------");
			
			double saldo;
			
			for(int i = 0; i < clientes.size(); i++) {
				List<Conta> contaCliente = clientes.get(i).contas;
				saldo = 0;
				
				for(int j = 0; j < contaCliente.size(); j++) {
					saldo += contaCliente.get(j).getSaldo();
				}
				
				gravarArq.println(clientes.get(i).getIdentificador() +" - Saldo R$ " + saldo);
			}
			
			arquivoSaldo.close();
			gravarArq.close();
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a geração do relatório.", "Relatório", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
		}
	}
	
	public static void relatorioSaldoClientes(List<Pessoa> clientes) {
		try {
			File arquivo = new File("SaldoContas.txt");
			
			if(arquivo.exists()) {
				arquivo.delete();
				arquivo.createNewFile();
			}
			
			FileWriter arquivoSaldo = new FileWriter(arquivo);
			PrintWriter gravarArq = new PrintWriter(arquivoSaldo);
			
			gravarArq.println("-------------------- Relatório Cliente Conta/Saldo --------------------");
			
			for(int i = 0; i < clientes.size(); i++) {
				List<Conta> contaCliente = clientes.get(i).contas;
				
				gravarArq.println(clientes.get(i).getIdentificador());
				
				for(int j = 0; j < contaCliente.size(); j++) {
					gravarArq.println(contaCliente.get(j).getNrConta() +": " + contaCliente.get(j).getSaldo());
				}
			}
			
			arquivoSaldo.close();
			gravarArq.close();
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a geração do relatório.", "Relatório", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
		}
	}
	
	public static void confirmaSair() {
		int input = JOptionPane.showConfirmDialog(null, "Deseja continuar?", 
                "Sair", JOptionPane.YES_NO_OPTION);

        if(input == 0) System.exit(0);
        else Menu.menu();
	}
	
	public static Pessoa buscarCliente(List<Pessoa> clientes, String texto) {
		int tamanho = clientes.size();
		String aux = jOptions.inputDialog(texto, "Buscar cliente");
		
		for(int i = 0; i < tamanho; i++) {
			if(clientes.get(i).getIdentificador().compareTo(aux) == 0) {
				return(clientes.get(i));
			}
		}
		
		return(null);
	}
	
	public static String[] convertendoContaString(Pessoa cliente) {
		//Considero que já foi verificado se tem conta
		int tamanho = cliente.getContas().size();
		String[] retornar = new String[tamanho];
		
		for(int i = 0; i < cliente.getContas().size(); i++) {
			retornar[i] = Integer.toString(cliente.getContas().get(i).getNrConta());
		}
		
		return(retornar);
	}
	
	public static Conta selecionaContaCliente(Pessoa cliente, String text) {
		if(cliente.getContas().size() == 0) {
			//Cliente não tem conta cadastrada
			return(null);
		}
		
		String[] lista = operacoesAuxiliares.convertendoContaString(cliente);
		
		String selecionado = jOptions.inputOpcoes(lista,"Contas", text);

		for(int i = 0; i < lista.length; i++) {
			if(lista[i].compareTo(selecionado) == 0) {
				return(cliente.getContas().get(i));
			}
		}
		
		//Aqui tem que vim um try e cath porque se passou no if 1 tem conta
		return(null);
	}
	
	public static int identificadores() {
		Random gerador = new Random();
		return(gerador.nextInt(1000000));
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