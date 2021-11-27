import Excecoes.CadastroException;
import org.junit.jupiter.api.*;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @BeforeAll
    public static void gerarCadastroDeClientes() {
        Cliente.setBancoDeDados("src/test/bancoTestes/clientela.ser");

        try {
            Cliente.cadastrarCliente("Isabelle Giovanna Valentina Fernandes", "36033023978");
            Cliente.cadastrarCliente("Clara Sandra Rodrigues", "53615621735");
            Cliente.cadastrarCliente("Milena Stella Melo", "77796872526");
            Cliente.cadastrarCliente("Sérgio Marcelo Almeida", "30063939703");
            Cliente.cadastrarCliente("Rosângela Ayla Galvão", "63608576002");

            Cliente.atualizarClientela();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void cadastrarCliente_DeveTerSucesso_QuandoCpfAindaNaoFoiCadastrado() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";

        assertDoesNotThrow(() -> Cliente.cadastrarCliente(nomeEsperado, cpfEsperado));

        assertTrue(Cliente.clientela.containsKey(cpfEsperado));

        Cliente cliente = Cliente.clientela.get(cpfEsperado);

        assertEquals(cliente.getNome(), nomeEsperado);
        assertEquals(cliente.getCpf(), cpfEsperado);
        assertTrue(cliente.isAtivo());

        Cliente.clientela.remove(cpfEsperado);
    }

    @Test
    public void cadastrarCliente_DeveFalhar_QuandoCpfJaFoiCadastrado() {
        String nome = "Joao";
        String cpf = "36033023978";

        assertThrows(CadastroException.class, () -> Cliente.cadastrarCliente(nome, cpf));
    }

    @Test
    public void carregarClientela_DeveCarregarTodosOsClientes() {
        Hashtable<String, Cliente> copiaClientela = (Hashtable<String, Cliente>) Cliente.clientela.clone();
        Cliente.limparClientela();
        assertDoesNotThrow(Cliente::carregarClientela);

        for (String chave : copiaClientela.keySet()) {
            assertTrue(Cliente.clientela.containsKey(chave));
            assertEquals(Cliente.clientela.get(chave), copiaClientela.get(chave));
        }

        for (String chave : Cliente.clientela.keySet()) {
            assertTrue(copiaClientela.containsKey(chave));
            assertEquals(copiaClientela.get(chave), Cliente.clientela.get(chave));
        }
    }

    @Test
    public void atualizarClientela_DeveAtualizarOBancoDeDados() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";

        assertDoesNotThrow(() -> Cliente.cadastrarCliente(nomeEsperado, cpfEsperado));
        assertDoesNotThrow(Cliente::atualizarClientela);

        Hashtable<String, Cliente> copiaClientela = (Hashtable<String, Cliente>) Cliente.clientela.clone();
        Cliente.limparClientela();
        assertDoesNotThrow(Cliente::carregarClientela);

        for (String chave : copiaClientela.keySet()) {
            assertTrue(Cliente.clientela.containsKey(chave));
            assertEquals(Cliente.clientela.get(chave), copiaClientela.get(chave));
        }

        for (String chave : Cliente.clientela.keySet()) {
            assertTrue(copiaClientela.containsKey(chave));
            assertEquals(copiaClientela.get(chave), Cliente.clientela.get(chave));
        }
    }
}
