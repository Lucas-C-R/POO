package poo;

import java.util.ArrayList;
import java.util.Objects;

public class Aviao {
    private ArrayList<Motor> motores;
    private int peso;
    private int maxTripulantes;
    private int maxPassageiros;
    private Manche manche;
    private String ligaDeslig;

    public Aviao(ArrayList<Motor> mot, int peso, int tripulantes, int passageiros){
        this.peso = peso;
        this.maxTripulantes = tripulantes;
        this.maxPassageiros = passageiros;
        this.ligaDeslig = "Desligado";

        if(mot.size() > 0 && mot.size() <= 8){
            if(mot.size() == 1){
                this.motores = mot;
            } else if(mot.size() % 2 == 0){
                this.motores = mot;
            }
        }

        this.manche = new Manche(this.motores);
    }

    // Serve para informar a classe principal, se o aviao foi criado com sucesso
    public ArrayList<Motor> getMotores() {
        return motores;
    }

    /**
     * Usa o manche para aumentar a velocidade do aviao.
     * @param v Em quanto sera aumentada a velocidade.
     * @param motor Index do motor a ser alterado.
     * @return Se a operacao foi realizada com sucesso ou nao.
     */
    public boolean aumentaVelocidade(int v, int motor){
        if(Objects.equals(this.ligaDeslig, "Ligado")){
            return this.manche.aceleraAviao(v, motor);
        } else
            return false;
    }

    /**
     * Usa o manche para reduzir a velocidade do aviao.
     * @param v Em quanto sera reduzida a velocidade.
     * @param motor Index do motor a ser alterado.
     * @return Se a operacao foi realizada com sucesso ou nao.
     */
    public boolean diminuiVelocidade(int v, int motor){
        if(Objects.equals(this.ligaDeslig, "Ligado")){
            return this.manche.reduzAviao(v, motor);
        } else
            return false;
    }

    /**
     * Liga ou desliga o aviao.
     * @return Se o aviao esta ligado ou desligado.
     */
    public String botao(){
        if(Objects.equals(this.ligaDeslig, "Desligado")){
            this.motores.forEach(motor -> motor.setPotencia(10));
            this.ligaDeslig = "Ligado";
            return "Aviao ligado.";
        } else{
            this.motores.forEach(motor -> motor.setPotencia(0));
            this.ligaDeslig = "Desligado";
            return "Aviao desligado.";
        }
    }

    /**
     * Permite ao piloto, mudar a direcao do aviao.
     * @param direc A direcao que aviao deve seguir.
     * @param inten A intensidade a forca aplicada.
     * @return Se o aviao conseguiu ou nao, mudar de direcao.
     */
    public String direcAviao(String direc, int inten){
        if(Objects.equals(this.ligaDeslig, "Ligado") && inten > 0){
            if(Objects.equals(direc, "cima") || Objects.equals(direc, "baixo") || Objects.equals(direc, "direita") || Objects.equals(direc, "esquerda")){
                return "O aviao foi para " + direc + ", com uma intensidade de " + inten + "N.";
            } else
                return "O aviao nao se moveu.";
        } else
            return "O aviao nao se moveu.";
    }
}
