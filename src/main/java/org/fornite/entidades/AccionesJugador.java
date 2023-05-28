package org.fornite.entidades;

public interface AccionesJugador {

    /**
     * Agrega un elemento al equipo en una posición libre (elemento vacío)
     * Si el elemento es un arma y ya lo tenemos, se suman las municiones
     *
     * @param elemento
     * @return
     */
    public boolean agregarEquipo(Elemento elemento);

    /**
     * Suelta un elemento del equipo, en su posición deja un Elemento vacío
     * Si la posición no es válida, muestra un mensaje de error.
     * No lanza una excepción
     * @param posicion
     * @return
     */
    public Elemento soltarEquipo(int posicion);

    /**
     * Selecciona la siguiente arma del equipo
     * Si no hay armas en el equipo, lanza una excepción
     * @throws SinArmaException
     */
    public void seleccionarSiguienteArma() throws SinArmaException;

    /**
     * Dispara el arma actual
     * Si no hay municiones lanza una excepción
     * Si no hay arma válidad seleccionada lanza una excepción
     *
     * @throws SinMunicionesException
     * @throws SinArmaException
     */
    public void dispararArma() throws SinMunicionesException, SinArmaException;


    /**
     * Devuelve el arma que tiene seleccionada el Jugador
     * @return
     */
    public Arma getArmaActual();
    }
