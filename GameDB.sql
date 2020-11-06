CREATE TABLE IF NOT EXISTS Records(
  recordID INTEGER NOT NULL PRIMARY KEY,
  title varchar(50),
  player varchar(50),
  bestTime TEXT -- HH:MM:SS
);

CREATE TABLE IF NOT EXISTS Stats(
  gameID INTEGER NOT NULL,
  avgTimeToBeat TEXT, -- HH:MM:SS
  recordID INTEGER NOT NULL,
  PRIMARY KEY (gameID, recordID),
  FOREIGN KEY (recordID) REFERENCES Records(recordID),
  FOREIGN KEY (gameID) REFERENCES Games(gameID)
);

CREATE TABLE IF NOT EXISTS Genres(
  genreID INTEGER NOT NULL PRIMARY KEY,
  name varchar(120)
);

CREATE TABLE IF NOT EXISTS Franchises(
  franchiseID INTEGER NOT NULL PRIMARY KEY,
  name varchar(120)
);

CREATE TABLE IF NOT EXISTS Sales(
  salesID INTEGER NOT NULL PRIMARY KEY,
  price REAL,
  global REAL,
  domestic REAL
);

CREATE TABLE IF NOT EXISTS Platforms(
  platformID INTEGER NOT NULL PRIMARY KEY,
  name varchar(120),
  releaseYear INTEGER,
  price REAL,
  console INTEGER -- 0 for false, 1 for true
);

CREATE TABLE IF NOT EXISTS Developers(
  devID INTEGER NOT NULL PRIMARY KEY,
  name varchar(120),
  yearsActive INTEGER
);

CREATE TABLE IF NOT EXISTS Studios(
  studioID INTEGER NOT NULL PRIMARY KEY,
  name varchar(120),
  yearFounded INTEGER,
  numEmployees INTEGER,
  country varchar(120)
);

CREATE TABLE IF NOT EXISTS Games(
  gameID INTEGER NOT NULL PRIMARY KEY,
  title varchar(50),
  releaseDate TEXT, -- YYYY-MM-DD
  genreID INTEGER NOT NULL,
  franchiseID INTEGER NOT NULL,
  platformID INTEGER NOT NULL,
  devID INTEGER NOT NULL,
  studioID INTEGER NOT NULL,
  salesID INTEGER NOT NULL,
  FOREIGN KEY (genreID) REFERENCES Genres(genreID),
  FOREIGN KEY (franchiseID) REFERENCES Franchises(franchiseID),
  FOREIGN KEY (platformID) REFERENCES Platforms(platformID),
  FOREIGN KEY (devID) REFERENCES Developers(devID),
  FOREIGN KEY (studioID) REFERENCES Studios(studioID),
  FOREIGN KEY (salesID) REFERENCES Sales(salesID)
);
