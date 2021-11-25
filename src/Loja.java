import java.io.IOException;
import java.util.ArrayList;

public class Loja {
    private ArrayList<Cliente> cadastroClientes;

    public static void main(String[] args) {
        Cliente primeiroCliente =
                new Cliente("Coisinha", "05899658431", true);

        Vendas iniciarVenda = new Vendas();
        try {
            iniciarVenda.processaVenda(primeiroCliente, 221546, (short) 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
