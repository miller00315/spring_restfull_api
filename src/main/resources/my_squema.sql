CREATE DATABASE SELLERS;

USE SELLERS;

CREATE TABLE client(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    cpf VARCHAR (11)
);

CREATE TABLE product(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100),
    unity_price NUMERIC (20, 2)
);

CREATE TABLE solicitation(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER REFERENCES client (ID),
    solicited_at TIMESTAMP,
    status VARCHAR(20),
    total NUMERIC(20, 2)
);

CREATE TABLE solicited_item(
    id INTEGER  PRIMARY  KEY  AUTO_INCREMENT,
    solicitation_id INTEGER REFERENCES solicitation (ID),
    product_id INTEGER REFERENCES product (ID),
    quantity INTEGER
);

CREATE TABLE api_user(
    id INTEGER  PRIMARY  KEY  AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    admin BOOL DEFAULT FALSE
);