package Venda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Cliente.*;
import Produto.*;

public class Venda {

    public static final String clienteNulo = "nil";
    public static final char separador = '|';
    public static final char fimDeLinha = '\n';

    public final Cliente cliente;
    public final Produto produto;
    public final int quantidade;
    public final String dataHorario;

    public Venda(String dataHorario, Cliente cliente, Produto produto, int quantidade) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataHorario = dataHorario;
    }

    public Venda(Cliente cliente, Produto produto, int quantidade) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataHorario = agora();
    }

    public Venda(Produto produto, int quantidade) {
        this.cliente = null;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataHorario = agora();
    }

    private String agora() {
        // Date time formatter: https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime horario = LocalDateTime.now();
        return dtf.format(horario);
    }

    @Override
    public String toString() {
        String cpf = clienteNulo;
        if (cliente != null) {
            cpf = cliente.getCpf();
        }
        return dataHorario                  + separador +
                produto.getIdentificador()  + separador +
                quantidade                  + separador +
                cpf                         + fimDeLinha;
    }
}
