CREATE TABLE voting (
  id_associeted binary(16) NOT NULL,
  id_topic binary(16) NOT NULL,
  vote varchar(3) NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_associeted, id_topic),
  KEY fk_topic_vote_idx (id_topic),
  CONSTRAINT fk_associeted FOREIGN KEY (id_associeted) REFERENCES associated (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_topic_vote FOREIGN KEY (id_topic) REFERENCES topic (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
