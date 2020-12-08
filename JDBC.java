//STEP 1. Import required packages
import java.sql.*;
import java.util.*;

public class JDBC {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost/cpsc408_2290022_2288893?serverTimezone=UTC";

   //  Database credentials
   static final String USER = "jdbcuser";
   static final String PASS = "jdbc";

   public List<String> list;

   public List<String> getTableAttributes(String tableName)
   {
     list = new ArrayList<String>();

     Connection conn = null;
     Statement stmt = null;
     try{
        //  Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //  Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        //  Execute SQL Statments
        System.out.println("Creating statement...");
        stmt = conn.createStatement();

        //  Grab attributes from table
        String sql;
        sql = String.format("SHOW COLUMNS FROM %s", tableName);
        ResultSet rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd  = rs.getMetaData();
        Integer columnsNumber = rsmd .getColumnCount();

        //  Extract data from result set
        while(rs.next()) {
          String columnValue = rs.getString(1);
          list.add(columnValue);
        }

        //  Clean-up environment
        rs.close();
        stmt.close();
        conn.close();

     }catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
     }catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
     }finally{
        //finally block used to close resources
        try{
           if(stmt!=null)
              stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
           if(conn!=null)
              conn.close();
        }catch(SQLException se){
           se.printStackTrace();
        }//end finally try
     }//end try
     return list;
  }//end main
}//end FirstExample
