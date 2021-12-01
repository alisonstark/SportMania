package Excecoes.Produto;

public class EstoqueInsuficienteException extends ProdutoException {

    public EstoqueInsuficienteException(Integer id) {
        super(id, "nao tem produtos o suficiente no estoque");
    }

}
