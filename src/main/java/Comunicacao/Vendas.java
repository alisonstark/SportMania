package Comunicacao;

import Cliente.Cliente;
import Excecoes.TabelaException;
import Produto.Produto;
import Venda.Venda;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Vendas implements ArmazenaTexto, FiltroPor<Venda, Cliente> {

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

    public void limpar() {
        registroDeVendas.clear();
    }

    /**
     * Cadastra a nova venda sem os dados do cliente
     *
     * @param identificador Identificador do produto
     * @param quantidade Quantidade a ser vendida
     * @throws TabelaException Se o produto não existir
     * @throws IOException Se houver erro na escrita da venda
     */
    public void processarVenda(String identificador, int quantidade) throws TabelaException, IOException {
        Produto produto = estoque.procurar(identificador);
        produto.retirarN(quantidade);

        Venda venda = new Venda(produto, quantidade);
        registroDeVendas.add(venda);
        registrarVenda();
    }

    /**
     * Cadastra a nova venda com os dados do cliente
     *
     * @param cpf CPF do cliente
     * @param identificador Identificador do produto
     * @param quantidade Quantidade a ser vendida
     * @throws TabelaException Se o produto não existir ou o cliente não for cadastrado
     * @throws IOException Se houver erro na escrita da venda
     */
    public void processarVenda(String cpf, String identificador, int quantidade) throws TabelaException, IOException {
        Cliente cliente = clientela.procurar(cpf);
        Produto produto = estoque.procurar(identificador);
        produto.retirarN(quantidade);

        Venda venda = new Venda(cliente, produto, quantidade);
        registroDeVendas.add(venda);
        registrarVenda();
    }

    private void registrarVenda() throws IOException {
        FileWriter fw = new FileWriter(bancoDeDados);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Venda venda : registroDeVendas)
            bw.write(venda.toString());
        bw.close();
    }

    private Venda fromString(String venda) throws TabelaException {
        String[] infos = venda.split(String.valueOf(Venda.separador));
        String dataHorario = infos[0];
        Produto produto = estoque.procurar(infos[1]);
        int quantidade = Integer.parseInt(infos[2]);
        Cliente cliente = null;
        if (!infos[3].equals(Venda.clienteNulo))
            cliente = clientela.procurar(infos[3]);
        return new Venda(dataHorario, cliente, produto, quantidade);
    }

    /**
     * Carrega o registro de vendas do banco de dados
     *
     * @throws IOException Se houver erro na leitura
     * @throws TabelaException Se o produto não existir ou o cliente não for cadastrado
     */
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

    /**
     * Registra as todas as vendas
     *
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    @Override
    public void atualizarLinhas() throws IOException {
        FileWriter fw = new FileWriter(bancoDeDados);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Venda venda : registroDeVendas) {
            bw.write(venda.toString());
        }
        bw.close();
    }

    public List<Venda> filtrarPor(Cliente cliente) {
        return registroDeVendas.stream()
                .filter(venda -> cliente.equals(venda.cliente))
                .toList();
    }

}
