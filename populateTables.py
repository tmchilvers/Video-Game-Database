import random
DATABASE = r"gameDB.db"

def insertRecord(insert_command):
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter the insert SQL command and data below:")
        editTables.input_sql_command(conn, insert_command)
    else:
        print("Error! Cannot create the database connection.")

def generateGameTitle():
    title1 = ['Mario', 'Zelda', 'Portal', 'Minecraft', 'Borderlands', 'Roller Coaster']
    subtitle = [': Zero Mission', ': Breath of the Wild', ': Sword and Shield', " 2", " Party", " Tycoon"]
    title1Loc = random.randInt(0,4)
    subtitleLoc = random.randInt(0,5)
    title = title1[title1Loc] + subtitle[subtitleLoc]
    return title

def generateFranchise():
    adjective = ['Super', 'The Legend of', 'The Amazing', 'Grand', 'Call of', 'The']
    name = ['Mario', 'Zelda', 'Pac-Man', 'Sonic The Hedgehog', 'Sims']
    franchise = adjective[random.randInt(0,5)] + " " + name[random.randInt(0,4)]
    return franchise

def generateName():
    first = ['Bob', 'Steve', 'Joe', 'Katie', 'Jessica', 'Elizabeth']
    last = ['Jones', 'Smith', 'Williams', 'Brown', 'Davis', 'Miller']
    firstLoc = random.randInt(0,4)
    lastLoc = random.randInt(0,5)
    name = first[firstLoc] + " " + last[lastLoc]
    return name

def generateConsole():
    first = ['Microsoft', 'Sony', 'Nintendo']
    last = ['Switch', 'Xbox', 'Playstation']
    firstLoc = random.randInt(0,2)
    lastLoc = random.randInt(0,2)
    console = first[firstLoc] + " " + last[lastLoc]
    return console

def generateGenre():
    g1 = ['Action', 'Puzzle', 'Platforming', 'Shooter', 'Racing', 'Adventure', 'Arcade', 'Open-World']
    g2 = ['Action', 'Puzzle', 'Platformer', 'Shooter', 'Racing', 'Adventure']
    genre = genre = g1[random.randInt(0,7)] + " " + g2[random.randInt(0,5)]
    return genre

def generatePrice():
    dollars = random.randInt(0,1000)
    cents = random.randInt(0, 99)
    price = "$" + dollars + "." + cents
    return price

def generateTime():
    time = random.randInt(0,100) + ':' + random.randInt(0, 59) + ':' + random.randInt(0,59)
    return time

def generateCompany():
    name1 = ["Micro", "So", "Nin"]
    name2 = ["Soft", "ny", "tendo"]
    company = name1[random.randInt(0,2)] + name2[random.randInt(0,2)]
    return company

def generateFilePath():
    name1 = ["asdf24o", "zxc45lm", "7531kljm"]
    name2 = ["asdf24o", "zxc45lm", "7531kljm"]
    filepath = "./GameDB/BoxArt/" + name1[random.randInt(0,2)] + " " + name2[random.randInt(0,2)]
    return filepath

def generateDate():
    date = random.randInt(1,12) + "/" + random.randInt(1, 31) + "/" + random.randInt(1900, 2020)
    return date

def generateReview():
    name1 = ["Good", "just awful", "ok"]
    review = "This game was " + name1[random.randInt(0,2)] + "."
    return review

def generateGPU():
    name1 = ["AMD", "NVIDIA", "ASUS"]
    gpu = name1[random.randInt(0,2)] + random.randInt(1000, 38000)
    return gpu

def generateCPU():
    name1 = ["Intel", "AMD"]
    cpu = name1[random.randInt(0,1)] + random.randInt(1000, 38000)
    return cpu

def generateResolution():
    resolution = random.randInt(360,3840) + "x" + random.randInt(240, 2160)
    return resolution

def addWRS():
    for(int i = 0; i < 180; i++):
        query = "Insert into Records values(" + i + ", " + generateGameTitle() + ", " + generatePlayer() + ", " + generateTime() + ")"
        insertRecord(query)

