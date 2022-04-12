package poo;

import java.util.HashMap;
import java.util.Objects;

public class Rack {
    private HashMap<String, MaqReal> maquinas;

    public Rack(){
        maquinas = new HashMap<>();
    }

    /**
     * Adiciona um computador ao RACK.
     * @param maq A maquina a ser adicionada.
     * @return O status da operacao.
     */
    public boolean addMaq(MaqReal maq){
        if(! this.maquinas.containsKey(maq.getNome()) && this.maquinas.size() < 5){
            if(maq.getDisco() > 0 && maq.getRam() > 0){
                this.maquinas.put(maq.getNome(), maq);
                return true;
            }
        }

        return false;
    }

    /**
     * Remove um computador do RACK.
     * @param nome Nome da maquina a ser removida.
     * @return O status da operacao.
     */
    public boolean removeMaq(String nome){
        if(this.maquinas.containsKey(nome)){
            this.maquinas.remove(nome);
            return true;
        } else
            return false;
    }

    /**
     * Liga ou desliga o computador.
     * @param nome Nome do computador a ser ligado ou desligado.
     * @return Se o computador foi ligado ou desligado ou se ele nao existe.
     */
    public String ligDeslMaq(String nome){
        try {
            if(Objects.equals(this.maquinas.get(nome).getStatus(), "Desligado")){
                this.maquinas.get(nome).setStatus("Ligado");
            } else{
                this.maquinas.get(nome).setStatus("Desligado");
            }

            return this.maquinas.get(nome).getStatus();
        } catch (Exception e){
            return "nao existe.";
        }
    }

    /**
     * Adiciona uma maquina virtual ao computador indicado.
     * @param real Nome do computador.
     * @param virtual A maquina virtual a ser adicionada.
     * @return O status da operacao.
     */
    public boolean addVirtual(String real, MaqVirtual virtual){
        if(this.maquinas.containsKey(real)){
            return this.maquinas.get(real).addVirtual(virtual);
        } else
            return false;
    }

    /**
     * Remove uma maquina virtual do computador indicado.
     * @param real Nome do computador.
     * @param virtual Nome da maquina virtual.
     * @return O status da operacao
     */
    public boolean removeVirtual(String real, String virtual){
        if(this.maquinas.containsKey(real)){
            return this.maquinas.get(real).removeVirtual(virtual);
        } else
            return false;
    }

    /**
     * Liga ou desliga a maquina virtual no computador indicado.
     * @param real Nome do computador.
     * @param virtual Nome da maquina virtual.
     * @return Se a maquina virtual foi ligada ou desligada ou se ela nao existe.
     */
    public String ligaDesligVirtual(String real, String virtual){
        if(this.maquinas.containsKey(real)){
            return this.maquinas.get(real).ligaDeslig(virtual);
        } else
            return "nao existe.";
    }

    @Override
    public String toString() {
        return "\n" + this.maquinas.keySet() + "\n";
    }

    public String listaMaq(){
        return "\n" + this.maquinas + "\n";
    }
}
