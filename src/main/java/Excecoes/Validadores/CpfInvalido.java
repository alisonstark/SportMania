package Excecoes.Validadores;

import Excecoes.ValidacaoException;

public class CpfInvalido extends ValidacaoException {

    public CpfInvalido(String cpf) {
        super("CPF " + cpf + " e invalido!");
    }

}
