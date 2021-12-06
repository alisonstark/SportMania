package Comunicacao;

import Cliente.Cliente;
import Excecoes.TabelaException;
import Produto.Produto;
import Venda.Venda;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Vendas implements ArmazenaTexto {

    private String bancoDeDados;
    private final Clientela clientela;
    private final Estoque estoque;
    private final ArrayList<Venda> registroDeVendas;

    public Vendas(String caminho, Clientela clientela, Estoque estoque) {
        this.bancoDeDados = caminho;
        this.clientela = clientela;
        this.estoque = estoque;
        this.registroDeVendas = new ArrayList<>();
    }

    public void setBancoDeDados(String caminho) {
        this.bancoDeDados = caminho;
    }

    public void processarVenda(Integer id, int quantidade) throws TabelaException, IOException {
        Produto produto = estoque.procurar(id);
        produto.retirarN(quantidade);

        Venda venda = new Venda(produto, quantidade);
        registrarVenda(venda);
    }

    public void processarVenda(String cpf, Integer id, int quantidade) throws TabelaException, IOException {
        Cliente cliente = clientela.procurar(cpf);
        Produto produto = estoque.procurar(id);
        produto.retirarN(quantidade);

        Venda venda = new Venda(cliente, produto, quantidade);
        registrarVenda(venda);
    }

    private void registrarVenda(Venda venda) throws IOException {
        FileWriter fw = new FileWriter(bancoDeDados, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(venda.toString());
        bw.close();
    }

    public Venda fromString(String venda) throws TabelaException {
        String[] infos = venda.split(String.valueOf(Venda.separador));
        String dataHorario = infos[0];
        Produto produto = estoque.procurar(Integer.parseInt(infos[1]));
        int quantidade = Integer.parseInt(infos[2]);
        Cliente cliente = null;
        if (!infos[3].equals(Venda.clienteNulo))
            cliente = clientela.procurar(infos[3]);
        return new Venda(dataHorario, cliente, produto, quantidade);
    }

    @Override
    public void carregarLinhas() throws IOException, TabelaException {
        FileReader fr = new FileReader(bancoDeDados);
        BufferedReader br = new BufferedReader(fr);
        Stream<String> stream = br.lines();

        for (String line : stream.toList()) {
            Venda venda = fromString(line);
            registroDeVendas.add(venda);
        }
        br.close();
    }

    @Override
    public void atualizarLinhas() throws IOException {
        FileWriter fw = new FileWriter(bancoDeDados);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Venda venda : registroDeVendas) {
            bw.write(venda.toString());
        }
        bw.close();
    }
}
