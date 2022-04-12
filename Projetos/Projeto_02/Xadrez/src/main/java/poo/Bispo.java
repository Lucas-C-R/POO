package poo;

import java.util.ArrayList;

/**
 * Classe Bispo que herda a classe Pecas
 */
public class Bispo extends Pecas{
    /**
     * Metodo construtor da classe Bispo
     * @param x A posicao no eixo X
     * @param y A posicao no eixo Y
     * @param c O codigo da cor da peca
     */
    public Bispo(int x, int y, int c) {
        super(x, y, c, "bispo");
    }

    /**
     * Adaptacao do metodo 'comer' para a classe Bispo
     * @param xAux A posicao X da peca a ser comida
     * @param yAux A posicao Y da peca a ser comida
     * @param pecas A lista com todas as pecas atualmente no jogo
     * @return Se foi possivel comer a peca
     */
    @Override
    public boolean comer(int xAux, int yAux, ArrayList<Pecas> pecas) {
        for(Pecas elemento: pecas){
            if(super.cor != elemento.cor && yAux == elemento.y && xAux == elemento.x){
                // Remove a peca comida
                pecas.remove(elemento);

                // Coloca o bispo no lugar da peca comida
                super.x = xAux;
                super.y = yAux;
                super.jogadas++;

                return true;
            }
        }

        return false;
    }

    /**
     * Adaptacao do metodo 'movePeca' para a classe Bispo
     * @param xAux A posicao X para a onde a peca vai
     * @param yAux A posicao Y para onde a peca vai
     * @param pecas A lista com todas as pecas atualmente no jogo
     * @return Se foi possivel completar o movimento
     */
    @Override
    public boolean movePeca(int xAux, int yAux, ArrayList<Pecas> pecas) {
        if(xAux < LIMMIN || xAux > LIMMAX || yAux < LIMMIN || yAux > LIMMAX){
            return false;
        }

        boolean mover = false;

        // Para a diagonal superior direita
        if(xAux - super.x == yAux - super.y && xAux - super.x > 0){
            mover = true;

            for(int i = (super.x + 1), j = (super.y + 1); i <= xAux && j <= yAux; i++, j++){
                for(Pecas elemento: pecas){
                    if(i == elemento.x && j == elemento.y){
                        return this.comer(i, j, pecas);
                    }
                }
            }

        // Para a diagonal inferior direita
        } else if(xAux - super.x == super.y - yAux && xAux - super.x > 0){
            mover = true;

            for(int i = (super.x + 1), j = (super.y - 1); i <= xAux && j >= yAux; i++, j--){
                for(Pecas elemento: pecas){
                    if(i == elemento.x && j == elemento.y){
                        return this.comer(i, j, pecas);
                    }
                }
            }

        // Para a diagonal superior esquerda
        } else if(super.x - xAux == yAux - super.y && super.x - xAux > 0){
            mover = true;

            for(int i = (super.x - 1), j = (super.y + 1); i >= xAux && j <= yAux; i--, j++){
                for(Pecas elemento: pecas){
                    if(i == elemento.x && j == elemento.y){
                        return this.comer(i, j, pecas);
                    }
                }
            }

        // Para a diagonal inferior esquerda
        } else if(super.x - xAux == super.y - yAux && super.x - xAux > 0){
            mover = true;

            for(int i = (super.x - 1), j = (super.y - 1); i >= xAux && j >= yAux; i--, j--){
                for(Pecas elemento: pecas){
                    if(i == elemento.x && j == elemento.y){
                        return this.comer(i, j, pecas);
                    }
                }
            }
        }

        if(mover){
            // Coloca o bispo na nova posicao
            super.x = xAux;
            super.y = yAux;
            super.jogadas++;

            return true;
        }

        return false;
    }
}
