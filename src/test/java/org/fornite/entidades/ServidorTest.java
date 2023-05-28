package org.fornite.entidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;

class ServidorTest {

        private Servidor servidor;
        private Jugador jugadores[];

        @BeforeEach
        public void setUp() {
            servidor = new Servidor();
            jugadores = new Jugador[6];
            jugadores[0] = new Jugador("Jugador1", 100, 50,1000);
            jugadores[1] = new Jugador("Jugador2", 100, 50,2000);
            jugadores[2] = new Jugador("Jugador3", 100, 50,5000);
            jugadores[3] = new Jugador("Jugador4", 100, 50, 500);
            jugadores[4] = new Jugador("Jugador5", 100, 50, 10000);
            jugadores[5] = new Jugador("Jugador6", 100,50,  3000);

        }

        @Test
        public void testEncolarJugador() {
            servidor.encolarJugador(jugadores[1]);
            servidor.encolarJugador(jugadores[4]);
            servidor.encolarJugador(jugadores[2]);
            assertEquals(3, servidor.getJugadores().size());
            assertEquals(jugadores[1], servidor.getJugadores().get(0));
            assertEquals(jugadores[4], servidor.getJugadores().get(1));
            assertEquals(jugadores[2], servidor.getJugadores().get(2));
        }

        @Test
        public void testDesencolarJugador() {
            servidor.encolarJugador(jugadores[0]);
            servidor.encolarJugador(jugadores[5]);
            servidor.encolarJugador(jugadores[3]);
            assertEquals(jugadores[0],servidor.desencolarJugador());
            assertEquals(2, servidor.getJugadores().size());
            assertEquals(jugadores[5], servidor.getJugadores().get(0));
            assertEquals(jugadores[5],servidor.desencolarJugador());
            assertEquals(1, servidor.getJugadores().size());
            assertEquals(jugadores[3], servidor.getJugadores().get(0));
            assertEquals(jugadores[3],servidor.desencolarJugador());
            assertEquals(0, servidor.getJugadores().size());
        }

    @Test
    public void testDesencolarColaVacia() {

        assertDoesNotThrow(() -> {
            assertEquals(new Jugador(),servidor.desencolarJugador());
        });
        assertEquals(0, servidor.getJugadores().size());
    }


    @Test
    public void testEncolarJugadorRepetido() {
        assertTrue(servidor.encolarJugador(jugadores[0]));
        assertFalse(servidor.encolarJugador(jugadores[0]));
        assertEquals(1, servidor.getJugadores().size());
    }

    @Test
    public void testAgregarJugadoresAlRanking() {
        for (int i = 0; i < jugadores.length; i++) {
            servidor.encolarJugador(jugadores[i]);
        }

        servidor.rellenarRanking();
        assertEquals(6, servidor.getJugadores().size());
        List<String> ranking = new ArrayList<>(servidor.getRankingJugadores().values());
        System.out.println(ranking);

        assertEquals(jugadores[4].getNombre(), ranking.get(0));
        assertEquals(jugadores[2].getNombre(), ranking.get(1));
        assertEquals(jugadores[5].getNombre(), ranking.get(2));
        assertEquals(jugadores[1].getNombre(), ranking.get(3));
        assertEquals(jugadores[0].getNombre(), ranking.get(4));
        assertEquals(jugadores[3].getNombre(), ranking.get(5));
    }

    @Test
    public void testVaciarJugadorDelRanking() {
        servidor.encolarJugador(jugadores[0]);
        servidor.encolarJugador(jugadores[1]);
        servidor.rellenarRanking();
        servidor.vaciarRanking();
        assertTrue(servidor.getRankingJugadores().isEmpty());
    }

    @Test
    public void testGetTopFive() {
        for (int i = 0; i < jugadores.length; i++) {
            servidor.encolarJugador(jugadores[i]);
        }
        servidor.rellenarRanking();
        Map<Integer, String> topFive = servidor.getTopFive();
        System.out.println(topFive);
        assertEquals(5, topFive.size());
        assertTrue(topFive.containsValue(jugadores[4].getNombre()));
        assertTrue(topFive.containsValue(jugadores[2].getNombre()));
        assertTrue(topFive.containsValue(jugadores[5].getNombre()));
        assertTrue(topFive.containsValue(jugadores[1].getNombre()));
        assertTrue(topFive.containsValue(jugadores[0].getNombre()));
    }



}