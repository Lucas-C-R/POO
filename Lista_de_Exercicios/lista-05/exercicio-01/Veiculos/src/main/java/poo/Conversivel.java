package poo;

/**
 * Interface que representa as caracteristicas de um conversivel
 */
public interface Conversivel extends VeiculoTerrestre {
    /**
     * Permite abrir a capota do veiculo
     * @return Se foi possivel abrir a capota
     */
    boolean abrirCapota();

    /**
     * Permite fechar a capota do veiculo
     * @return Se foi possivel fechar a capota
     */
    boolean fecharCapota();
}
