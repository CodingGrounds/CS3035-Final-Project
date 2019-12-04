CREATE TABLE IF NOT EXISTS category(
  id INTEGER PRIMARY KEY,
  colNumber INTEGER NOT NULL,
  object BLOB NOT NULL,
  boardId INTEGER NOT NULL,
  FOREIGN KEY(boardId) REFERENCES board(id)
);