package poo;

/**
 * Interface que representa as caracteristicas da tracao integral
 */
public interface TracaoIntegral extends VeiculoTerrestre {
    /**
     * Permite ativar ou desativar a tracao
     * @return Se o processo foi um sucesso ou nao
     */
    boolean ativarDesativarTracao();
}
