package pooTest;

import org.junit.jupiter.api.Test;
import poo.Peao;
import poo.Pecas;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PeaoTest {
    @Test
    public void movePeaoBrancoTest(){
        Peao p = new Peao(1, 1, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(p);

        // O peao nao pode se mover para os lados
        assertFalse(p.movePeca(2, 1, pecas));
        assertFalse(p.movePeca(0, 1, pecas));

        // O peao nao pode se mover livremente nas diagonais
        assertFalse(p.movePeca(2, 2, pecas));
        assertFalse(p.movePeca(0, 2, pecas));
        assertFalse(p.movePeca(0, 0, pecas));
        assertFalse(p.movePeca(2, 0, pecas));

        // O peao so pode andar 1 casa ou 2, caso seja seu primeiro movimento
        assertFalse(p.movePeca(1, 4, pecas));

        // O peao so pode andar 2 casas, no seu primeiro movimento
        assertTrue(p.movePeca(1, 3, pecas));
        assertFalse(p.movePeca(1, 5, pecas));

        // O peao nao pode andar para tras
        assertFalse(p.movePeca(1, 2, pecas));

        Peao p1 = new Peao(1, 1, 0);
        pecas.add(p1);

        // Com outra peca no local onde ele quer se mover, nao consegue executar o comando
        assertFalse(p1.movePeca(1, 3, pecas));
        assertTrue(p1.movePeca(1, 2, pecas));

        Peao p2 = new Peao(2, 1, 0);
        pecas.add(p2);

        // Ja tendo realizado seu primieiro movimento, nao pode mais pular 2 casas
        assertTrue(p2.movePeca(2, 2, pecas));
        assertFalse(p2.movePeca(2, 2, pecas));
    }

    @Test
    public void movePeaoPretoTest(){
        Peao p = new Peao(7, 7, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(p);

        // O peao nao pode se mover para os lados
        assertFalse(p.movePeca(6, 7, pecas));
        assertFalse(p.movePeca(8, 7, pecas));

        // O peao nao pode se mover livremente nas diagonais
        assertFalse(p.movePeca(8, 8, pecas));
        assertFalse(p.movePeca(6, 8, pecas));
        assertFalse(p.movePeca(6, 6, pecas));
        assertFalse(p.movePeca(8, 6, pecas));

        // O peao so pode andar 1 casa ou 2, caso seja seu primeiro movimento
        assertFalse(p.movePeca(7, 4, pecas));

        // O peao so pode andar 2 casas, no seu primeiro movimento
        assertTrue(p.movePeca(7, 5, pecas));
        assertFalse(p.movePeca(7, 3, pecas));

        // O peao nao pode andar para tras
        assertFalse(p.movePeca(7, 6, pecas));

        Peao p1 = new Peao(7, 7, 1);
        pecas.add(p1);

        // Com outra peca no local onde ele quer se mover, nao consegue executar o comando
        assertFalse(p1.movePeca(7, 5, pecas));
        assertTrue(p1.movePeca(7, 6, pecas));

        Peao p2 = new Peao(6, 7, 1);
        pecas.add(p2);

        // Ja tendo realizado seu primieiro movimento, nao pode mais pular 2 casas
        assertTrue(p2.movePeca(6, 6, pecas));
        assertFalse(p2.movePeca(6, 4, pecas));
    }

    @Test
    public void comerPecasPretasTest(){
        Peao p = new Peao(1, 1, 0);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(p);

        // Nao e possivel comer nas diagonais inferiores
        pecas.add(new Peao(0, 0, 1));
        assertFalse(p.movePeca(0, 0, pecas));
        pecas.add(new Peao(2, 0, 1));
        assertFalse(p.movePeca(2, 0, pecas));

        // Nao e possivel comer uma peca da mesma cor
        pecas.add(new Peao(0, 2, 0));
        assertFalse(p.movePeca(0, 2, pecas));

        // Comendo pecas, corretamente
        pecas.add(new Peao(2, 2, 1));
        assertTrue(p.movePeca(2, 2, pecas));
        pecas.add(new Peao(1, 3, 1));
        assertTrue(p.movePeca(1, 3, pecas));
    }

    @Test
    public void comerPecasBrancasTest(){
        Peao p = new Peao(7, 7, 1);
        ArrayList<Pecas> pecas = new ArrayList<>();
        pecas.add(p);

        // Nao e possivel comer nas diagonais inferiores
        pecas.add(new Peao(8, 8, 0));
        assertFalse(p.movePeca(8, 8, pecas));
        pecas.add(new Peao(6, 8, 0));
        assertFalse(p.movePeca(62, 8, pecas));

        // Nao e possivel comer uma peca da mesma cor
        pecas.add(new Peao(8, 6, 1));
        assertFalse(p.movePeca(6, 6, pecas));

        // Comendo pecas, corretamente
        pecas.add(new Peao(6, 6, 0));
        assertTrue(p.movePeca(6, 6, pecas));
        pecas.add(new Peao(7, 5, 0));
        assertTrue(p.movePeca(7, 5, pecas));
    }
}
