package pooTest;

import org.junit.jupiter.api.Test;
import poo.Pampa;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes da classe Pampa
 */
public class PampaTest {
    /**
     * Testa os metodos ativarTracao e desativarTracao
     */
    @Test
    public void ativarDesativarTracaoTest(){
        Pampa p = new Pampa("Pampa S");

        // Tracao comeca desativada
        assertFalse(p.getTracao());

        // Conseguiu ativar a tracao
        assertTrue(p.ativarDesativarTracao());
        assertTrue(p.getTracao());

        p.trocarMarcha(1);
        p.acelerar(15);

        // Com o carro em movimento, nao e possivel ativar ou desativar a tracao
        assertFalse(p.ativarDesativarTracao());
        assertFalse(p.ativarDesativarTracao());

        p.frear(15);

        // Conseguiu desativar a tracao
        assertTrue(p.ativarDesativarTracao());
        assertFalse(p.getTracao());
    }

    /**
     * Testa o metodo frear
     */
    @Test
    public void frearTest(){
        Pampa p = new Pampa("Pampa S");

        p.frear(10);

        // Carro ja esta parado, entao nao tem como reduzir
        assertEquals(0, p.getVelocidade());

        p.trocarMarcha(1);
        p.acelerar(15);
        p.frear(10);

        assertEquals(5, p.getVelocidade());

        p.frear(5);
        assertEquals(0, p.getVelocidade());

        p.acelerar(15);
        p.frear(-10);

        // Valores negativos nao sao aceitos
        assertEquals(15, p.getVelocidade());

        // frenagem abrupta
        p.frear(50);
        assertEquals(0, p.getVelocidade());
    }

    /**
     * Testa o metodo acelerar
     */
    @Test
    public void acelerarTest(){
        Pampa p = new Pampa("Pampa S");

        // Nao sao aceitos valores negativos
        p.acelerar(-20);
        assertEquals(0, p.getVelocidade());

        // Nao aceita velocidades acima do limite da marcha
        p.trocarMarcha(1);
        p.acelerar(20);
        assertEquals(0, p.getVelocidade());

        p.acelerar(15);
        assertEquals(15, p.getVelocidade());

        p.trocarMarcha(2);
        p.acelerar(10);
        assertEquals(25, p.getVelocidade());

        p.trocarMarcha(3);
        p.acelerar(20);
        assertEquals(45, p.getVelocidade());

        p.trocarMarcha(4);
        p.acelerar(25);
        assertEquals(70, p.getVelocidade());

        p.trocarMarcha(5);
        p.acelerar(70);
        assertEquals(140, p.getVelocidade());

        p.acelerar(10);

        // Extrapolou o limite de velocidade
        assertEquals(140, p.getVelocidade());
    }

    /**
     * Testa o metodo trocarMarcha
     */
    @Test
    public void trocarMarchaTest(){
        Pampa p = new Pampa("Pampa S");

        // Marcha inexistente
        p.trocarMarcha(6);
        assertEquals(0, p.getMarcha());

        p.trocarMarcha(1);
        p.acelerar(15);
        assertEquals(1, p.getMarcha());

        p.trocarMarcha(2);
        p.acelerar(10);
        assertEquals(2, p.getMarcha());

        // Carro etava muito rapido para essa marcha, entao morreu
        p.trocarMarcha(1);
        assertEquals(0, p.getVelocidade());

        p.trocarMarcha(1);
        p.acelerar(15);
        p.trocarMarcha(2);
        p.acelerar(10);

        p.trocarMarcha(3);
        p.acelerar(20);
        assertEquals(3, p.getMarcha());

        p.trocarMarcha(4);
        p.acelerar(25);
        assertEquals(4, p.getMarcha());

        p.trocarMarcha(5);
        p.acelerar(70);
        assertEquals(5, p.getMarcha());

        p.frear(70);
        p.trocarMarcha(4);
        assertEquals(4, p.getMarcha());

        // Carro mudou de marcha, porem morreu no processo
        p.trocarMarcha(-1);
        assertEquals(0, p.getVelocidade());
        assertEquals(-1, p.getMarcha());

        p.trocarMarcha(0);
        assertEquals(0, p.getMarcha());
    }

    /**
     * Testa os metodos abrirCacamba e fecharCacamba
     */
    @Test
    public void abrirFecharCacambaTest(){
        Pampa p = new Pampa("Pampa S");

        // Cacamba comeca fechada
        assertFalse(p.fecharCacamba());
        assertFalse(p.getCacamba());

        p.trocarMarcha(1);
        p.acelerar(1);

        // Nao e possivel abrir a cacamba com o carro em movimento
        assertFalse(p.abrirCacamba());
        assertFalse(p.getCacamba());

        p.frear(1);

        // Cacamba aberta com sucesso
        assertTrue(p.abrirCacamba());
        assertTrue(p.getCacamba());

        // Cacamba ja esta aberta
        assertFalse(p.abrirCacamba());

        p.acelerar(1);

        // Nao e possivel fechar a cacamba com o carro em movimento
        assertFalse(p.fecharCacamba());
        assertTrue(p.getCacamba());
    }
}
