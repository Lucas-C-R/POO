package poo;

import java.util.ArrayList;


/**
 * Classe que representa um tabuleiro de xadrez
 */
public class Tabuleiro {
    private ArrayList<Pecas> pecas;
    private int ultX;
    private int ultY;
    private Pecas ultPec;
    private boolean pecaSelecionada = false;

    public ArrayList<Pecas> getPecas() {
        return pecas;
    }

    public int getUltX() {
        return ultX;
    }

    public void setUltX(int ultX) {
        this.ultX = ultX;
    }

    public int getUltY() {
        return ultY;
    }

    public void setUltY(int ultY) {
        this.ultY = ultY;
    }

    public Pecas getUltPec() {
        return ultPec;
    }

    public void setUltPec(Pecas ultPec) {
        this.ultPec = ultPec;
    }

    public boolean isPecaSelecionada() {
        return pecaSelecionada;
    }

    public void setPecaSelecionada(boolean pecaSelecionada) {
        this.pecaSelecionada = pecaSelecionada;
    }

    /**
     * Metodo contrutor da classe Tabuleiro
     */
    public Tabuleiro(){
        this.pecas = new ArrayList<>();
        this.ultPec = new Peao(-1, -1, 1);


        int cor = 0;

        // Adicionando as pecas ao tabuleiro
        for(int k = 0; k < 8; k = k + 7){
            this.pecas.add(new Torre(0, k, cor));
            this.pecas.add(new Cavalo(1, k, cor));
            this.pecas.add(new Bispo(2, k, cor));
            this.pecas.add(new Rainha(3, k, cor));
            this.pecas.add(new Rei(4, k, cor));

            switch (cor){
                case 0:
                    for(int i = 0; i < 8; i++){
                        this.pecas.add(new Peao(i, k + 1, cor));
                    }
                    break;
                case 1:
                    for(int i = 0; i < 8; i++){
                        this.pecas.add(new Peao(i, k - 1, cor));
                    }
                    break;
            }

            this.pecas.add(new Bispo(5, k, cor));
            this.pecas.add(new Cavalo(6, k, cor));
            this.pecas.add(new Torre(7, k, cor));

            cor++;
        }
    }

    /**
     * Verifica se deve encerrar o jogo
     * @return Se o jogo esta encerrado ou nao
     */
    public boolean encerraJogo(){
        boolean reiBranco = false;
        boolean reiPreto = false;

        for(Pecas elemento: this.pecas){
            // Verifica se o rei branco ainda esta no jogo
            if(elemento.nome.equals("rei_branco.png")){
                reiBranco =  true;

            // Verifica se o rei preto ainda esta no jogo
            } else if(elemento.nome.equals("rei_preto.png")){
                reiPreto = true;
            }
        }


        return !reiBranco || !reiPreto;
    }
}
