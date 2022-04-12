import javax.print.attribute.standard.Media;

public class CalcMedia {
    public int media(int[] nota) {
        double PP = Math.pow(nota[0], 3) * Math.pow(nota[1], 4) * Math.pow(nota[2], 2);
        PP = Math.pow(PP, 1.0/9) * 0.9;

        double LE = (nota[3] * nota[4] * nota[5] * nota[6] * nota[7]);
        LE = Math.pow(LE, 1.0/5) * 0.1;

        return (int) Math.round((PP + LE));
    }

    public static void main(String[] args) {
        CalcMedia resultado = new CalcMedia();

        if (args.length != 8) {
            System.out.println("Sao necessarios 8 notas!");
        } else {
            int[] notas = new int[8];
            boolean notasPadrao = true;

            for (int i = 0; i < args.length; i++) {
                notas[i] = Integer.parseInt(args[i]);

                if (notas[i] < 0 || notas[i] > 10) {
                    System.out.println("As notas precisam estar entre 0 e 10!");
                    notasPadrao = false;
                    break;
                }
            }

            if (notasPadrao) {
                int CF = resultado.media(notas);

                if (CF >= 6) {
                    System.out.println("APROVADO");
                } else {
                    System.out.println("REPROVADO");
                }
            }
        }
    }
}