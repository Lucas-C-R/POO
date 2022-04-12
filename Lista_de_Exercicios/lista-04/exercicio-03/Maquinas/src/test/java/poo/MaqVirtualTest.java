package poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaqVirtualTest {
    @Test
    public void ligaDesligaTest(){
        MaqVirtual v = new MaqVirtual("Mamonas", 10, 8);

        assertEquals("Ligado", v.getStatus());

        v.setStatus("Desligado");
        assertEquals("Desligado", v.getStatus());

        v.setStatus("Ligado");
        assertEquals("Ligado", v.getStatus());
    }
}
