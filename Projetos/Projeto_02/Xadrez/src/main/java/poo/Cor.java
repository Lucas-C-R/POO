package poo;

/**
 * Enum para cores
 */
public enum Cor {
    BRANCO(0), PRETO(1);

    public final int codigo;

    /**
     * Metodo construtor
     * @param i
     */
    Cor(int i) {
        this.codigo = i;
    }

    /**
     * Le um codigo fornecido e verifica se ele esta associado a uma cor
     * @param c O codigo da cor
     * @return A cor associada ao codigo fornecido
     */
    public static Cor getCor(int c){
        for (Cor cor: Cor.values()){
            if (c == cor.codigo){
                return cor;
            }
        }
        throw new IllegalArgumentException("codigo invalido");
    }

    /**
     * Le um codigo fornecido e verifica se ele esta associado a uma cor
     * @param c O codigo da cor
     * @return O complemento do nome da peca, associado ao codigo fornecido
     */
    public static String getNome(int c){
        if(c == BRANCO.codigo){
            return "_branco.png";
        } else if(c == PRETO.codigo){
            return "_preto.png";
        }
        throw new IllegalArgumentException("codigo invalido");
    }
}
