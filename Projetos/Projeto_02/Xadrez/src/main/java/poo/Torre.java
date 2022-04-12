package poo;

import java.util.ArrayList;

/**
 * Classe Torre que herda a classe Pecas
 */
public class Torre extends Pecas{
    /**
     * Metodo construtor da classe Torre
     * @param x A posicao no eixo X
     * @param y A posicao no eixo Y
     * @param c O codigo da cor da peca
     */
    public Torre(int x, int y, int c) {
        super(x, y, c, "torre");
    }

    /**
     * Adaptacao do metodo 'comer' para a classe Torre
     * @param xAux A posicao X da peca a ser comida
     * @param yAux A posicao Y da peca a ser comida
     * @param pecas A lista com todas as pecas atualmente no jogo
     * @return Se foi possivel comer a peca
     */
    @Override
    public boolean comer(int xAux, int yAux, ArrayList<Pecas> pecas) {
        for(Pecas elemento: pecas){
            if(yAux == elemento.y && xAux == elemento.x && super.cor != elemento.cor){
                // Remove a peca comida
                pecas.remove(elemento);

                // Coloca a torre no lugar da peca comida
                super.x = xAux;
                super.y = yAux;
                super.jogadas++;

                return true;
            }
        }

        return false;
    }

    /**
     * Adaptacao do metodo 'movePeca' para a classe Torre
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

        // Para direita
        if(xAux - super.x > 0 && yAux - super.y == 0){
            mover = true;

            for(int i = (super.x + 1); i <= xAux; i++){
                for(Pecas elemento: pecas){
                    if(i == elemento.x && yAux == elemento.y){
                        return this.comer(i, yAux, pecas);
                    }
                }
            }

        // Para esquerda
        } else if(super.x - xAux > 0 && yAux - super.y == 0) {
            mover = true;

            for(int i = (super.x - 1); i >= xAux; i--){
                for(Pecas elemento: pecas){
                    if(i == elemento.x && yAux == elemento.y){
                        return this.comer(i, yAux, pecas);
                    }
                }
            }

        //  Para cima
        } else if(yAux - super.y > 0 && super.x - xAux == 0){
            mover = true;

            for(int i = (super.y + 1); i <= yAux; i++){
                for(Pecas elemento: pecas){
                    if(i == elemento.y && xAux == elemento.x){
                        return this.comer(xAux, i, pecas);
                    }
                }
            }

        // Para baixo
        } else if(super.y - yAux > 0 && super.x - xAux == 0){
            mover = true;

            for(int i = (super.y - 1); i >= yAux; i--){
                for(Pecas elemento: pecas){
                    if(i == elemento.y && xAux == elemento.x){
                        return this.comer(xAux, i, pecas);
                    }
                }
            }
        }

        if(mover){
            // Coloca a torre na nova posicao
            super.x = xAux;
            super.y = yAux;
            super.jogadas++;

            return true;
        }

        return false;
    }
}
