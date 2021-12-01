package Excecoes.Produto;

public class ProdutoJaExistenteException extends ProdutoException {

    public ProdutoJaExistenteException(Integer id) {
        super(id, "produto ja existe");
    }

}