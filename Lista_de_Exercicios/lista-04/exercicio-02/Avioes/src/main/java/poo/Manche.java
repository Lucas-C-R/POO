package poo;

import java.util.ArrayList;

public class Manche {
    private ArrayList<Motor> motores;

    public Manche(ArrayList<Motor> mot){
        this.motores = mot;
    }

    /**
     * Aumenta a potencia no motor desejado.
     * @param v Quanto deve ser aumentado.
     * @param mot Qual motor sera alterado.
     * @return Se a operacao foi um sucesso ou nao.
     */
    public boolean aceleraAviao(int v, int mot){
        try {
            return this.motores.get(mot).aumentaPotencia(v);
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Reduz a potencia no motor desejado.
     * @param v Quanto deve ser reduzido.
     * @param mot Qual motor sera alterado.
     * @return Se a operacao foi um sucesso ou nao.
     */
    public boolean reduzAviao(int v, int mot){
        try {
            return this.motores.get(mot).diminuiPotencia(v);
        } catch (Exception e){
            return false;
        }
    }
}
