create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 id_documento varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table zona (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 dias_entrega int(5) not null,
 primary key (id)
);

insert into zona (nombre, dias_entrega) values ('Fontibon', 5);
insert into zona (nombre, dias_entrega) values ('Usaquen', 8);
insert into zona (nombre, dias_entrega) values ('Soacha', 15);
insert into zona (nombre, dias_entrega) values ('La estrella', 11);