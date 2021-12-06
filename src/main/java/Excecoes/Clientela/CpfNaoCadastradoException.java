package Excecoes.Clientela;

public class CpfNaoCadastradoException extends ClientelaException {

    public CpfNaoCadastradoException(String cpf) {
        super(cpf, "nao existe no cadastro");
    }

}
