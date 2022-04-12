package poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MotorTest {
    @Test
    public void aumentaPotenciaTest(){
        Motor m = new Motor("helice");

        assertFalse(m.aumentaPotencia(-10));
        assertFalse(m.aumentaPotencia(101));
        assertTrue(m.aumentaPotencia(20));
    }

    @Test
    public void diminuiPotenciaTest(){
        Motor m = new Motor("helice");

        assertFalse(m.diminuiPotencia(-10));
        assertFalse(m.diminuiPotencia(20));

        m.aumentaPotencia(20);
        assertTrue(m.diminuiPotencia(20));
    }
}
