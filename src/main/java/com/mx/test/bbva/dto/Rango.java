package com.mx.test.bbva.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rango<T> implements Serializable {
    private T inicio;
    private T fin;

    public Rango() {
    }

    public Rango(T inicio, T fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public T getInicio() {
        return inicio;
    }

    public void setInicio(T inicio) {
        this.inicio = inicio;
    }

    public T getFin() {
        return fin;
    }

    public void setFin(T fin) {
        this.fin = fin;
    }

}
