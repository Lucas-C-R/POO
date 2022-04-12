package poo;

import java.util.Scanner;

public class Principal {
    Rack r = new Rack();

    public boolean menu(){
        System.out.println("\nEscolha uma das opcoes abaixo:\n");
        System.out.println("Digite 1 para adcionar um computaor ao RACK.");
        System.out.println("Digite 2 para desligar ou ligar um computador.");
        System.out.println("Digite 3 para remover um computador.");
        System.out.println("Digite 4 para adicionar uma maquina virtual.");
        System.out.println("Digite 5 para desligar ou ligar uma maquina virtual.");
        System.out.println("Digite 6 para remover uma maquina virtual.");
        System.out.println("Digite 7 para visualizar uma lista contendo todos os dispositivos ligados e seus status.");
        System.out.println("Digite 8 para encerrar a operacao.\n");


        Scanner entrada = new Scanner(System.in);
        String op = entrada.next();
        Scanner teclado = new Scanner(System.in);

        switch(op){
            case "1":
                System.out.print("Insira o nome deste computador: ");
                String nome = teclado.nextLine();

                try{
                    System.out.print("Insira o tamanho da memoria de disco (em GB): ");
                    int disco = teclado.nextInt();

                    System.out.print("Insira o tamanho da RAM (em GB): ");
                    int ram = teclado.nextInt();

                    if(this.r.addMaq(new MaqReal(nome, disco, ram))){
                        System.out.println("Maquina adicionada com sucesso.");
                    } else{
                        System.out.println("Erro ao adicionar a maquina.");
                    }

                    break;
                } catch (Exception e){
                    System.err.println("O valor digitado e invalido!!");
                    break;
                }

            case "2":
                System.out.println("Escolha um dos computadores abaixo: \n");
                System.out.println(this.r.toString());

                nome = teclado.nextLine();

                System.out.println(nome + ": " + this.r.ligDeslMaq(nome));

                break;

            case "3":
                System.out.println("Escolha um dos computadores abaixo: \n");
                System.out.println(this.r.toString());

                nome = teclado.nextLine();

                if(this.r.removeMaq(nome)){
                    System.out.println("Computador removido com sucesso.");
                } else{
                    System.out.println("Ocorreu um erro ao remover o computador.");
                }

                break;

            case "4":
                System.out.println("Escolha um dos computadores abaixo: \n");
                System.out.println(this.r.toString());

                nome = teclado.nextLine();

                System.out.print("Informe o nome da maquina virtual: ");
                Scanner virtual = new Scanner(System.in);
                String nomeVirt = virtual.nextLine();

                try {
                    System.out.print("Insira o tamanho da memoria de disco (em GB): ");
                    int discoVirt = teclado.nextInt();

                    System.out.print("Insira o tamanho da RAM (em GB): ");
                    int ramVirt = teclado.nextInt();

                    if(this.r.addVirtual(nome, new MaqVirtual(nomeVirt, discoVirt, ramVirt))){
                        System.out.println("Maquina virtual adicionada com sucesso.");
                    } else{
                        System.out.println("Erro ao adicionar a maquina virtual.");
                    }
                    break;
                } catch(Exception e){
                    System.err.println("O valor digitado e invalido!!");
                    break;
                }

            case "5":
                System.out.println("Escolha um computador e uma maquina virtual associada a ele: \n");
                System.out.println(this.r.listaMaq());

                nome = teclado.nextLine();

                System.out.print("Insira o nome da maquina virtual: ");
                Scanner s = new Scanner(System.in);
                nomeVirt = s.nextLine();

                System.out.println(this.r.ligaDesligVirtual(nome, nomeVirt));

                break;

            case "6":
                System.out.println("Escolha um computador e uma maquina virtual associada a ele: \n");
                System.out.println(this.r.listaMaq());

                nome = teclado.nextLine();
                Scanner v = new Scanner(System.in);
                nomeVirt = v.nextLine();

                if(this.r.removeVirtual(nome, nomeVirt)){
                    System.out.println("Maquina virtual removida com sucesso.");
                } else{
                    System.out.println("Erro ao remover a maquina virtual.");
                }

                break;

            case "7":
                System.out.println(this.r.listaMaq());

                break;

            case "8":
                return false;

            default:
                System.err.println("O valor digitado e invalido!!");
        }

        return true;
    }

    public static void main(String[] args) {
        Principal p = new Principal();
        boolean continuar = true;

        while(continuar){
            continuar = p.menu();
        }
    }
}
