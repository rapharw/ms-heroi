/*
    SCRIPTS DE CRIACAO
*/
DROP TABLE IF EXISTS PODERES_HEROIS;
DROP TABLE IF EXISTS UNIVERSOS;
DROP TABLE IF EXISTS HEROIS;
DROP TABLE IF EXISTS PODERES;

DROP TABLE IF EXISTS USUARIOS;
DROP TABLE IF EXISTS LOGIN;



-- Table UNIVERSOS
CREATE TABLE UNIVERSOS (
    id int IDENTITY(1,1) primary key,
    descricao varchar(255) NOT NULL
);



-- Table HEROIS
CREATE TABLE HEROIS (
    id int IDENTITY(1,1) primary key,
    nome varchar(255) NOT NULL,
    id_universo int not null,
    data_cadastro datetime NOT NULL,
    situacao varchar(7) not null
);

ALTER TABLE HEROIS ADD CONSTRAINT CHK_FLG_ATIVO CHECK (situacao in ('ATIVO', 'INATIVO'));
ALTER TABLE HEROIS ADD CONSTRAINT FK_ID_UNIVERSO FOREIGN KEY ( id_universo ) REFERENCES UNIVERSOS ( id );



-- Table PODERES
CREATE TABLE PODERES (
    id int IDENTITY(1,1) primary key ,
    descricao varchar(255) NOT NULL
);



-- Table PODERES_HEROIS
CREATE TABLE PODERES_HEROIS (
    id_heroi int not null,
    id_poder int not null
);

ALTER TABLE PODERES_HEROIS ADD CONSTRAINT FK_ID_HEROI FOREIGN KEY ( id_heroi ) REFERENCES HEROIS ( id );
ALTER TABLE PODERES_HEROIS ADD CONSTRAINT FK_ID_PODER FOREIGN KEY ( id_poder ) REFERENCES PODERES ( id ) ON DELETE CASCADE;




-- Table USUARIOS
create table USUARIOS (
    id int IDENTITY(1,1) primary key ,
    nome varchar(255) NOT NULL,
    role varchar (10) not null
);

ALTER TABLE USUARIOS ADD CONSTRAINT CHK_PERFIL_ADM CHECK (role in ('ROLE_COMUM', 'ROLE_ADM'));

create table LOGIN (
    username varchar(15) primary key,
    password varchar(60) NOT NULL,
    id_usuario int not null
);

ALTER TABLE LOGIN ADD CONSTRAINT FK_ID_USUARIO FOREIGN KEY ( id_usuario ) REFERENCES USUARIOS ( id );




/*
    SCRIPTS DE CARGA
*/

-- CARGA TABLE UNIVERSOS
INSERT INTO UNIVERSOS (descricao) values ('EY Comics');
INSERT INTO UNIVERSOS (descricao) values ('Trainee Comics');
INSERT INTO UNIVERSOS (descricao) values ('Outros');


-- CARGA TABLE PODERES
INSERT INTO PODERES (descricao) values ('Palácio da Memória');
INSERT INTO PODERES (descricao) values ('Super velocidade');


-- CARGA TABLE USUARIO
insert into USUARIOS (nome, role) values ('Raphael', 'ROLE_COMUM');
insert into USUARIOS (nome, role) values ('Renato', 'ROLE_COMUM');
insert into USUARIOS (nome, role) values ('Wanderley', 'ROLE_ADM');


-- CARGA TABLE LOGIN
-- password-> raphael@123
insert into LOGIN (username, password, id_usuario) values ('r0001', 'URYsLkWg3L5+QMYfo28KZsMU1QfhdNcY', 1);
-- password-> renato@123
insert into LOGIN (username, password, id_usuario) values ('r0002', 'CV7Uvk8gzncfhqO+KYGx+U9w3h1vN91+', 2);
-- password-> wanderley@123
insert into LOGIN (username, password, id_usuario) values ('w0001', 'Ol546GnPsVvsj9nakyYMhWl+/B/d2NOD', 3);