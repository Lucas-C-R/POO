package poo;

/**
 * Classe que representa o carro Panther
 */
public class Panther extends VeiculoManual implements Conversivel, TracaoIntegral, VeiculoAnfibio{
    private boolean capota = false;
    private boolean tracao = false;
    private boolean lastro = true;
    private boolean rodas = true;
    private int tempRefri = 0;

    /**
     * Informa se a capota esta aberta
     * @return O estado da capota
     */
    public boolean getCapota(){
        return this.capota;
    }

    /**
     * Informa se a tracao esta ativada
     * @return O estado da tracao
     */
    public boolean getTracao(){
        return this.tracao;
    }

    /**
     * Informa se o lastro esta cheio
     * @return O estado do lastro
     */
    public boolean getLastro() {
        return this.lastro;
    }

    /**
     * Informa se as rodas estao abertas
     * @return O estado das rodas
     */
    public boolean getRodas(){
        return this.rodas;
    }

    /**
     * Informa o nivel do ar
     * @return O estado do ar
     */
    public int getTempRefri() {
        return this.tempRefri;
    }

    /**
     * Altera o botao da temperatura
     * @param t Novo valor do botao
     */
    public void setTempRefri(int t){
        if(t == 0 || t == 1 || t == 2 || t == 3){
            this.tempRefri = t;
        }
    }

    /**
     * Metodo construtor da classe
     * @param nome Nome do veiculo
     */
    public Panther(String nome) {
        super(nome, 140);
    }

    @Override
    public boolean abrirCapota() {
        // Se a capota estiver fechada e o carro parado, abre a capota
        if(!this.capota && super.getVelocidade() == 0){
            return this.capota = true;
        } else
            return false;
    }

    @Override
    public boolean fecharCapota() {
        // Se a capota estiver aberta e o carro parado, abre a capota
        if(this.capota && super.getVelocidade() == 0){
            this.capota = false;
            return true;
        } else
            return false;
    }

    @Override
    public boolean ativarDesativarTracao() {
        // Se o caro estiver parado e com as rodas abertas, permite ativar ou desativar a tracao
        if(super.getVelocidade() == 0 && this.rodas){
            this.tracao = !this.tracao;
            return true;
        } else
            return false;
    }

    @Override
    public void esvaziarLastro() {
        this.lastro = false;
    }

    @Override
    public void encherLastro() {
        // Se as rodas estiverem abaixadas e o lastro estiver vazio, permite encher ele
        if(this.rodas && !this.lastro)
            this.lastro = true;
    }

    @Override
    public boolean recolherRodas() {
        // Se as rodas estiverem abaixadas e o carro parado, esvazia o lastro e recolhe as rodas
        if(this.rodas && super.getVelocidade() == 0){
            this.esvaziarLastro();
            this.rodas = false;
            return true;
        } else
            return false;
    }

    @Override
    public boolean abrirRodas() {
        // Se as rodas estiverem recolhidas e o carro parado, abre as rodas
        if(!this.rodas && super.getVelocidade() == 0){
            return this.rodas = true;
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
    public void trocarMarcha(int m){
        super.trocarMarcha(m);
    }
}
