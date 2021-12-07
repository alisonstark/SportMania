package Excecoes.Validadores;

import Excecoes.ValidacaoException;

public class CpfInvalidoException extends ValidacaoException {

    public CpfInvalidoException(String cpf) {
        super("CPF " + cpf + " e invalido!");
    }

}
