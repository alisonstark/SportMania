package Excecoes.Produto;

public class ProdutoInexistenteException extends ProdutoException {

    public ProdutoInexistenteException(Integer id) {
        super(id, "nao existe");
    }

}
