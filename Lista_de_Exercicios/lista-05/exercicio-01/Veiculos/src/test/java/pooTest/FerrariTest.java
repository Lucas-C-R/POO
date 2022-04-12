package pooTest;

import org.junit.jupiter.api.Test;
import poo.Ferrari;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes da classe Ferrari
 */
public class FerrariTest {
    /**
     * Testa o metodo frear
     */
    @Test
    public void frearTest(){
        Ferrari f = new Ferrari("125S");

        f.frear(10);

        // Carro ja esta parado, entao nao tem como reduzir
        assertEquals(0, f.getVelocidade());

        f.trocarMarcha(1);
        f.acelerar(15);
        f.frear(10);

        assertEquals(5, f.getVelocidade());

        f.frear(5);
        assertEquals(0, f.getVelocidade());

        f.acelerar(15);
        f.frear(-10);

        // Valores negativos nao sao aceitos
        assertEquals(15, f.getVelocidade());

        // frenagem abrupta
        f.frear(50);
        assertEquals(0, f.getVelocidade());
    }

    /**
     * Testa o metodo acelerar
     */
    @Test
    public void acelerarTest(){
        Ferrari f = new Ferrari("125S");

        // Nao sao aceitos valores negativos
        f.acelerar(-20);
        assertEquals(0, f.getVelocidade());

        // Nao aceita velocidades acima do limite da marcha
        f.trocarMarcha(1);
        f.acelerar(20);
        assertEquals(0, f.getVelocidade());

        f.acelerar(15);
        assertEquals(15, f.getVelocidade());

        f.trocarMarcha(2);
        f.acelerar(10);
        assertEquals(25, f.getVelocidade());

        f.trocarMarcha(3);
        f.acelerar(20);
        assertEquals(45, f.getVelocidade());

        f.trocarMarcha(4);
        f.acelerar(25);
        assertEquals(70, f.getVelocidade());

        f.trocarMarcha(5);
        f.acelerar(130);
        assertEquals(200, f.getVelocidade());

        f.acelerar(10);

        // Extrapolou o limite de velocidade
        assertEquals(200, f.getVelocidade());
    }

    /**
     * Testa o metodo trocarMarcha
     */
    @Test
    public void trocarMarchaTest(){
        Ferrari f = new Ferrari("125S");

        // Marcha inexistente
        f.trocarMarcha(6);
        assertEquals(0, f.getMarcha());

        f.trocarMarcha(1);
        f.acelerar(15);
        assertEquals(1, f.getMarcha());

        f.trocarMarcha(2);
        f.acelerar(10);
        assertEquals(2, f.getMarcha());

        // Carro etava muito rapido para essa marcha, entao morreu
        f.trocarMarcha(1);
        assertEquals(0, f.getVelocidade());

        f.trocarMarcha(1);
        f.acelerar(15);
        f.trocarMarcha(2);
        f.acelerar(10);

        f.trocarMarcha(3);
        f.acelerar(20);
        assertEquals(3, f.getMarcha());

        f.trocarMarcha(4);
        f.acelerar(25);
        assertEquals(4, f.getMarcha());

        f.trocarMarcha(5);
        f.acelerar(130);
        assertEquals(5, f.getMarcha());

        f.frear(130);
        f.trocarMarcha(4);
        assertEquals(4, f.getMarcha());

        // Carro mudou de marcha, porem morreu no processo
        f.trocarMarcha(-1);
        assertEquals(0, f.getVelocidade());
        assertEquals(-1, f.getMarcha());

        f.trocarMarcha(0);
        assertEquals(0, f.getMarcha());
    }

    /**
     * Testa os metodos abrirCapota e fecharCapota
     */
    @Test
    public void abrirFecharCapotaTest() {
        Ferrari f = new Ferrari("125S");

        assertTrue(f.abrirCapota());
        assertTrue(f.getCapota());

        // Se a capota ja estiver aberta, retorna false
        assertFalse(f.abrirCapota());
        assertTrue(f.getCapota());

        // Conseguiu fechar a capota
        assertTrue(f.fecharCapota());
        assertFalse(f.getCapota());

        // Se a capota ja estiver fechada, retorna false
        assertFalse(f.fecharCapota());
        assertFalse(f.getCapota());

        // Se estiver com menos de 20 km/h, abre a capota
        f.trocarMarcha(1);
        f.acelerar(15);
        assertTrue(f.abrirCapota());
        assertTrue(f.getCapota());

        // Se estiver igual ou com mais de 20 km/h, nao permite fechar a capota
        f.trocarMarcha(2);
        f.acelerar(10);
        assertFalse(f.fecharCapota());
        assertTrue(f.getCapota());

        // Se estiver com menos de 20 km/h, fecha a capota
        f.frear(10);
        assertTrue(f.fecharCapota());
        assertFalse(f.getCapota());

        // Se estiver igual ou com mais de 20 km/h, nao permite abrir a capota
        f.acelerar(5);
        assertFalse(f.abrirCapota());
        assertFalse(f.getCapota());
    }

    /**
     * Testa os metodos ligarFarolNeblina e desligarFarolDeblina
     */
    @Test
    public void ligarDesligarFarolNeblinaTest(){
        Ferrari f = new Ferrari("125S");

        assertTrue(f.ligarFarolNeblina());
        assertTrue(f.getFarolDeNeblina());

        // Farol de neblina ja esta aceso
        assertFalse(f.ligarFarolNeblina());
        assertTrue(f.getFarolDeNeblina());

        assertTrue(f.desligarFarolNeblina());
        assertFalse(f.getFarolDeNeblina());

        // Farol de neblina ja esta desligado
        assertFalse(f.desligarFarolNeblina());
        assertFalse(f.getFarolDeNeblina());
    }
}
