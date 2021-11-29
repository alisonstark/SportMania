package Produto;

import java.util.ArrayList;

public class Produto {

    private final Integer id;
    private final String nome;
    private float preco;
    private int estoque;
    private boolean emEstoque;
    private final Categoria categoria;
    // TODO adicionar atributo ativo (exclusão do estoque / temporariamente zerado no estoque)
    protected ArrayList<String> cadastroProdutosVendidos;         // String: data, produto, quant, valor, cliente

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

    public ArrayList<String> getCadastroProdutosVendidos() {
        return cadastroProdutosVendidos;
    }

}
