CREATE TABLE session (
  id CHAR(36) NOT NULL,
  id_topic CHAR(36) NOT NULL,
  start datetime NOT NULL,
  end datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY id_topic_UNIQUE (id_topic),
  CONSTRAINT fk_topic FOREIGN KEY (id_topic) REFERENCES topic (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;