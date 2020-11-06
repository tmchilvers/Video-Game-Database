import sqlite3
from sqlite3 import Error


def create_connection(db_file):
    """ create a database connection to the SQLite database"""
    conn = None
    try:
        conn = sqlite3.connect(db_file)
        return conn
    except Error as e:
        print(e)
    return conn


def input_sql_command(conn, sql_command):
    """ takes a general sql command"""
    try:
        c = conn.cursor()
        conn.execute("PRAGMA foreign_keys = on")
        c.execute(sql_command)
        conn.commit()

        # Read the data from the query
        rows = c.fetchall()
        for row in rows:
            print(row)

    except Error as e:
        print(e)

def drop_table(conn, table):
    """ drop a table from the create_table_sql statement"""
    drop_table_sql = "DROP TABLE IF EXISTS " + table + ";"
    print(drop_table_sql)

    try:
        c = conn.cursor()
        conn.execute("PRAGMA foreign_keys = on")
        c.execute(drop_table_sql)
        conn.commit()
    except Error as e:
        print(e)
