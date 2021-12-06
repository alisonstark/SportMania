package Excecoes.Clientela;


import Excecoes.TabelaException;

public abstract class ClientelaException extends TabelaException {

    public ClientelaException(String cpf, String msg) {
        super("Cliente #" + cpf + " " + msg + "!");
    }

}
