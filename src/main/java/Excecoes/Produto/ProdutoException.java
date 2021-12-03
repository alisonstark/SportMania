package Excecoes.Produto;

import Excecoes.TabelaException;

public abstract class ProdutoException extends TabelaException {

    public ProdutoException(Integer id, String msg) {
        super("Produto #" + id + " " + msg + "!");
    }

}
