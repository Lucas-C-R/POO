package poo;

import java.util.HashMap;
import java.util.Objects;

public class MaqReal {
    private String status;
    private String nome;
    private int disco;
    private int ram;
    private HashMap<String, MaqVirtual> maquinas;

    public MaqReal(String nome, int disco, int ram){
        this.nome = nome;
        this.disco = disco;
        this.ram = ram;
        this.status = "Ligado";
        maquinas = new HashMap<>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

        // Se o computador for desligado, entao todas as maquinas virtuais associadas a ele, tambem serao.
        if(Objects.equals(this.status, "Desligado")){
            this.maquinas.values().forEach(virtual -> virtual.setStatus("Desligado"));
        }
    }

    public String getNome() {
        return nome;
    }

    public int getDisco() {
        return disco;
    }

    public int getRam() {
        return ram;
    }

    /**
     * Adiciona uma maquina virtual.
     * @param v Maquina virtual a ser adicionada.
     * @return O status da operacao.
     */
    public boolean addVirtual(MaqVirtual v){
        if(Objects.equals(this.status, "Ligado") && ! this.maquinas.containsKey(v.getNome())){
            if(v.getDisco() > 0 && v.getRam() > 0){
                if(this.disco - v.getDisco() >= 0 && this.ram - v.getRam() >= 0){
                    this.maquinas.put(v.getNome(), v);
                    this.disco -= v.getDisco();
                    this.ram -= v.getRam();
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Remove uma maquina virtual.
     * @param nome Nome da maquina virtual a ser removida.
     * @return O status da operacao.
     */
    public boolean removeVirtual(String nome){
        if(this.maquinas.containsKey(nome) && Objects.equals(this.status, "Ligado")){
            this.disco += this.maquinas.get(nome).getDisco();
            this.ram += this.maquinas.get(nome).getRam();
            this.maquinas.remove(nome);
            return true;
        } else
            return false;
    }

    /**
     * Liga ou desliga uma maquina virtual.
     * @param nome Nome da maquina.
     * @return Se a maquina virtual foi ligada ou desligada ou se ela nao existe.
     */
    public String ligaDeslig(String nome){
        if(Objects.equals(this.status, "Ligado") && this.maquinas.containsKey(nome)){
            if(Objects.equals(this.maquinas.get(nome).getStatus(), "Ligado")){
                this.maquinas.get(nome).setStatus("Desligado");
            } else{
                this.maquinas.get(nome).setStatus("Ligado");
            }

            return maquinas.get(nome).getStatus();
        } else
            return "nao existe.";
    }

    @Override
    public String toString() {
        if(this.maquinas.isEmpty()){
            return this.status;
        } else
            return this.status + this.maquinas;
    }
}
