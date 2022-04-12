package pooTest;

import org.junit.jupiter.api.Test;
import poo.Peao;
import poo.Pecas;
import poo.Rainha;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RainhaTest {
    @Test
    public void moverRainhaBranca(){
        Rainha r = new Rainha(4, 4, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(r);

        // Possiveis movimentacoes da rainha lateralmente
        assertTrue(r.movePeca(7, 4, pecas));
        assertTrue(r.movePeca(4, 4, pecas));
        assertTrue(r.movePeca(4, 7, pecas));
        assertTrue(r.movePeca(4, 4, pecas));

        // Possiveis movimentos da rainha diagonalmente
        assertTrue(r.movePeca(7, 7, pecas));
        assertTrue(r.movePeca(4, 4, pecas));
        assertTrue(r.movePeca(7, 1, pecas));
        assertTrue(r.movePeca(4, 4, pecas));

        // Movimentos impossiveis
        assertFalse(r.movePeca(5, 6, pecas));
        assertFalse(r.movePeca(3, 6, pecas));
        assertFalse(r.movePeca(0, 1, pecas));
        assertFalse(r.movePeca(3, 2, pecas));
        assertFalse(r.movePeca(5, 2, pecas));
    }

    @Test
    public void moverPretaBranca(){
        Rainha r = new Rainha(4, 4, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(r);

        // Possiveis movimentacoes da rainha lateralmente
        assertTrue(r.movePeca(7, 4, pecas));
        assertTrue(r.movePeca(4, 4, pecas));
        assertTrue(r.movePeca(4, 7, pecas));
        assertTrue(r.movePeca(4, 4, pecas));

        // Possiveis movimentos da rainha diagonalmente
        assertTrue(r.movePeca(7, 7, pecas));
        assertTrue(r.movePeca(4, 4, pecas));
        assertTrue(r.movePeca(7, 1, pecas));
        assertTrue(r.movePeca(4, 4, pecas));

        // Movimentos impossiveis
        assertFalse(r.movePeca(5, 6, pecas));
        assertFalse(r.movePeca(3, 6, pecas));
        assertFalse(r.movePeca(0, 1, pecas));
        assertFalse(r.movePeca(3, 2, pecas));
        assertFalse(r.movePeca(5, 2, pecas));
    }

    @Test
    public void comerPecasPretasTest(){
        Rainha r = new Rainha(4, 4, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(r);

        // Comendo para cima
        pecas.add(new Peao(4, 5, 1));
        assertTrue(r.movePeca(4, 5, pecas));
        assertEquals(1, pecas.size());

        // Comendo para baixo
        pecas.add(new Peao(4, 4, 1));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Comendo pra direita
        pecas.add(new Peao(5, 4, 1));
        assertTrue(r.movePeca(5, 4, pecas));
        assertEquals(1, pecas.size());

        // Comendo para esquerda
        pecas.add(new Peao(4, 4, 1));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come a peca de cor diferente, na diagonal superior direita, e para no lugar dela
        pecas.add(new Peao(5, 5, 1));
        assertTrue(r.movePeca(7, 7, pecas));
        assertEquals(5, r.x);
        assertEquals(5, r.y);
        assertEquals(1, pecas.size());

        // Come a peca na diagonal inferior esquerda
        pecas.add(new Peao(4, 4, 1));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come a peca na diagonal superior esquerda
        pecas.add(new Peao(3, 5, 1));
        assertTrue(r.movePeca(3, 5, pecas));
        assertEquals(1, pecas.size());

        // Come a peca na diagonal inferior direita
        pecas.add(new Peao(4, 4, 1));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come pulando algumas casas
        pecas.add(new Peao(2, 2, 1));
        assertTrue(r.movePeca(2, 2, pecas));
        assertEquals(1, pecas.size());

        // Peca com mesma cor em cima
        pecas.add(new Peao(3, 5, 0));
        assertFalse(r.movePeca(3, 5, pecas));
        assertEquals(2, pecas.size());

        // Peca com a mesma cor embaixo
        pecas.add(new Peao(3, 1, 0));
        assertFalse(r.movePeca(3, 1, pecas));
        assertEquals(3, pecas.size());

        // Peca com a mesma cor na direita
        pecas.add(new Peao(5, 3, 0));
        assertFalse(r.movePeca(5, 4, pecas));
        assertEquals(4, pecas.size());

        // Peca com a mesma cor na esquerda
        pecas.add(new Peao(1, 3, 0));
        assertFalse(r.movePeca(1, 3, pecas));
        assertEquals(5, pecas.size());

        // Peca da mesma cor no canto superior direito
        pecas.add(new Peao(4, 4, 0));
        assertFalse(r.movePeca(4, 4, pecas));
        assertEquals(6, pecas.size());

        // Peca da mesma cor no canto superior esquerdo
        pecas.add(new Peao(0, 4, 0));
        assertFalse(r.movePeca(0, 4, pecas));
        assertEquals(7, pecas.size());

        // Peca da mesma cor no canto inferior direito
        pecas.add(new Peao(4, 0, 0));
        assertFalse(r.movePeca(4, 0, pecas));
        assertEquals(8, pecas.size());

        // Peca da mesma cor no canto inferior esquerdo
        pecas.add(new Peao(0, 0, 0));
        assertFalse(r.movePeca(0, 0, pecas));
        assertEquals(9, pecas.size());
    }

    @Test
    public void comerPecasBrancasTest(){
        Rainha r = new Rainha(4, 4, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(r);

        // Comendo para cima
        pecas.add(new Peao(4, 5, 0));
        assertTrue(r.movePeca(4, 5, pecas));
        assertEquals(1, pecas.size());

        // Comendo para baixo
        pecas.add(new Peao(4, 4, 0));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Comendo pra direita
        pecas.add(new Peao(5, 4, 0));
        assertTrue(r.movePeca(5, 4, pecas));
        assertEquals(1, pecas.size());

        // Comendo para esquerda
        pecas.add(new Peao(4, 4, 0));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come a peca de cor diferente, na diagonal superior direita, e para no lugar dela
        pecas.add(new Peao(5, 5, 0));
        assertTrue(r.movePeca(7, 7, pecas));
        assertEquals(5, r.x);
        assertEquals(5, r.y);
        assertEquals(1, pecas.size());

        // Come a peca na diagonal inferior esquerda
        pecas.add(new Peao(4, 4, 0));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come a peca na diagonal superior esquerda
        pecas.add(new Peao(3, 5, 0));
        assertTrue(r.movePeca(3, 5, pecas));
        assertEquals(1, pecas.size());

        // Come a peca na diagonal inferior direita
        pecas.add(new Peao(4, 4, 0));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come pulando algumas casas
        pecas.add(new Peao(2, 2, 0));
        assertTrue(r.movePeca(2, 2, pecas));
        assertEquals(1, pecas.size());

        // Peca com mesma cor em cima
        pecas.add(new Peao(3, 5, 1));
        assertFalse(r.movePeca(3, 5, pecas));
        assertEquals(2, pecas.size());

        // Peca com a mesma cor embaixo
        pecas.add(new Peao(3, 1, 1));
        assertFalse(r.movePeca(3, 1, pecas));
        assertEquals(3, pecas.size());

        // Peca com a mesma cor na direita
        pecas.add(new Peao(5, 3, 1));
        assertFalse(r.movePeca(5, 4, pecas));
        assertEquals(4, pecas.size());

        // Peca com a mesma cor na esquerda
        pecas.add(new Peao(1, 3, 1));
        assertFalse(r.movePeca(1, 3, pecas));
        assertEquals(5, pecas.size());

        // Peca da mesma cor no canto superior direito
        pecas.add(new Peao(4, 4, 1));
        assertFalse(r.movePeca(4, 4, pecas));
        assertEquals(6, pecas.size());

        // Peca da mesma cor no canto superior esquerdo
        pecas.add(new Peao(0, 4, 1));
        assertFalse(r.movePeca(0, 4, pecas));
        assertEquals(7, pecas.size());

        // Peca da mesma cor no canto inferior direito
        pecas.add(new Peao(4, 0, 1));
        assertFalse(r.movePeca(4, 0, pecas));
        assertEquals(8, pecas.size());

        // Peca da mesma cor no canto inferior esquerdo
        pecas.add(new Peao(0, 0, 1));
        assertFalse(r.movePeca(0, 0, pecas));
        assertEquals(9, pecas.size());

    }
}
