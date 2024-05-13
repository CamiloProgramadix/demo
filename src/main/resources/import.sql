
INSERT INTO users (name, last_name, email, password) VALUE ('Camilo','Cabrales', 'camilocabrales2005@gmail.com','1234');
INSERT INTO users (name, last_name, email, password) VALUES ('Ezequiel', 'Palencia','palenciaezequiel09@gmail.com','5678'); 
INSERT INTO users (name, last_name, email, password ) VALUES ('Jesus', 'Deulufeutt', 'jesusdeulufeuttmendez@gmail.com','0987'); 

INSERT INTO product(name, price, category, images) VALUES('Estufa De Empotrar 4 Puestos', 600000,  'Domestico', 'http://localhost:8080/images/imagen.png');
INSERT INTO product(name, price, category, images) VALUES ('Estufa De Piso 30"', 1200000, 'Industrial', 'http://localhost:8080/images/imagen.png'); 
INSERT INTO product (name, price, category, images) VALUES ('Estufa De Empotrar Vidrio Templado Roja 6545 Gas Natural ', 2000000, 'Industrial', 'http://localhost:8080/images/imagen.png');

INSERT INTO buys(date, id_product, id_user) VALUES ('2006-02-12-13-12-12', 1, 1);
INSERT INTO buys(date, id_product, id_user) VALUES ('2006-02-14-13-12-12', 2, 3);
INSERT INTO buys(date, id_product, id_user) VALUES ('2006-02-14-13-12-12', 3, 3);

INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO role_user (user_id, role_id) VALUES (1, 2);
INSERT INTO role_user (user_id, role_id) VALUES (2, 1);
INSERT INTO role_user (user_id, role_id) VALUES (3, 1);