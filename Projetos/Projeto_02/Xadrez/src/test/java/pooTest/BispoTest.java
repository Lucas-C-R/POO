package pooTest;

import org.junit.jupiter.api.Test;
import poo.Bispo;
import poo.Peao;
import poo.Pecas;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BispoTest {
    @Test
    public void moverBispoBrancoTest(){
        Bispo b = new Bispo(4, 4, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(b);

        // Possiveis movimentos do bispo
        assertTrue(b.movePeca(7, 7, pecas));
        assertTrue(b.movePeca(4, 4, pecas));
        assertTrue(b.movePeca(7, 1, pecas));
        assertTrue(b.movePeca(4, 4, pecas));

        // O bispo so se movimenta nas diagonais
        assertFalse(b.movePeca(4, 5, pecas));
        assertFalse(b.movePeca(5, 4, pecas));
        assertFalse(b.movePeca(4, 3, pecas));
        assertFalse(b.movePeca(3, 4, pecas));
        assertFalse(b.movePeca(5, 6, pecas));
        assertFalse(b.movePeca(5, 2, pecas));
        assertFalse(b.movePeca(3, 6, pecas));
        assertFalse(b.movePeca(3, 2, pecas));
    }

    @Test
    public void moverBispoPretoTest(){
        Bispo b = new Bispo(4, 4, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(b);

        // Possiveis movimentos do bispo
        assertTrue(b.movePeca(7, 7, pecas));
        assertTrue(b.movePeca(4, 4, pecas));
        assertTrue(b.movePeca(7, 1, pecas));
        assertTrue(b.movePeca(4, 4, pecas));

        // O bispo so se movimenta nas diagonais
        assertFalse(b.movePeca(4, 5, pecas));
        assertFalse(b.movePeca(5, 4, pecas));
        assertFalse(b.movePeca(4, 3, pecas));
        assertFalse(b.movePeca(3, 4, pecas));
        assertFalse(b.movePeca(5, 6, pecas));
        assertFalse(b.movePeca(5, 2, pecas));
        assertFalse(b.movePeca(3, 6, pecas));
        assertFalse(b.movePeca(3, 2, pecas));
    }

    @Test
    public void comerPecasPretasTest(){
        Bispo b = new Bispo(4, 4, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(b);

        // Come a peca de cor diferente, na diagonal superior direita, e para no lugar dela
        pecas.add(new Peao(5, 5, 1));
        assertTrue(b.movePeca(7, 7, pecas));
        assertEquals(5, b.x);
        assertEquals(5, b.y);
        assertEquals(1, pecas.size());

        // Come a peca na diagonal inferior esquerda
        pecas.add(new Peao(4, 4, 1));
        assertTrue(b.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come a peca na diagonal superior esquerda
        pecas.add(new Peao(3, 5, 1));
        assertTrue(b.movePeca(3, 5, pecas));
        assertEquals(1, pecas.size());

        // Come a peca na diagonal inferior direita
        pecas.add(new Peao(4, 4, 1));
        assertTrue(b.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come pulando algumas casas
        pecas.add(new Peao(2, 2, 1));
        assertTrue(b.movePeca(2, 2, pecas));
        assertEquals(1, pecas.size());

        // Peca da mesma cor no canto superior direito
        pecas.add(new Peao(4, 4, 0));
        assertFalse(b.movePeca(4, 4, pecas));
        assertEquals(2, pecas.size());

        // Peca da mesma cor no canto superior esquerdo
        pecas.add(new Peao(0, 4, 0));
        assertFalse(b.movePeca(0, 4, pecas));
        assertEquals(3, pecas.size());

        // Peca da mesma cor no canto inferior direito
        pecas.add(new Peao(4, 0, 0));
        assertFalse(b.movePeca(4, 0, pecas));
        assertEquals(4, pecas.size());

        // Peca da mesma cor no canto inferior esquerdo
        pecas.add(new Peao(0, 0, 0));
        assertFalse(b.movePeca(0, 0, pecas));
        assertEquals(5, pecas.size());

        // Maximo que consegue se mover nesta direcao
        assertTrue(b.movePeca(3, 3, pecas));
    }

    @Test
    public void comerPecasBrancasTest(){
        Bispo b = new Bispo(4, 4, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(b);

        // Come a peca de cor diferente, na diagonal superior direita, e para no lugar dela
        pecas.add(new Peao(5, 5, 0));
        assertTrue(b.movePeca(7, 7, pecas));
        assertEquals(5, b.x);
        assertEquals(5, b.y);
        assertEquals(1, pecas.size());

        // Come a peca na diagonal inferior esquerda
        pecas.add(new Peao(4, 4, 0));
        assertTrue(b.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come a peca na diagonal superior esquerda
        pecas.add(new Peao(3, 5, 0));
        assertTrue(b.movePeca(3, 5, pecas));
        assertEquals(1, pecas.size());

        // Come a peca na diagonal inferior direita
        pecas.add(new Peao(4, 4, 0));
        assertTrue(b.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come pulando algumas casas
        pecas.add(new Peao(2, 2, 0));
        assertTrue(b.movePeca(2, 2, pecas));
        assertEquals(1, pecas.size());

        // Peca da mesma cor no canto superior direito
        pecas.add(new Peao(4, 4, 1));
        assertFalse(b.movePeca(4, 4, pecas));
        assertEquals(2, pecas.size());

        // Peca da mesma cor no canto superior esquerdo
        pecas.add(new Peao(0, 4, 1));
        assertFalse(b.movePeca(0, 4, pecas));
        assertEquals(3, pecas.size());

        // Peca da mesma cor no canto inferior direito
        pecas.add(new Peao(4, 0, 1));
        assertFalse(b.movePeca(4, 0, pecas));
        assertEquals(4, pecas.size());

        // Peca da mesma cor no canto inferior esquerdo
        pecas.add(new Peao(0, 0, 1));
        assertFalse(b.movePeca(0, 0, pecas));
        assertEquals(5, pecas.size());

        // Maximo que consegue se mover nesta direcao
        assertTrue(b.movePeca(3, 3, pecas));
    }
}
