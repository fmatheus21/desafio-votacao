CREATE TABLE voting (
  id_associated CHAR(36) NOT NULL,
  id_session CHAR(36) NOT NULL,
  vote VARCHAR(3) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_associated, id_session),
  CONSTRAINT fk_associated FOREIGN KEY (id_associated) REFERENCES associated (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_session_vote FOREIGN KEY (id_session) REFERENCES session (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
