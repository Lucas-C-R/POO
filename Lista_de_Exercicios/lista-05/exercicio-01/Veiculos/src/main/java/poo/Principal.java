package poo;

import java.util.ArrayList;

/**
 * Classe principal
 */
public class Principal {
    private ArrayList<Object> lista = new ArrayList<>();

    /**
     * Invoca os metodos da classe Ferrari
     */
    public void metodosFerrari(){
        ((Ferrari) this.lista.get(0)).trocarMarcha(1);
        System.out.println("A sua marcha atual e: " + ((Ferrari) this.lista.get(0)).getMarcha());

        ((Ferrari) this.lista.get(0)).acelerar(15);
        System.out.println("Velocidade: " + ((Ferrari) this.lista.get(0)).getVelocidade() + "km/h");

        ((Ferrari) this.lista.get(0)).acelerar(5);
        System.out.println("Troque a marcha para acelerar, ainda em: " + ((Ferrari) this.lista.get(0)).getVelocidade() + "km/h");

        ((Ferrari) this.lista.get(0)).frear(12);
        System.out.println("Velocidade atual: " + ((Ferrari) this.lista.get(0)).getVelocidade() + "km/h");

        ((Ferrari) this.lista.get(0)).trocarMarcha(4);
        System.out.println("Pouca velocidade para esta marcha, velocidade atual: " + ((Ferrari) this.lista.get(0)).getVelocidade() + "km/h");

        if(((Ferrari) this.lista.get(0)).abrirCapota()){
            System.out.println("Capota aberta");
        }

        ((Ferrari) this.lista.get(0)).ligarFarolNeblina();
        if(((Ferrari) this.lista.get(0)).getFarolDeNeblina()){
            System.out.println("Ligando farois de neblina");
        }
    }

    /**
     * Invoca os metodos da classe Pampa
     */
    public void metodosPampa(){
        if(((Pampa) this.lista.get(1)).abrirCacamba()){
            System.out.println("Abrindo cacamba");
        }

        if(((Pampa) this.lista.get(1)).fecharCacamba()){
            System.out.println("Fechando a cacamba");
        }

        if(((Pampa) this.lista.get(1)).ativarDesativarTracao()){
            System.out.println("Ativando tracao");
        }

        ((Pampa) this.lista.get(1)).trocarMarcha(1);
        ((Pampa) this.lista.get(1)).acelerar(1);

        if(!((Pampa) this.lista.get(1)).abrirCacamba()){
            System.out.println("O carro deve estar parado para abrir a cacamba");
        }

        if(!((Pampa) this.lista.get(1)).ativarDesativarTracao()){
            System.out.println("O carro precisa estar parado para ativar ou desativar a tracao");
        }
    }

    /**
     * Invoca os metodos da classe Panther
     */
    public void metodosPanther(){
        ((Panther) this.lista.get(2)).setTempRefri(2);
        System.out.println("O botao do ar esta em: " + ((Panther) this.lista.get(2)).getTempRefri());

        if(((Panther) this.lista.get(2)).recolherRodas()){
            System.out.println("Recolhendo as rodas");
        }

        if(!((Panther) this.lista.get(2)).ativarDesativarTracao()){
            System.out.println("As rodas precisam estar abaixadas para mexer na tracao");
        }

        ((Panther) this.lista.get(2)).abrirRodas();
        ((Panther) this.lista.get(2)).encherLastro();
        if(((Panther) this.lista.get(2)).getLastro()){
            System.out.println("Lastro cheio");
        }
    }

    /**
     * Metodo main
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        Principal p = new Principal();
        
        p.lista.add(new Ferrari("Ferrari 125S"));
        p.lista.add(new Pampa("Pampa S"));
        p.lista.add(new Panther("Panther"));

        p.metodosFerrari();

        System.out.println("\n");

        p.metodosPampa();

        System.out.println("\n");

        p.metodosPanther();
    }
}
