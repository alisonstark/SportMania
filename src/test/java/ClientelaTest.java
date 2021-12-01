import Cliente.Cliente;
import Comunicacao.Clientela;
import Excecoes.Cliente.*;
import org.junit.jupiter.api.*;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class ClientelaTest {

    private static Clientela clientela;

    @BeforeAll
    public static void gerarCadastroDeClientes() {
        clientela = new Clientela("src/test/bancoTestes/clientela.ser");

        try {
            clientela.cadastrarCliente("Isabelle Giovanna Valentina Fernandes", "36033023978");
            clientela.cadastrarCliente("Clara Sandra Rodrigues", "53615621735");
            clientela.cadastrarCliente("Milena Stella Melo", "77796872526");
            clientela.cadastrarCliente("Sérgio Marcelo Almeida", "30063939703");
            clientela.cadastrarCliente("Rosângela Ayla Galvão", "63608576002");

            clientela.atualizarClientela();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void cadastrarCliente_DeveTerSucesso_QuandoCpfAindaNaoFoiCadastrado() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";

        assertDoesNotThrow(() -> clientela.cadastrarCliente(nomeEsperado, cpfEsperado));

        assertTrue(clientela.contemCliente(cpfEsperado));

        final Cliente[] cliente = new Cliente[1];
        assertDoesNotThrow(() -> cliente[0] = clientela.procurarCliente(cpfEsperado));

        assertEquals(cliente[0].getNome(), nomeEsperado);
        assertEquals(cliente[0].getCpf(), cpfEsperado);
        assertTrue(cliente[0].isAtivo());

        clientela.removerCliente(cpfEsperado);
    }

    @Test
    public void cadastrarCliente_DeveFalhar_QuandoCpfJaFoiCadastrado() {
        String nome = "Joao";
        String cpf = "36033023978";

        assertThrows(CpfJaCadastradoException.class, () -> clientela.cadastrarCliente(nome, cpf));
    }

    @Test
    public void carregarClientela_DeveCarregarTodosOsClientes() {
        Hashtable<String, Cliente> copiaClientela = clientela.clonarClientela();
        clientela.limparClientela();
        assertDoesNotThrow(clientela::carregarClientela);

        final Cliente[] cliente = new Cliente[1];
        for (String chave : copiaClientela.keySet()) {
            assertTrue(clientela.contemCliente(chave));
            assertDoesNotThrow(() -> cliente[0] = clientela.procurarCliente(chave));
            assertEquals(cliente[0], copiaClientela.get(chave));
        }

        for (String chave : clientela.retornarCpfsCadastrados()) {
            assertTrue(copiaClientela.containsKey(chave));
            assertDoesNotThrow(() -> cliente[0] = clientela.procurarCliente(chave));
            assertEquals(copiaClientela.get(chave), cliente[0]);
        }
    }

    @Test
    public void atualizarClientela_DeveAtualizarOBancoDeDados() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";

        assertDoesNotThrow(() -> clientela.cadastrarCliente(nomeEsperado, cpfEsperado));
        assertDoesNotThrow(clientela::atualizarClientela);

        Hashtable<String, Cliente> copiaClientela = clientela.clonarClientela();
        clientela.limparClientela();
        assertDoesNotThrow(clientela::carregarClientela);

        final Cliente[] cliente = new Cliente[1];
        for (String chave : copiaClientela.keySet()) {
            assertTrue(clientela.contemCliente(chave));
            assertDoesNotThrow(() -> cliente[0] = clientela.procurarCliente(chave));
            assertEquals(cliente[0], copiaClientela.get(chave));
        }

        for (String chave : clientela.retornarCpfsCadastrados()) {
            assertTrue(copiaClientela.containsKey(chave));
            assertDoesNotThrow(() -> cliente[0] = clientela.procurarCliente(chave));
            assertEquals(copiaClientela.get(chave), cliente[0]);
        }
    }
}
