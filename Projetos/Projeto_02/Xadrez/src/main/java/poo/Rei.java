package poo;

import java.util.ArrayList;

/**
 * Classe Rei que herda a classe Pecas
 */
public class Rei extends Pecas{
    /**
     * Metodo construtor da classe Rei
     * @param x A posicao no eixo X
     * @param y A posicao no eixo Y
     * @param c O codigo da cor da peca
     */
    public Rei(int x, int y, int c) {
        super(x, y, c, "rei");
    }

    /**
     * Adaptacao do metodo 'comer' para a classe Rei
     * @param xAux A posicao X da peca a ser comida
     * @param yAux A posicao Y da peca a ser comida
     * @param pecas A lista com todas as pecas atualmente no jogo
     * @return Se foi possivel comer a peca
     */
    @Override
    public boolean comer(int xAux, int yAux, ArrayList<Pecas> pecas) {
        for(Pecas elemento: pecas){
            if(elemento.cor != super.cor && elemento.x == xAux && elemento.y == yAux){
                // Remove a peca comida
                pecas.remove(elemento);

                // Coloca o rei no lugar da peca comida
                super.x = xAux;
                super.y = yAux;
                super.jogadas++;

                return true;
            }
        }

        return false;
    }

    /**
     * Adaptacao do metodo 'movePeca' para a classe Rei
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

        // Diagonal superior esquerda e direita
        if(yAux - super.y == 1 && (super.x - xAux == 1 || xAux - super.x == 1)){
            mover = true;

          // Diagonal inferior esquerda e direita
        } else if(super.y - yAux == 1 && (xAux - super.x == 1 || super.x - xAux == 1)){
            mover = true;

          // Para cima e para baixo
        } else if(xAux - super.x == 0 && (super.y - yAux == 1 || yAux - super.y == 1)){
            mover = true;

          // Para a direita e para a esquerda
        }  else if(super.y - yAux == 0 && (xAux - super.x == 1 || super.x - xAux == 1)){
            mover = true;
        }

        if(mover){
            for(Pecas elemento: pecas){
                if(elemento.x == xAux && elemento.y == yAux){
                    return this.comer(xAux, yAux, pecas);
                }
            }

            // Coloca o rei na nova posicao
            super.x = xAux;
            super.y = yAux;
            super.jogadas++;

            return true;
        }

        return false;
    }
}
