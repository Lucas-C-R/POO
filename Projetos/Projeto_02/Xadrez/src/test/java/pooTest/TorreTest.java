package pooTest;

import org.junit.jupiter.api.Test;
import poo.Peao;
import poo.Pecas;
import poo.Torre;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TorreTest {
    @Test
    public void moveTorreBrancaTest(){
        Torre t = new Torre(3, 3, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(t);

        // Possiveis movimentacoes da torre
        assertTrue(t.movePeca(7, 3, pecas));
        assertTrue(t.movePeca(3, 3, pecas));
        assertTrue(t.movePeca(3, 7, pecas));
        assertTrue(t.movePeca(3, 3, pecas));

        // Torre nao anda na diagonal
        assertFalse(t.movePeca(4, 4, pecas));
        assertFalse(t.movePeca(2, 4, pecas));
        assertFalse(t.movePeca(2, 2, pecas));
        assertFalse(t.movePeca(4, 2, pecas));

        // Nao consegue se mover, se tiver uma peca da mesma cor no caminho
        pecas.add(new Peao(3, 4, 0));
        assertFalse(t.movePeca(3, 7, pecas));

        // Se houver uma peca de cor diferente no caminho, para no local onde esta peca estava
        pecas.add(new Peao(4, 3, 1));
        assertTrue(t.movePeca(7, 3, pecas));
        assertEquals(4, t.x);
        assertEquals(3, t.y);
    }

    @Test
    public void moveTorrePretaTest(){
        Torre t = new Torre(3, 3, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(t);

        // Possiveis movimentacoes da torre
        assertTrue(t.movePeca(7, 3, pecas));
        assertTrue(t.movePeca(3, 3, pecas));
        assertTrue(t.movePeca(3, 7, pecas));
        assertTrue(t.movePeca(3, 3, pecas));

        // Torre nao anda na diagonal
        assertFalse(t.movePeca(4, 4, pecas));
        assertFalse(t.movePeca(2, 4, pecas));
        assertFalse(t.movePeca(2, 2, pecas));
        assertFalse(t.movePeca(4, 2, pecas));

        // Nao consegue se mover, se tiver uma peca da mesma cor no caminho
        pecas.add(new Peao(3, 4, 1));
        assertFalse(t.movePeca(3, 7, pecas));

        // Se houver uma peca de cor diferente no caminho, para no local onde esta peca estava
        pecas.add(new Peao(4, 3, 0));
        assertTrue(t.movePeca(7, 3, pecas));
        assertEquals(4, t.x);
        assertEquals(3, t.y);
    }

    @Test
    public void comerPecasPretasTest(){
        Torre t = new Torre(3, 3, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(t);

        // Comendo para cima
        pecas.add(new Peao(3, 5, 1));
        assertTrue(t.movePeca(3, 5, pecas));
        assertEquals(1, pecas.size());

        // Comendo para baixo
        pecas.add(new Peao(3, 3, 1));
        assertTrue(t.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Comendo pra direita
        pecas.add(new Peao(5, 3, 1));
        assertTrue(t.movePeca(5, 3, pecas));
        assertEquals(1, pecas.size());

        // Comendo para esquerda
        pecas.add(new Peao(3, 3, 1));
        assertTrue(t.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Peca com mesma cor em cima
        pecas.add(new Peao(3, 5, 0));
        assertFalse(t.movePeca(3, 5, pecas));
        assertEquals(2, pecas.size());

        // Peca com a mesma cor embaixo
        pecas.add(new Peao(3, 1, 0));
        assertFalse(t.movePeca(3, 1, pecas));
        assertEquals(3, pecas.size());

        // Peca com a mesma cor na direita
        pecas.add(new Peao(5, 3, 0));
        assertFalse(t.movePeca(5, 4, pecas));
        assertEquals(4, pecas.size());

        // Peca com a mesma cor na esquerda
        pecas.add(new Peao(1, 3, 0));
        assertFalse(t.movePeca(1, 3, pecas));
        assertEquals(5, pecas.size());
    }

    @Test
    public void comerPecasBrancasTest(){
        Torre t = new Torre(3, 3, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(t);

        // Comendo para cima
        pecas.add(new Peao(3, 5, 0));
        assertTrue(t.movePeca(3, 5, pecas));
        assertEquals(1, pecas.size());

        // Comendo para baixo
        pecas.add(new Peao(3, 3, 0));
        assertTrue(t.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Comendo pra direita
        pecas.add(new Peao(5, 3, 0));
        assertTrue(t.movePeca(5, 3, pecas));
        assertEquals(1, pecas.size());

        // Comendo para esquerda
        pecas.add(new Peao(3, 3, 0));
        assertTrue(t.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Peca com mesma cor em cima
        pecas.add(new Peao(3, 5, 1));
        assertFalse(t.movePeca(3, 5, pecas));
        assertEquals(2, pecas.size());

        // Peca com a mesma cor embaixo
        pecas.add(new Peao(3, 1, 1));
        assertFalse(t.movePeca(3, 1, pecas));
        assertEquals(3, pecas.size());

        // Peca com a mesma cor na direita
        pecas.add(new Peao(5, 3, 1));
        assertFalse(t.movePeca(5, 4, pecas));
        assertEquals(4, pecas.size());

        // Peca com a mesma cor na esquerda
        pecas.add(new Peao(1, 3, 1));
        assertFalse(t.movePeca(1, 3, pecas));
        assertEquals(5, pecas.size());
    }
}
