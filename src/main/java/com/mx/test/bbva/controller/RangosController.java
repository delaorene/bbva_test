package com.mx.test.bbva.controller;

import com.mx.test.bbva.dto.Rango;
import com.mx.test.bbva.service.RangoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RangosController<T> {

    @Autowired
    private RangoServiceImpl rangoService;

    @PostMapping(value = "/rangos")
    public List<List<T>> mergeRanges(@RequestBody List<List<T>> rangos) {
        List<Rango<T>> rangosSolapados = new ArrayList<>();

        for (List<T> rangoData : rangos) {
            if (rangoData.size() == 2) {
                Rango<T> rango = new Rango<>(rangoData.get(0), rangoData.get(1));
                rangosSolapados.add(rango);
            } else {
                throw new IllegalArgumentException("Cada rango debe tener exactamente dos elementos.");
            }
        }

        List<List<Rango<T>>> rangosLimpios = rangoService.eliminarSolapamiento(rangosSolapados);

        List<List<T>> rangosFusionados = new ArrayList<>();
        for (List<Rango<T>> rangosFusionado : rangosLimpios) {
            List<T> rangosData = new ArrayList<>();
            for (Rango<T> data : rangosFusionado) {
                rangosData.add(data.getInicio());
                rangosData.add(data.getFin());
            }
            rangosFusionados.add(rangosData);
        }

        return rangosFusionados;
    }
}
