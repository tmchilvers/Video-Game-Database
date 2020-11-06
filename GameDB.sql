CREATE TABLE IF NOT EXISTS Records(
  recordID INTEGER NOT NULL PRIMARY KEY,
  title varchar(50),
  player varchar(50),
  bestTime TEXT -- HH:MM:SS
);

CREATE TABLE IF NOT EXISTS Stats(
  statsID INTEGER NOT NULL PRIMARY KEY,
  avgTimeToBeat TEXT, -- HH:MM:SS
  recordID INTEGER,
  FOREIGN KEY (recordID) REFERENCES Records(recordID)
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
  genreID INTEGER,
  franchiseID INTEGER,
  platformID INTEGER,
  devID INTEGER,
  studioID INTEGER,
  statsID INTEGER,
  salesID INTEGER,
  FOREIGN KEY (genreID) REFERENCES Genres(genreID),
  FOREIGN KEY (franchiseID) REFERENCES Franchises(franchiseID),
  FOREIGN KEY (platformID) REFERENCES Platforms(platformID),
  FOREIGN KEY (devID) REFERENCES Developers(devID),
  FOREIGN KEY (studioID) REFERENCES Studios(studioID),
  FOREIGN KEY (statsID) REFERENCES Stats(statsID)
  FOREIGN KEY (salesID) REFERENCES Sales(salesID)
);
