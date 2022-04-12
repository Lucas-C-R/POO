package pooTest;

import org.junit.jupiter.api.Test;
import poo.Panther;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes da classe Panther
 */
public class PantherTest {
    /**
     * Teste dos metodos abrirCapota e fecharCapota
     */
    @Test
    public void abrirFecharCapotaTest(){
        Panther p = new Panther("Panther");

        // Capota comeca fechada
        assertFalse(p.fecharCapota());
        assertFalse(p.getCapota());

        assertTrue(p.abrirCapota());
        assertTrue(p.getCapota());

        // A capota ja esta aberta
        assertFalse(p.abrirCapota());
        assertTrue(p.getCapota());

        p.trocarMarcha(1);
        p.acelerar(1);

        // Com o carro em movimento, nao e possivel fechar a capota
        assertFalse(p.fecharCapota());
        assertTrue(p.getCapota());

        p.frear(1);
        assertTrue(p.fecharCapota());
        assertFalse(p.getCapota());

        // Com o carro em movimento, nao e possivel abrir a capota
        p.acelerar(1);
        assertFalse(p.abrirCapota());
        assertFalse(p.getCapota());
    }

    /**
     * Teste dos metodos ativarTracao e desativarTracao
     */
    @Test
    public void ativarDesativarTracaoTest(){
        Panther p = new Panther("Panther");

        // Tracao comeca desativada
        assertFalse(p.getTracao());

        // Conseguiu ativar a tracao
        assertTrue(p.ativarDesativarTracao());
        assertTrue(p.getTracao());

        // So e possivel desativar a tracao, se as rodas estiverem abertas
        p.recolherRodas();
        assertFalse(p.ativarDesativarTracao());
        assertTrue(p.getTracao());

        p.abrirRodas();
        p.trocarMarcha(1);
        p.acelerar(15);

        // Com o carro em movimento, nao e possivel ativar ou desativar a tracao
        assertFalse(p.ativarDesativarTracao());
        assertFalse(p.ativarDesativarTracao());

        p.frear(15);

        // Conseguiu desativar a tracao
        assertTrue(p.ativarDesativarTracao());
        assertFalse(p.getTracao());

        // So e possivel ativar a tracao, se as rodas estiverem abertas
        p.recolherRodas();
        assertFalse(p.ativarDesativarTracao());
        assertFalse(p.getTracao());
    }

    /**
     * Teste dos metodos esvaziarLastro e encherLastro
     */
    @Test
    public void esvaziarEncherLastroTest(){
        Panther p = new Panther("Panther");

        // O lastro comeca cheio
        assertTrue(p.getLastro());

        p.esvaziarLastro();
        assertFalse(p.getLastro());

        // Se as rodas estao recolhidas, nao e possivel encher o lastro
        p.recolherRodas();
        p.encherLastro();
        assertFalse(p.getLastro());

        p.abrirRodas();
        p.encherLastro();
        assertTrue(p.getLastro());
    }

    /**
     * Teste dos metodos recolherRodas e abrirRodas
     */
    @Test
    public void recolherAbrirRodasTest(){
        Panther p = new Panther("Panther");

        // As rodas comecam abertas
        assertFalse(p.abrirRodas());
        assertTrue(p.getRodas());

        // Recolhendo as rodas
        assertTrue(p.recolherRodas());
        assertFalse(p.getRodas());
        assertFalse(p.getLastro());

        p.trocarMarcha(1);
        p.acelerar(12);

        // Com o carro em movimento, nao e possivel abrir as rodas
        assertFalse(p.abrirRodas());
        assertFalse(p.getRodas());
        assertFalse(p.getLastro());

        p.frear(12);

        // Abrindo as rodas
        assertTrue(p.abrirRodas());
        assertTrue(p.getRodas());

        p.acelerar(12);

        // Com o carro em movimento, nao e possivel recolher as rodas
        assertFalse(p.recolherRodas());
        assertTrue(p.getRodas());
    }

    /**
     * Teste do metodo frear
     */
    @Test
    public void frearTest(){
        Panther p = new Panther("Panther");

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
     * Teste do metodo acelerar
     */
    @Test
    public void acelerarTest(){
        Panther p = new Panther("Panther");

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
     * Teste do metodo trocarMarcha
     */
    @Test
    public void trocarMarchaTest(){
        Panther p = new Panther("Panther");

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
     * Teste do metodo setTempRefri
     */
    @Test
    public void setTempRefri(){
        Panther p = new Panther("Panther");

        p.setTempRefri(1);
        assertEquals(1, p.getTempRefri());

        p.setTempRefri(3);
        assertEquals(3, p.getTempRefri());

        p.setTempRefri(2);
        assertEquals(2, p.getTempRefri());

        // A escala do ar vai de 0 a 3
        p.setTempRefri(-1);
        assertEquals(2, p.getTempRefri());

        p.setTempRefri(4);
        assertEquals(2, p.getTempRefri());
    }
}
