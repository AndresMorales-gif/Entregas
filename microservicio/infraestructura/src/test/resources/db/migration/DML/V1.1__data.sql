insert into usuario(id, nombre, id_documento, fecha_creacion) values(1,'test','123456',now());
insert into usuario(id, nombre, id_documento, fecha_creacion) values(2,'testDestinatario','123457',now());
insert into zona(nombre, dias_entrega) values('testZona',6);
insert into envio(id, remitente, destinatario, zona, envio_plus, peso_carga, fecha_entrega, precio, fecha_creacion)
values(1, 1, 2, 1, 0, 15, dateadd('DAY', 7, now()), 150, now());