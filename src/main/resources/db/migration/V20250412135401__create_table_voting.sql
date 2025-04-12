CREATE TABLE voting (
  id_associeted CHAR(36) NOT NULL,
  id_session CHAR(36) NOT NULL,
  vote varchar(3) NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_associeted, id_session),
  KEY fk_session_vote_idx (id_session),
  CONSTRAINT fk_associeted FOREIGN KEY (id_associeted) REFERENCES associated (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_session_vote FOREIGN KEY (id_session) REFERENCES session (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
