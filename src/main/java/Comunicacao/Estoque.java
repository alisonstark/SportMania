package Comunicacao;

import Excecoes.Produto.*;
import Produto.Produto;
import Produto.Categoria;

import java.io.*;
import java.util.Hashtable;

public class Estoque {
    private String bancoDeDados;
    public Hashtable<Integer, Produto> estoque;

    public Estoque(String caminho) {
        this.bancoDeDados = caminho;
        this.estoque = new Hashtable<>();
    }

    public void setBancoDeDados(String caminho) {
        bancoDeDados = caminho;
    }

    public boolean contemProduto(Integer id) {
        return estoque.containsKey(id);
    }

    public Produto procurarProduto(Integer id) {
        return estoque.get(id);
    }

    public void carregarProdutos() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(bancoDeDados);

        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Produto produto = (Produto) ois.readObject();
                estoque.put(produto.getId(), produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarProdutos() throws IOException {
        FileOutputStream fos = new FileOutputStream(bancoDeDados);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Produto produto : estoque.values()) {
            oos.writeObject(produto);
        }
        oos.close();
    }

    // produto que não existe no cadastro de produtos (no estoque)
    public void adicionarProduto(String nome, float preco, int estoque, String categoria) throws ProdutoJaExistenteException {
        Integer id = hashCode();
        if (contemProduto(id)) {
            throw new ProdutoJaExistenteException(id);
        } else {
            Categoria categoriaProduto = Produto.identificaCategoriaProduto(categoria);
            Produto produto = new Produto(nome, preco, estoque, categoriaProduto);

            this.estoque.put(id, produto);
        }
    }
    // TODO a pensar se vamos precisar...
    public void retirarProduto(Integer id) throws ProdutoInexistenteException{
        if (!contemProduto(id)) {
            throw new ProdutoInexistenteException(id);
        } else
            this.estoque.remove(id);
    }
}
