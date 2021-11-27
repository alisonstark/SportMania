package Cliente;

import Excecoes.CadastroException;

import java.io.*;
import java.util.Hashtable;

public class Clientes {

    private static String bancoDeDados = "/BancoDeDados/clientes.ser";
    public static Hashtable<String, Cliente> clientela = new Hashtable<>();

    public static void setBancoDeDados(String caminho) {
        bancoDeDados = caminho;
    }

    public static void limparClientela() {
        clientela.clear();
    }

    /**
     * Le os objetos {@code Cliente}s do banco de dados, do arquivo "clientela.ser" e armazena todos em uma
     * {@code Hashtable}, em que a chave eh o cpf do cliente.
     *
     * @throws IOException Caso ocorra erro na leitura do arquivo
     */
    public static void carregarClientela() throws IOException {
        FileInputStream fis = new FileInputStream(bancoDeDados);

        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Cliente cliente = (Cliente) ois.readObject();
                clientela.put(cliente.getCpf(), cliente);
            }
        } catch (ClassNotFoundException e) {
            // TODO Definir o que deve ser feito se ocorrer erro
            e.printStackTrace();
        }
    }

    /**
     * Sobreescreve o arquivo do banco de dados com todos os clientes, atualizando-o.
     *
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public static void atualizarClientela() throws IOException {
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
     * @throws CadastroException Se o cpf já estiver cadastrado
     */
    public static void cadastrarCliente(String nome, String cpf) throws CadastroException {
        Cliente cliente = new Cliente(nome, cpf, true);
        if (clientela.containsKey(cpf)) {
            throw new CadastroException("Cpf ja cadastrado");
        }
        clientela.put(cpf, cliente);
    }

}
