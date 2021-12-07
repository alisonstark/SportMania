package Cliente;

import Excecoes.ValidacaoException;
import Validadores.ValidaCpf;
import Validadores.ValidaEmail;
import Validadores.ValidaTelefone;

import java.io.*;

public class Cliente implements Serializable {
    private String nome;
    private final String cpf;
    private String telefone;
    private String email;
    private boolean ativo;

    public Cliente(String nome, String cpf, String telefone, String email, boolean ativo) throws ValidacaoException {
        ValidaCpf validaCpf = new ValidaCpf(cpf);

        this.nome = nome.trim();
        this.cpf = validaCpf.ehValido();
        setTelefone(telefone);
        this.email = null;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws ValidacaoException {
        ValidaTelefone validaTelefone = new ValidaTelefone(telefone);
        this.telefone = validaTelefone.ehValido();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidacaoException {
        ValidaEmail validaEmail = new ValidaEmail(email);
        this.email = validaEmail.ehValido();
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