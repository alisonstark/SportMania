package Produto;

import Excecoes.Produto.EstoqueInsuficienteException;

import java.util.ArrayList;

public class Produto {

    private final Integer id;
    private final String nome;
    private float preco;
    private int estoque;
    private boolean emEstoque;
    private final Categoria categoria;
    protected ArrayList<String> cadastroProdutosVendidos;

    public Produto(String nome, float preco, int estoque, Categoria categoria) {
        this.id = hashCode();
        this.nome = nome;
        this.preco = preco;
        this.estoque = Math.max(estoque, 0);
        this.categoria = categoria;
        this.emEstoque = estoque != 0;

        this.cadastroProdutosVendidos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
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
            throw new EstoqueInsuficienteException(id);
        }
        estoque -= quantidade;
    }

    public ArrayList<String> getCadastroProdutosVendidos() {
        return cadastroProdutosVendidos;
    }
    public static Categoria identificaCategoriaProduto(String categoria){
        return switch (categoria) {
            case "roupa" -> Categoria.ROUPA;
            case "calcado" -> Categoria.CALCADO;
            case "acessorio" -> Categoria.ACESSORIO;
            default -> Categoria.EQUIPAMENTO;
        };
    }
}
