package ex2;

import edu.princeton.cs.algs4.Draw;

import java.time.LocalTime;

public class Principal {
    private Draw desenho;
    private Relogio[] vetRelog = new Relogio[4];

    public Principal(){
        this.desenho = new Draw();
        // definindo a área de desenho -- https://introcs.cs.princeton.edu/java/stdlib/javadoc/Draw.html
        this.desenho.setCanvasSize(600,600);
        // definindo a escala da área de desenho -- leia mais na documentação da classe
        this.desenho.setXscale(0, 600);
        this.desenho.setYscale(0, 600);
        this.desenho.clear(desenho.LIGHT_GRAY);
    }

    public boolean adicionarRelogio(Relogio relog){
        for(int i = 0; i < 4; i++){
            if(vetRelog[i] == null){
                vetRelog[i] = relog;
                return true;
            }
        }

        return false;
    }

    public void desenharRelogios(){
        LocalTime now = LocalTime.now();

        int horas = now.getHour();
        int minutos = now.getMinute();
        int segundos = now.getSecond();

        for(int j = 0; j < 4 && vetRelog[j] != null; j++){
            vetRelog[j].desenhoRelog(desenho, horas, minutos, segundos);
        }
    }

    public static void main(String[] args) {
        Principal relogs = new Principal();
        Relogio[] vetRe = new Relogio[5];

        vetRe[0] = new Relogio(150, 450, -3, "SP");
        vetRe[1] = new Relogio(450, 450, 8, "Tokyo");
        vetRe[2] = new Relogio(450, 150, -10, "New York");
        vetRe[3] = new Relogio(150, 150, 16, "Greenwich");
        vetRe[4] = new Relogio(0.5, 0.5, 12, "San Tiago");

        for(int i = 0; i < 5; i++){
            relogs.adicionarRelogio(vetRe[i]);
        }


        relogs.desenharRelogios();
    }
}
