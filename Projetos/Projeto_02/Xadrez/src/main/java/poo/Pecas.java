package poo;

import java.util.ArrayList;

/**
 * Classe abstrata que representa caracteristicas de uma peca de xadrez
 */
public abstract class Pecas {
    public int x;
    public int y;
    Cor cor;
    public int jogadas;
    public static final int LIMMIN = 0;
    public static final int LIMMAX = 8;
    public String nome;

    /**
     * Metodo construtor da classe Pecas
     * @param x A posicao no eixo X
     * @param y A posicao no eixo Y
     * @param c O codigo da cor da peca
     * @param nome O nome da peca
     */
    public Pecas(int x, int y, int c, String nome){
        this.x = x;
        this.y = y;
        this.cor = Cor.getCor(c);
        this.nome = nome + Cor.getNome(c);
    }

    /**
     * Metodo base que permite que uma peca coma outra
     * @param xAux A posicao X da peca a ser comida
     * @param yAux A posicao Y da peca a ser comida
     * @param pecas A lista com todas as pecas atualmente no jogo
     * @return Se foi possivel comer a peca
     */
    public abstract boolean comer(int xAux, int yAux, ArrayList<Pecas> pecas);

    /**
     * Metodo base que permite a movimentacao de uma peca
     * @param xAux A posicao X para a onde a peca vai
     * @param yAux A posicao Y para onde a peca vai
     * @param pecas A lista com todas as pecas atualmente no jogo
     * @return Se foi possivel completar o movimento
     */
    public abstract boolean movePeca(int xAux, int yAux, ArrayList<Pecas> pecas);
}
