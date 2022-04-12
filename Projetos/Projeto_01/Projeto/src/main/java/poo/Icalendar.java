package poo;

import java.util.Objects;
import java.util.Scanner;

public class Icalendar {
    private Vcalendar calendario;

    public String mexeData(String op){
        Scanner teclado = new Scanner(System.in);
        String data;

        switch (op){
            // Para data e hora do EXDATE
            case "0":
                System.out.print("Informe a hora que nao fara parte do evento: ");
                data = teclado.next();

                System.out.print("Informe a data que nao fara parte do evento (utilize '-' ao inves de '/'): ");
                data += ',' + teclado.next();
                break;

            // Para data e hora iniciais
            case "1":
                System.out.print("Informe a hora inicial: ");
                data = teclado.next();

                System.out.print("Informe a data inicial (utilize '-' ao inves de '/'): ");
                data += ',' + teclado.next();
                break;

            // Para data e hora finais
            case "2":
                System.out.print("Informe a hora final: ");
                data = teclado.next();

                System.out.print("Informe a data final (utilize '-' ao inves de '/'): ");
                data += ',' + teclado.next();
                break;

            // Para data e hora genericas
            default:
                System.out.print("Informe a hora: ");
                data = teclado.next();

                System.out.print("Informe a data (utilize '-' ao inves de '/'): ");
                data += ',' + teclado.next();
                break;
        }

        return data;
    }

    public boolean modificaRrule(String opRrule, String sumario){
        Scanner opcao = new Scanner(System.in);
        Scanner teclado = new Scanner(System.in);

        switch (opRrule) {
            // Adiciona o RRULE
            case "1":
                String[] dadosRrule = new String[4];

                System.out.print("Informe a frequencia: ");
                dadosRrule[0] = teclado.next().toUpperCase();

                System.out.print("Informe o intervalo: ");
                dadosRrule[1] = teclado.next();

                dadosRrule[2] = mexeData("2");

                System.out.print("Informe os dias: ");
                dadosRrule[3] = teclado.next().toUpperCase();

                // Se nao conseguiu adicionar um RRULE, retorna um erro
                if (! this.calendario.addRrule(sumario, dadosRrule[0], dadosRrule[1], dadosRrule[2], dadosRrule[3])) {
                    System.out.println("\nErro ao adicionar o RRULE\n\n");
                    return false;
                }

                String decis = "S";

                // Enquanto o laco permanecer, vai adicionando um EXDATE
                while (decis.equals("S")) {
                    System.out.print("Deseja adicionar uma data a ser excluida do evento recorrente (S/N)? ");
                    decis = teclado.next().toUpperCase();

                    if (decis.equals("S")) {
                        // Se nao conseguir adicionar um EXDATE, retorna um erro
                        if(! this.calendario.addExdate(sumario, mexeData("0"))){
                            System.out.println("\nErro ao adicionar o EXDATE\n\n");
                        }
                    }
                }

                break;

            // Remove o RRULE
            case "2":
                // Se nao conseguir remover o RRULE, retorna um erro
                if (! this.calendario.removeRrule(sumario)) {
                    System.out.println("\nErro ao remover o RRULE!\n\n");
                    return false;
                }
                break;

            // Modifica o RRULE
            case "3":
                System.out.println("\nEscolha o numero referente a opcao desejada:");
                System.out.println("1.A frequencia.");
                System.out.println("2.O intervalo.");
                System.out.println("3.Data e hora do termino da repeticao.");
                System.out.println("4.Os dias em que ocorre a repeticao.");

                opRrule = opcao.next();

                System.out.print("Informe o novo valor do atributo: ");
                String novoAtributo;

                // Caso o usuario queira alterar n data e hora do RRULE, formata os novos valores
                if(opRrule.equals("3")){
                    novoAtributo = mexeData("2");
                } else{
                    novoAtributo = teclado.next();
                }

                // Retorna um erro caso nao consiga modificar o RRULE
                if (! this.calendario.updateRrule(sumario, opRrule, novoAtributo)) {
                    System.out.println("\nErro ao modificar o RRULE.\n\n");
                    return false;
                }
                break;

            // Adiciona um EXDATE
            case "4":
                // Retorna um erro caso nao consiga adicionar o EXDATE
                if (! this.calendario.addExdate(sumario, mexeData("0"))) {
                    System.out.println("\nErro ao adicionar o EXDATE!\n\n");
                    return false;
                }
                break;

            // Remove um EXDATE
            case "5":
                // Retorna um erro caso nao consiga remover o EXDATE
                if (!this.calendario.removeExdate(sumario, mexeData("3"))) {
                    System.out.println("\nErro ao remover o EXDATE!\n\n");
                    return false;
                }
                break;

            // Modifica um EXDATE
            case "6":
                // Retorna um erro caso nao consiga modificar o EXDATE
                if (!this.calendario.updateExdate(sumario, mexeData("1"), mexeData("0"))) {
                    System.out.println("Erro ao atualizar o EXDATE!");
                    return false;
                }
                break;

            default:
                System.out.println("Opcao invalida!");
                break;
        }

        return true;
    }

