public class Cliente {
    String nome;
    String cpf;
    boolean ativo;

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

}
