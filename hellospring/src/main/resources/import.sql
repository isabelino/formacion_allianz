INSERT INTO clientes (nombre,apellido,email,telefono,create_at) VALUES ('Jose','Perez','jp@email.com',631224558,'2022-01-26');
INSERT INTO clientes (nombre,apellido,email,telefono,create_at) VALUES ('Carlos','Lopez','cl@email.com',92124445,'2022-01-26');
INSERT INTO clientes (nombre,apellido,email,telefono,create_at) VALUES ('Maria','Orellana','mo@email.com',8954545,'2022-01-26');
INSERT INTO clientes (nombre,apellido,email,telefono,create_at) VALUES ('Dina','Ramirez','dr@email.com',63154545,'2022-01-26');
INSERT INTO clientes (nombre,apellido,email,telefono,create_at) VALUES ('Pepe','Casillas','pc@email.com',654544558,'2022-01-26');
INSERT INTO clientes (nombre,apellido,email,telefono,create_at) VALUES ('Enrrique','Iglesias','ei@email.com',54578558,'2022-01-26');
INSERT INTO clientes (nombre,apellido,email,telefono,create_at) VALUES ('Pedro','Diaz','pd@email.com',91245454,'2022-01-26');
INSERT INTO clientes (nombre,apellido,email,telefono,create_at) VALUES ('Ramon','Gonzalez','rg@email.com',25454558,'2022-01-26');



INSERT INTO usuarios (username,password,enabled) VALUES('rolando','$2a$10$VlPKYJMvuKX24HscJnwHJenu8s/1zr8f.qmiQ1dbCoQP1K3HHOX.C',1);
INSERT INTO usuarios (username,password,enabled) VALUES('admin','$2a$10$Z81mwqQ5FNnNUaKWHZ3/E.F1A8n91OZFHWyV2BogspAma0OhPxKW2',1);

INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,1);