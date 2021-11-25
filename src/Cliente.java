import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cliente {
    String nome;
    String cpf;
    boolean ativo;  // true se o cliente estiver ativo (para listagem no cadastro de clientes)

    static ArrayList<Cliente> clientela = new ArrayList<>();
    ArrayList<String> registroCompras;

    public Cliente(String nome, String cpf, boolean ativo){
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
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


    public static ArrayList<String> geraCadastroCliente(String nome, String cpf, boolean ativo) throws IOException {
        // TODO escreve um txt para o cliente, contendo os dados de suas compras futuras
        // Escrever num txt
        ArrayList<String> cadastroClientes = new ArrayList<>();

        BufferedWriter writer = new BufferedWriter(new FileWriter("cadastroClientes", true));

        // TODO faça aí, namoral (arquivo txt está sobrescrevendo ou só adicionando)
        writer.write(nome + " " + cpf + " " + ativo + "\n");
        cadastroClientes.add(nome + " " + cpf + " " + ativo);

        clientela.add(new Cliente(nome, cpf, true));

        writer.close();
        return cadastroClientes;
    }

    public ArrayList<String> getRegistroCompras() {
        return registroCompras;
    }
}
