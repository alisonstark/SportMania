package Venda;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Cliente.*;
import Comunicacao.Clientela;
import Comunicacao.Estoque;
import Produto.*;

public class Venda {

    private static final String clienteNaoCadastrado = "0";
    private static final char separador = '|';
    private static final char fimDeLinha = '\n';

    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private String dataHorario;

    private final Clientela clientela;
    private final Estoque estoque;

    public Venda(Clientela clientela, Estoque estoque) {
        this.clientela = clientela;
        this.estoque = estoque;
    }

    public void processaVenda(Integer id, int quantidade) {
        if (!estoque.contemProduto(id)) {
            // TODO throw exception
        }
        produto = estoque.procurarProduto(id);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime horario = LocalDateTime.now();
        dataHorario = dtf.format(horario);


    }

    public void processaVenda(String cpf, Integer id, int quantidade) {
        // Date time formatter: https://www.javatpoint.com/java-get-current-date
        // TODO diminuir quantidade do estoque (chamar metodo atualizarProduto())

        if (!clientela.contemCliente(cpf)) {
            // TODO throw exception
        }
        cliente = clientela.procurarCliente(cpf);

        processaVenda(id, quantidade);
    }

    @Override
    public String toString() {
        return dataHorario       + separador +
                produto.getId()  + separador +
                quantidade       + separador +
                cliente.getCpf() + fimDeLinha;
    }
}
