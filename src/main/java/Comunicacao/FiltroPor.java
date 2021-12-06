package Comunicacao;

import java.util.List;

public interface FiltroPor<T, C> {

    List<T> filtrarPor(C x);

}
