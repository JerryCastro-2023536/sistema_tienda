drop database if exists SistemaVenta_IN5CM;
create database SistemaVenta_IN5CM;
use SistemaVenta_IN5CM;

create table Clientes(
	dpi_cliente int primary key not null,
    nombre_cliente varchar(50) not null,
    apellido_cliente varchar(50) not null,
    direccion varchar(100) not null,
    estado int not null
);

create table Usuarios(
	codigo_usuario int primary key not null auto_increment,
    username varchar(45) not null,
    password varchar(45) not null,
    email varchar(60) not null,
    foto mediumblob,
    rol varchar(45) not null,
    estado int not null
);

create table Productos(
	codigo_producto int primary key not null,
    nombre_producto varchar(60) not null,
    precio decimal(10,2) not null,
    stock int not null,
    estado int not null
);

create table Ventas(
	codigo_venta int primary key not null,
    fecha_venta date not null,
    total decimal(10,2) not null,
    estado int not null,
    clientes_dpi_cliente int not null,
    usuarios_codigo_usuario int not null,
	foreign key (clientes_dpi_cliente) references Clientes (dpi_cliente) on delete cascade,
    foreign key (usuarios_codigo_usuario) references Usuarios(codigo_usuario) on delete cascade
);

create table Detalle_Venta(
	codigo_detalle_venta int primary key not null,
    cantidad int not null,
    precio_unitario decimal(10,2) not null,
    subtotal decimal(10,2) not null,
    productos_codigo_producto int not null ,
    ventas_codigo_venta int not null,
    foreign key (productos_codigo_producto) references Productos(codigo_producto) on delete cascade,
    foreign key (ventas_codigo_venta) references Ventas(codigo_venta) on delete cascade
);

-- PROCEDIMIENTOS ALMACANADOS 

-- CLIENTES
delimiter $$
create procedure sp_agregar_cliente(
    in p_dpi int,
    in p_nombre varchar(50),
    in p_apellido varchar(50),
    in p_direccion varchar(100),
    in p_estado int
)
begin
    insert into Clientes(dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado)
    values(p_dpi, p_nombre, p_apellido, p_direccion, p_estado);
end $$
delimiter ;

delimiter $$
create procedure sp_listar_clientes()
begin
    select * from Clientes;
end $$
delimiter ;

delimiter $$
create procedure sp_actualizar_cliente(
    in p_dpi int,
    in p_nombre varchar(50),
    in p_apellido varchar(50),
    in p_direccion varchar(100),
    in p_estado int
)
begin
    update Clientes c
    set c.nombre_cliente = p_nombre,
        c.apellido_cliente = p_apellido,
        c.direccion = p_direccion,
        c.estado = p_estado
    where c.dpi_cliente = p_dpi;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_cliente(in p_dpi int)
begin
    delete from Clientes where dpi_cliente = p_dpi;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_cliente(in p_dpi int)
begin 
	select * from Clientes where dpi_cliente = p_dpi;
end $$
delimiter ;

-- USUARIOS
delimiter $$
create procedure sp_agregar_usuario(
    in p_codigo int,
    in p_username varchar(45),
    in p_password varchar(45),
    in p_email varchar(60),
    in p_rol varchar(45),
    in p_estado int
)
begin
    insert into Usuarios(codigo_usuario, username, password, email, rol, estado)
    values(p_codigo, p_username, p_password, p_email, p_rol, p_estado);
end $$
delimiter ;

delimiter $$
create procedure sp_listar_usuarios()
begin
    select * from Usuarios;
end $$
delimiter ;

delimiter $$
create procedure sp_actualizar_usuario(
    in p_codigo int,
    in p_username varchar(45),
    in p_password varchar(45),
    in p_email varchar(60),
    in p_rol varchar(45),
    in p_estado int
)
begin
    update Usuarios u
    set u.username = p_username,
        u.password = p_password,
        u.email = p_email,
        u.rol = p_rol,
        u.estado = p_estado
    where u.codigo_usuario = p_codigo;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_usuario(in p_codigo int)
begin
    delete from Usuarios where codigo_usuario = p_codigo;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_usuario(in p_codigo int)
