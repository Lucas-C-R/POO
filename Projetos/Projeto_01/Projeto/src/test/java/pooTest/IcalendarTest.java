package pooTest;

import org.junit.jupiter.api.Test;
import poo.Vcalendar;
import poo.Vevent;
import static org.junit.jupiter.api.Assertions.*;

public class IcalendarTest {

    @Test
    public void testaFalhas(){
        // Tentando criar um calendario com uma VEVENT incorreta (data inicial passada com '/' ao inves de '-')
        Vcalendar calendario = new Vcalendar("IFSC", "poo", "PT", new Vevent("teste", "13:30:00,03/09/2001", "21:45:12,14-02-2022", "casa"));
        assertEquals(calendario.toString(), "\nErro ao criar o calendario!\n\n");

        // Tentando criar um calendario com uma VEVENT incorreta (hora inicial acima de 23)
        calendario = new Vcalendar("IFSC", "poo", "PT", new Vevent("teste", "24:30:00,03-09-2001", "21:45:12,14-02-2022", "casa"));
        assertEquals(calendario.toString(), "\nErro ao criar o calendario!\n\n");

        // Tentando criar um calendario com uma VEVENT incorreta (minuto inicial passou de 59)
        calendario = new Vcalendar("IFSC", "poo", "PT", new Vevent("teste", "13:60:00,03-09-2001", "21:45:12,14-02-2022", "casa"));
        assertEquals(calendario.toString(), "\nErro ao criar o calendario!\n\n");

        // Tentando criar um calendario com uma VEVENT incorreta (segundo inicial passou de 59)
        calendario = new Vcalendar("IFSC", "poo", "PT", new Vevent("teste", "13:20:60,03-09-2001", "21:45:12,14-02-2022", "casa"));
        assertEquals(calendario.toString(), "\nErro ao criar o calendario!\n\n");

        // Tentando criar um calendario com uma VEVENT incorreta (data final passada com '/' ao inves de '-')
        calendario = new Vcalendar("IFSC", "poo", "PT", new Vevent("teste", "13:30:00,03-09-2001", "21:45:12,14/02-2022", "casa"));
        assertEquals(calendario.toString(), "\nErro ao criar o calendario!\n\n");

        // Tentando criar um calendario com uma VEVENT incorreta (hora final passou de 23)
        calendario = new Vcalendar("IFSC", "poo", "PT", new Vevent("teste", "13:30:00,03-09-2001", "24:45:12,14-02-2022", "casa"));
        assertEquals(calendario.toString(), "\nErro ao criar o calendario!\n\n");

        // Tentando criar um calendario com uma VEVENT incorreta (minuto final passou de 59)
        calendario = new Vcalendar("IFSC", "poo", "PT", new Vevent("teste", "13:30:00,03-09-2001", "21:60:12,14-02-2022", "casa"));
        assertEquals(calendario.toString(), "\nErro ao criar o calendario!\n\n");

        // Tentando criar um calendario com uma VEVENT incorreta (segundo final passou de 59)
        calendario = new Vcalendar("IFSC", "poo", "PT", new Vevent("teste", "13:30:00,03-09-2001", "21:45:60,14-02-2022", "casa"));
        assertEquals(calendario.toString(), "\nErro ao criar o calendario!\n\n");
    }

