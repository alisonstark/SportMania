package Comunicacao;

import Cliente.Cliente;
import Excecoes.Cliente.ClienteException;
import Excecoes.Cliente.CpfNaoCadastradoException;
import Excecoes.Produto.*;
import Produto.Produto;
import Venda.Venda;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Vendas {

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

    private void registrarVenda(Venda venda) throws IOException {
        FileWriter fw = new FileWriter(bancoDeDados, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(venda.toString());
        bw.close();
    }

    public void processarVenda(Integer id, int quantidade) throws ProdutoException, IOException {
        Produto produto = estoque.procurarProduto(id);
        produto.retirarN(quantidade);

        Venda venda = new Venda(produto, quantidade);
        registrarVenda(venda);
    }

    public void processarVenda(String cpf, Integer id, int quantidade) throws ClienteException, ProdutoException, IOException {
        Cliente cliente = clientela.procurarCliente(cpf);
        Produto produto = estoque.procurarProduto(id);
        produto.retirarN(quantidade);

        Venda venda = new Venda(cliente, produto, quantidade);
        registrarVenda(venda);
    }
}
