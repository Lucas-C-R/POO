package pooTest;

import org.junit.jupiter.api.Test;
import poo.Peao;
import poo.Pecas;
import poo.Rei;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ReiTest {
    @Test
    public void moveReiBrancoTest(){
        Rei r = new Rei(1, 1, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(r);

        // Move para frente, direita, esquerda e para tras
        assertTrue(r.movePeca(1, 2, pecas));
        assertTrue(r.movePeca(2, 2, pecas));
        assertTrue(r.movePeca(1, 2, pecas));
        assertTrue(r.movePeca(1, 1, pecas));

        // Move para a diagonal superior direita, inferiror esquerda, superior esquerda e inferior direita
        assertTrue(r.movePeca(2, 2, pecas));
        assertTrue(r.movePeca(1, 1, pecas));
        assertTrue(r.movePeca(0, 2, pecas));
        assertTrue(r.movePeca(1, 1, pecas));

        // Se houver uma peca da mesma cor na posicao desejada, nao permite mover
        pecas.add(new Peao(1, 2, 0));
        assertFalse(r.movePeca(1, 2, pecas));


        Rei r1 = new Rei(3, 3, 0);
        pecas.add(r1);

        // Tentando ir pra frente, pra direita, para baixo e para a esquerda, mas o rei so anda uma casa
        assertFalse(r1.movePeca(3, 5, pecas));
        assertFalse(r1.movePeca(5, 3, pecas));
        assertFalse(r1.movePeca(3, 1, pecas));
        assertFalse(r1.movePeca(1, 3, pecas));
    }

    @Test
    public void moveReiPretoTest(){
        Rei r = new Rei(7, 7, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(r);

        // Move para frente, direita, esquerda e para tras
        assertTrue(r.movePeca(7, 8, pecas));
        assertTrue(r.movePeca(8, 8, pecas));
        assertTrue(r.movePeca(7, 8, pecas));
        assertTrue(r.movePeca(7, 7, pecas));

        // Move para a diagonal superior direita, inferiror esquerda, superior esquerda e inferior direita
        assertTrue(r.movePeca(8, 8, pecas));
        assertTrue(r.movePeca(7, 7, pecas));
        assertTrue(r.movePeca(6, 8, pecas));
        assertTrue(r.movePeca(7, 7, pecas));

        // Se houver uma peca da mesma cor na posicao desejada, nao permite mover
        pecas.add(new Peao(7, 8, 1));
        assertFalse(r.movePeca(7, 8, pecas));

        Rei r1 = new Rei(5, 5, 1);
        pecas.add(r1);

        // Tenta se mover pulando casas, mas nao consegue
        assertFalse(r1.movePeca(5, 3, pecas));
        assertFalse(r1.movePeca(3, 5, pecas));
        assertFalse(r1.movePeca(5, 7, pecas));
        assertFalse(r1.movePeca(7, 5, pecas));
    }

    @Test
    public void comerPecasPretasTest(){
        Rei r = new Rei(3, 3, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(r);

        // Come pra frente
        pecas.add(new Peao(3, 4, 1));
        assertTrue(r.movePeca(3, 4, pecas));
        assertEquals(1, pecas.size());

        // Come pra tras
        pecas.add(new Peao(3, 3, 1));
        assertTrue(r.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Come na direita
        pecas.add(new Peao(4, 3, 1));
        assertTrue(r.movePeca(4, 3, pecas));
        assertEquals(1, pecas.size());

        // Come na esquerda
        pecas.add(new Peao(3, 3, 1));
        assertTrue(r.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Come na diagonal superior direita
        pecas.add(new Peao(4, 4, 1));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come na diagonal inferiror esquerda
        pecas.add(new Peao(3, 3, 1));
        assertTrue(r.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Come na diagonal superior esquerda
        pecas.add(new Peao(2, 4, 1));
        assertTrue(r.movePeca(2, 4, pecas));
        assertEquals(1, pecas.size());

        // Come na diagonal inferior direita
        pecas.add(new Peao(3, 3, 1));
        assertTrue(r.movePeca(3, 3, pecas));
        assertEquals(1, pecas.size());

        // Uma peca da mesma cor na frente
        pecas.add(new Peao(3, 4, 0));
        assertFalse(r.movePeca(3, 4, pecas));
        assertEquals(2, pecas.size());

        // Uma peca da mesma cor na diagonal superior direita
        pecas.add(new Peao(4, 4, 0));
        assertFalse(r.movePeca(4, 4, pecas));
        assertEquals(3, pecas.size());

        // Uma peca da mesma cor na direia
        pecas.add(new Peao(4, 3, 0));
        assertFalse(r.movePeca(4, 3, pecas));
        assertEquals(4, pecas.size());

        // Uma peca da mesma cor na diagonal inferior direita
        pecas.add(new Peao(2, 4, 0));
        assertFalse(r.movePeca(2, 4, pecas));
        assertEquals(5, pecas.size());

        // Uma peca da mesma cor atras
        pecas.add(new Peao(3, 2, 0));
        assertFalse(r.movePeca(3, 2, pecas));
        assertEquals(6, pecas.size());

        // Uma peca da mesma cor no canto inferior esquerdo
        pecas.add(new Peao(2, 2, 0));
        assertFalse(r.movePeca(2, 2, pecas));
        assertEquals(7, pecas.size());

        // Uma peca da mesma cor na esquerda
        pecas.add(new Peao(2, 3, 0));
        assertFalse(r.movePeca(2, 3, pecas));
        assertEquals(8, pecas.size());

        // Uma peca da mesma cor no canto superior esquerdo
        pecas.add(new Peao(2, 4, 0));
        assertFalse(r.movePeca(2, 4, pecas));
        assertEquals(9, pecas.size());
    }

    @Test
    public void comerPecasBrancasTest(){
        // Todas as perspectivas sao baseadas na visao da peca

        Rei r = new Rei(5, 5, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(r);

        // Come uma peca na frente
        pecas.add(new Peao(5, 4, 0));
        assertTrue(r.movePeca(5, 4, pecas));
        assertEquals(1, pecas.size());

        // Come uma peca atras
        pecas.add(new Peao(5, 5, 0));
        assertTrue(r.movePeca(5, 5, pecas));
        assertEquals(1, pecas.size());

        // Come uma peca na esquerda
        pecas.add(new Peao(6, 5, 0));
        assertTrue(r.movePeca(6, 5, pecas));
        assertEquals(1, pecas.size());

        // Come uma peca na direita
        pecas.add(new Peao(5, 5, 0));
        assertTrue(r.movePeca(5, 5, pecas));
        assertEquals(1, pecas.size());

        // Come uma peca no canto superior esquerdo
        pecas.add(new Peao(4, 6, 0));
        assertTrue(r.movePeca(4, 6, pecas));
        assertEquals(1, pecas.size());

        // Come uma peca no canto inferior direito
        pecas.add(new Peao(5, 5, 0));
        assertTrue(r.movePeca(5, 5, pecas));
        assertEquals(1, pecas.size());

        // Come uma peca no canto superior direito
        pecas.add(new Peao(4, 4, 0));
        assertTrue(r.movePeca(4, 4, pecas));
        assertEquals(1, pecas.size());

        // Come uma peca no canto inferior esquerdo
        pecas.add(new Peao(5, 5, 0));
        assertTrue(r.movePeca(5, 5, pecas));
        assertEquals(1, pecas.size());

        // Uma peca da mesma cor na frente
        pecas.add(new Peao(5, 4, 1));
        assertFalse(r.movePeca(5, 4, pecas));
        assertEquals(2, pecas.size());

        // Uma peca da mesma cor na diagonal superior direita
        pecas.add(new Peao(4, 4, 1));
        assertFalse(r.movePeca(4, 4, pecas));
        assertEquals(3, pecas.size());

        // Uma peca da mesma cor na direia
        pecas.add(new Peao(4, 5, 1));
        assertFalse(r.movePeca(4, 5, pecas));
        assertEquals(4, pecas.size());

        // Uma peca da mesma cor na diagonal inferior direita
        pecas.add(new Peao(4, 6, 1));
        assertFalse(r.movePeca(4, 6, pecas));
        assertEquals(5, pecas.size());

        // Uma peca da mesma cor atras
        pecas.add(new Peao(5, 6, 1));
        assertFalse(r.movePeca(5, 6, pecas));
        assertEquals(6, pecas.size());

        // Uma peca da mesma cor no canto inferior esquerdo
        pecas.add(new Peao(6, 6, 1));
        assertFalse(r.movePeca(6, 6, pecas));
        assertEquals(7, pecas.size());

        // Uma peca da mesma cor na esquerda
        pecas.add(new Peao(6, 5, 1));
        assertFalse(r.movePeca(6, 5, pecas));
        assertEquals(8, pecas.size());

        // Uma peca da mesma cor no canto superior esquerdo
        pecas.add(new Peao(4, 6, 1));
        assertFalse(r.movePeca(4, 6, pecas));
        assertEquals(9, pecas.size());
    }
}
