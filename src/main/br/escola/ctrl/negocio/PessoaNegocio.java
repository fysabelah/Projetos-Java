package main.br.escola.ctrl.negocio;

import main.br.escola.ctrl.exception.PessoaException;
import main.br.escola.model.dao.PessoaDAO;
import main.br.escola.model.entities.Pessoa;

import java.util.Calendar;
import java.util.List;

public class PessoaNegocio {

    PessoaDAO dao = new PessoaDAO();

    public Pessoa inserir(Pessoa pessoa) throws PessoaException {
        this.validarPessoa(pessoa);

        List<Pessoa> cadastrada = dao.buscarPorCpf(pessoa.getCpf());

        if (cadastrada != null && !cadastrada.isEmpty()) {
            throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF!");
        }

        return dao.inserir(pessoa);
    }

    public List<Pessoa> buscaTodos() throws PessoaException {
        return dao.buscaTodos();
    }

    public Pessoa buscaPorId(Integer id) throws PessoaException {
        return dao.buscaPorId(id);
    }

    public Pessoa alterar(Pessoa pessoa) throws PessoaException {
        this.validarPessoa(pessoa);

        return dao.alterar(pessoa);
    }

    public void excluir(Integer id) throws PessoaException {
        dao.excluir(id);
    }

    private void validarPessoa(Pessoa pessoa) throws PessoaException {
        List<Pessoa> pessoas = dao.buscarPorCpf(pessoa.getCpf());

        if (!pessoas.isEmpty()) {
            throw new PessoaException("Pessoa já cadastrada");
        }

        if (pessoa.getNmPessoa() == null || pessoa.getNmPessoa().trim().isEmpty()) {
            throw new PessoaException("Nome da pessoa é obrigatório.");
        }

        if (pessoa.getCpf().toString().length() != 11) {
            throw new PessoaException("CPF deve ter pelo menos 11 algarismos.");
        }

        Calendar calHoje = Calendar.getInstance();
        Calendar calNascimento = Calendar.getInstance();
        calNascimento.setTime(pessoa.getDtNascimento());

        if (calHoje.after(calNascimento)) {
            throw new PessoaException("Data de Nascimento deve ser maior que hoje.");
        }

    }
}
