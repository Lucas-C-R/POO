package pooTest;

import org.junit.jupiter.api.Test;
import poo.Pessoa;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {
    @Test
    public void compareToTest(){
        Pessoa p1 = new Pessoa("Joao","Silva" , "1990-01-01");
        Pessoa p2 = new Pessoa("Ana", "Paula" , "1989-01-01");
        Pessoa p3 = new Pessoa ("Joao", "Santos", "1991-01-01");
        Pessoa p4 = new Pessoa ("Joao", "Silva" , "1980-01-01");
        Pessoa p5 = new Pessoa("Joao","Silva" , "1990-01-01");

        // Comparacoes baseadas na p1 e na p5
        assertEquals(1, p1.compareTo(p2));
        assertEquals(1, p1.compareTo(p3));
        assertEquals(1, p1.compareTo(p4));
        assertEquals(0, p1.compareTo(p5));

        // Comparacoes baseadas na p2
        assertEquals(-1, p2.compareTo(p1));
        assertEquals(-1, p2.compareTo(p3));
        assertEquals(-1, p2.compareTo(p4));
        assertEquals(-1, p2.compareTo(p5));

        // Comparacoes baseadas na p3
        assertEquals(-1, p3.compareTo(p1));
        assertEquals(1, p3.compareTo(p2));
        assertEquals(-1, p3.compareTo(p4));
        assertEquals(-1, p3.compareTo(p5));

        // Comparacoes baseadas na p4
        assertEquals(-1, p4.compareTo(p1));
        assertEquals(1, p4.compareTo(p2));
        assertEquals(1, p4.compareTo(p3));
        assertEquals(-1, p4.compareTo(p5));
    }
}
