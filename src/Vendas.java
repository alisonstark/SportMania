import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Vendas {

    public void processaVenda(Cliente cliente, Produto produto, short quant) throws RuntimeException{
        // Date time formatter: https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime horario = LocalDateTime.now();

        int indexOfProduto = Produto.getEstoqueRoupa().indexOf(produto);
        ArrayList<Produto> produtosVendidos = new ArrayList<>();

        // Tentar com switch?
        if (produto.getCategoria().equals(Categoria.ROUPA)) {

            if ( Produto.getEstoqueRoupa().get(indexOfProduto).isEmEstoque() &&
                    Produto.getEstoqueRoupa().get(indexOfProduto).getEstoque() >= quant) {
                //TODO remover a quantidade no estoque do txt
                produto.getCadastroProdutosVendidos().
                        add(dtf.format(horario) + ", " + produto.getNome() + ", x" + quant + ", " + produto.getPreco() + "R$, " + cliente.getNome());
                //TODO escrever entrada no txt (cadastro de vendas)
            } else {
                throw new RuntimeException("Quantidade insuficiente em estoque!");
            }
        }
    }
}
