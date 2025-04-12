CREATE TABLE person (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(70) NOT NULL,
  document varchar(20) NOT NULL, 
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY document_UNIQUE (document) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO person (id, name, document) VALUES
(1, 'Maria Eliane Cardoso', '29443049333'),
(2, 'André Paulo Oliveira', '01243876166'),
(3, 'Tomás Bryan Fogaça', '58840076808'),
(4, 'Carolina Jennifer Luana Moura', '47327146091'),
(5, 'Otávio Carlos Tiago Rocha', '36557255746'),
(6, 'Anthony Davi Diogo Barbosa', '25749083098'),
(7, 'Ayla Tatiane Patrícia Teixeira', '72335211153'),
(8, 'Levi Tiago Guilherme Dias', '40949258059'),
(9, 'Silvana Betina Corte Real', '37757905516'),
(10, 'Augusto Yuri Sales', '59787584337');