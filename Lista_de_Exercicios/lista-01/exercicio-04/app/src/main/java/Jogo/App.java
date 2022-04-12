package Jogo;

public class App {
    public int[] jogo(int saldo, int meta) {
        int venceu = 0, media = 0;

        while(saldo > 0){
            media++;

            saldo += (Math.random() < 0.5) ? +1 : -1;

            if(saldo == meta){
                venceu++;
                break;
            }
        }
        
        int[] vetAux = {media,venceu};

        return vetAux;
    }

    public static void main(String[] args) {
        App app = new App();

        int saldo = Integer.parseInt(args[0]);
        int meta = Integer.parseInt(args[1]);
        int quantRetorno = Integer.parseInt(args[2]);
        int venceu = 0, media = 0;

        for(int i = 0; i < quantRetorno; i++){
            int[] intVet = app.jogo(saldo, meta);

            media += intVet[0];

            venceu += intVet[1];
        }

        double porcent = (venceu * 100)/quantRetorno;

        System.out.print("Atingiu sua meta em " + venceu + " das " + quantRetorno + " vezes que foi no estabelecimento, ou seja, ganhou em ");
        System.out.println((int) Math.round(porcent) + "% das vezes. O numero medio de apostas foi " + media/quantRetorno + ".");
    }
}