    public boolean modificaVevent(String opVevent){
        Scanner opcao = new Scanner(System.in);
        Scanner teclado = new Scanner(System.in);

        switch (opVevent) {
            // Adiciona um VEVENT
            case "1":
                String[] dadosVevent = new String[4];

                System.out.print("Informe o assunto do evento: ");
                dadosVevent[0] = opcao.nextLine();

                System.out.print("Informe a hora de inicio do evento: ");
                dadosVevent[1] = teclado.next();

                System.out.print("Informe a data de inicio do evento (utilize '-' ao inves de '/'): ");
                dadosVevent[1] += ',' + teclado.next();

                // Se a data ou a hora nao estiverem com o tamanho valido (hh:mm:ss,dd/mm/aaaa) retorna um erro
                if(dadosVevent[1].length() != 19){
                    System.out.println("\nData ou hora incorretos!!\n\n");
                    return false;
                }

                System.out.print("Informe a hora de encerramento do evento: ");
                dadosVevent[2] = teclado.next();

                System.out.print("Informe a data de encerramento do evento (utilize '-' ao inves de '/'): ");
                dadosVevent[2] += ',' + teclado.next();

                // Se a data ou a hora nao estiverem com o tamanho valido (hh:mm:ss,dd/mm/aaaa) retorna um erro
                if(dadosVevent[2].length() != 19){
                    System.out.println("\nData ou hora incorretos!!\n\n");
                    return false;
                }

                System.out.print("Informe o local do evento: ");
                dadosVevent[3] = opcao.nextLine();

                // Tenta criar um VEVENT
                try {
                    Vevent evento = new Vevent(dadosVevent[0], dadosVevent[1], dadosVevent[2], dadosVevent[3]);
                    this.calendario.addVevent(evento);

                    System.out.print("Deseja adicionar uma regra (S/N)? ");
                    String resposta = teclado.next().toUpperCase();

                    // Se o usuario desejar, tenta criar um RRULE
                    if(Objects.equals(resposta, "S")){
                        modificaRrule("1", dadosVevent[0]);
                    }
                }catch (Exception e){
                    System.out.println("\nErro ao adicionar o VEVENT!\n\n");
                    return false;
                }
                break;

            // Remove um VEVENT
            case "2":
                System.out.print("\nInforme o sumario do evento a ser excluido: ");
                String sumario = teclado.nextLine();

                // Se nao conseguir remover o VEVENT, retorna um erro
                if (!this.calendario.removeVevent(sumario)) {
                    System.out.println("\nErro ao remover o VEVENT!\n\n");
                    return false;
                }
                break;

            // Modifica o VEVENT
            case "3":
                System.out.println("Informe o sumario do VEVENT a ser modificado:");
                sumario = teclado.nextLine();

                System.out.println("\nEscolha o atributo a ser modificado:");
                System.out.println("1.Sumario.");
                System.out.println("2.Data de inicio do evento.");
                System.out.println("3.Data de encerramento do evento.");
                System.out.println("4.Local do evento.");

                opVevent = opcao.next();

                System.out.print("Informe o valor do novo atributo: ");
                String novoAtrib = teclado.nextLine();

                // Se nao conseguir modificar o VEVENT, retorna um erro
                if (!this.calendario.updateVevent(sumario, opVevent, novoAtrib)) {
                    System.out.println("\nErro ao alterar o valor.\n\n");
                    return false;
                }
                break;

            default:
                System.out.println("\nOpcao inexistente!\n\n");
                break;
        }

        return true;
    }

