import Cliente.Cliente;
import Cliente.Clientes;
import Excecoes.CadastroException;
import org.junit.jupiter.api.*;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @BeforeAll
    public static void gerarCadastroDeClientes() {
        Clientes.setBancoDeDados("src/test/bancoTestes/clientela.ser");

        try {
            Clientes.cadastrarCliente("Isabelle Giovanna Valentina Fernandes", "36033023978");
            Clientes.cadastrarCliente("Clara Sandra Rodrigues", "53615621735");
            Clientes.cadastrarCliente("Milena Stella Melo", "77796872526");
            Clientes.cadastrarCliente("Sérgio Marcelo Almeida", "30063939703");
            Clientes.cadastrarCliente("Rosângela Ayla Galvão", "63608576002");

            Clientes.atualizarClientela();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void cadastrarCliente_DeveTerSucesso_QuandoCpfAindaNaoFoiCadastrado() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";

        assertDoesNotThrow(() -> Clientes.cadastrarCliente(nomeEsperado, cpfEsperado));

        assertTrue(Clientes.clientela.containsKey(cpfEsperado));

        Cliente cliente = Clientes.clientela.get(cpfEsperado);

        assertEquals(cliente.getNome(), nomeEsperado);
        assertEquals(cliente.getCpf(), cpfEsperado);
        assertTrue(cliente.isAtivo());

        Clientes.clientela.remove(cpfEsperado);
    }

    @Test
    public void cadastrarCliente_DeveFalhar_QuandoCpfJaFoiCadastrado() {
        String nome = "Joao";
        String cpf = "36033023978";

        assertThrows(CadastroException.class, () -> Clientes.cadastrarCliente(nome, cpf));
    }

    @Test
    public void carregarClientela_DeveCarregarTodosOsClientes() {
        Hashtable<String, Cliente> copiaClientela = (Hashtable<String, Cliente>) Clientes.clientela.clone();
        Clientes.limparClientela();
        assertDoesNotThrow(Clientes::carregarClientela);

        for (String chave : copiaClientela.keySet()) {
            assertTrue(Clientes.clientela.containsKey(chave));
            assertEquals(Clientes.clientela.get(chave), copiaClientela.get(chave));
        }

        for (String chave : Clientes.clientela.keySet()) {
            assertTrue(copiaClientela.containsKey(chave));
            assertEquals(copiaClientela.get(chave), Clientes.clientela.get(chave));
        }
    }

    @Test
    public void atualizarClientela_DeveAtualizarOBancoDeDados() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";

        assertDoesNotThrow(() -> Clientes.cadastrarCliente(nomeEsperado, cpfEsperado));
        assertDoesNotThrow(Clientes::atualizarClientela);

        Hashtable<String, Cliente> copiaClientela = (Hashtable<String, Cliente>) Clientes.clientela.clone();
        Clientes.limparClientela();
        assertDoesNotThrow(Clientes::carregarClientela);

        for (String chave : copiaClientela.keySet()) {
            assertTrue(Clientes.clientela.containsKey(chave));
            assertEquals(Clientes.clientela.get(chave), copiaClientela.get(chave));
        }

        for (String chave : Clientes.clientela.keySet()) {
            assertTrue(copiaClientela.containsKey(chave));
            assertEquals(copiaClientela.get(chave), Clientes.clientela.get(chave));
        }
    }
}
