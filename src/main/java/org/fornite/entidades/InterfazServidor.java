package org.fornite.entidades;

import java.util.Map;

public interface InterfazServidor {

    /**
     * Agrega un elemento al equipo al final de la cola
     * No permite añadir jugadores repetidos
     * @param jugador
     * @return
     */
    public boolean encolarJugador(Jugador jugador);

    /**
     * Saca al jugador que está primero en la cola
     * Si la cola está vacía, devuelve un Jugador vacio
     * @return
     */
    public Jugador desencolarJugador();

    /**
     * Agrega todos los jugadores de la cola al ranking
     *
     */
    public void rellenarRanking();

    /**
     * Vacía el ranking de jugadores
     */
    public void vaciarRanking();

    /**
     * Devuelve el diccionario de los 5 mejores jugadores del ranking
     * @return
     */
    public Map<Integer, String> getTopFive();


    }
