import java.util.ArrayList;

public class Cliente {
    String nome;
    String cpf;
    boolean ativo;  // true se o cliente estiver ativo (para listagem no cadastro de clientes)
    ArrayList<String> registroCompras;

    public Cliente(String nome, String cpf, boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
        this.registroCompras = geraCadastroCliente(nome, cpf, true);
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

    public ArrayList<String> geraCadastroCliente(String nome, String cpf, boolean ativo){
        // TODO escreve um txt para o cliente, contendo os dados de suas compras futuras
        ArrayList<String> cabecalhoCadastroCliente = new ArrayList<>();
        cabecalhoCadastroCliente.add(getNome() + " .......... " + getCpf() + " .......... ");

        return cabecalhoCadastroCliente;
    }

    public ArrayList<String> getRegistroCompras() {
        return registroCompras;
    }
}
