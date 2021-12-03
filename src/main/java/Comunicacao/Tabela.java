package Comunicacao;

import Excecoes.TabelaException;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

public abstract class Tabela<Chave, Valor> {

    protected final Hashtable<Chave, Valor> tabela;

    public Tabela() {
        this.tabela = new Hashtable<>();
    }

    public Set<Chave> retornarChaves() {
        return tabela.keySet();
    }

    public Collection<Valor> retornarValores() {
        return tabela.values();
    }

    public boolean contem(Chave chave) {
        return tabela.containsKey(chave);
    }

    public Valor procurar(Chave chave) throws TabelaException {
        return tabela.get(chave);
    }

    protected abstract void adicionar(Valor valor) throws TabelaException;

    protected abstract void remover(Chave chave) throws TabelaException;

    public void limpar() {
        tabela.clear();
    }

    public Hashtable<Chave, Valor> clonar() {
        return (Hashtable<Chave, Valor>) tabela.clone();
    }

}
