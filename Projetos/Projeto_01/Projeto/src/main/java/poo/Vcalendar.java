package poo;

import java.util.LinkedList;

public class Vcalendar {
    private final String VERSAO = "2.0";
    private String prodid;
    private LinkedList<Vevent> eventos = new LinkedList<>();

    public LinkedList<Vevent> getEventos() {
        return eventos;
    }

    public Vcalendar(String empresa, String aplicativo, String idioma, Vevent evento){
        this.prodid = "-//" + empresa + "//" + aplicativo + "//" + idioma;
        if(! this.addVevent(evento)){
            this.prodid = null;
        }
    }

    public boolean updateProdid(String dado, String valorAlternado){
        if(this.prodid == null){
            return false;
        }

        int i;

        switch (dado){
            // Altera o nome da empresa
            case "1":
                i = this.prodid.indexOf("//", 2);
                this.prodid = "-//" + valorAlternado + this.prodid.substring(i);
                break;

            // Altera o nome do aplicativo
            case "2":
                i = this.prodid.indexOf("//", 2);
                int j = this.prodid.lastIndexOf("//");
                this.prodid = this.prodid.substring(0, i) + "//" + valorAlternado + this.prodid.substring(j);
                break;

            // Altera o idioma
            case "3":
                i = this.prodid.lastIndexOf("//");
                this.prodid = this.prodid.substring(0, i) + "//" + valorAlternado;
                break;

            default:
                return false;
        }

        return true;
    }

    public boolean addVevent(Vevent evento){
        if(this.prodid == null){
            return false;
        }

        // Se ja houver um VEVENT com o sumario fornecido, nao permite adicionar o 'evento'
        for(Vevent elemento: this.eventos){
            if(elemento.getSumario().equals(evento.getSumario())){
                return false;
            }
        }

        // Se os dados de 'evento' estiverem em ordem, adiciona ele na lista
        if(evento.getFimData() != null && evento.getInicData() != null && evento.getLocal() != null && evento.getSumario() != null){
            this.eventos.add(evento);
            return true;
        }

        return false;
    }

    public boolean removeVevent(String sumario){
        if(this.prodid == null){
            return false;
        }

        // Se houver um VEVENT com o sumario fornecido, o remove da lista
        for(Vevent elemento: this.eventos){
            if(elemento.getSumario().equals(sumario)){
                return this.eventos.remove(elemento);
            }
        }
        return false;
    }

    public boolean updateVevent(String sumario, String indice, String valorAlternado){
        if(this.prodid == null){
            return false;
        }

        for(Vevent elemento: this.eventos){
            //Se houver um VEVENT com o sumario fornecido, modifica ele
            if(elemento.getSumario().equals(sumario)){
                switch (indice){
                    // Modifica o sumario
                    case "1":
                        elemento.setSumario(valorAlternado);
                        return true;

                    // Modifica a data inicial
                    case "2":
                        String inicAnt = elemento.getInicData();

                        elemento.setInicData(valorAlternado);

                        // Se forem iguais, entao significa que que a operacao nao foi realizada, logo, retorna 'false'
                        return ! elemento.getInicData().equals(inicAnt);

                    // Modifica a data final
                    case "3":
                        String finAnt = elemento.getFimData();

                        elemento.setFimData(valorAlternado);

                        // Se forem iguais, entao significa que que a operacao nao foi realizada, logo, retorna 'false'
                        return ! elemento.getFimData().equals(finAnt);

                    // Modifica o local do evento
                    case "4":
                        elemento.setLocal(valorAlternado);
                        return true;

                    default:
                        return false;
                }
            }
        }

        return false;
    }

    public boolean addRrule(String sumario, String freq, String inter, String until, String byday){
        if(this.prodid == null){
            return false;
        }

        // Se houver um VEVENT com o sumario fornecido, entao tenta adicionar um RRULE
        for(Vevent elemento: this.eventos){
            if(elemento.getSumario().equals(sumario)){
                return elemento.addRrule(freq, inter, until, byday);
            }
        }

        return false;
    }

    public boolean removeRrule(String sumario){
        if(this.prodid == null){
            return false;
        }

        // Se houver um VEVENT com o sumario fornecido, entao tenta remover um RRULE
        for(Vevent elemento: this.eventos){
            if(elemento.getSumario().equals(sumario)){
                return elemento.removeRrule();
            }
        }

        return false;
    }

    public boolean updateRrule(String sumario, String indice, String valorAlternado){
        if(this.prodid == null){
            return false;
        }

        // Se houver um VEVENT com o sumario fornecido, entao tenta modificar um RRULE
        for(Vevent elemento: this.eventos){
            if(elemento.getSumario().equals(sumario)){
                return elemento.updateRrule(indice, valorAlternado);
            }
        }

        return false;
    }

    public boolean addExdate(String sumario, String exdate){
        if(this.prodid == null){
            return false;
        }

        // Se houver um VEVENT com o sumario fornecido, entao tenta adicionar um EXDATE
        for(Vevent elemento: this.eventos){
            if(elemento.getSumario().equals(sumario)){
                return elemento.addExdate(exdate);
            }
        }

        return false;
    }

    public boolean removeExdate(String sumario, String exdate){
        if(this.prodid == null){
            return false;
        }

        // Se houver um VEVENT com o sumario fornecido, entao tenta remover um EXDATE
        for(Vevent elemento: this.eventos){
            if(elemento.getSumario().equals(sumario)){
                return elemento.removeExdate(exdate);
            }
        }

        return false;
    }

    public boolean updateExdate(String sumario, String exdateAnt, String exdateNovo){
        if(this.prodid == null){
            return false;
        }

        // Se houver um VEVENT com o sumario fornecido, entao tenta modificar um EXDATE
        for(Vevent elemento: this.eventos){
            if(elemento.getSumario().equals(sumario)){
                return elemento.updateExdate(exdateAnt, exdateNovo);
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if(this.prodid == null){
            return "\nErro ao criar o calendario!\n\n";
        }else{
            StringBuilder aux = new StringBuilder();

            this.eventos.forEach(elemento -> aux.append(elemento));

            return "\nBEGIN:VCALENDAR" + '\n' +
                    "VERSION:" + VERSAO + '\n' +
                    "PRODID:" + prodid + '\n' +
                    aux +
                    "END:VCALENDAR" + '\n';
        }
    }
}
