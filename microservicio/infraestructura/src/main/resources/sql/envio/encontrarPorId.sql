select id,remitente, destinatario, zona, envio_plus, peso_carga, fecha_entrega, precio, fecha_creacion
from envio where id = :id