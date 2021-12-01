package Excecoes.Produto;

public abstract class ProdutoException extends Exception {

    public ProdutoException(Integer id, String msg) {
        super("Produto #" + id + " " + msg + "!");
    }

}
