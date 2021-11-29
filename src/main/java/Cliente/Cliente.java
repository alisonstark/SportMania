package Cliente;

import java.io.*;
import java.util.ArrayList;

public class Cliente implements Serializable {
    private String nome;
    private final String cpf;
    private boolean ativo;  // true se o cliente estiver ativo (para listagem no cadastro de clientes)
    // TODO melhorar o registro de compras
    private ArrayList<String> registroCompras; // data da compra + ... + id/nome

    public Cliente(String nome, String cpf, boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
        this.registroCompras = new ArrayList<>();
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