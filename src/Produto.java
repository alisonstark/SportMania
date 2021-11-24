import java.util.ArrayList;

public class Produto {
    private final int id;
    private String nome;
    private float preco;
    private short estoque;
    private boolean emEstoque;

    private Categoria categoria;

    private ArrayList<String> cadastroProdutosVendidos;         // String: data, produto, quant, valor, cliente
    private ArrayList<Produto> estoqueProduto;

    public Produto(String nome, float preco, short estoque, Categoria categoria) {
        this.id = hashCode();
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque >= 0 ? estoque : 0;
        this.categoria = categoria;
        this.emEstoque = estoque != 0;

        this.cadastroProdutosVendidos = new ArrayList<>();
        this.estoqueProduto = geraEstoqueProduto();
    }
    public int getId() {
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
    public short getEstoque(){return estoque;}
    public void setEstoque(short adicional){this.estoque += adicional;}
    public Categoria getCategoria(){return categoria;}

    public ArrayList<Produto> getEstoqueProduto(Categoria categoria){
        ArrayList<Produto> produtosDaCategoria = new ArrayList<>();

        for (Produto produto : this.estoqueProduto){
            if (produto.getCategoria().equals(categoria))
                produtosDaCategoria.add(produto);
        }
        return produtosDaCategoria;
    }

    public boolean isEmEstoque() {
        return emEstoque;
    }
    public void setEmEstoque(boolean emEstoque) {
        this.emEstoque = emEstoque;
    }
    public ArrayList<String> getCadastroProdutosVendidos(){return cadastroProdutosVendidos;}

    public ArrayList<Produto> geraEstoqueProduto(){
        ArrayList<String> txt = new ArrayList<>(); // Teste
        ArrayList<Produto> produtos = new ArrayList<>();

        for (String linhaTxt : txt){
            String[] produtoNaLinha = linhaTxt.split(",");
            String categoria = produtoNaLinha[3];

            switch (categoria) {
                case "roupa" -> produtos.add(new Produto(produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                        Short.parseShort(produtoNaLinha[2]), Categoria.ROUPA));
                case "acessorio" -> produtos.add(new Produto(produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                        Short.parseShort(produtoNaLinha[2]), Categoria.ACESSORIO));
                case "calcado" -> produtos.add(new Produto(produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                        Short.parseShort(produtoNaLinha[2]), Categoria.CALCADO));
                default -> produtos.add(new Produto(produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                        Short.parseShort(produtoNaLinha[2]), Categoria.EQUIPAMENTO));
            }
        }
        return produtos;
    }
    public void acrescentaEstoqueProduto(Produto produto, short acrescimo){
        this.estoqueProduto.remove(produto);
        this.estoqueProduto.add(new Produto(produto.getNome(),
                produto.getPreco(),
                (short) (produto.getEstoque() + acrescimo),
                produto.getCategoria()));
    }
}
