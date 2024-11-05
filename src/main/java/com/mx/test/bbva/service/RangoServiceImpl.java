package com.mx.test.bbva.service;

import com.mx.test.bbva.dto.Rango;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RangoServiceImpl<T extends Comparable<T>> {

    /**
     * Método para eliminar el solapamiento
     * @param rangos lista de rangos a ordenar
     * @return lista ordenada sin solapamiento
     */
    public List<List<Rango<T>>> eliminarSolapamiento(final List<Rango<T>> rangos) {
        Collections.sort(rangos, Comparator.comparing(Rango::getInicio));

        List<List<Rango<T>>> rangosFusionados = new ArrayList<>();

        List<Rango<T>> listaTemporal = new ArrayList<>();

        for (Rango<T> current : rangos) {
            if (listaTemporal.isEmpty() || !esSolapado(listaTemporal.get(listaTemporal.size() - 1), current)) {
                listaTemporal.add(current);
            } else {
                Rango<T> ultimoRango = listaTemporal.get(listaTemporal.size() - 1);
                Rango<T> fusionado = new Rango<>(ultimoRango.getInicio(), current.getFin());
                listaTemporal.set(listaTemporal.size() - 1, fusionado);
            }
        }

        for (Rango<T> rango : listaTemporal) {
            List<Rango<T>> listaLimpia = new ArrayList<>();
            listaLimpia.add(rango);
            rangosFusionados.add(listaLimpia);
        }

        return rangosFusionados;
    }

    /**
     * Método para validar si los rangos se solapan
     * @param rango1 rango inicial
     * @param rango2 rango final
     * @return true si se solapa, false en cas contrario
     */
    private boolean esSolapado(Rango<T> rango1, Rango<T> rango2) {
        return rango1.getFin().compareTo(rango2.getInicio()) >= 0 &&
                rango1.getInicio().compareTo(rango2.getFin()) <= 0;
    }
}