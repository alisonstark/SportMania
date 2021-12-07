package Validadores;

import Excecoes.ValidacaoException;
import Excecoes.Validadores.TelefoneInvalidoException;

public class ValidaTelefone extends Validador {

    private String modificado;

    public ValidaTelefone(String telefone) {
        super(telefone.trim());
    }

    @Override
    public String ehValido() throws ValidacaoException {
        modificado = original.replaceAll("[ ()-]", "");
        if (!modificado.matches("\\d{10}"))
            throw new TelefoneInvalidoException(original);
        return modificado;
    }

}
