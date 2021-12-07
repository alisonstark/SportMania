package Validadores;

import Excecoes.ValidacaoException;
import Excecoes.Validadores.CpfInvalidoException;

public class ValidaCpf extends Validador {

    private String modificado;
    private final int[] digits = new int[11];

    public ValidaCpf(String cpf) {
        super(cpf.trim());
    }

    private void converteEmDigitos() throws CpfInvalidoException {
        modificado = original.replaceAll("[.-]", "");
        if (!modificado.matches("\\d{11}"))
            throw new CpfInvalidoException(original);

        for (int i = 0; i < 11; i++)
            digits[i] = modificado.charAt(i) - '0';

    }

    // https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
    @Override
    public String ehValido() throws ValidacaoException {
        converteEmDigitos();

        boolean tudoIgual = true;
        for (int i = 1; i < 11; i++) {
            if (digits[0] != digits[i]) {
                tudoIgual = false;
                break;
            }
        }
        if (tudoIgual)
            throw new CpfInvalidoException(original);

        int dig10 = calcula10Digito();
        int dig11 = calcula11Digito();

        if (dig10 != digits[9] || dig11 != digits[10])
            throw new CpfInvalidoException(original);

        return modificado;
    }

    private int calcula10Digito() {
        int sum10 = 0;
        for (int i = 0; i < 9; i++)
            sum10 += (10 - i) * digits[i];
        int dig10 = 11 - sum10 % 11;
        if (dig10 > 9)
            dig10 = 0;
        return dig10;
    }

    private int calcula11Digito() {
        int sum11 = 0;
        for (int i = 0; i < 10; i++)
            sum11 += (11 - i) * digits[i];
        int dig11 = 11 - sum11 % 11;
        if (dig11 > 9)
            dig11 = 0;
        return dig11;
    }

}
