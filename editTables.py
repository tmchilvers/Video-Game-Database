import sqlite3
from sqlite3 import Error


def create_connection(db_file):
    """ create a database connection to the SQLite database
        specified by db_file
    :param db_file: database file
    :return: Connection object or None
    """
    conn = None
    try:
        conn = sqlite3.connect(db_file)
        return conn
    except Error as e:
        print(e)

    return conn


def create_table(conn, create_table_sql):
    """ create a table from the create_table_sql statement
    :param conn: Connection object
    :param create_table_sql: a CREATE TABLE statement
    :return:
    """
    try:
        c = conn.cursor()
        c.execute(create_table_sql)
        conn.commit()
    except Error as e:
        print(e)

def drop_table(conn, table):
    """ drop a table from the create_table_sql statement
    :param conn: Connection object
    :param create_table_sql: a DROP TABLE statement
    :return:
    """

    drop_table_sql = "DROP TABLE IF EXISTS " + table + ";"
    print(drop_table_sql)

    try:
        c = conn.cursor()
        c.execute(drop_table_sql)
        conn.commit()
    except Error as e:
        print(e)

def insert_record(conn, insert_command):
    """ insert a record with insert_command statement
    :param conn: Connection object
    :param insert_command: a INSERT TABLE statement
    :return:
    """
    try:
        c = conn.cursor()
        c.execute(insert_command)
        conn.commit()
    except Error as e:
        print(e)
