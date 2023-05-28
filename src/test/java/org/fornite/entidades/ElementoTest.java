package org.fornite.entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoTest {
    private Elemento objeto;

    @BeforeEach
    public void setup() {
        objeto = new Elemento();
    }
    @Test
    public void testSetNombreValido() {
        String nombre = "John Doe";
        objeto.setNombre(nombre);
        assertEquals("john doe", objeto.getNombre(), "El nombre debe estar en minúsculas y sin espacios al principio o al final");
    }

    @Test
    public void testSetNombreNull() {
        String nombre = null;
        objeto.setNombre(nombre);
        assertNotNull(objeto.getNombre(), "El nombre no debe ser nulo");
    }

    @Test
    public void testSetNombreEspacios() {
        String nombre = "   John Doe   ";
        objeto.setNombre(nombre);
        assertEquals("john doe", objeto.getNombre(), "El nombre debe estar en minúsculas y sin espacios al principio o al final");
    }
}