package Excecoes.Cliente;

public class CpfJaCadastradoException extends ClienteException {

    public CpfJaCadastradoException(String cpf) {
        // TODO melhorar mensagem de erro
        super(cpf);
    }

}
