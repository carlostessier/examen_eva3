package org.fornite.entidades;

import java.io.*;
import java.util.*;

public class Servidor implements InterfazServidor, Persistencia {
    private Collection<Jugador> jugadores;
    private Map<Integer, String> rankingJugadores;

    private final static String FICHERO = "jugadores.csv";

    public Servidor() {
        jugadores = new LinkedList<Jugador>();
        rankingJugadores = new TreeMap<Integer, String>(Collections.reverseOrder());
    }

    @Override
    public boolean encolarJugador(Jugador jugador) {
        if (jugadores.contains(jugador)) {
            return false;
        } else {
            ((LinkedList)this.jugadores).addLast(jugador);
            return true;
        }

    }

    @Override
    public Jugador desencolarJugador() {
        Jugador jugador = new Jugador();
        if (!jugadores.isEmpty()) {
            jugador = ((LinkedList<Jugador>)this.jugadores).getFirst();
            ((LinkedList)this.jugadores).removeFirst();
        }
        return jugador;
    }

    @Override
    public void rellenarRanking() {

        for (int i = 0; i < this.jugadores.size(); i++) {
            this.rankingJugadores.put(((LinkedList<Jugador>)this.jugadores).get(i).getPuntos(), ((LinkedList<Jugador>)this.jugadores).get(i).getNombre());
        }
    }

    @Override
    public void vaciarRanking() {
        this.rankingJugadores.clear();
    }

    public LinkedList<Jugador> getJugadores() {
        return (LinkedList<Jugador>)jugadores;
    }

    public Map<Integer, String> getRankingJugadores() {
        return rankingJugadores;
    }

    @Override
    public Map<Integer, String> getTopFive() {
        Map<Integer, String> topFive = new TreeMap<Integer, String>(Collections.reverseOrder());

        int contador = 0;
        for (Map.Entry<Integer, String> entry : this.rankingJugadores.entrySet()) {
            if (contador < 5) {
                topFive.put(entry.getKey(), entry.getValue());
                contador++;
            }
        }

        return topFive;

    }


    @Override
    public void guardar(Collection<Jugador> coleccion) {

        try (BufferedWriter flujoSalida = new BufferedWriter(new FileWriter(FICHERO))) {
                for (Jugador jugador : coleccion) {
                    String entrada = String.format("%s;%d\n", jugador.getNombre(),jugador.getPuntos());
                    flujoSalida.write(entrada);
                }

        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el fichero");
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero "+e.getMessage());
        }
    }


    @Override
    public Collection<Jugador> cargar() {
        Collection<Jugador> coleccion = new LinkedList<Jugador>();
        try (BufferedReader flujoEntrada = new BufferedReader(new FileReader(FICHERO))) {
                String entrada;
                while ( (entrada = flujoEntrada.readLine())!=null) {
                    ;
                    String campos[] = entrada.split(";");
                    Jugador jugador = new Jugador(campos[0], Integer.parseInt(campos[1]));
                    coleccion.add(jugador);
                }

        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el fichero");
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero");
        }

        return coleccion;
    }
}
