select id,remitente, destinatario, zona, envio_plus, peso_carga, fecha_entrega, precio, fecha_creacion
from envio where remitente = :remitente
             and (:fechaInicio IS NULL OR fecha_entrega >= :fechaInicio)
             and (:fechaFinal IS NULL OR fecha_entrega <= :fechaFinal)