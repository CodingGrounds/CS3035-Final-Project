CREATE TABLE IF NOT EXISTS settings(
    id INTEGER PRIMARY KEY,
    object BLOB NOT NULL,
    boardId INTEGER NOT NULL,
    FOREIGN KEY(boardId) REFERENCES board(id)
);






