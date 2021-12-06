package Excecoes.Estoque;

public class ProdutoJaExistenteException extends EstoqueException {

    public ProdutoJaExistenteException(String identificador) {
        super(identificador, "produto ja existe");
    }

}