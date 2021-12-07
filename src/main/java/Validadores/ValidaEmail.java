package Validadores;

import Excecoes.ValidacaoException;
import Excecoes.Validadores.EmailInvalidoException;

public class ValidaEmail extends Validador {

    public ValidaEmail(String email) {
        super(email.trim());
    }

    @Override
    public String ehValido() throws ValidacaoException {
        // https://www.horadecodar.com.br/2020/09/07/expressao-regular-para-validar-e-mail-javascript-regex/
        if (!original.matches("/\\S+@\\S+\\.\\S+/"))
            throw new EmailInvalidoException(original);
        return original;
    }


}
