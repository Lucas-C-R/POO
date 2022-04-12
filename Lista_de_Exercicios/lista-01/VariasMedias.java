import java.util.Scanner;

public class VariasMedias {
    public int calcMedia(int[] nota) {
        double PP = Math.pow(nota[0], 3) * Math.pow(nota[1], 4) * Math.pow(nota[2], 2);
        PP = Math.pow(PP, 1.0/9) * 0.9;

        double LE = (nota[3] * nota[4] * nota[5] * nota[6] * nota[7]);
        LE = Math.pow(LE, 1.0/5) * 0.1;

        return (int) Math.round((PP + LE));
    }

    public int[] separaValores(String[] dados){
        int[] notas = new int[8];

        for (int i = 0, j = 1; j < dados.length; i++, j++) {
            notas[i] = Integer.parseInt(dados[j]);

            if (notas[i] < 0 || notas[i] > 10) {
                System.out.println("As notas de " + dados[0] + " precisam estar entre 0 e 10!");
                
                break;
            }
        }

        return notas;
    }

    public static void main(String[] args) {
        VariasMedias medias = new VariasMedias();

        Scanner entrada = new Scanner(System.in);

        while (entrada.hasNext()) {
            String[] vetStrings = entrada.nextLine().split(" ");

            if (vetStrings.length != 9) {
                System.out.println("A formatacao do(a) estudante " + vetStrings[0] + " esta errada!");
            } else {
                int[] notas = new int[8];

                notas = medias.separaValores(vetStrings);

                int CF = medias.calcMedia(notas);

                if (CF >= 6) {
                    System.out.println(vetStrings[0] + " : " + CF + " APROVADO");
                } else {
                    System.out.println(vetStrings[0] + " : " + CF + " REPROVADO");
                }
            }
        }
    }
}
