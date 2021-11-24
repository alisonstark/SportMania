import java.util.ArrayList;

public class Produto {
    private final int id;
    private String nome;
    private float preco;
    private short estoque;
    private boolean emEstoque;

    private Categoria categoria;

    private ArrayList<String> cadastroProdutosVendidos;         // String: data, produto, quant, valor, cliente

    public Produto(String nome, float preco, short estoque, Categoria categoria) {
        this.id = hashCode();
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque >= 0 ? estoque : 0;
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
    public short getEstoque(){return estoque;}
    public Categoria getCategoria(){return categoria;}

    public boolean isEmEstoque() {
        return emEstoque;
    }
    public void setEmEstoque(boolean emEstoque) {
        this.emEstoque = emEstoque;
    }

    public ArrayList<String> getCadastroProdutosVendidos(){return cadastroProdutosVendidos;}

    public static ArrayList<Produto> getEstoqueRoupa(){
        // TODO ler produtos a partir de um txt
        ArrayList<String> txt = new ArrayList<>();

        ArrayList<Produto> camisa = new ArrayList<>();
        for (String linhadoTxt : txt){
            // exemplo de linha do txt (para roupas -> por simplicidade, desconsiderar marcas)
            //TODO apagar linha posteriormente
            // camisa.add(new Produto("Camisa Xadrez Masc", 25.69f, Categoria.ROUPA, 89));

            // txt contém em cada linha: "Nome do produto, preco, quant estoque"

            String[] produtoNaLinha = linhadoTxt.split(",");
            camisa.add(new Produto (produtoNaLinha[0], Float.parseFloat(produtoNaLinha[1]),
                    Short.parseShort(produtoNaLinha[2]), Categoria.ROUPA));
        }
        return camisa;
    }


    }
