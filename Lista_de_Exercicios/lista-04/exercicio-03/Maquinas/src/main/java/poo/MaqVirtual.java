package poo;

public class MaqVirtual {
    private String status;
    private String nome;
    private int disco;
    private int ram;

    public MaqVirtual(String nome, int disco, int ram){
        this.status = "Ligado";
        this.nome = nome;
        this.disco = disco;
        this.ram = ram;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return this.status;
    }
}
