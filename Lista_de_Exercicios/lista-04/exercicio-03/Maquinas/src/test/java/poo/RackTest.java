package poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RackTest {
    @Test
    public void addMaqTest(){
        Rack r = new Rack();

        assertTrue(r.addMaq(new MaqReal("ACDC", 1000, 8)));
        assertTrue(r.addMaq(new MaqReal("Oasis", 1000, 16)));
        assertTrue(r.addMaq(new MaqReal("Pink Floyd", 1000, 8)));

        // Tentando adicionar uma maquina com um nome existente
        assertFalse(r.addMaq(new MaqReal("ACDC", 1000, 16)));

        assertTrue(r.addMaq(new MaqReal("Gorillaz", 1000, 32)));
        assertTrue(r.addMaq(new MaqReal("Blitz", 1000, 8)));

        // Sem mais espaco no RACK
        assertFalse(r.addMaq(new MaqReal("Beatles", 1000, 8)));
    }

    @Test
    public void removeMaqTest(){
        Rack r = new Rack();

        r.addMaq(new MaqReal("Legiao", 1000, 32));

        // Maquina inexistente
        assertFalse(r.removeMaq("Blitz"));

        assertTrue(r.removeMaq("Legiao"));
    }

    @Test
    public void ligaDeslMaqTest(){
        Rack r = new Rack();

        r.addMaq(new MaqReal("Raimundos", 1000, 8));

        // Maquina inexistente
        assertEquals("nao existe.", r.ligDeslMaq("Legiao"));

        // Computador ligado
        assertEquals("Desligado", r.ligDeslMaq("Raimundos"));

        // Computador desligado
        assertEquals("Ligado", r.ligDeslMaq("Raimundos"));
    }

    @Test
    public void addVirtualTest(){
        Rack r = new Rack();

        r.addMaq(new MaqReal("Ramones", 50, 12));

        // Maquina real inexistente
        assertFalse(r.addVirtual("Legiao", new MaqVirtual("Capital Inicial", 10, 6)));

        assertTrue(r.addVirtual("Ramones", new MaqVirtual("Capital Inicial", 10, 6)));
        assertTrue(r.addVirtual("Ramones", new MaqVirtual("Oasis", 40, 5)));

        // Limite da memoria de disco alcancado
        assertFalse(r.addVirtual("Ramones", new MaqVirtual("ACDC", 20, 6)));

        r.removeVirtual("Ramones", "Oasis");
        assertTrue(r.addVirtual("Ramones", new MaqVirtual("Pink Floyd", 20, 6)));

        // Limite da memoria RAM alcancado
        assertFalse(r.addVirtual("Ramones", new MaqVirtual("Gorillaz", 5, 3)));
    }

    @Test
    public void removeVirtualTest(){
        Rack r = new Rack();

        r.addMaq(new MaqReal("Blitz", 30, 8));

        r.addVirtual("Blitz", new MaqVirtual("Beatles", 10, 4));

        // Maquina real inexistente
        assertFalse(r.removeVirtual("Oasis", "Beatles"));

        // Maquina virtual inexistente
        assertFalse(r.removeVirtual("Blitz", "Oasis"));

        assertTrue(r.removeVirtual("Blitz", "Beatles"));
    }

    @Test
    public void ligaDesligVirtualTest(){
        Rack r = new Rack();

        r.addMaq(new MaqReal("Blitz", 30, 8));
        r.addVirtual("Blitz", new MaqVirtual("Beatles", 10, 4));

        // Maquina virtual ligada
        assertEquals("Desligado", r.ligaDesligVirtual("Blitz", "Beatles"));

        // Maquina virtual desligada
        assertEquals("Ligado", r.ligaDesligVirtual("Blitz", "Beatles"));
    }
}