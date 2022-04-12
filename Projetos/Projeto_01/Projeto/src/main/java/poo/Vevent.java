package poo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Vevent {
    private String sumario;
    private String uid;
    private String dataCriac;
    private String inicData;
    private String fimData;
    private String local;
    private Rrule regra;

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public String getUid() {
        return uid;
    }

    private void setUid() {
        // Gera um UID aleatorio
        this.uid = UUID.randomUUID().toString();
    }

    public String getDataCriac() {
        return dataCriac;
    }

    private void setDataCriac() {
        // Obtem a data atual
        LocalDateTime agora = LocalDateTime.now();

        this.dataCriac = separaDatas(agora.toString().substring(11, 19) + "," + agora.toString().substring(0, 10));
    }

    public String getInicData() {
        return inicData;
    }

    public void setInicData(String inicData) {
        inicData = separaDatas(inicData);

        // Se a data passada estiver nos moldes
        if(inicData != null){
            // Se a data final ja estiver estipulada, entao nao permite que a data inicial ultrapasse ela
            if(this.fimData != null){
                if(inicData.compareTo(this.fimData) <= 0){
                    this.inicData = inicData;
                }
            } else
                this.inicData = inicData;
        }
    }

    public String getFimData() {
        return fimData;
    }

    public void setFimData(String fimData) {
        String aux = separaDatas(fimData);

        // Se a data final for menor que a data inicial, entao nao a adiciona
        if(aux != null && aux.compareTo(this.inicData) > 0){
            this.fimData = separaDatas(fimData);
        }
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public Vevent(String sumario, String dataInic, String dataFim, String local){
        setInicData(dataInic);

        if(this.inicData != null){
            setSumario(sumario);
            setUid();
            setDataCriac();
            setFimData(dataFim);
            setLocal(local);
        }
    }

    /**
     * Formata a hora e a data passadas, para os padroes exigidos
     * @param dados A hora e a data
     * @return A data e hora, no padrao desejado ou um 'null', caso nao consiga
     */
    private String formataDatas(int[] dados){
        LocalDateTime localDateTime;

        try {
            localDateTime = LocalDateTime.of(dados[5], dados[4], dados[3], dados[0], dados[1], dados[2]);

            return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
        } catch(Exception e){
            // Esse try vai ser utilizado pelo atributo 'dataCriac', ja que o LocalDateTime.now(), retorna um formato diferente
            try {
                localDateTime = LocalDateTime.of(dados[3], dados[4], dados[5], dados[0], dados[1], dados[2]);

                return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
            } catch(Exception e1){
                return null;
            }
        }
    }

    /**
     * Verifica se os dias e meses, estao corretos
     * @param dia O dia a ser verificado
     * @param mes O mes a ser verificado
     * @return Se o dia e o mes, estao corretos
     */
    private boolean verificaDatas(int dia, int mes){
        if(mes >= 1 && mes <= 7){
            if(mes == 2){
                if(! (dia >= 1 && dia <= 28)){
                    return false;
                }
            } else if(mes % 2 != 0){
                if(! (dia >= 1 && dia <= 31)){
                    return false;
                }
            } else{
                if(! (dia >= 1 && dia <= 30)){
                    return false;
                }
            }
        } else if(mes >= 8 && mes <= 12){
            if(mes % 2 == 0){
                if(! (dia >= 1 && dia <= 31)){
                    return false;
                }
            } else{
                if(! (dia >= 1 && dia <= 30)){
                    return false;
                }
            }
        } else{
            return false;
        }

        return true;
    }

    /**
     * Verifica se o horario passada, esta em um formato correto
     * @param dados O horario e a data desejados
     * @return Se o horario e a data, estao nos conformes
     */
    private boolean verificaHoras(int[] dados){
        if(dados[0] >= 0 && dados[0] < 24 && dados[1] >= 0 && dados[1] < 60 && dados[2] >= 0 && dados[2] < 60){
            boolean teste1 = verificaDatas(dados[3], dados[4]);

            // O LocalDateTime.now() retorna um formato diferente
            boolean teste2 = verificaDatas(dados[5], dados[4]);

            return (teste1 || teste2);
        } else
            return false;
    }

    /**
     * Separa as horas, minutos, segundos, o dia, mes e ano.
     * @param data A data desejada
     * @return A data no formato estipulado
     */
    private String separaDatas(String data){
        int[] dados = new int[6];
        int k = 0;

        try{
            // TODO ao inves disso usar int[] dados = data.split(":")
            for(int i = 0, j = data.indexOf(':'); k <= 2;i = j + 1, j = data.indexOf(':', i), k++){
                if(j == -1){
                    j = i + 2;
                }
                dados[k] = Integer.parseInt(data.substring(i, j));
            }

            for(int i = data.indexOf(',') + 1, j = data.indexOf("-"); k <= 5; i = j + 1, j = data.indexOf("-", i), k++){
                if(j == -1){
                    j = data.length();
                }
                dados[k] = Integer.parseInt(data.substring(i, j));
            }

            if(verificaHoras(dados)){
                return formataDatas(dados);
            } else
                return null;
        } catch (Exception e){
            return null;
        }
    }

    public boolean addRrule(String freq, String inter, String until, String byday){
        until = separaDatas(until);

        // Se nao houver nenhuma regra e a data for menor que a data final do VCALENDAR, permite adicionar uma
        if(Objects.equals(this.regra, null) && until.compareTo(this.fimData) <= 0 && !until.equals(null)){
            this.regra = new Rrule(freq, inter, until, byday);

            if(this.regra.getFreq() == null || this.regra.getInterval() == null || this.regra.getByday() == null){
                this.regra = null;
                return false;
            } else
                return true;
        } else
            return false;
    }

    public boolean removeRrule(){
        // Se houver uma regra, remove ela
        if(! Objects.equals(this.regra, null)){
            this.regra = null;
            return true;
        } else
            return false;
    }

    public boolean updateRrule(String indice, String valorAlterado){
        // Se existir uma regra, permite modificar um atributo dela
        if(! Objects.equals(this.regra, null)){
            switch (indice){
                // Mexe na frequencia
                case "1":
                    return this.regra.setFreq(valorAlterado.toUpperCase());

                // Mexe no intervalo
                case "2":
                    return this.regra.setInterval(valorAlterado);

                // Mexe na data limite
                case "3":
                    return this.regra.setUntil(separaDatas(valorAlterado));

                // Mexe nos dias
                case "4":
                    return this.regra.setByday(valorAlterado.toUpperCase());

                default:
                    break;
            }
        }

        return false;
    }

    public boolean addExdate(String exdate){
        return this.regra.addExdate(separaDatas(exdate));
    }

    public boolean updateExdate(String exdateAnt, String exdateNovo){
        return this.regra.updateExdate(separaDatas(exdateAnt), separaDatas(exdateNovo));
    }

    public boolean removeExdate(String exdate){
        return this.regra.removeExdate(separaDatas(exdate));
    }

    @Override
    public String toString() {
        if(! Objects.equals(this.regra, null)){
            return "BEGIN:VEVENT" + "\n" +
                    "SUMMARY:" + sumario + '\n' +
                    "DTSTAMP:" + dataCriac + '\n' +
                    "DTSTART:" + inicData + '\n' +
                    "DTEND:" + fimData + '\n' +
                    "UID:" + uid + '\n' +
                    regra +
                    "LOCATION:" + local + '\n' +
                    "END:VEVENT" + '\n';
        } else{
            return "BEGIN:VEVENT" + "\n" +
                    "SUMMARY:" + sumario + '\n' +
                    "DTSTAMP:" + dataCriac + '\n' +
                    "DTSTART:" + inicData + '\n' +
                    "DTEND:" + fimData + '\n' +
                    "UID:" + uid + '\n' +
                    "LOCATION:" + local + '\n' +
                    "END:VEVENT" + '\n';
        }
    }
}
