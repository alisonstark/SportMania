package Excecoes.Estoque;

import Excecoes.TabelaException;

public abstract class EstoqueException extends TabelaException {

    public EstoqueException(String identificador, String msg) {
        super("Produto #" + identificador + " " + msg + "!");
    }

}
