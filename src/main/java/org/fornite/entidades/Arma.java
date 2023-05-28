package org.fornite.entidades;

public class Arma extends Elemento implements AccionesArma{
    public static final int MAX_DAÑO = 100;
    public static final int MAX_MUNICIÓN = 999;
    private int municiones;
    private int daño;

    public Arma(){
        this("", 0, 0);
    }

    public Arma(String nombre, int municiones, int daño) {
        super(nombre);
        setDaño(daño);
        setMuniciones(municiones);
    }

    // getters y setters para las propiedades

    @Override
    public void disparar() throws SinMunicionesException {
        if (this.municiones > 0) {
            this.municiones--;
        } else {
            throw new SinMunicionesException("El arma " + getNombre() + " no tiene municiones");
        }
    }

    /**
     * Dispara el arma y reduce en disparos la munición.
     * Si el arma se queda sin munición, lanza una excepción SinMunicionesException
     *
     * @param disparos
     * @throws SinMunicionesException
     */
    @Override
    public void disparar(int disparos) throws SinMunicionesException {
        if (this.municiones < disparos) {
            this.municiones = 0;
            throw new SinMunicionesException("El arma " + getNombre() + " no tiene municiones");
        } else {
            this.municiones -= disparos;
        }
    }

    @Override
    public void recargar(int municiones) {
        setMuniciones(getMuniciones() + municiones);
    }

    public int getMuniciones() {
        return municiones;
    }

    public void setMuniciones(int municiones) {
        if(municiones < 0)
            this.municiones = 0;
        else if (municiones > MAX_MUNICIÓN)
            this.municiones = MAX_MUNICIÓN;
        else
            this.municiones = municiones;


    }

    public int getDaño() {
        return daño;
    }

    public void setDaño(int daño) {
        if(daño < 0)
            this.daño = 0;
        else if (daño > MAX_DAÑO)
            this.daño = MAX_DAÑO;
        else
            this.daño = daño;
    }

    @Override
    public String toString() {
        return getNombre()+" [" +
                "municiones=" + municiones +
                ", daño=" + daño +
                "]\n";
    }
}

