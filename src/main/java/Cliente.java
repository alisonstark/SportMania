import Excecoes.CadastroException;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Cliente implements Serializable {
    private String nome;
    private final String cpf;
    private boolean ativo;  // true se o cliente estiver ativo (para listagem no cadastro de clientes)
    // TODO implementar o registro de compras
    private ArrayList<String> registroCompras;

    private static String bancoDeDados = "/BancoDeDados/clientela.ser";
    public static Hashtable<String, Cliente> clientela = new Hashtable<>();

    public Cliente(String nome, String cpf, boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
        // TODO implementar o registro de compras
        this.registroCompras = new ArrayList<String>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtividade(boolean atividade) {
        this.ativo = atividade;
    }

    public ArrayList<String> getRegistroCompras() {
        return registroCompras;
    }
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
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            while (fis.available() > 0) {
                Cliente cliente = (Cliente) ois.readObject();
                clientela.put(cliente.getCpf(), cliente);
            }
        } catch (ClassNotFoundException e) {
            // TODO Definir o que deve ser feito se ocorrer erro
            e.printStackTrace();
        } finally {
            ois.close();
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

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", ativo=" + ativo +
                '}';
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }
        if (outro == null || getClass() != outro.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) outro;
        return ativo == cliente.ativo &&
                nome.equals(cliente.nome) &&
                cpf.equals(cliente.cpf) &&
                registroCompras.equals(cliente.getRegistroCompras());
    }

}