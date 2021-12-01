import SportManiaGUI.UsuarioGUI;
import javax.swing.*;

public class Loja {
    public static void main(String[] args) {
        // Testes
        /*
        ArrayList<Produto> produtos = Produto.geraEstoqueProduto();
        Cliente primeiroCliente =
               new Cliente("Coisinha", "05899658431", true);
       Vendas iniciarVenda = new Vendas();



        try {
            iniciarVenda.processaVenda(primeiroCliente, 883049899, (short) 4);
            for (Produto produto : Produto.geraEstoqueProduto()){
                System.out.println(produto.getNome() + " --> " + produto.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Cliente.geraCadastroCliente("Gustavo", "05894555896", true);
        Cliente.geraCadastroCliente("Pedro", "05892255896", true);
        System.out.println(Cliente.clientela);
    }

         */
        JFrame appFrame = new UsuarioGUI("SportMania");
        appFrame.setVisible(true);
    }
}
