package poo;

/**
 * Interface que representa as caracteristicas um veiculo anfibio
 */
public interface VeiculoAnfibio extends VeiculoMarinho{
    /**
     * Permite recolher as rodas do veiculo
     * @return Se foi possivel recolher as rodas
     */
    boolean recolherRodas();

    /**
     * Permite abrir as rodas do veiculo
     * @return Se foi possivel abrir as rodas
     */
    boolean abrirRodas();
}
