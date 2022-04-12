package poo;

/**
 * Classe que representa o veiculo Pampa
 */
public class Pampa extends VeiculoManual implements TracaoIntegral {
    private boolean tracao = false;
    private boolean cacamba = false;

    /**
     * Informa se a tracao esta ativa ou nao
     * @return O estado da tracao
     */
    public boolean getTracao() {
        return this.tracao;
    }

    /**
     * Informa se a cacamba esta aberta ou fechada
     * @return O estado da cacamba
     */
    public boolean getCacamba() {
        return this.cacamba;
    }

    /**
     * Metodo construtor da classe
     * @param nome Nome do veiculo
     */
    public Pampa(String nome) {
        super(nome, 140);
    }

    @Override
    public boolean ativarDesativarTracao() {
        // Se o carro estiver parado, ativa ou desativa a tracao
        if(super.getVelocidade() == 0){
            this.tracao = !this.tracao;
            return true;
        } else
            return false;
    }

    @Override
    public void frear(int i) {
        super.frear(i);
    }

    @Override
    public void acelerar(int i) {
        super.acelerar(i);
    }

    @Override
    public void trocarMarcha(int m) {
        super.trocarMarcha(m);
    }

    /**
     * Permite abrir a cacamba
     * @return Se foi possivel abrir
     */
    public boolean abrirCacamba(){
        // Se o carro estiver parado e com a cacamba fechada, abre ela
        if(super.getVelocidade() == 0 && !this.cacamba){
            return this.cacamba = true;
        } else
            return false;
    }

    /**
     * Permite fechar a cacamba
     * @return Se foi possivel fechar
     */
    public boolean fecharCacamba(){
        // Se o carro estiver parado e com a cacamba aberta, fecha ela
        if(super.getVelocidade() == 0 && this.cacamba){
            this.cacamba = false;
            return true;
        } else
            return false;
    }
}
