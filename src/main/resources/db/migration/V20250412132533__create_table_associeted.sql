CREATE TABLE associated (
  id CHAR(36) NOT NULL,
  id_person int NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY id_person_UNIQUE (id_person),
  CONSTRAINT fk_person_associated FOREIGN KEY (id_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO associated (id, id_person, created_at) VALUES
('5ffab750-17bb-11f0-9044-581122c7752d', 1, CURRENT_TIMESTAMP),
('64cd0f27-17bb-11f0-9044-581122c7752d', 2, CURRENT_TIMESTAMP),
('674a3782-17bb-11f0-9044-581122c7752d', 3, CURRENT_TIMESTAMP),
('6db1702a-17bb-11f0-9044-581122c7752d', 4, CURRENT_TIMESTAMP),
('70658514-17bb-11f0-9044-581122c7752d', 5, CURRENT_TIMESTAMP),
('739ea638-17bb-11f0-9044-581122c7752d', 6, CURRENT_TIMESTAMP),
('76715a58-17bb-11f0-9044-581122c7752d', 7, CURRENT_TIMESTAMP),
('7923b12a-17bb-11f0-9044-581122c7752d', 8, CURRENT_TIMESTAMP),
('7c5c738e-17bb-11f0-9044-581122c7752d', 9, CURRENT_TIMESTAMP),
('7f59f589-17bb-11f0-9044-581122c7752d', 10, CURRENT_TIMESTAMP);

