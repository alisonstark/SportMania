package Produto;

public enum Categoria {
    // https://www.devmedia.com.br/enums-no-java/38764
    ROUPA("roupa"),
    CALCADO("calcado"),
    ACESSORIO("acessorio"),
    EQUIPAMENTO("equipamento"),
    DESCONHECIDO("desconhecido");

    private final String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return this.categoria;
    }

    /**
     * Retorna a cosntante correta da enumeracao de acordo com a string dada.
     *
     * @param categoria Uma {@code String} com a categoria do produto
     * @return A constante da categoria
     */
    public static Categoria mapearString(String categoria) {
        switch (categoria) {
            case "roupa" -> {
                return ROUPA;
            }
            case "calcado" -> {
                return CALCADO;
            }
            case "acessorio" -> {
                return ACESSORIO;
            }
            case "equipamento" -> {
                return EQUIPAMENTO;
            }
            default -> {
                return DESCONHECIDO;
            }
        }
    }

}