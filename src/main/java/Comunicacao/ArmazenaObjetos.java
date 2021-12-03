package Comunicacao;

import Excecoes.TabelaException;

import java.io.IOException;

public interface ArmazenaObjetos {

    void carregarObjetos() throws IOException, ClassNotFoundException, TabelaException;

    void atualizarObjetos() throws IOException;

}
