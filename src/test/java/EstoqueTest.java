import Excecoes.TabelaException;
import Produto.*;
import Comunicacao.Estoque;
import Excecoes.Estoque.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class EstoqueTest {

    private static Estoque estoque;

    @BeforeAll
    public static void gerarEstoque() throws TabelaException, IOException {
        estoque = new Estoque("src/test/bancoTestes/estoque.ser");

        estoque.adicionarProduto("camiseta", 20.99f, 10, Categoria.ROUPA.toString());
        estoque.adicionarProduto("tenis", 10.99f, 18, Categoria.CALCADO.toString());
        estoque.adicionarProduto("bola", 15.99f, 12, Categoria.ACESSORIO.toString());
        estoque.adicionarProduto("naoimporta", 13.99f, 15, Categoria.EQUIPAMENTO.toString());
        estoque.adicionarProduto("regata", 18.99f, 15, Categoria.ROUPA.toString());

        estoque.atualizarObjetos();
    }

    @Test
    public void adicionarProduto_DeveTerSucesso_QuandoProdutoNaoExistir() {
        String nomeEsperado = "bastao";
        float precoEsperado = 16.99f;
        int estoqueEsperado = 13;
        String categoriaEsperada = Categoria.ACESSORIO.toString();

        final String[] ids = new String[1];
        assertDoesNotThrow(() -> ids[0] = estoque.adicionarProduto(nomeEsperado, precoEsperado, estoqueEsperado, categoriaEsperada));
        String id = ids[0];

        assertTrue(estoque.contem(id));

        final Produto[] produtos = new Produto[1];
        assertDoesNotThrow(() -> produtos[0] = estoque.procurar(id));
        Produto produto = produtos[0];

        assertEquals(nomeEsperado, produto.getNome());
        assertEquals(precoEsperado, produto.getPreco());
        assertEquals(estoqueEsperado, produto.getEstoque());
        assertEquals(categoriaEsperada, produto.getCategoria().toString());

        assertDoesNotThrow(() -> estoque.remover(id));
    }

    @Test
    public void adicionarProduto_DeveTerSucesso_QuandoProdutoJaExistir() {
        String nome = "camiseta";
        float preco = 20.99f;
        int quantidade = 10;
        String categoria = Categoria.ROUPA.toString();

        assertThrows(ProdutoJaExistenteException.class, () -> estoque.adicionarProduto(nome, preco, quantidade, categoria));
    }

    @Test
    public void carregarObjetos_DeveCarregarTodosOsProdutos() {
        Hashtable<String, Produto> copia = estoque.clonar();
        estoque.limpar();
        assertDoesNotThrow(estoque::carregarObjetos);

        final Produto[] produtos = new Produto[1];
        for (String chave : copia.keySet()) {
            assertTrue(estoque.contem(chave));
            assertDoesNotThrow(() -> produtos[0] = estoque.procurar(chave));
            assertEquals(produtos[0], copia.get(chave));
        }

        for (String chave : estoque.retornarChaves()) {
            assertTrue(copia.containsKey(chave));
            assertDoesNotThrow(() -> produtos[0] = estoque.procurar(chave));
            assertEquals(copia.get(chave), produtos[0]);
        }
    }

    @Test
    public void atualizarObjetos_DeveAtualizarOBancoDeDados() {
        String nomeEsperado = "cesta";
        float precoEsperado = 24.99f;
        int estoqueEsperado = 17;
        String categoriaEsperada = Categoria.EQUIPAMENTO.toString();

        assertDoesNotThrow(() -> estoque.adicionarProduto(nomeEsperado, precoEsperado, estoqueEsperado, categoriaEsperada));
        assertDoesNotThrow(estoque::atualizarObjetos);

        Hashtable<String, Produto> copia = estoque.clonar();
        estoque.limpar();
        assertDoesNotThrow(estoque::carregarObjetos);

        final Produto[] produtos = new Produto[1];
        for (String chave : copia.keySet()) {
            assertTrue(estoque.contem(chave));
            assertDoesNotThrow(() -> produtos[0] = estoque.procurar(chave));
            assertEquals(produtos[0], copia.get(chave));
        }

        for (String chave : estoque.retornarChaves()) {
            assertTrue(copia.containsKey(chave));
            assertDoesNotThrow(() -> produtos[0] = estoque.procurar(chave));
            assertEquals(copia.get(chave), produtos[0]);
        }
    }

}
