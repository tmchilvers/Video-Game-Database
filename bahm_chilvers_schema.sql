CREATE TABLE IF NOT EXISTS Records(
  recordID INTEGER NOT NULL PRIMARY KEY,
  title varchar(50),
  player varchar(50),
  bestTime TEXT -- HH:MM:SS
);

CREATE TABLE IF NOT EXISTS Genres(
  genreID INTEGER NOT NULL PRIMARY KEY,
  name varchar(120)
);

CREATE TABLE IF NOT EXISTS Franchises(
  franchiseID INTEGER NOT NULL PRIMARY KEY,
  name varchar(120)
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

CREATE TABLE IF NOT EXISTS BoxArts(
  artID INTEGER NOT NULL PRIMARY KEY,
  artPath varchar(150) -- system path to file
);

CREATE TABLE IF NOT EXISTS ConsoleSpecs(
  consoleSpecID INTEGER NOT NULL PRIMARY KEY,
  resolution INTEGER,
  RAM INTEGER, -- measured in GB
  GPU INTEGER, -- measured in MHz
  processor varchar(100)
);

CREATE TABLE IF NOT EXISTS Platforms(
  platformID INTEGER NOT NULL PRIMARY KEY,
  name varchar(120),
  releaseYear INTEGER,
  price REAL,
  console INTEGER, -- 0 for false, 1 for true
  consoleSpecID INTEGER NOT NULL,
  FOREIGN KEY (consoleSpecID) REFERENCES ConsoleSpecs(consoleSpecID)
  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS DLC(
  dlcID INTEGER NOT NULL PRIMARY KEY,
  price REAL,
  name varchar(120),
  newGame INTEGER, -- 0 for False, 1 for True if offers extended gameplay
  newCustom INTEGER -- 0 for False, 1 for True if offers new customization
);

CREATE TABLE IF NOT EXISTS InstructionManuals(
  manualID INTEGER NOT NULL PRIMARY KEY,
  manualPath varchar(150) -- system path to file
);

CREATE TABLE IF NOT EXISTS Reviews(
  reviewID INTEGER NOT NULL PRIMARY KEY,
  score INTEGER, -- out of 10
  comment varchar(300)
);

CREATE TABLE IF NOT EXISTS Trailers(
  trailerID INTEGER NOT NULL PRIMARY KEY,
  trailerPath varchar(150), -- system path to file
  releaseDate TEXT -- YYYY-MM-DD
);

CREATE TABLE IF NOT EXISTS Games(
  gameID INTEGER NOT NULL PRIMARY KEY,
  title varchar(50),
  releaseDate TEXT, -- YYYY-MM-DD
  genreID INTEGER NOT NULL,
  franchiseID INTEGER,
  platformID INTEGER NOT NULL,
  devID INTEGER NOT NULL,
  studioID INTEGER NOT NULL,
  artID INTEGER NOT NULL,
  dlcID INTEGER NOT NULL,
  manualID INTEGER NOT NULL,
  reviewID INTEGER NOT NULL,
  trailerID INTEGER NOT NULL,
  FOREIGN KEY (genreID) REFERENCES Genres(genreID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (franchiseID) REFERENCES Franchises(franchiseID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (platformID) REFERENCES Platforms(platformID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (devID) REFERENCES Developers(devID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (studioID) REFERENCES Studios(studioID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (artID) REFERENCES BoxArts(artID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (dlcID) REFERENCES DLC(dlcID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (manualID) REFERENCES InstructionManuals(manualID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (reviewID) REFERENCES Reviews(reviewID)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (trailerID) REFERENCES Trailers(trailerID)
  ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS Sales(
  salesID INTEGER NOT NULL PRIMARY KEY,
  gameID INTEGER NOT NULL,
  price REAL,
  global REAL,
  domestic REAL,
  FOREIGN KEY (gameID) REFERENCES Games(gameID)
  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Stats(
  gameID INTEGER NOT NULL,
  avgTimeToBeat TEXT, -- HH:MM:SS
  recordID INTEGER NOT NULL,
  PRIMARY KEY (gameID, recordID),
  FOREIGN KEY (recordID) REFERENCES Records(recordID)
  ON DELETE NO ACTION,
  FOREIGN KEY (gameID) REFERENCES Games(gameID)
  ON DELETE CASCADE
);