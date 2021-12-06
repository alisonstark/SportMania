package Excecoes.Estoque;

public class ProdutoInexistenteException extends EstoqueException {

    public ProdutoInexistenteException(String identificador) {
        super(identificador, "nao existe");
    }

}
