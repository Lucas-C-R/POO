package poo;

/**
 * Interface que representa as caracteristicas de um veiculo terrestre
 */
public interface VeiculoTerrestre {
    /**
     * Permite frear o veiculo
     * @param i Quanto quer se reduzir
     */
    void frear(int i);

    /**
     * Permite acelerar o veiculo
     * @param i O quanto se quer acelerar
     */
    void acelerar(int i);
}
