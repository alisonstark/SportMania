package Produto;

import Excecoes.Estoque.EstoqueInsuficienteException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Produto implements Serializable {

    public static String separador = "::";

    private final String identificador;
    private final String nome;
    private final Categoria categoria;
    private float preco;
    private int estoque;
    private boolean emEstoque;
    protected ArrayList<String> cadastroProdutosVendidos;

    public Produto(String nome, float preco, int estoque, Categoria categoria) {
        this.identificador = categoria + "::" + nome;
        this.nome = nome;
        this.preco = preco;
        this.estoque = Math.max(estoque, 0);
        this.categoria = categoria;
        this.emEstoque = estoque != 0;

        this.cadastroProdutosVendidos = new ArrayList<>();
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

    public void setEstoque(short adicional) {
        this.estoque += adicional;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public boolean isEmEstoque() {
        return emEstoque;
    }

    public void setEmEstoque(boolean emEstoque) {
        this.emEstoque = emEstoque;
    }

    public void retirarN(int quantidade) throws EstoqueInsuficienteException {
        if (estoque < quantidade) {
            throw new EstoqueInsuficienteException(identificador);
        }
        estoque -= quantidade;
    }

    public ArrayList<String> getCadastroProdutosVendidos() {
        return cadastroProdutosVendidos;
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
