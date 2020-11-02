Genre(genreID, genre)
Studio(studioID, name, yearFounded)
Developer(devID, name, yearsActive, employer)
Franchise(franchiseID, name)
WorldRecords(recordID, game, console, playerName, bestTime)
Console(consoleID, name, sales, releaseYear, type);
Game(gameID, title, console, releaseDate, sales, genre, franchise, studio, leadDev, averageTime, worldRecord)

CREATE TABLE IF NOT EXISTS Genre(
  genreID integer NOT NULL PRIMARY KEY,
  genre varchar(50)
);

CREATE TABLE IF NOT EXISTS Studio(
  studioID integer NOT NULL PRIMARY KEY,
  name varchar(50),
  yearFounded integer
);

CREATE TABLE IF NOT EXISTS Developer(
  devID integer NOT NULL PRIMARY KEY,
  name varchar(50),
  yearsActive integer,
  employer varchar(50) FOREIGN KEY REFERENCES Studio(name)
);

CREATE TABLE IF NOT EXISTS Franchise(
  franchiseID integer NOT NULL PRIMARY KEY,
  name varchar(50)
);

CREATE TABLE IF NOT EXISTS WorldRecords(
  recordID integer NOT NULL PRIMARY KEY,
  game varchar(50) REFERNCES Game(title),
  console varchar(50) FOREIGN KEY REFERENCES Console(name),
  playerName varchar(50),
  bestTime numeric
);

create table Console(
  consoleID integer not null primary key,
  name varchar(50),
  sales numeric,
  releaseYear integer,
  type varchar(50)
);

create table Game(
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
