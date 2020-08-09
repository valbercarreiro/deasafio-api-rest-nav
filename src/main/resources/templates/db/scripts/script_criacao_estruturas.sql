CREATE DATABASE desafio
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


CREATE TABLE usuario (
  usuario_id SERIAL   NOT NULL ,
  nome VARCHAR   NOT NULL ,
  email VARCHAR   NOT NULL ,
  senha VARCHAR   NOT NULL   ,
PRIMARY KEY(usuario_id));


CREATE TABLE marca (
  marca_id SERIAL   NOT NULL ,
  nome VARCHAR      ,
PRIMARY KEY(marca_id));


CREATE TABLE patrimonio (
  patrimonio_id SERIAL   NOT NULL ,
  marca_id INTEGER   NOT NULL ,
  nome VARCHAR   NOT NULL ,
  descricao VARCHAR    ,
  num_tombo VARCHAR      ,
PRIMARY KEY(patrimonio_id)  ,
  FOREIGN KEY(marca_id)
    REFERENCES marca(marca_id));


CREATE INDEX patrimonio_FKIndex1 ON patrimonio (marca_id);


CREATE INDEX IFK_Rel_01 ON patrimonio (marca_id);