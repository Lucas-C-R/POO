package poo;

import edu.princeton.cs.algs4.Draw;

import java.awt.*;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Principal {
    private Draw areaDeDesenho;

    public Principal(String entrada){
        this.areaDeDesenho = new Draw();

        StringBuilder configuracoes = new StringBuilder(entrada);

        int i = configuracoes.indexOf(",");

        int area = Integer.parseInt(configuracoes.substring(0, i));

        this.areaDeDesenho.setCanvasSize(area, area);
        this.areaDeDesenho.setXscale(0, area);
        this.areaDeDesenho.setYscale(0, area);
        this.areaDeDesenho.clear(Color.GRAY);
    }

    /**
     * Pega uma linha do arquivo e separa as informacoes.
     * @param linha Linha do arquivo a ser separada.
     * @return Um vetor com as informacoes separadas.
     */
    public String[] obterDados(String linha){
        String[] dados = new String[6];
        StringBuilder descricaoRobo = new StringBuilder(linha);

        int inic = 0, fim;
        for(int i = 0; i < 6; i++){
            fim = descricaoRobo.indexOf(",", inic + 1);

            // Verifica se ja chegou na ultima informacao contida na linha
            if(fim != -1){
                dados[i] = descricaoRobo.substring(inic, fim);
            } else{
                dados[i] = descricaoRobo.substring(inic, descricaoRobo.length());
            }

            inic = fim + 1;
        }

        return dados;
    }

    /**
     * Escolhe randomicamente uma entre oito opcoes de cores, para a linha a ser desenhada
     */
    public void escolherCor(){
        Random cor = new Random();

        int escolha = cor.nextInt(8);
        switch (escolha){
            case 0:
                areaDeDesenho.setPenColor(Color.BLACK);
                break;

            case 1:
                areaDeDesenho.setPenColor(Color.BLUE);
                break;

            case 2:
                areaDeDesenho.setPenColor(Draw.PINK);
                break;

            case 3:
                areaDeDesenho.setPenColor(Draw.ORANGE);
                break;

            case 4:
                areaDeDesenho.setPenColor(Color.RED);
                break;

            case 5:
                areaDeDesenho.setPenColor(Color.WHITE);
                break;

            case 6:
                areaDeDesenho.setPenColor(Color.YELLOW);
                break;

            case 7:
                areaDeDesenho.setPenColor(Color.GREEN);
                break;
        }
    }

    /**
     * Desenha a trajetoria do robo.
     * @param coordAnt CoordAnt Coordenada inicial da linha.
     * @param coordAtual CoordAtual Coordenada final da linha.
     */
    public void desenhaTrajeto(String coordAnt, String coordAtual){
        if(! Objects.equals(coordAnt, coordAtual)){
            int i = coordAnt.indexOf(",");
            int xAnt = Integer.parseInt(coordAnt.substring(0, i));
            int yAnt = Integer.parseInt(coordAnt.substring(i + 1));

            i = coordAtual.indexOf(",");
            int xAtual = Integer.parseInt(coordAtual.substring(0, i));
            int yAtual = Integer.parseInt(coordAtual.substring(i + 1));

            this.areaDeDesenho.line(xAnt, yAnt, xAtual, yAtual);
            this.areaDeDesenho.pause(1000);
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String inform = teclado.nextLine();

        Principal principal = new Principal(inform);

        String[] dados = principal.obterDados(inform);
        Robo rob = new Robo(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3], Integer.parseInt(dados[4]), Integer.parseInt(dados[5]));

        // Informa ao robo qual e o plano de exploracao
        while(teclado.hasNext()){
            rob.planoExploracao(teclado.next());
        }

        boolean executou = true;

        // Executa o plano de exploracao
        for(int i = 0; i < rob.getPlano().length(); i++){
            if(rob.executaPlano()){
                principal.escolherCor();

                principal.desenhaTrajeto(rob.coordenadaAnterior(), rob.coordenadaAtual());
            }

            // Encerra a execucao caso acabe a bateria
            if(rob.getQuantMov() == 0){
                executou = false;
                break;
            }
        }

        if(! executou){
            System.out.print("Acabou a bateria do robô na coordenada (" + rob.coordenadaAtual());
            System.out.print(") e com a frente para " + rob.getFrente());
            System.out.print(". No plano restam " + rob.comandosNaoRealizados().length());
            System.out.println(" comandos que não foram processados.");
        } else{
            System.out.print("A coordenada final do robo e (" + rob.coordenadaAtual());
            System.out.print("), ele esta olhando para o " + rob.getFrente());
            System.out.println(" e ainda lhe restam " + rob.getQuantMov() + " movimentos.");
        }
    }
}
