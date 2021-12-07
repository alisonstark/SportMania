package Excecoes.Validadores;

import Excecoes.ValidacaoException;

public class EmailInvalidoException extends ValidacaoException {

    public EmailInvalidoException(String email) {
        super("Email " + email + " e invalido!");
    }

}
