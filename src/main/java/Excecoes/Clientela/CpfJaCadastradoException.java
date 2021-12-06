package Excecoes.Clientela;

public class CpfJaCadastradoException extends ClientelaException {

    public CpfJaCadastradoException(String cpf) {
        super(cpf, "ja foi cadastrado");
    }

}
