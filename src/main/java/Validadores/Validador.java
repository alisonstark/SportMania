package Validadores;

import Excecoes.ValidacaoException;

public abstract class Validador {

    protected final String original;

    public Validador(String original) {
        this.original = original;
    }

    public abstract String ehValido() throws ValidacaoException;

}
