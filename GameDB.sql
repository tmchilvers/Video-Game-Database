Genre(genreID, genre)
Studio(studioID, name, yearFounded)
Developer(devID, name, yearsActive, employer)
Franchise(franchiseID, name)
WorldRecords(recordID, game, console, playerName, bestTime)
Console(consoleID, name, sales, releaseYear, type);
Game(gameID, title, console, releaseDate, sales, genre, franchise, studio, leadDev, averageTime, worldRecord)

create table Genre(
  genreID integer not null primary key,
  genre varchar(50)
);

create table Studio(
  studioID integer not null primary key,
  name varchar(50),
  yearFounded integer
);

create table Developer(
  devID integer not null primary key,
  name varchar(50),
  yearsActive integer,
  employer varchar(50) foreign key references Studio(name)
);

create table Franchise(
  franchiseID integer not null primary key,
  name varchar(50)
);

create table WorldRecords(
  recordID integer not null primary key,
  game varchar(50) references  Game(title),
  console varchar(50) foreign key references Console(name),
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
