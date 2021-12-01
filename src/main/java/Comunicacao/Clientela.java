package Comunicacao;

import Cliente.Cliente;
import Excecoes.Cliente.*;

import java.io.*;
import java.util.Hashtable;
import java.util.Set;

public class Clientela {

    private String bancoDeDados;
    private final Hashtable<String, Cliente> clientela;

    public Clientela(String caminho) {
        this.bancoDeDados = caminho;
        this.clientela = new Hashtable<>();
    }

    public void setBancoDeDados(String caminho) {
        bancoDeDados = caminho;
    }

    public void limparClientela() {
        clientela.clear();
    }

    public boolean contemCliente(String cpf) {
        return clientela.containsKey(cpf);
    }

    public Cliente procurarCliente(String cpf) throws CpfNaoCadastradoException {
        if (!contemCliente(cpf)) {
            throw new CpfNaoCadastradoException(cpf);
        }
        return clientela.get(cpf);
    }

    public void removerCliente(String cpf) {
        clientela.remove(cpf);
    }

    public Set<String> retornarCpfsCadastrados() {
        return clientela.keySet();
    }

    public Hashtable<String, Cliente> clonarClientela() {
        return (Hashtable<String, Cliente>) clientela.clone();
    }

    /**
     * Le os objetos {@code Cliente}s do banco de dados, do arquivo "clientela.ser" e armazena todos em uma
     * {@code Hashtable}, em que a chave eh o cpf do cliente.
     *
     * @throws IOException Caso ocorra erro na leitura do arquivo
     */
    public void carregarClientela() throws IOException {
        FileInputStream fis = new FileInputStream(bancoDeDados);

        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Cliente cliente = (Cliente) ois.readObject();
                clientela.put(cliente.getCpf(), cliente);
            }
        } catch (ClassNotFoundException e) {
            // TODO exibir msg de erro
            e.printStackTrace();
        }
    }

    /**
     * Sobreescreve o arquivo do banco de dados com todos os clientes, atualizando-o.
     *
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void atualizarClientela() throws IOException {
        FileOutputStream fos = new FileOutputStream(bancoDeDados);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Cliente cliente : clientela.values()) {
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
    public void cadastrarCliente(String nome, String cpf) throws CpfJaCadastradoException {
        Cliente cliente = new Cliente(nome, cpf, true);
        if (contemCliente(cpf)) {
            throw new CpfJaCadastradoException(cpf);
        }
        clientela.put(cpf, cliente);
    }

}
