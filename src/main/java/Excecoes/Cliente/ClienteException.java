package Excecoes.Cliente;


import Excecoes.TabelaException;

public abstract class ClienteException extends TabelaException {

    public ClienteException(String msg) {
        // TODO melhorar mensagem de erro
        super(msg);
    }

}
