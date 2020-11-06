import sqlite3
from sqlite3 import Error
import editTables

''' CONSTANTS ---------------------------------------------'''
DATABASE = r"gameDB.db"

''' MAIN FUNCTIONS ---------------------------------------------'''
# Let chooser choose what to do with database
def startUp():
    print("-----------------------------------")
    print("\nPlease select a function below:\n")
    print("    0. To EXIT program.")
    print("    1. Create the database: 'gameDB.db'")
    print("    2. Add table to database")
    print("    3. Drop table from database")
    print("    4. Insert record into a table")
    print("    5. Delete record from a table")
    print("    6. Update record from a table")
    print("    7. Execute a query\n")
    ans = input('> ')
    chooseFunc(ans)

# Find function chosen by user
def chooseFunc(_ans):
    if _ans == '0':
        print("Quitting Program.")
        exit()

    elif _ans == '1':
        createDatabase()

    elif _ans == '2':
        createTable()

    elif _ans == '3':
        dropTable()

    elif _ans == '4':
        insertRecord()

    elif _ans == '5':
        deleteRecord()

    elif _ans == '6':
        updateRecord()

    elif _ans == '7':
        chooseQuery()

    else:
        print("INVALID INPUT")

''' DATABASE FUNCTIONS ---------------------------------------------'''
# 1. Create the video game database
def createDatabase():
    editTables.create_connection(DATABASE)
    print("Database Created")

# 2. Create a table
def createTable():
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter the SQL table create command below:")
        create_table_sql = input('> ')
        editTables.input_sql_command(conn, create_table_sql)
    else:
        print("Error! Cannot create the database connection.")

# 3. Drop a table
def dropTable():
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter just the name of the table below:")
        table = input('> ')
        editTables.drop_table(conn, table)
    else:
        print("Error! Cannot create the database connection.")

# 4. Insert a record into a table
def insertRecord():
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter the insert SQL command and data below:")
        insert_command = input('> ')
        editTables.input_sql_command(conn, insert_command)
    else:
        print("Error! Cannot create the database connection.")

# 5. Delete a record into a Table
def deleteRecord():
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter the table name below:")
        table = input('> ')
        print("Please enter the primary key value below ('primaryKey=1'):")
        value = input('> ')
        delete_command = ('DELETE FROM %s WHERE %s' % (table, value))
        editTables.input_sql_command(conn, delete_command)
    else:
        print("Error! Cannot create the database connection.")

# 6. Insert a record into a table
def updateRecord():
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter the update SQL command and data below:")
        update_command = input('> ')
        editTables.input_sql_command(conn, update_command)
    else:
        print("Error! Cannot create the database connection.")

# 7. Choose a query
def chooseQuery():
    conn = editTables.create_connection(DATABASE)
    print("-----------------------------------")
    print("\nPlease choose a query below:\n")
    print("    1. Shows global sales by Franchise")
    print("    2. Shows studios founded in each year")
    print("    3. Shows games grouped by genre")
    print("    4. Shows the games each developer has worked on")
    print("    5. Returns world record for each game")
    query = input('> ')
    if conn is not None:
        if query == '1':
            query_command = 'SELECT g.title,f.name,s.global from Games g natural join Sales s natural join Franchises f;'
        elif query == '2':
            query_command = 'SELECT name, yearFounded from Studios GROUP BY yearFounded;'
        elif query == '3':
            query_command = 'SELECT g2.name, g1.title from Games g1 natural join Genres g2 GROUP BY g2.name;'
        elif query == '4':
            query_command = 'SELECT d.name, g.title from Developers d natural join Games g GROUP BY d.name;'
        elif query == '5':
            query_command = 'SELECT r.title, r.player, r.bestTime from Records r;'
        else:
            print("INVALID INPUT")
        editTables.input_sql_command(conn, query_command)
    else:
        print("Error! Cannot create the database connection.")

''' MAIN ---------------------------------------------'''
def main():
    print("Hello! This is an interface for the Video Game Database using Sqlite3.")
    while(1):
        startUp()

if __name__ == '__main__':
    main()