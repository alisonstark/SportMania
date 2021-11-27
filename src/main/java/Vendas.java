import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vendas {

    public Vendas() {
    }

    //TODO receber o cpf para identificação do cliente && descontar do estoque (ArrayList<Produto>)
    public void processaVenda(Cliente cliente, int id, short quant) throws IOException {
        // Date time formatter: https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime horario = LocalDateTime.now();

        for (Produto produto : Produto.geraEstoqueProduto()) {
            if (produto.getId() == id) {

                if (produto.isEmEstoque() && produto.getEstoque() >= quant) {
                    //TODO remover a quantidade no estoque do arrayList produtos
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
                        //TODO escrever entrada no txt (cadastro de vendas) --> criar cadastroVendas da loja
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
                // TODO resolver caso em que ID não foi encontrada
            }
        }
    }
}
