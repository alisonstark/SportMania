import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Cliente.*;
import Produto.*;

public class Vendas {

    private static String bancoDeDados = "/BancoDeDaos/vendas.txt";
    private static final char separador = '|';

    public static void setBancoDeDados(String caminho) {
        bancoDeDados = caminho;
    }

    public static void processaVenda(String cpf, Integer id, short quant) throws IOException {
        // Date time formatter: https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime horario = LocalDateTime.now();

        if (!Clientes.clientela.containsKey(cpf)) {
            // TODO falta implementar
        }
        if (!Produtos.produtos.containsKey(id)) {
            // TODO falta implementar
        }

        Cliente cliente = Clientes.clientela.get(cpf);
        Produto produto = Produtos.produtos.get(id);

        FileWriter fw = new FileWriter(bancoDeDados, true);
        BufferedWriter bw = new BufferedWriter(fw);

        String dado = dtf.format(horario) + separador +
                      produto.getId()     + separador +
                      produto.getNome()   + separador +
                      produto.getPreco()  + separador +
                      quant               + separador +
                      cliente.getCpf()    + '\n';

        bw.write(dado);
        bw.close();

    }
}
