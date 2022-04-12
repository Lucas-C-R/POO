package poo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoboTest {
    @Test
    public void coordenadaAtualTest(){
        Robo rob = new Robo(400, 5, 5, "L", 5, 10);

        // Coordenada fornecida inicialmente
        assertEquals("5,5", rob.coordenadaAtual());

        // Robo apenas girou, sem se deslocar
        rob.giraRobo("E");
        assertEquals("5,5", rob.coordenadaAtual());

        // Robo se deslocou
        rob.deslocaRobo();
        assertEquals("5,15", rob.coordenadaAtual());
    }

    @Test
    public void coordenadaAnteriorTest(){
        Robo rob = new Robo(400, 5, 5, "L", 5, 10);

        // Coordenada fornecida inicialmente
        assertEquals("5,5", rob.coordenadaAnterior());

        // Robo se deslocou para (15,5)
        rob.deslocaRobo();
        assertEquals("5,5", rob.coordenadaAnterior());

        // Robo apenas girou, sem se deslocar
        rob.giraRobo("E");
        assertEquals("15,5", rob.coordenadaAnterior());

        // Robo se deslocou para (15,15)
        rob.deslocaRobo();
        assertEquals("15,5", rob.coordenadaAnterior());
    }

    @Test
    public void ConstrutorTest(){
        Robo constru1 = new Robo(400, 5, 15, "L", 80, 10);

        assertEquals(400, constru1.getArea());
        assertEquals(5, constru1.getCoordX());
        assertEquals(15, constru1.getCoordY());
        assertEquals("L", constru1.getFrente());
        assertEquals(80, constru1.getQuantMov());
        assertEquals(10, constru1.getUnidadeCamin());

        Robo constru2 = new Robo(100, 110, 30, "S", 110, 30);

        assertEquals(100, constru2.getArea());
        assertNotEquals(110, constru2.getCoordX());
        assertNotEquals(30,constru2.getCoordY());
        assertEquals("N", constru2.getFrente());
        assertEquals(100, constru2.getQuantMov());
        assertEquals(30, constru2.getUnidadeCamin());
    }

    @Test
    public void planoExploracaoTest(){
        Robo rob = new Robo(400, 5, 5, "N", 5, 10);

        // Adicionando a primeira instrucao
        rob.planoExploracao("M");
        assertEquals("M", rob.getPlano());

        // Adicionando instrucoes subsequentes
        rob.planoExploracao("E");
        assertEquals("ME", rob.getPlano());
        rob.planoExploracao("D");
        assertEquals("MED", rob.getPlano());

        // Teste com mais de uma instrucao
        rob.planoExploracao("DD");
        assertEquals("MED", rob.getPlano());

        // Teste com uma instrucao incorreta
        rob.planoExploracao("X");
        assertEquals("MED", rob.getPlano());

        // Teste com uma instrucao vazia
        rob.planoExploracao("");
        assertEquals("MED", rob.getPlano());
    }

    @Test
    public void comandosNaoRealizadosTest(){
        Robo rob = new Robo(400, 5, 5, "L", 5, 10);

        // Adicionando comandos
        rob.planoExploracao("E");
        rob.planoExploracao("D");
        rob.planoExploracao("M");

        // Nenhum comando foi executado ainda
        assertEquals("EDM", rob.comandosNaoRealizados());

        // So um comando executado
        rob.executaPlano();
        assertEquals("DM", rob.comandosNaoRealizados());

        // Multiplos comandos ja foram executados
        rob.executaPlano();
        assertEquals("M", rob.comandosNaoRealizados());

        // Todos os comandos foram executados
        rob.executaPlano();
        assertEquals("", rob.comandosNaoRealizados());
    }

    @Test
    public void giraRoboTest(){
        Robo rob = new Robo(400, 5, 15, "L", 80, 10);

        // Fazendo o robo virar para a direita, nos 4 pontos cardeais
        assertEquals("S", rob.giraRobo("D"));
        assertEquals("O", rob.giraRobo("D"));
        assertEquals("N", rob.giraRobo("D"));
        assertEquals("L", rob.giraRobo("D"));

        // Fazendo o robo virar para a esquerda, nos 4 pontos cardeais
        assertEquals("N", rob.giraRobo("E"));
        assertEquals("O", rob.giraRobo("E"));
        assertEquals("S", rob.giraRobo("E"));
        assertEquals("L", rob.giraRobo("E"));

        // Colocando qualquer coisa que nao seja uma direcao valida
        assertEquals("L", rob.giraRobo("Algo diferente de uma direcao"));

        // Forca o robo a continuar girando, ate que sua bateria acabe
        for(int i = rob.getQuantMov(); i > 0; i--){
            rob.giraRobo("E");
        }

        String frente = rob.getFrente();

        // Mostra que o robo nao ira mais executar nenhuma acao, se estiver sem bateria
        assertEquals(frente, rob.giraRobo("D"));
        assertEquals(frente, rob.giraRobo("D"));
    }

    @Test
    public void deslocaRoboTest(){
        Robo rob = new Robo(400, 5, 5, "O", 5, 10);

        // Robo tentando ir para o Oeste
        assertFalse(rob.deslocaRobo());

        // Robo tentando ir para o Sul
        rob.giraRobo("E");
        assertFalse(rob.deslocaRobo());

        // Robo indo para o Leste
        rob.giraRobo("E");
        assertTrue(rob.deslocaRobo());

        // Robo indo para o Norte
        rob.giraRobo("E");
        assertTrue(rob.deslocaRobo());

        // Robo nao deve mais andar, pois acabou a bateria
        assertFalse(rob.deslocaRobo());
    }

    @Test
    public void executaPlanoTest(){
        Robo rob = new Robo(400, 5, 5, "N", 5, 10);

        // Adicionando comandos
        rob.planoExploracao("E");
        rob.planoExploracao("D");
        rob.planoExploracao("M");

        // Testa se o comando 'E' pode ser executado
        assertTrue(rob.executaPlano());

        // Testa se o comando 'D' pode ser executado
        assertTrue(rob.executaPlano());

        // Testa se o comando 'M' pode ser executado
        assertTrue(rob.executaPlano());

        // Testa se ele retorna um false, ao nao ter mais comandos
        assertFalse(rob.executaPlano());
    }
}