def addStats():
    for(int i = 0; i < 180; i++):
        query = "Insert into Stats(" + i + ", " + generateTime() + ", " + random.randInt(0, 180) + ")"
        insertRecord(query)

def addGenre():
    for(int i = 0; i < 180; i++):
        query = "Insert into Genres(" + i + ", " + generateGenre() + ")"
        insertRecord(query)

def addFranchise():
    for(int i = 0; i < 180; i++):
        query = "Insert into Franchises(" + i + ", " + generateFranchise() + ")"
        insertRecord(query)

def addSales():
    for(int i=0; i < 180; i++):
        query = "Insert into Sales(" + i + ", " + random.randInt(0,1000) + ", " + generatePrice() + ", " + generatePrice() + ", " + generatePrice() + ")"
        insertRecord(query)

def addDev():
    for(int i=0; i < 180; i++):
        query = "Insert into Developers(" + i + ", " + generateName() + ", " + random.randInt(0, 100) + ")"
        insertRecord(query)

def addPlatform():
    for(int i=0; i < 180; i++):
        query = "Insert into Platforms(" + i + ", " + generateName() + ", " + random.randInt(1988, 2020) + ", " + generatePrice() + ", " + random.randInt(0, 1000) + ")"
        insertRecord(query)

def addStuios():
    for(int i = 0; i < 180; i++):
        query = "Insert into Studios(" + i + ", " + generateCompany() + ", " + random.randInt(1988, 2020) + ", " + random.randInt(1, 100) + ", United States)"
        insertRecord(query)

def addBoxArts():
    for(int i=0; i < 180; i++):
        query = "Insert into BoxArts(" + i + ", " + generateFilePath() + ".png)"
        insertRecord(query)

def addInstructionManuals():
    for(int i=0; i < 180; i++):
        query = "Insert into InstructionManuals(" + i + ", " + generateFilePath() + ".pdf)"
        insertRecord(query)

def addTrailers():
    for(int i=0; i < 180; i++):
        query = "Insert into Trailers(" + i + ", " + generatePath() + ".mp4, " + generateDate() + ")"
        insertRecord(query)

def addReviews():
    for(int i = 0; i < 180; i++):
        query =
         "Insert into Reviews(" + i + ", " + random.randInt(1,5) + ", " + generateReview() + ")"
        insertRecord(query)

def addDLC():
    for(int i = 0; i < 180; i++):
        if(random.randInt(0,1) == 0):
            query = "Insert into DLC(" + i + ", " + generatePrice() + ", " + generateGame() + " Expansion Pass, true, false)"
            insertRecord(query)
        else:
            query = "Insert into DLC(" + i + ", " + generatePrice() + ", " + generateGame() + " Expansion Pass, false, true)"
            insertRecord(query)

def addConsoleSpecs():
    for(int i = 0; i < 180; i++):
        query = "Insert into ConsoleSpecs(" + i + ", " + generateResolution() + ", " + random.randInt(1,16) + "gb, " + generateGPU() + ", " + generateCPU() + ")"
        insertRecord(query)


def addGames():
    for(int i = 0; i < 1000000; i++):
        query = "Insert into Games(" + i + ", " + generateGame() + ", " + generatDate() + ", " + random.randInt(0,180) + ", "
        query = query + random.randInt(0,180) + ", " + random.randInt(0,180) + ", " + random.randInt(0,180) + ", " + random.randInt(0,180) + ", "
        query = query + random.randInt(0,180) + ", " + random.randInt(0,180) + ", " + random.randInt(0,180) + ", " + random.randInt(0,180) + ", " + random.randInt(0,180) + ")"
        insertRecord(query)

def addAllData():
    addWRS()
    addStats()
    addGenre()
    addFranchise()
    addSales()
    addDev()
    addPlatform()
    addStudios()
    addBoxArts()
    addInstructionManuals()
    addTrailers()
    addReviews()
    addDLC()
    addConsoleSpecs()
    addGames()
