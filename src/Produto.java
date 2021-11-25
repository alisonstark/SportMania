import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Produto {
    private final int id;
    private final String nome;
    private float preco;
    private int estoque;
    private boolean emEstoque;

    protected Categoria categoria;
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
    public int getEstoque(){return estoque;}
    public void setEstoque(short adicional){this.estoque += adicional;}
    public Categoria getCategoria(){return categoria;}

    public static ArrayList<Produto> getEstoqueProduto(Categoria categoria) throws IOException {
        ArrayList<Produto> produtosDaCategoria = new ArrayList<>();

        for (Produto produto : Produto.geraEstoqueProduto()){
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

    public static ArrayList<Produto> geraEstoqueProduto() throws IOException {
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("estoqueProdutos.txt"));
            String linhaTxt;
            String[] produtoNaLinha;
            String categoria;

            while ((linhaTxt = reader.readLine()) != null){
                produtoNaLinha = linhaTxt.split(",");
                 categoria = produtoNaLinha[3];

                switch (categoria) {
                    case "roupa" -> produtos.add(new Produto(produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                            Integer.parseInt(produtoNaLinha[2]), Categoria.ROUPA));
                    case "acessorio" -> produtos.add(new Produto(produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                            Integer.parseInt(produtoNaLinha[2]), Categoria.ACESSORIO));
                    case "calcado" -> produtos.add(new Produto(produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                            Integer.parseInt(produtoNaLinha[2]), Categoria.CALCADO));
                    default -> produtos.add(new Produto(produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                            Integer.parseInt(produtoNaLinha[2]), Categoria.EQUIPAMENTO));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return produtos;
    }
    /*
    public void acrescentaEstoqueProduto(Produto produto, short acrescimo) throws IOException {
        produtos.remove(produto);
        produtos.add(new Produto(produto.getNome(),
                produto.getPreco(),
                (short) (produto.getEstoque() + acrescimo),
                produto.getCategoria()));
    }

     */
}
