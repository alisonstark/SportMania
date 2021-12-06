package Comunicacao;

import Cliente.Cliente;
import Excecoes.Cliente.*;
import Excecoes.TabelaException;

import java.io.*;

public class Clientela extends Tabela<String, Cliente> implements ArmazenaObjetos {

    private String bancoDeDados;

    public Clientela(String caminho) {
        this.bancoDeDados = caminho;
    }

    public void setBancoDeDados(String caminho) {
        bancoDeDados = caminho;
    }

    /**
     * Le os objetos {@code Cliente}s do banco de dados, do arquivo "clientela.ser" e armazena todos em uma
     * {@code Hashtable}, em que a chave eh o cpf do cliente.
     *
     * @throws IOException Caso ocorra erro na leitura do arquivo
     * @throws ClassNotFoundException
     * @throws TabelaException
     */
    public void carregarObjetos() throws IOException, ClassNotFoundException, TabelaException {
        FileInputStream fis = new FileInputStream(bancoDeDados);
        ObjectInputStream ois = new ObjectInputStream(fis);

        while (fis.available() > 0) {
            Cliente cliente = (Cliente) ois.readObject();
            adicionar(cliente);
        }
        ois.close();
    }

    /**
     * Sobreescreve o arquivo do banco de dados com todos os clientes, atualizando-o.
     *
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void atualizarObjetos() throws IOException {
        FileOutputStream fos = new FileOutputStream(bancoDeDados);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Cliente cliente : retornarValores()) {
            oos.writeObject(cliente);
        }
        oos.close();
    }

    /**
     * Cadastra o cliente.
     *
     * @param nome Nome do cliente
     * @param cpf Cpf do cliente
     * @throws CpfJaCadastradoException Se o cpf já estiver cadastrado
     */
    public void cadastrarCliente(String nome, String cpf) throws TabelaException {
        Cliente cliente = new Cliente(nome, cpf, true);
        if (contem(cpf))
            throw new CpfJaCadastradoException(cpf);
        else
            adicionar(cliente);
    }

    @Override
    protected void adicionar(Cliente cliente) throws TabelaException {
        String cpf = cliente.getCpf();
        if (contem(cpf))
            throw new CpfJaCadastradoException(cpf);
        tabela.put(cpf, cliente);
    }

    @Override
    public void remover(String cpf) throws TabelaException {
        if (!contem(cpf))
            throw new CpfNaoCadastradoException(cpf);
        else
            tabela.remove(cpf);
    }

    @Override
    public Cliente procurar(String cpf) throws TabelaException {
        if (!contem(cpf))
            throw new CpfNaoCadastradoException(cpf);
        else
            return super.procurar(cpf);
    }
}
