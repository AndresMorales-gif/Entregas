update envio
    set remitente = :remitente,
        destinatario = :destinatario,
        zona = :zona,
        envio_plus = :envioPlus,
        peso_carga = :pesoCarga,
        fecha_entrega = :fechaEntrega,
        precio = :precio,
        fecha_creacion = :fechaCreacion
where id = :id