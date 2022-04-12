package poo;

import java.util.Objects;

public class Motor {
    private String tipo;
    private int potencia;

    public Motor(String tipo){
        if(Objects.equals(tipo, "turbina") || Objects.equals(tipo, "helice")){
            this.tipo = tipo;
        }
    }

    public int getPotencia() {
        return potencia;
    }

    // Permite setar a potencia do motor para 0% quando o aviao estiver desligado e 10% quando ligado
    public void setPotencia(int potencia) {
        if(potencia == 0 || potencia == 10){
            this.potencia = potencia;
        }
    }

    /**
     * Aumenta a potencia do motor.
     * @param p Quanto deve ser aumentado.
     * @return Se a operacao foi ou nao, um sucesso.
     */
    public boolean aumentaPotencia(int p){
        if(p > 0 && p <= 100 && this.potencia + p <= 100){
            this.potencia += p;
            return true;
        } else
            return false;
    }

    /**
     * Reduz a potencia do motor.
     * @param p Quanto deve ser reduzido.
     * @return Se a operacao foi um sucesso ou nao.
     */
    public boolean diminuiPotencia(int p){
        if(p > 0 && p <= 100 && this.potencia - p >= 0){
            this.potencia -= p;
            return true;
        } else
            return false;
    }
}