    public boolean modificaIcalendar(String op){
        Scanner opcao = new Scanner(System.in);
        Scanner teclado = new Scanner(System.in);

        switch (op) {
            // Interage com o VCALENDAR
            case "1":
                System.out.println("\nEscolha o numero referente a opcao desejada:");
                System.out.println("1.Modificar o nome da empresa.");
                System.out.println("2.Modificar o nome do aplicativo.");
                System.out.println("3.Modificar a lingua.");

                String opVcalendar = opcao.next();
                String nome;

                switch (opVcalendar) {
                    // Modifica o nome da empresa
                    case "1":
                        System.out.print("Informe o novo nome da empresa: ");
                        nome = teclado.nextLine();

                        // Se nao conseguir modificar o nome da empresa, retorna um erro
                        if(! this.calendario.updateProdid("1", nome)){
                            System.out.println("\nErro ao modificar o nome da empresa!\n\n.");
                            return false;
                        }
                        break;

                    // Modifica o nome do aplicativo
                    case "2":
                        System.out.print("Informe o novo nome do aplicativo: ");
                        nome = teclado.nextLine();

                        // Se nao conseguir modificar o nome do aplicativo, retorna um erro
                        if(! this.calendario.updateProdid("2", nome)){
                            System.out.println("\nErro ao modificar o nome do aplicativo!\n\n.");
                            return false;
                        }
                        break;

                    // Modifica a lingua
                    case "3":
                        System.out.print("Informe a nova lingua: ");
                        nome = teclado.next().toUpperCase();

                        // Se nao conseguir modificar a lingua, retorna um erro
                        if(! this.calendario.updateProdid("3", nome)){
                            System.out.println("\nErro ao modificar a lingua!\n\n.");
                            return false;
                        }
                        break;

                    default:
                        System.out.println("\nOpcao invalida!\n\n");
                        break;
                }
                break;

            // Interage com o VEVENT
            case "2":
                System.out.println("\nEscolha o numero referente a opcao desejada:");
                System.out.println("1.Adicionar um VEVENT.");
                System.out.println("2.Remover um VEVENT.");
                System.out.println("3.Alterar um VEVENT");

                String opVevent = opcao.next();

                return modificaVevent(opVevent);

            // Interage com o RRULE e com o EXDATE
            case "3":
                System.out.println("\nEscolha o numero referente a opcao desejada:");
                System.out.println("1.Adicionar um RRULE.");
                System.out.println("2.Remover um RRULE.");
                System.out.println("3.Alterar um RRULE.");
                System.out.println("4.Adicionar um EXDATE.");
                System.out.println("5.Remover um EXDATE.");
                System.out.println("6.Alterar um EXDATE.");

                String opRrule = opcao.next();

                System.out.print("\nInforme o sumario do evento desejado: ");
                String sumario = teclado.next();

                return modificaRrule(opRrule, sumario);

            default:
                System.out.println("\nOpcao invalida!\n\n");
                break;
        }

        return true;
    }

