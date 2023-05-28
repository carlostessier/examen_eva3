package org.fornite.entidades;
import java.util.Objects;


public class Jugador implements AccionesJugador {
    public static final int MAX_NUMERO_EQUIPO = 5;
    public static final int MAX_SALUD = 100;
    public static final int MAX_ESCUDO = 100;


    private String nombre;
    private int salud;
    private int escudo;
    private int puntos;
    private Elemento[] equipo;

    private int posicionArmaActual;

    public Jugador() {
        this("", 0, 0,0);
    }

    public Jugador(String nombre, int puntos) {
        this(nombre, 100, 100,puntos);
    }
    public Jugador(String nombre, int salud, int escudo, int puntos) {
        setNombre(nombre);
        setSalud(salud);
        setEscudo(escudo);
        this.puntos = puntos;
        this.equipo = new Elemento[MAX_NUMERO_EQUIPO];
        for (int i = 0; i < equipo.length; i++) {
            equipo[i] = new Elemento();
        }

    }

    @Override
    public boolean agregarEquipo(Elemento elemento) {
        boolean añadido = false;
        // Si el elemento es un arma y ya lo tenemos, se suman las municiones
        if (elemento instanceof Arma) {
            for (int i = 0; i < this.equipo.length; i++) {
                if (this.equipo[i].equals(elemento)) {
                    añadido = true;
                    ((Arma) this.equipo[i]).setMuniciones(((Arma) this.equipo[i]).getMuniciones() + ((Arma) elemento).getMuniciones());
                    break;
                }
            }
        }
        if (!añadido){
            // buscamos hueco en el equipo
            for (int i = 0; i < this.equipo.length; i++) {
                if (this.equipo[i].equals(new Elemento())) {
                    this.equipo[i] = elemento;
                    añadido = true;
                    break;
                }
            }
        }
        return añadido;
    }

    @Override
    public Elemento soltarEquipo(int posicion) {
        Elemento elemento = new Elemento();
        if (posicion < 0 || posicion >= this.equipo.length) {
            System.err.println("posicion no válida");
        }
        else {
            elemento = this.equipo[posicion];
            this.equipo[posicion] = new Elemento();
        }
        return elemento;
    }

    @Override
    public void seleccionarSiguienteArma() throws SinArmaException {
        boolean found = false;
        int index = (posicionArmaActual + 1) % equipo.length;

        for (int i = 0; i < equipo.length; i++) {
            Elemento elemento = equipo[index];

            if (elemento instanceof Arma) {
                posicionArmaActual = index;
                found = true;
                break;
            }

            index = (index + 1) % equipo.length;
        }

        if (!found) {
            throw new SinArmaException("No se encontraron armas en el equipo");
        }
    }


    @Override
    public void dispararArma() throws SinMunicionesException, SinArmaException {
       Arma arma;

        if ( !(this.equipo[posicionArmaActual] instanceof Arma)) {
            throw new SinArmaException("No hay un arma seleccionada"+posicionArmaActual);
        }
        arma = (Arma) this.equipo[posicionArmaActual];
        arma.disparar();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre != null){
            this.nombre = nombre.trim().toLowerCase();
        }

    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        if(salud < 0)
            this.salud = 0;
        else if (salud > MAX_SALUD)
            this.salud = MAX_SALUD;
        else
            this.salud = salud;
    }

    public int getEscudo() {
        return escudo;
    }

    public void setEscudo(int escudo) {
        if(escudo < 0)
            this.escudo = 0;
        else if (escudo > MAX_ESCUDO)
            this.escudo = MAX_ESCUDO;
        else
            this.escudo = escudo;
    }

    public Elemento[] getEquipo() {
        return equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        if(puntos < 0) {
            this.puntos = 0;
        }
        else {
            this.puntos = puntos;
        }
    }

    @Override
    public Arma getArmaActual() {
        if (posicionArmaActual < 0 || posicionArmaActual >= this.equipo.length || this.equipo[posicionArmaActual] == null || !(this.equipo[posicionArmaActual] instanceof Arma)) {
            throw new IllegalArgumentException("Índice de arma no válido");
        }
        return (Arma) this.equipo[posicionArmaActual];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jugador jugador = (Jugador) o;

        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    @Override
    public String toString() {
        return
                nombre + " [" +
                "salud=" + salud +
                ", escudo=" + escudo +
                "]\n";
    }
}

