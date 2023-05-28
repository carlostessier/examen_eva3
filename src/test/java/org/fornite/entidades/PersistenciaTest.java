package org.fornite.entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersistenciaTest {

    private Servidor servidor;

    @BeforeEach
    public void setUp() {
        servidor = new Servidor();

        servidor.encolarJugador( new Jugador("Jugador1", 100, 50,1000));
        servidor.encolarJugador( new Jugador("Jugador2", 100, 50,2000));
        servidor.encolarJugador( new Jugador("Jugador3", 100, 50,5000));
        servidor.encolarJugador( new Jugador("Jugador4", 100, 50, 500));
        servidor.encolarJugador( new Jugador("Jugador5", 100, 50, 10000));
        servidor.encolarJugador( new Jugador("Jugador6", 100,50,  3000));

    }

    @Test
    public void testGuardar() {
        servidor.guardar(servidor.getJugadores());
        Collection<Jugador> coleccionJugadores = servidor.cargar();

        assertEquals(6, coleccionJugadores.size());


    }

    @Test
    public void testCargar() {
        servidor.guardar(servidor.getJugadores());
        Collection<Jugador> coleccionJugadores = servidor.cargar();

        assertEquals(6, coleccionJugadores.size());
        Iterator<Jugador> iterator = coleccionJugadores.iterator();
        //Returns an iterator over the elements
        while (iterator.hasNext()) {
            assertTrue(servidor.getJugadores().contains(iterator.next()));
        }

    }
}