    @Test
    public void testaAcertos(){
        // Criando um VCALENDAR com informacoes corretas
        Vevent v = new Vevent("teste", "13:30:00,03-09-2001", "21:45:12,14-02-2022", "casa");
        Vcalendar calendario = new Vcalendar("IFSC", "poo", "PT", v);
        assertEquals(calendario.toString(), "\nBEGIN:VCALENDAR\n" +
                                                    "VERSION:2.0\n" +
                                                    "PRODID:-//IFSC//poo//PT\n" +
                                                    "BEGIN:VEVENT\n" +
                                                    "SUMMARY:teste\n" +
                                                    "DTSTAMP:" + v.getDataCriac() + '\n' +
                                                    "DTSTART:20010903T133000\n" +
                                                    "DTEND:20220214T214512\n" +
                                                    "UID:" + v.getUid() + '\n' +
                                                    "LOCATION:casa\n" +
                                                    "END:VEVENT\n" +
                                                    "END:VCALENDAR" + '\n');

        // Adicionando um RRULE
        calendario.addRrule("teste", "DAILY", "4", "16:50:15,09-12-2008", "MO,TU");
        assertEquals(calendario.toString(), "\nBEGIN:VCALENDAR\n" +
                                                    "VERSION:2.0\n" +
                                                    "PRODID:-//IFSC//poo//PT\n" +
                                                    "BEGIN:VEVENT\n" +
                                                    "SUMMARY:teste\n" +
                                                    "DTSTAMP:" + v.getDataCriac() + '\n' +
                                                    "DTSTART:20010903T133000\n" +
                                                    "DTEND:20220214T214512\n" +
                                                    "UID:" + v.getUid() + '\n' +
                                                    "RRULE:FREQ=DAILY;INTERVAL=4;UNTIL=20081209T165015;BYDAY=MO,TU\n" +
                                                    "LOCATION:casa\n" +
                                                    "END:VEVENT\n" +
                                                    "END:VCALENDAR\n");

        // Adicionando um EXDATE
        calendario.addExdate("teste", "12:20:40,10-08-2007");
        assertEquals(calendario.toString(), "\nBEGIN:VCALENDAR\n" +
                                                    "VERSION:2.0\n" +
                                                    "PRODID:-//IFSC//poo//PT\n" +
                                                    "BEGIN:VEVENT\n" +
                                                    "SUMMARY:teste\n" +
                                                    "DTSTAMP:" + v.getDataCriac() + '\n' +
                                                    "DTSTART:20010903T133000\n" +
                                                    "DTEND:20220214T214512\n" +
                                                    "UID:" + v.getUid() + '\n' +
                                                    "RRULE:FREQ=DAILY;INTERVAL=4;UNTIL=20081209T165015;BYDAY=MO,TU\n" +
                                                    "EXDATE:20070810T122040\n" +
                                                    "LOCATION:casa\n" +
                                                    "END:VEVENT\n" +
                                                    "END:VCALENDAR\n");

        // Adicionando mais um EXDATE
        calendario.addExdate("teste", "22:30:12,05-03-2008");
        assertEquals(calendario.toString(), "\nBEGIN:VCALENDAR\n" +
                                                    "VERSION:2.0\n" +
                                                    "PRODID:-//IFSC//poo//PT\n" +
                                                    "BEGIN:VEVENT\n" +
                                                    "SUMMARY:teste\n" +
                                                    "DTSTAMP:" + v.getDataCriac() + '\n' +
                                                    "DTSTART:20010903T133000\n" +
                                                    "DTEND:20220214T214512\n" +
                                                    "UID:" + v.getUid() + '\n' +
                                                    "RRULE:FREQ=DAILY;INTERVAL=4;UNTIL=20081209T165015;BYDAY=MO,TU\n" +
                                                    "EXDATE:20070810T122040\n" +
                                                    "EXDATE:20080305T223012\n" +
                                                    "LOCATION:casa\n" +
                                                    "END:VEVENT\n" +
                                                    "END:VCALENDAR\n");

        // Adicionando mais um VEVENT
        Vevent v1 = new Vevent("POO", "07:30:00,03-09-2021", "09:20:00,04-03-2022", "IFSC");
        calendario.addVevent(v1);
        assertEquals(calendario.toString(), "\nBEGIN:VCALENDAR\n" +
                                                    "VERSION:2.0\n" +
                                                    "PRODID:-//IFSC//poo//PT\n" +
                                                    "BEGIN:VEVENT\n" +
                                                    "SUMMARY:teste\n" +
                                                    "DTSTAMP:" + v.getDataCriac() + '\n' +
                                                    "DTSTART:20010903T133000\n" +
                                                    "DTEND:20220214T214512\n" +
                                                    "UID:" + v.getUid() + '\n' +
                                                    "RRULE:FREQ=DAILY;INTERVAL=4;UNTIL=20081209T165015;BYDAY=MO,TU\n" +
                                                    "EXDATE:20070810T122040\n" +
                                                    "EXDATE:20080305T223012\n" +
                                                    "LOCATION:casa\n" +
                                                    "END:VEVENT\n" +
                                                    "BEGIN:VEVENT\n" +
                                                    "SUMMARY:POO\n" +
                                                    "DTSTAMP:" + v1.getDataCriac() + '\n' +
                                                    "DTSTART:20210903T073000\n" +
                                                    "DTEND:20220304T092000\n" +
                                                    "UID:" + v1.getUid() + '\n' +
                                                    "LOCATION:IFSC\n" +
                                                    "END:VEVENT\n" +
                                                    "END:VCALENDAR\n");

        // Removendo o RRULE e consequentemente, os dois EXDATE
        calendario.removeRrule("teste");
        assertEquals(calendario.toString(), "\nBEGIN:VCALENDAR\n" +
                                                    "VERSION:2.0\n" +
                                                    "PRODID:-//IFSC//poo//PT\n" +
                                                    "BEGIN:VEVENT\n" +
                                                    "SUMMARY:teste\n" +
                                                    "DTSTAMP:" + v.getDataCriac() + '\n' +
                                                    "DTSTART:20010903T133000\n" +
                                                    "DTEND:20220214T214512\n" +
                                                    "UID:" + v.getUid() + '\n' +
                                                    "LOCATION:casa\n" +
                                                    "END:VEVENT\n" +
                                                    "BEGIN:VEVENT\n" +
                                                    "SUMMARY:POO\n" +
                                                    "DTSTAMP:" + v1.getDataCriac() + '\n' +
                                                    "DTSTART:20210903T073000\n" +
                                                    "DTEND:20220304T092000\n" +
                                                    "UID:" + v1.getUid() + '\n' +
                                                    "LOCATION:IFSC\n" +
                                                    "END:VEVENT\n" +
                                                    "END:VCALENDAR\n");
    }
}
