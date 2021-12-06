package Comunicacao;

import Excecoes.Estoque.*;
import Excecoes.TabelaException;
import Produto.*;

import java.io.*;

public class Estoque extends Tabela<String, Produto> implements ArmazenaObjetos {
    private String bancoDeDados;

    public Estoque(String caminho) {
        this.bancoDeDados = caminho;
    }

    public void setBancoDeDados(String caminho) {
        bancoDeDados = caminho;
    }

    public void carregarObjetos() throws IOException, ClassNotFoundException, TabelaException {
        FileInputStream fis = new FileInputStream(bancoDeDados);
        ObjectInputStream ois = new ObjectInputStream(fis);

        while (fis.available() > 0) {
            Produto produto = (Produto) ois.readObject();
            adicionar(produto);
        }
        ois.close();
    }

    public void atualizarObjetos() throws IOException {
        FileOutputStream fos = new FileOutputStream(bancoDeDados);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Produto produto : retornarValores())
            oos.writeObject(produto);
        oos.close();
    }

    public String adicionarProduto(String nome, float preco, int estoque, String categoria) throws TabelaException {
        Categoria categoriaProduto = Categoria.mapearString(categoria);
        Produto produto = new Produto(nome, preco, estoque, categoriaProduto);
        String identificador = produto.getIdentificador();

        if (contem(identificador))
            throw new ProdutoJaExistenteException(identificador);
        else {
            adicionar(produto);
            return identificador;
        }
    }

    @Override
    protected void adicionar(Produto produto) throws TabelaException {
        String identificador = produto.getIdentificador();
        if (contem(produto.getIdentificador()))
            throw new ProdutoJaExistenteException(identificador);
        else
            tabela.put(identificador, produto);
    }

    @Override
    public void remover(String identificador) throws TabelaException {
        if (!contem(identificador))
            throw new ProdutoInexistenteException(identificador);
        else
            tabela.remove(identificador);
    }

    @Override
    public Produto procurar(String identificador) throws TabelaException {
        if (!contem(identificador))
            throw new ProdutoInexistenteException(identificador);
        else
            return super.procurar(identificador);
    }
}