begin 
	select * from Usuarios where codigo_usuario = p_codigo;
end $$
delimiter ;

-- PRODUCTOS
delimiter $$
create procedure sp_agregar_producto(
    in p_codigo int,
    in p_nombre varchar(60),
    in p_precio decimal(10,2),
    in p_stock int,
    in p_estado int
)
begin
    insert into Productos(codigo_producto, nombre_producto, precio, stock, estado)
    values(p_codigo, p_nombre, p_precio, p_stock, p_estado);
end $$
delimiter ;

delimiter $$
create procedure sp_listar_productos()
begin
    select * from Productos;
end $$
delimiter ;

delimiter $$
create procedure sp_actualizar_producto(
    in p_codigo int,
    in p_nombre varchar(60),
    in p_precio decimal(10,2),
    in p_stock int,
    in p_estado int
)
begin
    update Productos p
    set p.nombre_producto = p_nombre,
        p.precio = p_precio,
        p.stock = p_stock,
        p.estado = p_estado
    where p.codigo_producto = p_codigo;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_producto(in p_codigo int)
begin
    delete from Productos where codigo_producto = p_codigo;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_producto(in p_codigo int)
begin 
	select * from Productos where codigo_producto = p_codigo;
end $$
delimiter ;

-- VENTAS 
delimiter $$
create procedure sp_agregar_venta(
    in p_codigo int,
    in p_fecha date,
    in p_total decimal(10,2),
    in p_estado int,
    in p_cliente int,
    in p_usuario int
)
begin
    insert into Ventas(codigo_venta, fecha_venta, total, estado, clientes_dpi_cliente, usuarios_codigo_usuario)
    values(p_codigo, p_fecha, p_total, p_estado, p_cliente, p_usuario);
end $$
delimiter ;

delimiter $$
create procedure sp_listar_ventas()
begin
    select * from Ventas;
end $$
delimiter ;

delimiter $$
create procedure sp_actualizar_venta(
    in p_codigo int,
    in p_fecha date,
    in p_total decimal(10,2),
    in p_estado int,
    in p_cliente int,
    in p_usuario int
)
begin
    update Ventas v
    set v.fecha_venta = p_fecha,
        v.total = p_total,
        v.estado = p_estado,
        v.clientes_dpi_cliente = p_cliente,
        v.usuarios_codigo_usuario = p_usuario
    where v.codigo_venta = p_codigo;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_venta(in p_codigo int)
begin
    delete from Ventas where codigo_venta = p_codigo;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_venta(in p_codigo int)
begin
    select * from Ventas where codigo_venta = p_codigo;
end $$
delimiter ;

-- DETALLE VENTA
delimiter $$
create procedure sp_agregar_detalle_venta(
    in p_codigo int,
    in p_cantidad int,
    in p_precio decimal(10,2),
    in p_subtotal decimal(10,2),
    in p_producto int,
    in p_venta int
)
begin
    insert into Detalle_Venta(codigo_detalle_venta, cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta)
    values(p_codigo, p_cantidad, p_precio, p_subtotal, p_producto, p_venta);
end $$
delimiter ;

delimiter $$
create procedure sp_listar_detalle_venta()
begin
    select * from Detalle_Venta;
end $$
delimiter ;

delimiter $$
create procedure sp_actualizar_detalle_venta(
    in p_codigo int,
    in p_cantidad int,
    in p_precio decimal(10,2),
    in p_subtotal decimal(10,2),
    in p_producto int,
    in p_venta int
)
begin
    update Detalle_Venta d
    set d.cantidad = p_cantidad,
        d.precio_unitario = p_precio,
        d.subtotal = p_subtotal,
        d.productos_codigo_producto = p_producto,
        d.ventas_codigo_venta = p_venta
    where d.codigo_detalle_venta = p_codigo;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_detalle_venta(in p_codigo int)
begin
    delete from Detalle_Venta where codigo_detalle_venta = p_codigo;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_detalle_venta(in p_codigo int)
begin
	select * from Detalle_Venta where codigo_detalle_venta = p_codigo;
end $$
delimiter ;

