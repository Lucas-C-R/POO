package ex2;

import edu.princeton.cs.algs4.Draw;

public class Relogio {
    private double x;
    private double y;
    private int fuso;
    private String nome;

    public Relogio(double cordX, double cordY, int fusoH, String nomeRelogio){
        x = cordX;
        y = cordY;
        nome = nomeRelogio;

        if(fusoH < -12 || fusoH > 12){
            fuso = 0;
        } else {
            fuso = fusoH;
        }
    }

    public void desenhoRelog(Draw relogio, int horas, int minutos, int segundos){
        relogio.setPenColor(relogio.WHITE);
        relogio.filledCircle(x, y, 100);
        relogio.setPenColor(relogio.BLACK);

        double r2 = 40; double r3 = 25; double r4 = 85; double r5 = 95;

        relogio.setPenRadius(0.01);

        for (int i = 0; i < 12; i++) {
            double theta = Math.toRadians(30 * i);
            //https://brasilescola.uol.com.br/matematica/simetria-no-circulo-trigonometrico.htm
            relogio.line(x + r5*Math.sin(theta), y + r5*Math.cos(theta), x + r4*Math.sin(theta), y + r4*Math.cos(theta));
        }

        double h = Math.toRadians(30 * (horas + fuso));
        double m = Math.toRadians(6 * minutos);
        double s = Math.toRadians(6 * segundos);

        //360 graus / 12 horas = 30 graus
        relogio.line(x, y, x + r3 * Math.sin(h), y + r3 * Math.cos(h));
        //360 graus / 60 minutos = 6 graus
        relogio.line(x, y, x + r3*2 * Math.sin(m), y + r3*2 * Math.cos(m));
        relogio.setPenColor(relogio.RED);relogio.setPenRadius(0.005);
        relogio.line(x, y, x + r2*2 * Math.sin(s), y + r2*2 * Math.cos(s));

        relogio.text(x,y - (r5 + r3), nome);
    }
}
