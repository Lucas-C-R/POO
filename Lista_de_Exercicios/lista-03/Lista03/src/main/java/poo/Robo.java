package poo;

import java.util.*;

public class Robo {
    /**
     * Area do quadrado.
     */
    private int area;

    /**
     * Coordenada do robo no eixo X.
     */
    private int coordX;

    /**
     * Coordenada do robo no eixo Y.
     */
    private int coordY;

    /**
     * Direcao apontada pelo robo.
     */
    private String frente;

    /**
     * Quantos movimentos o robo ainda pode fazer.
     */
    private int quantMov;

    /**
     * Quantas unidades o robo ira caminhar ao se movimentar.
     */
    private int unidadeCamin;

    /**
     * Quantidade maxima de movimentos que o robo podera efetuar.
     */
    private final int MOVIMENTOMAX = 100;

    /**
     * Lista contendo as coordenadas que o robo ja passou.
     */
    private LinkedList<String> coordenadas = new LinkedList<>();

    /**
     * Plano de exploracao do robo.
     */
    private StringBuilder plano = new StringBuilder();

    /**
     * Quantidade de comandos do plano de exploracao que ja foram analisados.
     */
    private int planoAnalisado;

    public int getArea() {
        return area;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public String getFrente() {
        return frente;
    }

    public int getQuantMov() {
        return quantMov;
    }

    public int getUnidadeCamin() {
        return unidadeCamin;
    }

    public String getPlano() {
        return plano.toString();
    }

    public String coordenadaAtual(){
        return coordenadas.getLast();
    }

    public String coordenadaAnterior(){
        if(this.coordenadas.size() == 1){
            return this.coordenadas.getLast();
        } else
            return this.coordenadas.get(this.coordenadas.size() - 2);
    }

    /**
     * Metodo construtor do robo.
     * @param area Area a ser explorada.
     * @param x Coordenada x.
     * @param y Coordenada y.
     * @param direc Direcao apontada pelo robo.
     * @param movimento Quantos movimentos o robo fara ate acabar a bateria.
     * @param unidades Quantas unidades ele ira andar por vez.
     */
    public Robo(int area, int x, int y, String direc, int movimento, int unidades){
        this.area = area;
        this.unidadeCamin = unidades;

        // Verifica se as coordenadas X e Y estao dentro da area de exploracao
        if(x >= 0 && x <= area && y >= 0 && y <= area){
            this.coordX = x;
            this.coordY = y;
        }else{
            // Se nao estiverem, os coloca em coordenadas aleatorias dentro dela

            Random r = new Random();

            this.coordX = r.nextInt(area);
            this.coordY = r.nextInt(area);

            direc = "N";
        }

        this.coordenadas.add(this.coordX + "," + this.coordY);

        // Verifica se a direcao esta correta
        if(Objects.equals(direc, "N") || Objects.equals(direc, "S") || Objects.equals(direc, "L") || Objects.equals(direc, "O")){
            this.frente = direc;
        } else{
            this.frente = "N";
        }

        // Verifica se a "bateria" esta entre o minimo e o maximo permitido
        if(movimento >= 0 && movimento <= this.MOVIMENTOMAX){
            this.quantMov = movimento;
        } else{
            if(movimento < 0){
                this.quantMov = 0;
            } else{
                this.quantMov = MOVIMENTOMAX;
            }
        }
    }

    /**
     * Guarda o plano de exploracao. Caso ele tenha algum erro, o elimina.
     * @param plano Plano de exploracao.
     */
    public void planoExploracao(String plano){
        if(Objects.equals(plano, "E") || Objects.equals(plano, "D") || Objects.equals(plano, "M")){
            this.plano.append(plano);
        }
    }

    /**
     * Fornece os comandos que ainda precisam ser executados pelo robo.
     * @return Plano de exploracao com os comandos nao executados.
     */
    public String comandosNaoRealizados(){
        if(this.planoAnalisado != this.plano.length()){
            return this.plano.substring(this.planoAnalisado);
        } else
            return "";
    }

    /**
     * Metodo que permite girar o robo.
     * @param direc Direcao que ele vai girar (Direita ou Esquerda).
     * @return Ponto cardeal que o robo esta olhando (Norte, Sul, Leste ou Oeste)
     */
    public String giraRobo(String direc){
        boolean girou = false;

        // Verifica se o robo esta combateria
        if(this.quantMov > 0){
            switch (direc){
                case "D":
                    switch (this.frente){
                        case "N":
                            this.frente = "L";
                            girou = true;
                            break;

                        case "L":
                            this.frente = "S";
                            girou = true;
                            break;

                        case "S":
                            this.frente = "O";
                            girou = true;
                            break;

                        case "O":
                            this.frente = "N";
                            girou = true;
                            break;
                    }

                    break;

                case "E":
                    switch (this.frente){
                        case "N":
                            this.frente = "O";
                            girou = true;
                            break;

                        case "O":
                            this.frente = "S";
                            girou = true;
                            break;

                        case "S":
                            this.frente = "L";
                            girou = true;
                            break;

                        case "L":
                            this.frente = "N";
                            girou = true;
                            break;
                    }

                    break;
            }
        }

        if(girou){
            this.quantMov--;
            this.coordenadas.add(this.coordX + "," + this.coordY);
        }

        return this.frente;
    }

    /**
     * Metodo que permite movimentar o robo.
     * @return Se o robo se moveu ou nao.
     */
    public boolean deslocaRobo(){
        boolean moveu = false;

        // Verifica se o robo esta com bateria
        if(this.quantMov > 0){
            switch (this.frente){
                case "N":
                    if(this.coordY + this.unidadeCamin <= this.area){
                        this.coordY += this.unidadeCamin;
                        moveu = true;
                    }
                    break;

                case "L":
                    if(this.coordX + this.unidadeCamin <= this.area){
                        this.coordX += this.unidadeCamin;
                        moveu = true;
                    }
                    break;

                case "S":
                    if(this.coordY - this.unidadeCamin >= 0){
                        this.coordY -= this.unidadeCamin;
                        moveu = true;
                    }
                    break;

                case "O":
                    if(this.coordX - this.unidadeCamin >= 0){
                        this.coordX -= this.unidadeCamin;
                        moveu = true;
                    }
                    break;
            }
        }

        if(moveu){
            this.quantMov--;
            this.coordenadas.add(this.coordX + "," + this.coordY);
        }

        return moveu;
    }

    /**
     * Executa o plano de exploracao.
     * @return Se executou o comando ou se eles acabaram.
     */
    public boolean executaPlano(){
        boolean executou = false;

        // Verifica se o robo esta com bateria
        if(this.quantMov > 0){
            // Verifica se o robo ja executou todos os comandos
           if(this.planoAnalisado != this.plano.length()){
               String comando = this.plano.substring(this.planoAnalisado, this.planoAnalisado + 1);

               switch (comando){
                   case "E":
                       this.giraRobo("E");
                       executou = true;
                       break;

                   case "D":
                       this.giraRobo("D");
                       executou = true;
                       break;

                   case "M":
                       executou = this.deslocaRobo();
                       break;
               }
           }
       }

        this.planoAnalisado++;

        return executou;
    }
}