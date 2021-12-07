package Excecoes.Validadores;

import Excecoes.ValidacaoException;

public class TelefoneInvalidoException extends ValidacaoException {

    public TelefoneInvalidoException(String telefone) {
        super("Telefone " + telefone + " e invalido!");
    }

}
