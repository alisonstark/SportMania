package Comunicacao;

import Venda.Venda;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Vendas {

    private String bancoDeDados;

    public Vendas(String caminho) {
        this.bancoDeDados = caminho;
    }

    public void setBancoDeDados(String caminho) {
        this.bancoDeDados = caminho;
    }

    public void registrarVenda(Venda venda) throws IOException {
        FileWriter fw = new FileWriter(bancoDeDados, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(venda.toString());
        bw.close();
    }

    public void processarVenda() {

    }

}
