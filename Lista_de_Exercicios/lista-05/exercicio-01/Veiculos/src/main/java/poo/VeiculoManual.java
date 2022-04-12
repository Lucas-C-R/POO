package poo;

/**
 * Classe abstrata que representa um veiculo terrestre com marcha manual
 */
public abstract class VeiculoManual implements VeiculoTerrestre{
    public String nome;
    private int velocidade;
    private int marcha = 0;
    private final int VELOCMAX;

    /**
     * Obtem a velocidade atual do veiculo
     * @return A velocidade do veiculo
     */
    public int getVelocidade() {
        return this.velocidade;
    }

    /**
     * Obtem a marcha atual do veiculo
     * @return A marcha atual
     */
    public int getMarcha() {
        return marcha;
    }

    /**
     * Metodo construtor da classe
     * @param nome Nome do veiculo
     * @param velocmax A velociade maxima do veiculo
     */
    protected VeiculoManual(String nome, int velocmax) {
        this.nome = nome;
        this.VELOCMAX = velocmax;
    }

    @Override
    public void frear(int i) {
        if(i > 0){
            // Freio abrupto
            if(i >= this.velocidade){
                this.velocidade = 0;
            } else{
                switch (this.marcha){
                    // mesma analise para a re e para a primeira
                    case -1:
                    case 1:
                        if(this.velocidade - i >= 0){
                            this.velocidade -= i;
                        }
                        break;

                    case 2:
                        if(this.velocidade - i >= 15){
                            this.velocidade -= i;
                        }
                        break;

                    case 3:
                        if(this.velocidade - i >= 25){
                            this.velocidade -= i;
                        }
                        break;

                    case 4:
                        if(this.velocidade - i >= 45){
                            this.velocidade -= i;
                        }
                        break;

                    case 5:
                        if(this.velocidade - i >= 70){
                            this.velocidade -= i;
                        }
                        break;

                    case 0:
                        // Se estiver no ponto morto, nao faz nada
                        break;
                }
            }
        }
    }

    @Override
    public void acelerar(int i) {
        if(i > 0){
            switch (this.marcha){
                case -1:
                    if(this.velocidade + i <= 20){
                        this.velocidade += i;
                    }
                    break;

                case 1:
                    if(this.velocidade + i <= 15){
                        this.velocidade += i;
                    }
                    break;

                case 2:
                    if(this.velocidade + i >= 15 && this.velocidade + i <= 25){
                        this.velocidade += i;
                    }
                    break;

                case 3:
                    if(this.velocidade + i >= 25 && this.velocidade + i <= 45){
                        this.velocidade += i;
                    }
                    break;

                case 4:
                    if(this.velocidade + i >= 45 && this.velocidade + i <= 70){
                        this.velocidade += i;
                    }
                    break;

                case 5:
                    if(this.velocidade + i >= 70 && this.velocidade + i <= this.VELOCMAX){
                        this.velocidade += i;
                    }
                    break;

                case 0:
                    // Se estiver no ponto morto, o carro ira morrer
                    this.velocidade = 0;
                    break;
            }
        }
    }

    /**
     * Permite trocar a marcha do veiculo
     * @param m A nova marcha
     */
    public void trocarMarcha(int m) {
        if(m >= -1 && m <= 5){
            switch (m){
                case 2:
                    // Se o carro nao estiver na velocidade necessaria para a segunda, ira morrer
                    if(this.velocidade < 15 || this.velocidade > 25){
                        this.velocidade = 0;
                    }
                    break;

                case 3:
                    // Se o carro nao estiver na velocidade necessaria para a terceira, ira morrer
                    if(this.velocidade < 25 || this.velocidade > 45){
                        this.velocidade = 0;
                    }
                    break;

                case 4:
                    // Se o carro nao estiver na velocidade necessaria para a quarta, ira morrer
                    if(this.velocidade < 45 || this.velocidade > 70){
                        this.velocidade = 0;
                    }
                    break;

                case 5:
                    // Se o carro nao estiver na velocidade necessaria para a quinta, ira morrer
                    if(this.velocidade < 70){
                        this.velocidade = 0;
                    }
                    break;

                default:
                    // Se o carro nao estiver parado, ira morrer, serve pras marchas -1, 0 e 1
                    if(this.velocidade != 0){
                        this.velocidade = 0;
                    }
                    break;
            }

            this.marcha = m;
        }
    }
}
