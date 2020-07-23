DROP TABLE IF EXISTS PODERES_HEROIS;
DROP TABLE IF EXISTS UNIVERSOS;
DROP TABLE IF EXISTS HEROIS;
DROP TABLE IF EXISTS PODERES;



-- Table UNIVERSOS
CREATE TABLE UNIVERSOS (
    id int IDENTITY(1,1) primary key,
    descricao varchar(255) NOT NULL
);



-- Table HEROI
CREATE TABLE HEROIS (
    id int IDENTITY(1,1) primary key,
    nome varchar(255) NOT NULL,
    id_universo int not null,
    data_cadastro datetime NOT NULL,
    flg_ativo varchar(1) not null
);

ALTER TABLE HEROIS ADD CONSTRAINT CHK_FLG_ATIVO CHECK (flg_ativo in ('S', 'N'));
ALTER TABLE HEROIS ADD CONSTRAINT FK_ID_UNIVERSO FOREIGN KEY ( id ) REFERENCES UNIVERSOS ( id );



-- Table PODERES
CREATE TABLE PODERES (
    id int IDENTITY(1,1) primary key ,
    descricao varchar(255) NOT NULL
);



-- Table PODERES_HEROIS
CREATE TABLE PODERES_HEROIS (
    id int IDENTITY(1,1) primary key,
    id_heroi int not null,
    id_poder int not null
);

ALTER TABLE PODERES_HEROIS ADD CONSTRAINT FK_ID_HEROI FOREIGN KEY ( id_heroi ) REFERENCES HEROIS ( id );
ALTER TABLE PODERES_HEROIS ADD CONSTRAINT FK_ID_PODER FOREIGN KEY ( id_poder ) REFERENCES PODERES ( id );