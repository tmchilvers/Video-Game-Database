import sqlite3
from sqlite3 import Error
import editTables

''' CONSTANTS ---------------------------------------------'''
DATABASE = r"gameDB.db"

''' MAIN FUNCTIONS ---------------------------------------------'''
# Let chooser choose what to do with database
def startUp():
    print("\nPlease select a function below:\n")
    print("    0. To EXIT program.")
    print("    1. Create the database: 'gameDB.db'")
    print("    2. Add table to database")
    print("    3. Drop table from database")
    print("    4. Insert record into a table")
    print("    5. Delete record from a table")
    print("    6. Execute a query\n")
    ans = input()
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
        create_table_sql = input()
        editTables.input_sql_command(conn, create_table_sql)
    else:
        print("Error! Cannot create the database connection.")

# 3. Drop a table
def dropTable():
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter just the name of the table below:")
        table = input()
        editTables.drop_table(conn, table)
    else:
        print("Error! Cannot create the database connection.")

# 4. Insert a record into a table
def insertRecord():
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter the insert SQL command and data below:")
        insert_command = input()
        editTables.input_sql_command(conn, insert_command)
    else:
        print("Error! Cannot create the database connection.")

# 5. Delete a record into a Table
def deleteRecord():
    conn = editTables.create_connection(DATABASE)
    if conn is not None:
        print("Please enter the delete SQL command and data below:")
        delete_command = input()
        editTables.input_sql_command(conn, delete_command)
    else:
        print("Error! Cannot create the database connection.")

''' MAIN ---------------------------------------------'''
def main():
    print("Hello! This is an interface for the Video Game Database using Sqlite3.")
    while(1):
        startUp()

if __name__ == '__main__':
    main()
