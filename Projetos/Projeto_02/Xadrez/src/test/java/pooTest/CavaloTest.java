package pooTest;

import org.junit.jupiter.api.Test;
import poo.Cavalo;
import poo.Peao;
import poo.Pecas;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CavaloTest {
    @Test
    public void moveCavaloBrancoTest(){
        Cavalo c = new Cavalo(3, 3, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(c);

        // Cavalo nao consegue se mover para as direcoes cardeais
        assertFalse(c.movePeca(3, 4, pecas));
        assertFalse(c.movePeca(3, 5, pecas));
        assertFalse(c.movePeca(4, 4, pecas));
        assertFalse(c.movePeca(4, 3, pecas));
        assertFalse(c.movePeca(4, 2, pecas));
        assertFalse(c.movePeca(3, 2, pecas));
        assertFalse(c.movePeca(2, 2, pecas));
        assertFalse(c.movePeca(2, 3, pecas));
        assertFalse(c.movePeca(2, 4, pecas));

        // Cavalo se movendo para cima e direita
        assertTrue(c.movePeca(4, 5, pecas));

        // Cavalo se movendo para baixo e para a esquerda
        assertTrue(c.movePeca(3, 3, pecas));

        // Cavalo de movendo para direita e para cima
        assertTrue(c.movePeca(5, 4, pecas));

        // Cavalo se movendo para esquerda e para baixo
        assertTrue(c.movePeca(3, 3, pecas));

        // Cavalo pula pecas livremente, independente da cor
        pecas.add(new Peao(4, 3, 0));
        pecas.add(new Peao(3, 4, 1));
        assertTrue(c.movePeca(5, 4, pecas));
        assertEquals(3, pecas.size());
    }

    @Test
    public void moveCavaloPretoTest(){
        Cavalo c = new Cavalo(6, 6, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(c);

        // Cavalo nao consegue se mover para as direcoes cardeais
        assertFalse(c.movePeca(6, 5, pecas));
        assertFalse(c.movePeca(6, 4, pecas));
        assertFalse(c.movePeca(5, 5, pecas));
        assertFalse(c.movePeca(5, 6, pecas));
        assertFalse(c.movePeca(5, 7, pecas));
        assertFalse(c.movePeca(6, 7, pecas));
        assertFalse(c.movePeca(7, 7, pecas));
        assertFalse(c.movePeca(7, 6, pecas));
        assertFalse(c.movePeca(7, 5, pecas));

        // Cavalo se movendo para baixo e para a esquerda
        assertTrue(c.movePeca(5, 4, pecas));

        // Cavalo se movendo para cima e direita
        assertTrue(c.movePeca(6, 6, pecas));

        // Cavalo de movendo para direita e para cima
        assertTrue(c.movePeca(8, 7, pecas));

        // Cavalo se movendo para esquerda e para baixo
        assertTrue(c.movePeca(6, 6, pecas));

        // Cavalo pula pecas livremente, independente da cor
        pecas.add(new Peao(5, 6, 0));
        pecas.add(new Peao(6, 5, 1));
        assertTrue(c.movePeca(5, 4, pecas));
        assertEquals(3, pecas.size());
    }

    @Test
    public void comerPecasPretasTest(){
        Cavalo c = new Cavalo(3, 3, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(c);

        // Para cima e direita
        pecas.add(new Peao(4, 5, 1));
        assertTrue(c.movePeca(4, 5, pecas));
        assertEquals(1, pecas.size());

        // Para baixo e esquerda
        pecas.add(new Peao(3, 3, 1));
        assertTrue(c.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Para cima e esquerda
        pecas.add(new Peao(2, 5, 1));
        assertTrue(c.movePeca(2, 5, pecas));
        assertEquals(1, pecas.size());

        // Para baixo e direita
        pecas.add(new Peao(3, 3, 1));
        assertTrue(c.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Para direita e cima
        pecas.add(new Peao(5, 4, 1));
        assertTrue(c.movePeca(5, 4, pecas));
        assertEquals(1, pecas.size());

        // Para a esquerda e para baixo
        pecas.add(new Peao(3, 3, 1));
        assertTrue(c.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Para direita e para baixo
        pecas.add(new Peao(5, 2, 1));
        assertTrue(c.movePeca(5, 2, pecas));
        assertEquals(1, pecas.size());

        // Para a esquerda e para cima
        pecas.add(new Peao(3, 3, 1));
        assertTrue(c.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Peca da mesma cor na posicao desejada
        pecas.add(new Peao(4, 5, 0));
        assertFalse(c.movePeca(4, 5, pecas));
        assertEquals(2, pecas.size());
    }

    @Test
    public void comerPecasBrancasTest(){
        Cavalo c = new Cavalo(3, 3, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(c);

        // Para cima e direita
        pecas.add(new Peao(4, 5, 0));
        assertTrue(c.movePeca(4, 5, pecas));
        assertEquals(1, pecas.size());

        // Para baixo e esquerda
        pecas.add(new Peao(3, 3, 0));
        assertTrue(c.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Para cima e esquerda
        pecas.add(new Peao(2, 5, 0));
        assertTrue(c.movePeca(2, 5, pecas));
        assertEquals(1, pecas.size());

        // Para baixo e direita
        pecas.add(new Peao(3, 3, 0));
        assertTrue(c.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Para direita e cima
        pecas.add(new Peao(5, 4, 0));
        assertTrue(c.movePeca(5, 4, pecas));
        assertEquals(1, pecas.size());

        // Para a esquerda e para baixo
        pecas.add(new Peao(3, 3, 0));
        assertTrue(c.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Para direita e para baixo
        pecas.add(new Peao(5, 2, 0));
        assertTrue(c.movePeca(5, 2, pecas));
        assertEquals(1, pecas.size());

        // Para a esquerda e para cima
        pecas.add(new Peao(3, 3, 0));
        assertTrue(c.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Peca da mesma cor na posicao desejada
        pecas.add(new Peao(4, 5, 1));
        assertFalse(c.movePeca(4, 5, pecas));
        assertEquals(2, pecas.size());
    }
}
