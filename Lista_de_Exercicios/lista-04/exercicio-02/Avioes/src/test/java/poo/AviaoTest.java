package poo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AviaoTest {
    @Test
    public void aumentaVelocidadeTest(){
        ArrayList<Motor> m = new ArrayList<>();
        m.add(new Motor("turbina"));

        Aviao a = new Aviao(m, 100, 8, 30);
        a.botao();

        assertTrue(a.aumentaVelocidade(10, 0));
        assertFalse(a.aumentaVelocidade(-10, 0));
        assertFalse(a.aumentaVelocidade(100, 0));
    }

    @Test
    public void diminuiVelocidadeTest(){
        ArrayList<Motor> m = new ArrayList<>();
        m.add(new Motor("turbina"));

        Aviao a = new Aviao(m, 100, 8, 30);
        a.botao();

        assertFalse(a.diminuiVelocidade(20, 0));

        a.aumentaVelocidade(10, 0);

        assertFalse(a.diminuiVelocidade(-10, 0));
        assertTrue(a.diminuiVelocidade(10, 0));
    }

    @Test
    public void botaoTest(){
        ArrayList<Motor> m = new ArrayList<>();
        m.add(new Motor("turbina"));

        Aviao a = new Aviao(m, 100, 8, 30);

        assertEquals("Aviao ligado.", a.botao());
        assertEquals("Aviao desligado.", a.botao());
    }

    @Test
    public void direcAviaoTest(){
        ArrayList<Motor> m = new ArrayList<>();
        m.add(new Motor("turbina"));

        Aviao a = new Aviao(m, 100, 8, 30);

        // Aviao ainda esta parado
        assertEquals("O aviao nao se moveu.", a.direcAviao("cima", 50));

        a.botao();
        a.aumentaVelocidade(50,0);
        assertEquals("O aviao nao se moveu.", a.direcAviao("cima", 0));
        assertEquals("O aviao nao se moveu.", a.direcAviao("cima", -50));

        assertEquals("O aviao foi para cima, com uma intensidade de 50N.", a.direcAviao("cima", 50));

        assertEquals("O aviao foi para baixo, com uma intensidade de 100N.", a.direcAviao("baixo", 100));

        assertEquals("O aviao foi para direita, com uma intensidade de 10N.", a.direcAviao("direita", 10));

        assertEquals("O aviao foi para esquerda, com uma intensidade de 3N.", a.direcAviao("esquerda", 3));
    }
}
