package poo;

import java.util.LinkedList;
import java.util.Objects;

public class Rrule {
    private String freq;
    private String interval;
    private String until;
    private String byday;
    private LinkedList<String> exdate = new LinkedList<>();

    /**
     * Seta o valor do atributo 'freq'
     * @param freq O valor que o atributo 'freq' ira assumir
     * @return Se o valor passado condiz com os parametros estipulados para frequencia
     */
    public boolean setFreq(String freq) {
        // Verifica se freq esta correto
        if(Objects.equals(freq, "DAILY") || Objects.equals(freq, "WEEKLY") || Objects.equals(freq, "MONTLHY") || Objects.equals(freq, "YEARLY")){
            this.freq = freq;
            return true;
        } else{
            return false;
        }
    }

    public String getFreq() {
        return freq;
    }

    /**
     * Seta o valor do atributo 'interval'
     * @param interval O intervalo que ocorrera a repeticao
     * @return Se o valor passado condiz com os parametros estipulados para 'interval'
     */
    public boolean setInterval(String interval) {
        char[] numeros = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        boolean confere;

        // Verifica se o intervalo esta correto
        for(int i = 0; i < interval.length(); i++){
            char c = interval.charAt(i);
            confere = false;

            // Verifica se cada caractere do intervalo e um numero valido
            for(int j = 0; j < numeros.length; j++){
                if(c == numeros[j]){
                    confere = true;
                    break;
                }
            }

            if(! confere){
                return false;
            }
        }

        this.interval = interval;
        return true;
    }

    public String getInterval() {
        return interval;
    }

    public boolean setUntil(String until) {
        // Se a data passada estiver no formato correto seta o atributo 'until'
        if(!until.equals(null)){
            this.until = until;
            return true;
        } else
            return false;
    }

    /**
     * Seta o valor do atributo 'byday'
     * @param byday Os dias em que ocorrera a repeticao
     * @return Se o valor passado condiz com os parametros estipulados para 'byday'
     */
    public boolean setByday(String byday) {
        String[] d = {"SU", "MO", "TU", "WE", "TH", "FR", "SA"};
        boolean confere = false;

        // Verifica se os dias estao corretos
        if(byday.length() == 2){
            for(int i = 0; i < d.length; i++){
                if(byday.equals(d[i])){
                    confere = true;
                    break;
                }
            }
        } else{
            // Caso haja mais de um dia, verifica se todos os valores foram digitados corretamente
            for(int i = 0, j = byday.indexOf(','); i < byday.length(); i = j + 1, j = byday.indexOf(',', i)){
                confere = false;

                if(j == -1){
                    j = byday.length();
                }

                for(int k = 0; k < d.length; k++){
                    if(byday.substring(i, j).equals(d[k])){
                        confere = true;
                        break;
                    }
                }

                if(! confere){
                    break;
                }
            }
        }

        if(confere){
            this.byday = byday;
            return true;
        } else{
            return false;
        }
    }

    public String getByday() {
        return byday;
    }

    public Rrule(String freq, String inter, String until, String byday){
        setFreq(freq);
        setInterval(inter);
        setUntil(until);
        setByday(byday);
    }

    /**
     * Adiciona um EXDATE
     * @param exdate O EXDATE que sera adicionado
     * @return Se a operacao foi realizada com sucesso
     */
    public boolean addExdate(String exdate){
        if(! this.exdate.contains(exdate) && ! Objects.equals(exdate, null) && this.until.compareTo(exdate) >= 0){
            this.exdate.add(exdate);
            return true;
        } else
            return false;
    }

    /**
     * Modifica um EXDATE
     * @param exdateAnt O EXDATE a ser modificado
     * @param exdateNovo O valor no novo EXDATE
     * @return Se a operacao foi um sucesso
     */
    public boolean updateExdate(String exdateAnt, String exdateNovo){
        if(this.exdate.contains(exdateAnt) && this.until.compareTo(exdateNovo) >= 0){
            int i = this.exdate.indexOf(exdateAnt);
            this.exdate.set(i, exdateNovo);
            return true;
        } else
            return false;
    }

    /**
     * Remove um EXDATE
     * @param exdate O EXDATE a ser removido
     * @return Se a operacao foi um sucesso
     */
    public boolean removeExdate(String exdate){
        return this.exdate.remove(exdate);
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();

        this.exdate.forEach(elemento -> aux.append("EXDATE:" + elemento + "\n"));

        return "RRULE:" +
                "FREQ=" + freq + ';' +
                "INTERVAL=" + interval + ';' +
                "UNTIL=" + until + ';' +
                "BYDAY=" + byday + '\n' +
                aux;

    }
}