package org.fornite.entidades;

public interface AccionesArma {

    /**
     * Dispara el arma y reduce en uno la munición.
     * Si el arma no tiene munición, lanza una excepción SinMunicionesException
     * @throws SinMunicionesException
     */
    public void disparar() throws SinMunicionesException;

    /**
     * Dispara el arma y reduce en uno la munición.
     * Si el arma no tiene munición, lanza una excepción SinMunicionesException
     * @throws SinMunicionesException
     */
    public void disparar(int disparos) throws SinMunicionesException;

    /**
     * Recarga el arma con las municiones indicadas
     * @param municiones
     */
    public void recargar(int municiones);
}
