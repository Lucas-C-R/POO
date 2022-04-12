package poo;

import java.util.ArrayList;

/**
 * Classe Peao que herda a classe Pecas
 */
public class Peao extends Pecas{
    /**
     * Metodo construtor da classe Peao
     * @param x A posicao no eixo X
     * @param y A posicao no eixo Y
     * @param c O codigo da cor da peca
     */
    public Peao(int x, int y, int c) {
        super(x, y, c, "peao");
    }

    /**
     * Adaptacao do metodo 'comer' para a classe Peao
     * @param xAux A posicao X da peca a ser comida
     * @param yAux A posicao Y da peca a ser comida
     * @param pecas A lista com todas as pecas atualmente no jogo
     * @return Se foi possivel comer a peca
     */
    @Override
    public boolean comer(int xAux, int yAux, ArrayList<Pecas> pecas) {
        if(yAux - super.y == 1 || super.y - yAux == 1){
            for(Pecas elemento: pecas){
                // Se houver uma peca no local e de cor diferente
                if(elemento.x == xAux && elemento.y == yAux && elemento.cor != super.cor){
                    // Remove a peca comida
                    pecas.remove(elemento);

                    // Coloca o peao no lugar da peca comida
                    super.x = xAux;
                    super.y = yAux;
                    super.jogadas++;

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Adaptacao do metodo 'movePeca' para a classe Peao
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

        switch (super.cor){
            case BRANCO:
                if(super.jogadas == 0 && yAux - super.y == 2){
                    mover = true;
                } else if(yAux - super.y == 1){
                    mover = true;
                }

                if(mover){
                    if(super.x - xAux == 1 || xAux - super.x == 1){
                        return this.comer(xAux, yAux, pecas);
                    }

                    for(int i = super.y + 1; i <= yAux; i++){
                        // Se houver uma peca no caminho
                        for(Pecas elemento: pecas){
                            if(elemento.x == super.x && elemento.y == i){
                                return false;
                            }
                        }
                    }

                    // Tira a peca de onde estava e coloca na nova posicao
                    super.y = yAux;
                    super.jogadas++;

                    return true;
                }
                break;

            case PRETO:
                if(super.jogadas == 0 && super.y - yAux == 2){
                    mover = true;
                } else if(super.y - yAux == 1){
                    mover = true;
                }

                if(mover){
                    if(super.x - xAux == 1 || xAux - super.x == 1){
                        return this.comer(xAux, yAux, pecas);
                    }

                    for(int i = yAux; i < super.y; i++){
                        // Se houver uma peca no caminho
                        for(Pecas elemento: pecas){
                            if(elemento.x == super.x && elemento.y == i){
                                return false;
                            }
                        }
                    }

                    // Tira a peca de onde estava e coloca na nova posicao
                    super.y = yAux;
                    super.jogadas++;

                    return true;
                }
                break;

            default:
                break;
        }

        return false;
    }
}
