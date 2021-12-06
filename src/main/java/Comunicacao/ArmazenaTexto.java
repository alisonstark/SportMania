package Comunicacao;

import Excecoes.TabelaException;

import java.io.IOException;

public interface ArmazenaTexto {

    void carregarLinhas() throws IOException, TabelaException;

    void atualizarLinhas() throws IOException;

}
