import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JOptionPane;

public class AuxiliarTransacoes {
	String aux = null;
	int contadorId = 0;
	
	public void confirmaSair() {
		Transacoes t = new Transacoes();
		
		int input = JOptionPane.showConfirmDialog(null, "Deseja continuar?", 
                "Sair", JOptionPane.YES_NO_OPTION);

        if(input == 0) System.exit(0);
        else t.menu();
	}
	
	public String scpf() {
		aux = JOptionPane.showInputDialog(null, 
                "CPF","Cadastro de Cliente", JOptionPane.PLAIN_MESSAGE);
	    while(aux.length() != 11){
	        aux = JOptionPane.showInputDialog(null,"CPF", 
	            "Cadastro de Cliente",JOptionPane.PLAIN_MESSAGE);
	    }
	    
	    return(aux);
	}
	
	public String scnpj() {
		aux = JOptionPane.showInputDialog(null, 
                "CNPJ","Cadastro de Cliente", JOptionPane.PLAIN_MESSAGE);
        while(aux.length() != 14){
            aux = JOptionPane.showInputDialog(null,"CNPJ", 
                "CNPJ",JOptionPane.PLAIN_MESSAGE);
        }
        
        return (aux);
	}
	
	public String nome() {
		return(JOptionPane.showInputDialog(null, "Nome", "Cadastro de Cliente",JOptionPane.PLAIN_MESSAGE));
	}
	
	public String endereco() {
		return(JOptionPane.showInputDialog(null, "Endereço","Cadastro de Cliente",JOptionPane.PLAIN_MESSAGE));
	}
	
	public String dataNascimento() {
		return(JOptionPane.showInputDialog(null, "Data de Nascimento","Cadastro de Cliente",JOptionPane.PLAIN_MESSAGE));
	}
	
	public String genero() {
		String[] op = {"Femino", "Masculino", "Não binário"};
		
		return (String) (JOptionPane.showInputDialog(null, "Genêro","Cadastro de Cliente", JOptionPane.PLAIN_MESSAGE, null,
		op,op[0]));
	}
	
	public String atividade() {
		return(JOptionPane.showInputDialog(null, "Atividade","Cadastro de Cliente",JOptionPane.PLAIN_MESSAGE));
	}
	
	public Pessoa cadastrarCliente() {
		String[] op1 = {"Pessoa Física", "Pessoa Jurídica"};
		int tipoPessoa = JOptionPane.showOptionDialog(null, 
                "Tipo", "Cadastro de Cliente",0, JOptionPane.PLAIN_MESSAGE, null, op1, op1[0]);
		
		switch(tipoPessoa) {
	    	case 0:
	           PessoaFisica p = new PessoaFisica(contadorId, nome(), endereco(), scpf(), dataNascimento(), genero());
	           contadorId += 1;
	           return(p);
	    	case 1:
	    		PessoaJuridica p1 = new PessoaJuridica(contadorId, nome(), endereco(), scnpj(), atividade());
	    		contadorId += 1;
	    		return(p1);
	    }
		
		return(null);
	}
	
	public double saldoInicial() {
		double valor;
		String inicial;
		DecimalFormat formato = new DecimalFormat("#.##"); 
		
		do {
			inicial = JOptionPane.showInputDialog(null, "Depósito inicial de R$", "Cadastro de Conta",JOptionPane.PLAIN_MESSAGE);
			inicial = inicial.replaceAll(",", ".");
			valor = Double.parseDouble(inicial);
			
			if(!inicial.contains(".")) {
				valor = Double.valueOf(formato.format(valor));
			}
		}while(valor < 50.0);
			
		
		return(valor);
	}
	
	public Conta buscarCliente(List<Conta> contas, String texto) {
		String aux = JOptionPane.showInputDialog(null,texto, 
	            "Buscar cliente",JOptionPane.PLAIN_MESSAGE);
		
		for(int i = 0; i < contas.size(); i++) {
			Conta c = contas.get(i);
			
			if(c.getCliente().getIdentificador().compareTo(aux) == 0) {
				return(c);
			}
		}
		
		return(null);
	}
}
