package poo;

import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.DrawListener;

import java.awt.*;

/**
 * Classe que ira controlar o jogo de xadrez
 */
public class Jogo implements DrawListener {
    private Draw draw;
    private final int DIMENSAO;
    private Tabuleiro tabuleiro;

    /**
     * Metodo construtor da classe Jogo
     * @param dimensao As dimensoes maximas do tabuleiro
     */
    public Jogo(int dimensao){
        this.draw = new Draw();
        this.DIMENSAO = dimensao;
        this.tabuleiro = new Tabuleiro();

        this.draw.addListener(this);
        this.draw.setXscale(0, this.DIMENSAO);
        this.draw.setYscale(0, this.DIMENSAO);
        this.draw.enableDoubleBuffering();

        desenharTela(-1, -1);
    }

    /**
     * Deseha o tabuleiro de xadrez
     * @param x A coordenada X da peca que foi selecionada pelo usuario, se nao houver, por padrao, 'x' sera igual a -1
     * @param y A coordenada Y da peca que foi selecionada pelo usuario, se nao houver, por padrao, 'y' sera igual a -1
     */
    public void desenharTabuleiro(int x, int y){
        this.draw.setPenColor(Color.gray);

        switch (x){
            // Se nao houver pecas selecionadas
            case -1:
                for(int i = 0; i <= this.DIMENSAO; i++){
                    for(int j = 0; j <= this.DIMENSAO; j++){
                        // Se a linha for impar, entao os quadrados pretos ficarao em posicoes pares
                        if(i % 2 != 0){
                            if(j % 2 != 0) this.draw.filledSquare(i + .5, j + .5, .5);

                        // Se a linha for par, entao os quadrados pretos ficarao em posicoes impares
                        } else if(j % 2 == 0) this.draw.filledSquare(i + .5, j + .5, .5);
                    }
                }
                break;

            // Se uma peca for selecionada
            default:
                for(int i = 0; i < this.DIMENSAO; i++){
                    for(int j = 0; j <= this.DIMENSAO; j++){
                        // Se 'i' e 'j' forem iguais a coordenada da peca selecionada, desenha um quadrado azul no local
                        if(i == x && j == y) {
                            this.draw.setPenColor(Color.blue);
                            this.draw.filledSquare(i + .5, j + .5, .5);
                            this.draw.setPenColor(Color.gray);

                        // Se a linha for impar, entao os quadrados pretos ficarao em posicoes pares
                        } else if(i % 2 != 0){
                            if(j % 2 != 0)
                                this.draw.filledSquare(i + .5, j + .5, .5);

                        // Se a linha for par, entao os quadrados pretos ficarao em posicoes impares
                        } else if(j % 2 == 0)
                            this.draw.filledSquare(i + .5, j + .5, .5);
                    }
                }
                break;
        }

    }

    /**
     * Desenha as pecas de xadrez que ainda estao no jogo
     */
    public void desenhaPecas(){
        for(Pecas elemento: this.tabuleiro.getPecas()){
            this.draw.picture(elemento.x + .5, elemento.y + .5, elemento.nome);
        }
    }

    /**
     * Desenha toda a tela do jogo, ou seja, o tabuleiro e as pecas
     * @param x A coordenada X da peca que foi selecionada pelo usuario, se nao houver, por padrao, 'x' sera igual a -1
     * @param y A coordenada Y da peca que foi selecionada pelo usuario, se nao houver, por padrao, 'y' sera igual a -1
     */
    public void desenharTela(int x, int y){
        this.draw.clear(Color.WHITE);
        this.desenharTabuleiro(x, y);
        this.desenhaPecas();
        this.draw.show();
    }

    /**
     * Captura o evento de botão pressionado do mouse
     * @param x coordenada X do cursor do mouse quando o botão foi pressionado
     * @param y coordenada Y do cursor do mouse quando o botão foi pressionado
     */
    @Override
    public void mousePressed(double x, double y) {

        for(Pecas elemento: this.tabuleiro.getPecas()){
            // Verifica se a pessoa selecionou uma casa ocupada
            if((Math.floor(x) == elemento.x) && (Math.floor(y) == elemento.y)){
                // Verifica se ja ha uma peca selecionada
                if(!this.tabuleiro.isPecaSelecionada() && elemento.cor != this.tabuleiro.getUltPec().cor){
                    this.tabuleiro.setPecaSelecionada(true);

                    this.desenharTela(elemento.x, elemento.y);
                    this.tabuleiro.setUltX(elemento.x);
                    this.tabuleiro.setUltY(elemento.y);

                    break;

                // Se for clicado novamente na casa selecionada, desmarca ela
                } else if(elemento.x == this.tabuleiro.getUltX() && elemento.y == this.tabuleiro.getUltY()){
                    this.desenharTela(-1, -1);

                    this.tabuleiro.setPecaSelecionada(false);

                    break;
                }

            // Se a pessoa clicar em uma casa depois de ter selecionado uma peca, tenta mover a peca para la
            } else if(this.tabuleiro.isPecaSelecionada() && elemento.x == this.tabuleiro.getUltX() && elemento.y == this.tabuleiro.getUltY()){
                // Verifica se e possivel mover a peca
                if(elemento.movePeca((int) x, (int) y, this.tabuleiro.getPecas())){
                    // Se um dos reis morrerem, reseta o jogo
                    if(this.tabuleiro.encerraJogo()){
                        this.tabuleiro = new Tabuleiro();
                    } else{
                        this.tabuleiro.setUltPec(elemento);
                    }

                    this.desenharTela(-1, -1);
                    this.tabuleiro.setPecaSelecionada(false);
                }
                break;
            }
        }
    }


    public static void main(String[] args) {
        Jogo x = new Jogo(8);
    }

    // Métodos da interface DrawListener que não serão usados neste jogo
    @Override
    public void mouseDragged(double v, double v1) {}

    @Override
    public void mouseReleased(double v, double v1) {}

    @Override
    public void mouseClicked(double v, double v1) {}

    @Override
    public void keyTyped(char c) {}

    @Override
    public void keyPressed(int i) {}

    @Override
    public void keyReleased(int i) {}
}
