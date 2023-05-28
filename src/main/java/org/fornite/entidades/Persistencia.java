package org.fornite.entidades;

import java.util.Collection;

public interface Persistencia {

    /**
     * Guarda la colección de jugadores en un fichero
     * @param coleccion
     */
    public void guardar(Collection<Jugador> coleccion);

    /**
     *
     * @return
     */
    public Collection<Jugador>  cargar();
}
