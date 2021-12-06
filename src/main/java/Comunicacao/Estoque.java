package Comunicacao;

import Excecoes.Produto.*;
import Excecoes.TabelaException;
import Produto.*;

import java.io.*;

public class Estoque extends Tabela<Integer, Produto> implements ArmazenaObjetos {
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

    public Integer adicionarProduto(String nome, float preco, int estoque, String categoria) throws TabelaException {
        Categoria categoriaProduto = Categoria.mapearString(categoria);
        Produto produto = new Produto(nome, preco, estoque, categoriaProduto);
        Integer id = produto.getId();

        if (contem(id))
            throw new ProdutoJaExistenteException(id);
        else {
            adicionar(produto);
            return id;
        }
    }

    @Override
    protected void adicionar(Produto produto) throws TabelaException {
        Integer id = produto.getId();
        if (contem(id))
            throw new ProdutoJaExistenteException(id);
        else
            tabela.put(id, produto);
    }

    @Override
    public void remover(Integer id) throws TabelaException {
        if (!contem(id))
            throw new ProdutoInexistenteException(id);
        else
            tabela.remove(id);
    }

    @Override
    public Produto procurar(Integer id) throws TabelaException {
        if (!contem(id))
            throw new ProdutoInexistenteException(id);
        else
            return super.procurar(id);
    }
}