    public void criaCalendario(){
        Scanner teclado = new Scanner(System.in);
        Scanner opcao = new Scanner(System.in);
        String[] dadosVcalendar = new String[3];

        System.out.print("Informe o nome da empresa: ");
        dadosVcalendar[0] = teclado.nextLine();

        System.out.print("Informe o nome do aplicativo: ");
        dadosVcalendar[1] = teclado.nextLine();

        System.out.print("Informe o codigo do idioma: ");
        dadosVcalendar[2] = teclado.next().toUpperCase();

        String[] dadosVevent = new String[4];

        System.out.print("Informe o assunto do evento: ");
        dadosVevent[0] = opcao.nextLine();

        System.out.print("Informe a hora de inicio do evento: ");
        dadosVevent[1] = teclado.next();

        System.out.print("Informe a data de inicio do evento (utilize '-' ao inves de '/'): ");
        dadosVevent[1] += ',' + teclado.next();

        // Se a data ou a hora nao estiverem com o tamanho valido (hh:mm:ss,dd/mm/aaaa) retorna um erro
        if(dadosVevent[1].length() != 19){
            System.out.println("\nData ou hora incorretos!!\n\n");
            return;
        }

        System.out.print("Informe a hora de encerramento do evento: ");
        dadosVevent[2] = teclado.next();

        System.out.print("Informe a data de encerramento do evento (utilize '-' ao inves de '/'): ");
        dadosVevent[2] += ',' + teclado.next();

        // Se a data ou a hora nao estiverem com o tamanho valido (hh:mm:ss,dd/mm/aaaa) retorna um erro
        if(dadosVevent[2].length() != 19){
            System.out.println("\nData ou hora incorretos!!\n\n");
            return;
        }

        System.out.print("Informe o local do evento: ");
        dadosVevent[3] = opcao.nextLine();

        // Tenta criar um VEVENT e um VCALENDAR
        try {
            Vevent evento = new Vevent(dadosVevent[0], dadosVevent[1], dadosVevent[2], dadosVevent[3]);
            this.calendario = new Vcalendar(dadosVcalendar[0], dadosVcalendar[1], dadosVcalendar[2], evento);

            System.out.print("Deseja adicionar uma regra (S/N)? ");
            String resposta = teclado.next().toUpperCase();

            // Se o usuario desejar, tenta criar um RRULE
            if(Objects.equals(resposta, "S")){
                modificaRrule("1", this.calendario.getEventos().peekFirst().getSumario());
            }
        }catch (Exception e){
            System.out.println("\nErro ao adicionar o VEVENT!\n\n");
        }
    }

    public boolean menu(){
        System.out.println("\nEscolha o numero referente a opcao desejada:");
        System.out.println("1.Criar um iCalendar.");
        System.out.println("2.Remover o iCalendar.");
        System.out.println("3.Modificar o iCalendar.");
        System.out.println("4.Ver iCalendar.");
        System.out.println("5.Sair.");

        Scanner opcao = new Scanner(System.in);
        String op = opcao.next();

        switch (op) {
            // Cria um VCALENDAR
            case "1":
                // Se nao houver um VCALENDAR criado, entao permite criar um
                if(this.calendario == null){
                    criaCalendario();
                } else{
                    System.out.println("\nJa ha um calendario criado!\n\n");
                }
                break;

            // Remove um VCALENDAR
            case "2":
                // Se houver um VCALENDAR, permite excluir ele e retorna uma mensagem
                if (this.calendario != null) {
                    this.calendario = null;
                    System.out.println("Calendario excluido com sucesso.");
                } else {
                    System.out.println("\nNao ha um calendario para ser excluido!\n\n");
                }

                break;

            // Modifica um VCALENDAR
            case "3":
                // Se houver um VCALENDAR, permite modifica-lo
                if(this.calendario != null){
                    System.out.println("\nEscolha o numero referente a opcao desejada:");
                    System.out.println("1.Modificar VCALENDAR.");
                    System.out.println("2.Modificar VEVENT.");
                    System.out.println("3.Modificar RRULE.");

                    String op2 = opcao.next();

                    modificaIcalendar(op2);
                } else{
                    System.out.println("\nNao ha um calendario para ser modificado!\n\n");
                }
                break;

            // Permite a visualizacao do VCALENDAR
            case "4":
                // Tenta visualizar o VCALENDAR, se nao houver, retorna um aviso
                try {
                    System.out.println(calendario.toString());
                } catch (Exception e) {
                    System.out.println("\nAinda nao ha um calendario para visualuzacao!\n\n");
                }

                break;

            // Encerra o programa
            case "5":
                return false;

            default:
                System.out.println("\nOpcao inexistente!\n\n");
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        Icalendar c = new Icalendar();

        while(c.menu());
    }
}