-- DATOS DE LAS ENTIDADES
call sp_agregar_cliente(1001,'Juan','Perez','Zona 1',1);
call sp_agregar_cliente(1002,'Maria','Lopez','Zona 2',1);
call sp_agregar_cliente(1003,'Carlos','Ramirez','Zona 3',1);
call sp_agregar_cliente(1004,'Ana','Gomez','Zona 4',1);
call sp_agregar_cliente(1005,'Luis','Martinez','Zona 5',1);
call sp_agregar_cliente(1006,'Sofia','Hernandez','Zona 6',1);
call sp_agregar_cliente(1007,'Pedro','Castillo','Zona 7',1);
call sp_agregar_cliente(1008,'Laura','Vasquez','Zona 8',1);
call sp_agregar_cliente(1009,'Diego','Morales','Zona 9',1);
call sp_agregar_cliente(1010,'Elena','Rojas','Zona 10',1);

call sp_agregar_usuario(1,'admin','123','admin@mail.com','ADMIN',1);
call sp_agregar_usuario(2,'user1','123','user1@mail.com','USER',1);
call sp_agregar_usuario(3,'user2','123','user2@mail.com','USER',1);
call sp_agregar_usuario(4,'user3','123','user3@mail.com','USER',1);
call sp_agregar_usuario(5,'user4','123','user4@mail.com','USER',1);
call sp_agregar_usuario(6,'user5','123','user5@mail.com','USER',1);
call sp_agregar_usuario(7,'user6','123','user6@mail.com','USER',1);
call sp_agregar_usuario(8,'user7','123','user7@mail.com','USER',1);
call sp_agregar_usuario(9,'user8','123','user8@mail.com','USER',1);
call sp_agregar_usuario(10,'user9','123','user9@mail.com','USER',1);

call sp_agregar_producto(1,'Leche',3500.00,10,1);
call sp_agregar_producto(2,'Cafe',50.00,100,1);
call sp_agregar_producto(3,'Queso',120.00,50,1);
call sp_agregar_producto(4,'Jugo',900.00,20,1);
call sp_agregar_producto(5,'Consome',750.00,15,1);
call sp_agregar_producto(6,'Yogurt',40.00,200,1);
call sp_agregar_producto(7,'Agua',500.00,25,1);
call sp_agregar_producto(8,'Arroz',1500.00,30,1);
call sp_agregar_producto(9,'Frijol',300.00,40,1);
call sp_agregar_producto(10,'Aceite',200.00,60,1);

call sp_agregar_venta(1,'2026-01-01',3500.00,1,1001,1);
call sp_agregar_venta(2,'2026-01-02',100.00,1,1002,2);
call sp_agregar_venta(3,'2026-01-03',240.00,1,1003,3);
call sp_agregar_venta(4,'2026-01-04',900.00,1,1004,4);
call sp_agregar_venta(5,'2026-01-05',750.00,1,1005,5);
call sp_agregar_venta(6,'2026-01-06',80.00,1,1006,6);
call sp_agregar_venta(7,'2026-01-07',500.00,1,1007,7);
call sp_agregar_venta(8,'2026-01-08',1500.00,1,1008,8);
call sp_agregar_venta(9,'2026-01-09',300.00,1,1009,9);
call sp_agregar_venta(10,'2026-01-10',200.00,1,1010,10);

call sp_agregar_detalle_venta(1,1,3500.00,3500.00,1,1);
call sp_agregar_detalle_venta(2,2,50.00,100.00,2,2);
call sp_agregar_detalle_venta(3,2,120.00,240.00,3,3);
call sp_agregar_detalle_venta(4,1,900.00,900.00,4,4);
call sp_agregar_detalle_venta(5,1,750.00,750.00,5,5);
call sp_agregar_detalle_venta(6,2,40.00,80.00,6,6);
call sp_agregar_detalle_venta(7,1,500.00,500.00,7,7);
call sp_agregar_detalle_venta(8,1,1500.00,1500.00,8,8);
call sp_agregar_detalle_venta(9,1,300.00,300.00,9,9);
call sp_agregar_detalle_venta(10,1,200.00,200.00,10,10);