package Produto;

import Excecoes.Estoque.EstoqueInsuficienteException;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {

    public static String separador = "::";

    private final String identificador;
    private final String nome;
    private final Categoria categoria;
    private float preco;
    private int estoque;

    public Produto(String nome, float preco, int estoque, Categoria categoria) {
        this.identificador = categoria + separador + nome;
        this.nome = nome;
        this.preco = preco;
        this.estoque = Math.max(estoque, 0);
        this.categoria = categoria;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public boolean temEstoque() {
        return estoque > 0;
    }

    /**
     * Retira uma certa quantidade do produto do estoque.
     *
     * @param quantidade Quantidade a ser retirada
     * @throws EstoqueInsuficienteException Se nao houver estoque o suficiente
     */
    public void retirarN(int quantidade) throws EstoqueInsuficienteException {
        if (estoque < quantidade)
            throw new EstoqueInsuficienteException(identificador);
        else
            estoque -= quantidade;
    }

    /**
     * Adiciona uma certa quantidade do produto do estoque.
     *
     * @param quantidade Quantidade a ser adicionada
     */
    public void adicionarN(int quantidade) {
        estoque += quantidade;
    }

    @Override
    public String toString() {
        return categoria + " " + nome + " " + estoque;
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro)
            return true;
        if (outro == null || getClass() != outro.getClass())
            return false;
        Produto produto = (Produto) outro;
        return Objects.equals(identificador, produto.identificador);
    }

}
