package poo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MancheTest {
    @Test
    public void aceleraAviaoTest(){
        ArrayList<Motor> m = new ArrayList<>();
        m.add(new Motor("helice"));

        Manche man = new Manche(m);

        assertFalse(man.aceleraAviao(-10, 0));
        assertFalse(man.aceleraAviao(101, 0));
        assertTrue(man.aceleraAviao(20, 0));
    }

    @Test
    public void reduzAviaoTest(){
        ArrayList<Motor> m = new ArrayList<>();
        m.add(new Motor("helice"));

        Manche man = new Manche(m);

        assertFalse(man.reduzAviao(20, 0));

        man.aceleraAviao(20, 0);
        assertFalse(man.reduzAviao(-20, 0));
        assertTrue(man.reduzAviao(10, 0));
    }
}
