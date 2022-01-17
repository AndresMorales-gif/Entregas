package com.ceiba.zona.consulta;

import com.ceiba.zona.modelo.dto.DtoZona;
import com.ceiba.zona.puerto.dao.DaoZona;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarZonas {

    private final DaoZona daoZona;

    public ManejadorListarZonas(DaoZona daoZona){
        this.daoZona = daoZona;
    }

    public List<DtoZona> ejecutar(){ return this.daoZona.listar(); }
}
