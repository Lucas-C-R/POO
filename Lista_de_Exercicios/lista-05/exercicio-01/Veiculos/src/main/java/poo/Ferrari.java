package poo;

/**
 * Classe que representa o veiculo Ferrari
 */
public class Ferrari extends VeiculoManual implements Conversivel{
    private boolean capota = false;
    private boolean farolDeNeblina = false;

    /**
     * Informa se a capota esta aberta ou fechada
     * @return O estado da capota
     */
    public boolean getCapota() {
        return this.capota;
    }

    /**
     * Informa se o farol de neblina esta ativo
     * @return Estado do farol
     */
    public boolean getFarolDeNeblina(){
        return this.farolDeNeblina;
    }

    /**
     * Metodo construtor da classe
     * @param nome Nome do veiculo
     */
    public Ferrari(String nome) {
        super(nome, 200);
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

    @Override
    public boolean abrirCapota() {
        // Se a capota estiver fechada e o carro estiver a menos de 20 km/h, abre ela
        if(! this.capota && super.getVelocidade() < 20){
            return this.capota = true;
        } else
            return false;
    }

    @Override
    public boolean fecharCapota() {
        // Se a capota estiver aberta e o carro estiver a menos de 20 km/h, fecha ela
        if(this.capota && super.getVelocidade() < 20){
            this.capota = false;
            return true;
        } else
            return false;
    }

    /**
     * Permite ligar os farois de neblina
     * @return Se foi possivel ligar os farois
     */
    public boolean ligarFarolNeblina(){
        // Se os farois de neblina estiverem desligados, liga eles
        if(! this.farolDeNeblina){
            return this.farolDeNeblina = true;
        } else
            return false;
    }

    /**
     * Permite desligar os farois de neblina
     * @return Se foi possivel desligar
     */
    public boolean desligarFarolNeblina(){
        // Se os farois de neblina estiverem ligados, desliga eles
        if(this.farolDeNeblina){
            this.farolDeNeblina = false;
            return true;
        } else
            return false;
    }
}
