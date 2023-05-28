package org.fornite.entidades;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JugadorTest {
    private Jugador jugador;
    private Arma arma;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador("Test", 100, 50,0);
        arma = new Arma("fusil de tirador designado",6,115);
    }

    @Test
    public void testCrearJugadorVacio() {
        jugador = new Jugador();
        assertEquals("", jugador.getNombre());
        assertEquals(0, jugador.getSalud());
        assertEquals(0, jugador.getEscudo());
        for (int i = 0; i < Jugador.MAX_NUMERO_EQUIPO; i++) {
            assertNotNull(jugador.getEquipo()[i]);
        }
    }

    @Test
    public void testCrearJugador() {
        assertEquals("test", jugador.getNombre());
        assertEquals(100, jugador.getSalud());
        assertEquals(50, jugador.getEscudo());
        for (int i = 0; i < Jugador.MAX_NUMERO_EQUIPO; i++) {
            assertNotNull(jugador.getEquipo()[i]);
        }
    }

    @Test
    public void testCrearJugadorNombrePuntos() {
        jugador = new Jugador("test2", 1000);
        assertEquals("test2", jugador.getNombre());
        assertEquals(100, jugador.getSalud());
        assertEquals(100, jugador.getEscudo());
        assertEquals(1000, jugador.getPuntos());
        for (int i = 0; i < Jugador.MAX_NUMERO_EQUIPO; i++) {
            assertNotNull(jugador.getEquipo()[i]);
        }
    }

    @Test
    public void testSetNombre() {
        jugador.setNombre("TEST");
        assertEquals("test", jugador.getNombre());
        jugador.setNombre("  TEST2  ");
        assertEquals("test2", jugador.getNombre());
        jugador.setNombre(null);
        assertEquals("test2", jugador.getNombre());
    }

    @Test
    public void testSetPuntosValidos() {
        int puntos = Integer.MAX_VALUE;
        jugador.setPuntos(puntos);

        assertEquals(Integer.MAX_VALUE, jugador.getPuntos(), "Los puntos pueden tener el máximo valor de Integer");
        jugador.setPuntos(-100);
        assertEquals(0, jugador.getPuntos(), "Los puntos no pueden ser negativos");

    }

    @Test
    public void testSetSaludValida() {
        int salud = 0;
        jugador.setSalud(salud);
        assertEquals(0, jugador.getSalud());
        salud = Jugador.MAX_SALUD + 1;
        jugador.setSalud(salud);
        assertEquals(Jugador.MAX_SALUD, jugador.getSalud(), "La salud no puede ser mayor que MAX_SALUD");
        salud = -1;
        jugador.setSalud(salud);
        assertEquals(0, jugador.getSalud(), "La salud no puede ser negativa");
    }

    @Test
    public void testSetEscudoValido() {
        int escudo = 0;
        jugador.setEscudo(escudo);
        assertEquals(0, jugador.getEscudo());
        escudo = Jugador.MAX_SALUD + 1;
        jugador.setEscudo(escudo);
        assertEquals(Jugador.MAX_ESCUDO, jugador.getEscudo(), "El escudo no puede ser mayor que MAX_ESCUDO");
        escudo = -1;
        jugador.setEscudo(escudo);
        assertEquals(0, jugador.getEscudo(), "El escudo no puede ser negativo");
    }

    @Test
    public void testAgregarEquipo() {
        Elemento elemento = new Elemento("vendas");
        jugador.agregarEquipo(elemento);
        assertEquals(elemento, jugador.getEquipo()[0]);
    }

    @Test
    public void agregarEquipoSiHayEspacio() {
        Jugador jugador = new Jugador("jugador", 10);
        Elemento elemento1 = new Elemento("Vendas");
        assertTrue(jugador.agregarEquipo(elemento1));
        Elemento elemento2 = new Elemento("Manzana");
        assertTrue(jugador.agregarEquipo(elemento2));
        Elemento elemento3 = new Elemento("Casco");
        assertTrue(jugador.agregarEquipo(elemento3));
        Elemento elemento4 = new Elemento("Armadura");
        assertTrue(jugador.agregarEquipo(elemento4));
        Elemento elemento5 = new Elemento("Platano");
        assertTrue(jugador.agregarEquipo(elemento5));
    }

    @Test
    public void soltarEquipo() {
        Elemento vacio = new Elemento();
        Elemento elemento1 = new Elemento("Vendas");
        jugador.agregarEquipo(elemento1);
        Elemento elemento2 = new Elemento("Manzana");
        jugador.agregarEquipo(elemento2);
        Elemento elemento3 = new Elemento("Casco");
        jugador.agregarEquipo(elemento3);
        Elemento elemento4 = new Elemento("Armadura");
        jugador.agregarEquipo(elemento4);
        Elemento elemento5 = new Elemento("Platano");
        jugador.agregarEquipo(elemento5);
        assertEquals(elemento3,jugador.soltarEquipo(2));
        assertEquals(vacio, jugador.getEquipo()[2]);
        assertTrue(jugador.agregarEquipo(elemento2));
        assertEquals(elemento2, jugador.getEquipo()[2]);

    }

    @Test
    public void soltarEquipoIndiceNoValido() {
        Elemento vacio = new Elemento();
        Elemento elemento1 = new Elemento("Vendas");
        jugador.agregarEquipo(elemento1);
        Elemento elemento2 = new Elemento("Manzana");
        jugador.agregarEquipo(elemento2);
        Elemento elemento3 = new Elemento("Casco");
        jugador.agregarEquipo(elemento3);
        Elemento elemento4 = new Elemento("Armadura");
        jugador.agregarEquipo(elemento4);
        Elemento elemento5 = new Elemento("Platano");
        jugador.agregarEquipo(elemento5);
        jugador.soltarEquipo(-1);
        assertDoesNotThrow(() -> assertEquals(vacio,jugador.soltarEquipo(-1)));
        assertDoesNotThrow(() -> assertEquals(vacio,jugador.soltarEquipo(Jugador.MAX_NUMERO_EQUIPO+1)));
    }

    @Test
    public void soltarEquipoVacio() {
        Elemento vacio = new Elemento();
        for (int i = 0; i < Jugador.MAX_NUMERO_EQUIPO; i++) {
            assertEquals(vacio,jugador.soltarEquipo(i));
        }
    }
    @Test
    public void agregarArmaIncrementaMunicionesSiArmaExistente() {
        Arma arma1 = new Arma("fusil de tirador designado",2,115);
        Arma arma2 = new Arma("fusil de tirador designado",3,115);
        jugador.agregarEquipo(arma1);
        jugador.agregarEquipo(arma2);
        assertEquals(5, ((Arma)jugador.getEquipo()[0]).getMuniciones());
    }

    @Test
    public void noAgregarEquipoSiEquipoLleno() {
        Jugador jugador = new Jugador("jugador", 10);
        for (int i = 0; i < Jugador.MAX_NUMERO_EQUIPO; i++) {
            jugador.agregarEquipo(new Elemento("elemento "+i));
        }
        assertFalse(jugador.agregarEquipo(new Elemento("elemento extra")));
    }

    @Test
    public void testAgregarArmaYaExistente() {
        jugador.agregarEquipo(arma);
        Elemento arma = new Arma("fusil de tirador designado",4,115);

        jugador.agregarEquipo(arma);
        try {
            jugador.seleccionarSiguienteArma();
            assertEquals(10, jugador.getArmaActual().getMuniciones());
        } catch (SinArmaException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSoltarEquipo() {
        jugador.agregarEquipo(arma);
        Elemento elementoSoltado = jugador.soltarEquipo(0);
        assertEquals(arma, elementoSoltado);
    }

    @Test
    public void testDispararConArmaValida() {
        boolean agregada = jugador.agregarEquipo(arma);
        assertTrue(agregada);
        assertDoesNotThrow(() -> {
            jugador.seleccionarSiguienteArma();
            jugador.dispararArma();
        });
    }

    @Test
    public void testDispararConArmaInvalida() {
        Elemento equipo = new Elemento("Vendajes");
        jugador.agregarEquipo(equipo);
        assertThrows(SinArmaException.class, () -> {
            jugador.dispararArma();
        });

    }

    @Test
    public void testDispararConArmaSinMuniciones() {
        Arma arma = new Arma("Fusil de tirador designado",0,115);
        jugador.agregarEquipo(arma);
        System.out.println(jugador.getEquipo()[0].getNombre());
        assertThrows(SinMunicionesException.class, () -> {
            jugador.dispararArma();
        });
    }

    @Test
    public void testSeleccionarSiguienteArma() throws SinArmaException {
        Arma arma1 = new Arma("Fusil de tirador designado",6,115);
        Arma arma2 = new Arma("Subfusil aguijon",30,12);

        Elemento venda = new Elemento("venda");

        Elemento platano = new Elemento("platano");

        jugador.agregarEquipo(arma1);
        jugador.agregarEquipo(venda);
        jugador.agregarEquipo(platano);
        jugador.agregarEquipo(arma2);

        assertDoesNotThrow(() -> {
            jugador.seleccionarSiguienteArma();
        });
        // Verificar que la próxima arma seleccionada es la correcta
        assertSame(arma2, jugador.getArmaActual());

        assertDoesNotThrow(() -> {
            jugador.seleccionarSiguienteArma();
        });

        assertSame(arma1, jugador.getArmaActual());
    }
}
