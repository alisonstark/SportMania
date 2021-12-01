package Excecoes.Cliente;

public class CpfNaoCadastradoException extends ClienteException {

    public CpfNaoCadastradoException(String cpf) {
        // TODO melhorar mensagem de erro
        super(cpf);
    }

}
