package com.ceiba.envio.puerto.repositorio;

import com.ceiba.envio.modelo.entidad.Envio;

public interface RepositorioEnvio {

    /**
     * Permite crear un envio
     * @param envio
     * @return el id generado
     */
    Long crear(Envio envio);

    /**
     * Permite actualizar un envio
     * @param envio
     */
    void actualizar(Envio envio);

    /**
     * Permite eliminar un envio
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un envio con el id
     * @return si existe o no
     */
    boolean existe(Long id);
}
