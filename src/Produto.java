import java.util.ArrayList;

public abstract class Produto {
    private int id;
    private String nome;
    private float preco;
    private short estoque;
    private Categoria categoria;

    private ArrayList<Produto> cadastroVendas;

    public Produto(int id, String nome, float preco, short estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque >= 0 ? estoque : 0;
        this.cadastroVendas = new ArrayList<>();
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
    public ArrayList<Produto> getCadastroVendas() {
        return cadastroVendas;
    }

    public void reduzirEstoque(short quant){
        this.estoque -= quant;
    }
    public void aumentarEstoque(short quant){
        this.estoque += quant;
    }
    public abstract void realizaVenda(short quant);
    public abstract void cadastrarVenda(short quant);
    }
