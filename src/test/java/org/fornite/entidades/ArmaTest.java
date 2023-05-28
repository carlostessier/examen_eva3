package org.fornite.entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmaTest {
    private Arma arma;

    @BeforeEach
    public void setup() {
        arma = new Arma();
    }

    @Test
    public void testConstructorVacio() {
        assertEquals("", arma.getNombre(), "El nombre debe ser una cadena vacía");
        assertEquals(0, arma.getMuniciones(), "Las municiones deben ser 0");
        assertEquals(0, arma.getDaño(), "El daño debe ser 0");
    }

    @Test
    public void testConstructorConValores() {
        String nombre = "Fusil de tirador designado ";
        int municiones = 4;
        int daño = 100;

        arma = new Arma(nombre, municiones, daño);

        assertEquals("fusil de tirador designado", arma.getNombre(), "El nombre debe ser igual al valor establecido");
        assertEquals(municiones, arma.getMuniciones(), "Las municiones deben ser igual al valor establecido");
        assertEquals(daño, arma.getDaño(), "El daño debe ser igual al valor establecido");
    }

    @Test
    public void testDispararConMunicionesSuficientes() {
        int municiones = 10;
        arma.setMuniciones(municiones);

        assertDoesNotThrow(() -> arma.disparar(), "No debe lanzar la excepción SinMunicionesException");
        assertEquals(municiones - 1, arma.getMuniciones(), "Las municiones deben reducirse en 1 después de disparar");
    }

    @Test
    public void testDispararRafagaConMunicionesSuficientes() {
        int municiones = 10;
        int disparos = 8;
        arma.setMuniciones(municiones);

        assertDoesNotThrow(() -> arma.disparar(disparos), "No debe lanzar la excepción SinMunicionesException");
        assertEquals(municiones - disparos, arma.getMuniciones(), "Las municiones deben reducirse en 1 después de disparar");
    }

    @Test
    public void testDispararSinMuniciones() {
        arma.setMuniciones(0);

        assertThrows(SinMunicionesException.class, () -> arma.disparar(), "Debe lanzar la excepción SinMunicionesException");
        assertEquals(0, arma.getMuniciones(), "Las municiones deben seguir siendo 0 después de intentar disparar sin municiones");
    }

    @Test
    public void testDispararRafagaSinMuniciones() {
        int municiones = 10;
        int disparos = 15;
        arma.setMuniciones(municiones);

        assertThrows(SinMunicionesException.class, () -> arma.disparar(disparos), "Debe lanzar la excepción SinMunicionesException");
        assertEquals(0, arma.getMuniciones(), "Las municiones deben seguir siendo 0 después de intentar disparar sin municiones");
    }

    @Test
    public void testRecargar() {
        arma.setMuniciones(0);
        arma.recargar(10);
        assertEquals(10, arma.getMuniciones(), "Las municiones deben ser 10 después de recargar");
    }

    @Test
    public void testRecargarPorEncimaDelTope() {
        arma.setMuniciones(Arma.MAX_MUNICIÓN - 10);
        arma.recargar(100);
        assertEquals(Arma.MAX_MUNICIÓN, arma.getMuniciones(), "Las municiones deben ser 10 después de recargar");
    }

    @Test
    public void testSetMunicionesValidas() {
        int municiones = 500;
        arma.setMuniciones(municiones);

        assertEquals(municiones, arma.getMuniciones(), "Las municiones deben ser igual al valor establecido");
    }

    @Test
    public void testSetMunicionesNegativas() {
        int municiones = -10;
        arma.setMuniciones(municiones);

        assertEquals(0, arma.getMuniciones(), "Las municiones deben ser 0 para valores negativos");
    }

    @Test
    public void testSetMunicionesMayorACapacidadMaxima() {
        int municiones = Arma.MAX_MUNICIÓN + 100;
        arma.setMuniciones(municiones);

        assertEquals(Arma.MAX_MUNICIÓN, arma.getMuniciones(), "Las municiones deben ser 0 para valores mayores a la capacidad máxima (999)");
    }

    @Test
    public void testSetDañoValido() {
        int daño = 50;
        arma.setDaño(daño);

        assertEquals(daño, arma.getDaño(), "El daño debe ser igual al valor establecido");
    }

    @Test
    public void testSetDañoNegativo() {
        int daño = -10;
        arma.setDaño(daño);

        assertEquals(0, arma.getDaño(), "El daño debe ser 0 para valores negativos");

    }

    @Test
    public void testSetDañoMayorACapacidadMaxima() {
        int daño = 200;
        arma.setDaño(daño);

        assertEquals(100, arma.getDaño(), "El daño debe ser 100 para valores mayores a la capacidad máxima (100)");
    }

    @Test
    public void testToString() {
        Arma arma = new Arma("fusil de tirador designado ",4,100);

        String expectedString = "fusil de tirador designado [municiones=4, daño=100]\n";
        assertEquals(expectedString, arma.toString());
    }

    @Test
    public void testIguales() {
        assertEquals(new Arma(), arma);
        Arma armaPrueba = new Arma("fusil de tirador designado ",4,115);
        Arma arma = new Arma("fusil de tirador designado ",4,115);

        assertEquals(armaPrueba, arma);
    }
}