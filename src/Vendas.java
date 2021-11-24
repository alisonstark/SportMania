import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Vendas {

    public void processaVenda(Cliente cliente, Produto produto, short quant) throws RuntimeException{
        // Date time formatter: https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime horario = LocalDateTime.now();

        int indexOfProduto = produto.getEstoqueProduto(produto.getCategoria()).indexOf(produto);
        ArrayList<Produto> produtosVendidos = new ArrayList<>();

        if (produto.getEstoqueProduto(produto.getCategoria()).get(indexOfProduto).isEmEstoque() &&
                    produto.getEstoqueProduto(produto.getCategoria()).get(indexOfProduto).getEstoque() >= quant) {
                //TODO remover a quantidade no estoque do txt ?
                if (cliente != null) {

                    cliente.getRegistroCompras().add(dtf.format(horario) + ", " +
                            produto.getId() + "-" + produto.getNome() + ", x" +
                            quant + ", " +
                            produto.getPreco() + "R$, " +
                            cliente.getNome() + "\n");

                    produto.getCadastroProdutosVendidos().
                            add(dtf.format(horario) + ", " +
                                    produto.getId() + "-" + produto.getNome() + ", x" +
                                    quant + ", " +
                                    produto.getPreco() + "R$, " +
                                    cliente.getNome() + "\n");
                    //TODO escrever entrada no txt (cadastro de vendas)
                } else
                    produto.getCadastroProdutosVendidos().
                            add(dtf.format(horario) + ", " +
                                    produto.getId() + "-" + produto.getNome() + ", x" +
                                    quant + ", " +
                                    produto.getPreco() + "R$, " +
                                    "Cliente nao identificado.\n");

            } else {
                throw new RuntimeException("Quantidade insuficiente em estoque!");
            }

    }
}
