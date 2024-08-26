create database banco;

GRANT ALL PRIVILEGES ON banco.*
to 'root'@'localhost';

USE banco;

CREATE TABLE aluno (
    matricula BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    idade INT,
    email VARCHAR(255)
);

