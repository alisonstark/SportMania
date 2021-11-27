package Produto;

import java.io.*;
import java.util.Hashtable;

public class Produtos {

    private static String bancoDeDados = "/BancoDeDados/produtos.ser";
    public static Hashtable<Integer, Produto> produtos;

    public static void setBancoDeDados(String caminho) {
        bancoDeDados = caminho;
    }

    public static void carregarProdutos() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(bancoDeDados);

        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Produto produto = (Produto) ois.readObject();
                produtos.put(produto.getId(), produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void atualizarProdutos() throws IOException {
        FileOutputStream fos = new FileOutputStream(bancoDeDados);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Produto produto : produtos.values()) {
            oos.writeObject(produto);
        }
        oos.close();
    }

    // TODO definir como adicionar produto
    public static void adicionarProduto(/* TODO definir parâmetro */) {

    }

    // TODO definir como retirar produto
    public static void retirarProduto(/* TODO definir parâmetro */) {

    }

}
