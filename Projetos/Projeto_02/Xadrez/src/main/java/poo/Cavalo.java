package poo;

import java.util.ArrayList;

/**
 * Classe Cavalo que herda a classe Pecas
 */
public class Cavalo extends Pecas{
    /**
     * Metodo construtor da classe Cavalo
     * @param x A posicao no eixo X
     * @param y A posicao no eixo Y
     * @param c O codigo da cor da peca
     */
    public Cavalo(int x, int y, int c) {
        super(x, y, c, "cavalo");
    }

    /**
     * Adaptacao do metodo 'comer' para a classe Cavalo
     * @param xAux A posicao X da peca a ser comida
     * @param yAux A posicao Y da peca a ser comida
     * @param pecas A lista com todas as pecas atualmente no jogo
     * @return Se foi possivel comer a peca
     */
    @Override
    public boolean comer(int xAux, int yAux, ArrayList<Pecas> pecas) {
        for(Pecas elemento: pecas){
            if(elemento.x == xAux && elemento.y == yAux && elemento.cor != super.cor){
                // Remove a peca comida
                pecas.remove(elemento);

                // Coloca o cavalo no lugar da peca comida
                super.y = yAux;
                super.x = xAux;
                super.jogadas++;

                return true;
            }
        }

        return false;
    }

    /**
     * Adaptacao do metodo 'movePeca' para a classe Cavalo
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

        // Para cima e direita e para baixo e direita e, para cima e esquerda e para baixo e esquerda
        if((xAux - super.x == 1 || super.x - xAux == 1) && (yAux - super.y == 2 || super.y - yAux == 2)){
            mover = true;

        // Na direita, para cima e para baixo e na esquerda, para cima e para baixo
        } else if((yAux - super.y == 1 || super.y - yAux == 1) && (xAux - super.x == 2 || super.x - xAux == 2)){
            mover = true;
        }

        if(mover){
            for(Pecas elemento: pecas){
                // Se houver uma peca naquela posicao
                if(elemento.x == xAux && elemento.y == yAux){
                    return comer(xAux, yAux, pecas);
                }
            }

            // Coloca o cavalo na nova posicao
            super.x = xAux;
            super.y = yAux;

            super.jogadas++;

            return true;
        }

        return false;
    }
}
