package com.ceiba.envio.servicio.base;

import com.ceiba.envio.modelo.dto.DtoEnvio;
import com.ceiba.zona.modelo.dto.DtoZona;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ServicioBasePrueba {

    public static DtoEnvio obtenerDataEnvio() {
        return new DtoEnvio(1L, "123456", "654321", 1L, Boolean.FALSE,
                15L, LocalDateTime.now().plusDays(5L).withHour(8).withMinute(0).withSecond(0).withNano(0), 150.0, LocalDateTime.now());
    }

    public static DtoZona obtenerZona() {
        return new DtoZona(1L, "zonaTest", 5);
    }

    public static Boolean validarFechaEntrega(LocalDateTime fechaEntrega, Integer diasEntrega, Boolean envioPlus) {
        if (Boolean.TRUE.equals(envioPlus)) {
            diasEntrega += 1;
            diasEntrega = diasEntrega / 2;
        }
        LocalDateTime fechaEntregaEsperada = LocalDateTime.now().plusDays(diasEntrega).withHour(8).withMinute(0).withSecond(0).withNano(0);
        if (fechaEntregaEsperada.getDayOfWeek() == DayOfWeek.SATURDAY) {
            fechaEntregaEsperada = fechaEntregaEsperada.plusDays(2);
        }
        if (fechaEntregaEsperada.getDayOfWeek() == DayOfWeek.SUNDAY) {
            fechaEntregaEsperada = fechaEntregaEsperada.plusDays(1);
        }
        System.out.println(fechaEntrega.toString());
        System.out.println(fechaEntregaEsperada.toString());
        return fechaEntrega.equals(fechaEntregaEsperada);
    }
}
