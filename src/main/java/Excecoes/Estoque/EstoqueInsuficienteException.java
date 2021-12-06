package Excecoes.Estoque;

public class EstoqueInsuficienteException extends EstoqueException {

    public EstoqueInsuficienteException(String identificador) {
        super(identificador, "nao tem produtos o suficiente no estoque");
    }

}
