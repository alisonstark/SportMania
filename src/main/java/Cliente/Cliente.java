package Cliente;

import java.io.*;

public class Cliente implements Serializable {
    private String nome;
    private final String cpf;
    private boolean ativo;

    public Cliente(String nome, String cpf, boolean ativo) {
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
        return cpf.equals(cliente.cpf);
    }

}