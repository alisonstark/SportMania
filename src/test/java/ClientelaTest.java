import Cliente.Cliente;
import Comunicacao.Clientela;
import Excecoes.Clientela.*;
import Excecoes.TabelaException;
import Excecoes.ValidacaoException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class ClientelaTest {

    private static Clientela clientela;

    @BeforeAll
    public static void gerarCadastroDeClientes() throws IOException, TabelaException, ValidacaoException {
        clientela = new Clientela("src/test/bancoTestes/clientela.ser");

        clientela.cadastrarCliente("Isabelle Giovanna Valentina Fernandes", "36033023978", "6925166749", "isabellegiovannavalentinafernandes_@viasegbrasil.com.br");
        clientela.cadastrarCliente("Clara Sandra Rodrigues", "53615621735", "8228826933", "clarasandrarodrigues-85@embraer.com");
        clientela.cadastrarCliente("Milena Stella Melo", "77796872526", "6327388690", "mmilenastellamelo@br.pwc.com");
        clientela.cadastrarCliente("Sérgio Marcelo Almeida", "30063939703", "4126379251", "sergiomarceloalmeida_@marcossousa.com");
        clientela.cadastrarCliente("Rosângela Ayla Galvão", "63608576002", "8336638747", "rosangelaaylagalvao..rosangelaaylagalvao@msn.com.br");

        clientela.atualizarObjetos();
    }

    @Test
    public void cadastrarCliente_DeveTerSucesso_QuandoCpfAindaNaoFoiCadastrado() {
        String nomeEsperado = "Cristiane Gabriela Almeida";
        String cpfEsperado = "45742554478";
        String telefoneEsperado = "6135759904";
        String emailEsperado = "ccristianegabrielaalmeida@bfgadvogados.com";

        assertDoesNotThrow(() -> clientela.cadastrarCliente(nomeEsperado, cpfEsperado, telefoneEsperado, emailEsperado));

        assertTrue(clientela.contem(cpfEsperado));

        final Cliente[] clientes = new Cliente[1];
        assertDoesNotThrow(() -> clientes[0] = clientela.procurar(cpfEsperado));
        Cliente cliente = clientes[0];

        assertEquals(cliente.getNome(), nomeEsperado);
        assertEquals(cliente.getCpf(), cpfEsperado);
        assertEquals(cliente.getTelefone(), telefoneEsperado);
        assertEquals(cliente.getEmail(), emailEsperado);
        assertTrue(cliente.isAtivo());

        assertDoesNotThrow(() -> clientela.remover(cpfEsperado));
    }

    @Test
    public void cadastrarCliente_DeveFalhar_QuandoCpfJaFoiCadastrado() {
        String nome = "Luiz Manuel Marcos Ramos";
        String cpf = "36033023978";
        String telefone = "9537550180";
        String email = "luizmanuelmarcosramos-86@life.com";

        assertThrows(CpfJaCadastradoException.class, () -> clientela.cadastrarCliente(nome, cpf, telefone, email));
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
        String nome = "Cristiane Gabriela Almeida";
        String cpf = "45742554478";
        String telefone = "6135759904";
        String email = "ccristianegabrielaalmeida@bfgadvogados.com";

        assertDoesNotThrow(() -> clientela.cadastrarCliente(nome, cpf, telefone, email));
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
