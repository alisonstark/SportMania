package Comunicacao;

import Excecoes.Estoque.*;
import Excecoes.TabelaException;
import Produto.*;

import java.io.*;
import java.util.List;

public class Estoque extends Tabela<String, Produto> implements ArmazenaObjetos, FiltroPor<Produto, Categoria> {
    private String bancoDeDados;

    public Estoque(String caminho) {
        this.bancoDeDados = caminho;
    }

    public void setBancoDeDados(String caminho) {
        bancoDeDados = caminho;
    }

    /**
     * Le os objetos {@code Produto}s do banco de dados, do arquivo "estoque.ser" e armazena todos em uma
     * {@code Hashtable}, em que a chave eh a combinacao da categoria e do nome do produto.
     *
     * @throws IOException Caso ocorra erro na leitura do arquivo
     * @throws ClassNotFoundException
     * @throws TabelaException
     */
    public void carregarObjetos() throws IOException, ClassNotFoundException, TabelaException {
        FileInputStream fis = new FileInputStream(bancoDeDados);
        ObjectInputStream ois = new ObjectInputStream(fis);

        while (fis.available() > 0) {
            Produto produto = (Produto) ois.readObject();
            adicionar(produto);
        }
        ois.close();
    }

    /**
     * Sobreescreve o arquivo do banco de dados com todos os produtos, atualizando-o.
     *
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void atualizarObjetos() throws IOException {
        FileOutputStream fos = new FileOutputStream(bancoDeDados);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Produto produto : retornarValores())
            oos.writeObject(produto);
        oos.close();
    }

    /**
     * Adiciona um novo produto no estoque.
     *
     * @param nome Nome do produto
     * @param preco Preco do produto
     * @param estoque Estoque do produto
     * @param categoria Categoria do produto
     * @return Identificador do produto
     * @throws TabelaException Se o produto ja existir
     */
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
        else {
            Produto produto = procurar(identificador);
            produto.setEstoque(0);
        }
    }

    /**
     * Devolve o objeto do {@code Produto} de acordo com o seu CPF
     *
     * @param identificador Identificador do produto
     * @return O {@code Produto}
     * @throws TabelaException Se o produto não existir
     */
    @Override
    public Produto procurar(String identificador) throws TabelaException {
        if (!contem(identificador))
            throw new ProdutoInexistenteException(identificador);
        else
            return super.procurar(identificador);
    }

    public List<Produto> filtrarPor(Categoria categoria) {
        return retornarValores()
                .stream()
                .filter(produto -> produto.getCategoria() == categoria)
                .toList();
    }

    public List<Produto> filtarPor(Boolean atividade) {
        return retornarValores()
                .stream()
                .filter(produto -> produto.temEstoque() == atividade)
                .toList();
    }

}
