package poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaqRealTest {
    @Test
    public void addVirtualTest(){
        MaqReal m = new MaqReal("Titas", 20, 16);

        // Memoria de disco invalida
        assertFalse(m.addVirtual(new MaqVirtual("Mutantes", -10, 8)));
        assertFalse(m.addVirtual(new MaqVirtual("Mutantes", 0, 8)));

        // Memoria RAM invalida
        assertFalse(m.addVirtual(new MaqVirtual("Mutantes", 10, -8)));
        assertFalse(m.addVirtual(new MaqVirtual("Mutantes", 10, 0)));

        assertTrue(m.addVirtual(new MaqVirtual("Mutantes", 10, 8)));

        // Computador desligado
        m.setStatus("Desligado");
        assertFalse(m.addVirtual(new MaqVirtual("Skank", 5, 5)));

        m.setStatus("Ligado");
        assertTrue(m.addVirtual(new MaqVirtual("Skank", 5, 5)));

        // Memoria de disco insuficiente
        assertFalse(m.addVirtual(new MaqVirtual("RPM",10, 3)));

        // Memoria RAM insuficiente
        assertFalse(m.addVirtual(new MaqVirtual("RPM",5, 8)));

        // RAM e disco insuficiente
        assertFalse(m.addVirtual(new MaqVirtual("RPM",10, 8)));
    }

    @Test
    public void removeVirtualTest(){
        MaqReal m = new MaqReal("Titas", 20, 16);

        m.addVirtual(new MaqVirtual("RPM",10, 8));

        // Maquina virtual inexistente
        assertFalse(m.removeVirtual("Skank"));

        // Computador desligado
        m.setStatus("Desligado");
        assertFalse(m.removeVirtual("RPM"));

        m.setStatus("Ligado");
        assertTrue(m.removeVirtual("RPM"));
    }

    @Test
    public void ligaDesligTest(){
        MaqReal m = new MaqReal("Titas", 20, 16);
        MaqVirtual v = new MaqVirtual("Skank", 10, 8);

        m.addVirtual(v);

        // Maquina virtual inexistente
        assertEquals("nao existe.", m.ligaDeslig("Legiao"));

        m.ligaDeslig("Skank");
        assertEquals("Desligado", v.getStatus());

        m.ligaDeslig("Skank");
        assertEquals("Ligado", v.getStatus());
    }
}
