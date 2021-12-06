import Cliente.Cliente;
import Comunicacao.*;
import Excecoes.Clientela.CpfNaoCadastradoException;
import Excecoes.Estoque.EstoqueInsuficienteException;
import Excecoes.Estoque.ProdutoInexistenteException;
import Excecoes.TabelaException;
import Produto.Produto;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VendasTest {

    public static Vendas vendas;
    public static Clientela clientela;
    public static Estoque estoque;

    public static List<Cliente> clientes;
    public static List<Produto> produtos;

    @BeforeAll
    public static void gerarRegistroDeVendas() throws TabelaException, IOException, ClassNotFoundException {
        clientela = new Clientela("src/test/bancoTestes/clientela.ser");
        clientela.carregarObjetos();
        clientes = clientela.retornarValores().stream().toList();

        estoque = new Estoque("src/test/bancoTestes/estoque.ser");
        estoque.carregarObjetos();
        produtos = estoque.retornarValores().stream().toList();

        vendas = new Vendas("src/test/bancoTestes/vendas.txt", clientela, estoque);

        vendas.processarVenda(clientes.get(0).getCpf(), produtos.get(0).getIdentificador(), 2);
        vendas.processarVenda(produtos.get(1).getIdentificador(), 3);
        vendas.processarVenda(clientes.get(2).getCpf(), produtos.get(2).getIdentificador(), 1);
        vendas.processarVenda(produtos.get(3).getIdentificador(), 5);
        vendas.processarVenda(clientes.get(4).getCpf(), produtos.get(4).getIdentificador(), 4);
    }

    @Test
    public void processarVenda_DeveTerSucesso_SeProdutoExistirETiverEstoque() {
        Produto produto = produtos.get(0);
        String identificador = produto.getIdentificador();

        int quantidade = 2;
        int estoqueFinal = produto.getEstoque() - quantidade;

        assertDoesNotThrow(() -> vendas.processarVenda(identificador, quantidade));
        assertEquals(estoqueFinal, produto.getEstoque());
    }

    @Test
    public void processarVenda_DeveFalhar_SeProdutoNaoExistir() {
        String identificador = "roupa::naoexiste";
        int quantidade = 2;

        assertThrows(ProdutoInexistenteException.class, () -> vendas.processarVenda(identificador, quantidade));
    }

    @Test
    public void processarVenda_DeveFalhar_SeNaoTiverEstoqueSuficiente() {
        Produto produto = produtos.get(0);
        String identificador = produto.getIdentificador();

        int quantidade = 100;

        assertThrows(EstoqueInsuficienteException.class, () -> vendas.processarVenda(identificador, quantidade));
    }

    @Test
    public void processarVenda_DeveTerSucesso_SeClienteEstiverCadastrado() {
        Cliente cliente = clientes.get(0);
        String cpf = cliente.getCpf();

        Produto produto = produtos.get(1);
        String identificador = produto.getIdentificador();

        int quantidade = 2;
        int estoqueFinal = produto.getEstoque() - quantidade;

        assertDoesNotThrow(() -> vendas.processarVenda(cpf, identificador, quantidade));
        assertEquals(estoqueFinal, produto.getEstoque());
    }

    @Test
    public void processarVenda_DeveFalhar_SeClienteNaoEstiverCadastrado() {
        String cpf = "nil";

        Produto produto = produtos.get(1);
        String identificador = produto.getIdentificador();

        int quantidade = 2;

        assertThrows(CpfNaoCadastradoException.class, () -> vendas.processarVenda(cpf, identificador, quantidade));
    }

}
