DROP DATABASE IF EXISTS trecostest;
CREATE DATABASE trecostest CHARACTER SET utf8 COLLATE utf8_general_ci;
USE trecostest;

CREATE TABLE users (
    uid INT AUTO_INCREMENT PRIMARY KEY,
    udate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    uname VARCHAR(255),
    uemail VARCHAR(255),
    upassword VARCHAR(255),
    ubirth DATE,
    ustatus ENUM('on', 'off', 'del') DEFAULT 'on'
);

CREATE TABLE categories (
    cid INT AUTO_INCREMENT PRIMARY KEY,
    cname VARCHAR(255)
);

CREATE TABLE documents (
    did INTEGER AUTO_INCREMENT PRIMARY KEY,
    ddate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    dcategory INT,
    duser INT,
    dname VARCHAR(255),
    dcontent TEXT,
    dstatus ENUM('on', 'off', 'del') DEFAULT 'on',
    FOREIGN KEY (dcategory) REFERENCES categories(cid),
    FOREIGN KEY (duser) REFERENCES users(uid)
);

INSERT INTO users(uname, uemail, upassword, ubirth) VALUES 
('Tânia Martins Carvalho', 'tania.carvalho96@gmail.com', sha1('#Tania1986'), '1986-05-02'), 
('Martim Almeida Oliveira', 'oliveira_martin@hotmail.com', sha1('#Martin2000'), '2000-01-28'), 
('Matilde Correia Goncalves', 'matildeGoncalves@yahoo.com.br', sha1('#Goncalves1989)', '1989-10-12');

INSERT INTO categories(cname) VALUES
('Bolos salgados'),
('Bolos doces'),
('Coberturas'),
('Salgados'),
('Complementos');

INSERT INTO documents(ddate, dcategory, duser, dname, dcontent, dstatus) VALUES
('2023-02-01 14:12:34', 1, 2, 'Bolo de pão Plus Vita', 'Lorem ipsum…', 'on'),
('2023-02-01 19:31:22', 2, 3, 'Bolo de fubá grelhado', 'Lorem ipsum…', 'on'),
('2023-02-01 22:11:44', 2, 1, 'Bolo de Bis', 'Lorem ipsum…', 'off'),
('2023-02-02 07:15:45', 3, 2, 'Ganache de pimentinha', 'Lorem ipsum…', 'on'),
('2023-02-02 18:12:52', 4, 2, 'Bolinho de feijoada', 'Lorem ipsum…', 'del'),
('2023-02-03 09:31:39', 5, 3, 'Batida de Tang de Limão', 'Lorem ipsum…', 'on');