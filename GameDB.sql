Genre(genreID, name)
Studios(studioID, name, yearFounded, numEmployees)
Developer(devID, name, yearsActive, employer)
Franchises(franchiseID, name)
Records(recordID, statsID, title, player, bestTime)
Stats(statsID, gameID, avgTimeToBeat)
Console(consoleID, name, sales, releaseYear, type)
Games(gameID, title, console, releaseDate, sales, genre, franchise, studio, leadDev, averageTime, worldRecord)

CREATE TABLE IF NOT EXISTS Genre(
  genreID integer NOT NULL PRIMARY KEY,
  name varchar(50)
);

CREATE TABLE IF NOT EXISTS Studios(
  studioID integer NOT NULL PRIMARY KEY,
  name varchar(50),
  yearFounded integer,
  numEmployees integer
);

CREATE TABLE IF NOT EXISTS Developer(
  devID integer NOT NULL PRIMARY KEY,
  studioID integer FOREIGN KEY REFERENCES Studio(studioID),
  name varchar(50),
  yearsActive integer
);

CREATE TABLE IF NOT EXISTS Franchises(
  franchiseID integer NOT NULL PRIMARY KEY,
  name varchar(50)
);

CREATE TABLE IF NOT EXISTS Stats(
  statsID integer NOT NULL PRIMARY KEY,
  gameID integer FOREIGN KEY REFERENCES Games(gameID),
  avgTimeToBeat numeric
);

CREATE TABLE IF NOT EXISTS Records(
  recordID integer NOT NULL PRIMARY KEY,
  statsID integer FOREIGN KEY REFERENCES Stats(statsID),
  title varchar(50),
  player varchar(50),
  bestTime numeric
);

CREATE TABLE IF NOT EXISTS Platforms(
  platformID integer NOT NULL PRIMARY KEY,
  name varchar(50)
);

CREATE TABLE IF NOT EXISTS Console(
  consoleID integer not null primary key,
  platformID integer FOREIGN KEY REFERENCES Platforms(platformID),
  name varchar(50),
  releaseYear integer,
  sales numeric
);

CREATE TABLE IF NOT EXISTS PCClients(
  pcClientsID integer not null primary key,
  platformID integer FOREIGN KEY REFERENCES Platforms(platformID),
  name varchar(50),
  releaseYear integer
)

CREATE TABLE IF NOT EXISTS Games(
  gameID integer not null primary key,
  title varchar(50),
  console varchar(50) foreign key references Console(name),
  releaseDate integer,
  sales numeric,
  genre varchar(50) foreign key references Genre(genre),
  franchise varchar(50) foreign key references Franchise(name),
  studio varchar(50) foreign key references Studio(name),
  leadDev varchar(50) foreign key references Developer(name),
  averageTime numeric,
  worldRecord numeric foreign key references WorldRecords(bestTime)
);
