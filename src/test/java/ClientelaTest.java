import Cliente.Cliente;
import Comunicacao.Clientela;
import Excecoes.Clientela.*;
import Excecoes.TabelaException;
import Excecoes.Validadores.CpfInvalido;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class ClientelaTest {

    private static Clientela clientela;

    @BeforeAll
    public static void gerarCadastroDeClientes() throws IOException, TabelaException, CpfInvalido {
        clientela = new Clientela("src/test/bancoTestes/clientela.ser");

        clientela.cadastrarCliente("Isabelle Giovanna Valentina Fernandes", "36033023978");
        clientela.cadastrarCliente("Clara Sandra Rodrigues", "53615621735");
        clientela.cadastrarCliente("Milena Stella Melo", "77796872526");
        clientela.cadastrarCliente("Sérgio Marcelo Almeida", "30063939703");
        clientela.cadastrarCliente("Rosângela Ayla Galvão", "63608576002");

        clientela.atualizarObjetos();
    }

    @Test
    public void cadastrarCliente_DeveTerSucesso_QuandoCpfAindaNaoFoiCadastrado() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";

        assertDoesNotThrow(() -> clientela.cadastrarCliente(nomeEsperado, cpfEsperado));

        assertTrue(clientela.contem(cpfEsperado));

        final Cliente[] clientes = new Cliente[1];
        assertDoesNotThrow(() -> clientes[0] = clientela.procurar(cpfEsperado));
        Cliente cliente = clientes[0];

        assertEquals(cliente.getNome(), nomeEsperado);
        assertEquals(cliente.getCpf(), cpfEsperado);
        assertTrue(cliente.isAtivo());

        assertDoesNotThrow(() -> clientela.remover(cpfEsperado));
    }

    @Test
    public void cadastrarCliente_DeveFalhar_QuandoCpfJaFoiCadastrado() {
        String nome = "Joao";
        String cpf = "36033023978";

        assertThrows(CpfJaCadastradoException.class, () -> clientela.cadastrarCliente(nome, cpf));
    }

    @Test
    public void carregarObjetos_DeveCarregarTodosOsClientes() {
        Hashtable<String, Cliente> copia = clientela.clonar();
        clientela.limpar();
        assertDoesNotThrow(clientela::carregarObjetos);

        final Cliente[] clientes = new Cliente[1];
        for (String chave : copia.keySet()) {
            assertTrue(clientela.contem(chave));
            assertDoesNotThrow(() -> clientes[0] = clientela.procurar(chave));
            assertEquals(clientes[0], copia.get(chave));
        }

        for (String chave : clientela.retornarChaves()) {
            assertTrue(copia.containsKey(chave));
            assertDoesNotThrow(() -> clientes[0] = clientela.procurar(chave));
            assertEquals(copia.get(chave), clientes[0]);
        }
    }

    @Test
    public void atualizarObjetos_DeveAtualizarOBancoDeDados() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";

        assertDoesNotThrow(() -> clientela.cadastrarCliente(nomeEsperado, cpfEsperado));
        assertDoesNotThrow(clientela::atualizarObjetos);

        Hashtable<String, Cliente> copia = clientela.clonar();
        clientela.limpar();
        assertDoesNotThrow(clientela::carregarObjetos);

        final Cliente[] cliente = new Cliente[1];
        for (String chave : copia.keySet()) {
            assertTrue(clientela.contem(chave));
            assertDoesNotThrow(() -> cliente[0] = clientela.procurar(chave));
            assertEquals(cliente[0], copia.get(chave));
        }

        for (String chave : clientela.retornarChaves()) {
            assertTrue(copia.containsKey(chave));
            assertDoesNotThrow(() -> cliente[0] = clientela.procurar(chave));
            assertEquals(copia.get(chave), cliente[0]);
        }
    }
}
