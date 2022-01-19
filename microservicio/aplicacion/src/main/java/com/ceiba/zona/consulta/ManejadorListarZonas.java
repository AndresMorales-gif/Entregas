package com.ceiba.zona.consulta;

import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZonaListar;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarZonas {

    private final DaoZonaListar daoZonaListar;

    public ManejadorListarZonas(DaoZonaListar daoZonaListar){
        this.daoZonaListar = daoZonaListar;
    }

    public List<DtoZona> ejecutar(){ return this.daoZonaListar.listar(); }
}
