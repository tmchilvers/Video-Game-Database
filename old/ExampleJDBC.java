//STEP 1. Import required packages
import java.sql.*;

public class ExampleJDBC {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost/cpsc408?serverTimezone=UTC";

   //  Database credentials
   static final String USER = "jdbcuser";
   static final String PASS = "jdbc";

   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute SQL Statments
      System.out.println("Creating statement...");
      stmt = conn.createStatement();

      // Create Table Department Managers---------------------------------------
      String createTable = "CREATE TABLE IF NOT EXISTS DeptManagers( "
      + "ID INT NOT NULL PRIMARY KEY,"
      + "fName VARCHAR(255), "
      + "lName VARCHAR(255), "
      + "Salary INT NOT NULL, "
      + "Department VARCHAR(255))";
      boolean tableExec = stmt.execute(createTable);
      System.out.print("CreateTable: " + tableExec + "\n");

      // Insert into table Department Managers----------------------------------
      String insert = "INSERT IGNORE INTO DeptManagers VALUES "
      + "(01, 'Bob', 'Smith', 140000, 'Marketing'), "
      + "(02, 'Tim', 'Foo', 140000, 'Finance'), "
      + "(03, 'Joe', 'Fong', 140000, 'Human Resource'), "
      + "(04, 'Billy', 'Blake', 140000, 'IT'), "
      + "(05, 'Jake', 'Earb', 140000, 'Operations Management'), "
      + "(06, 'Matthew', 'Linstead', 140000, 'Research and Development'), "
      + "(07, 'David', 'Burns', 140000, 'Production'), "
      + "(08, 'Derek', 'Foo II', 140000, 'Sales'), "
      + "(09, 'Rene', 'Gade', 140000, 'Administration'), "
      + "(10, 'Erik', 'Snowden', 140000, 'Security')";
      int rows = stmt.executeUpdate(insert);
      System.out.println("Rows inserted into Table: " + rows);

      // Queries into Department Managers---------------------------------------
      String sql;
      sql = "SELECT ID, fName, lName, Salary, Department FROM DeptManagers";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("ID");
         String fName = rs.getString("fName");
         String lName = rs.getString("lName");
         int salary = rs.getInt("Salary");
         String department = rs.getString("Department");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", First Name: " + fName);
         System.out.print(", Last Name: " + lName);
         System.out.print(", Salary: " + salary);
         System.out.println(", Department: " + department);
      }

      //PREPARED STATEMENTS:----------------------------------------------------
      //Update row in Department Managers
      String updateStatement = "update ignore DeptManagers set fname=?, lname=?, salary=?, department=? where id=?";
      PreparedStatement preparedStatement = conn.prepareStatement(updateStatement);
      preparedStatement.setString(1, "Monty");
      preparedStatement.setString(2, "Python");
      preparedStatement.setInt(3, 180000);
      preparedStatement.setString(4, "CEO");
      preparedStatement.setInt(5, 1);
      int rowsUpdated = preparedStatement.executeUpdate();
      System.out.println("Rows Updated: " + rowsUpdated);

      // Query in Department Managers-------------------------------------------
      String queryStatement = "select * from DeptManagers where fname=? and lname=?";
      preparedStatement = conn.prepareStatement(queryStatement);
      preparedStatement.setString(1, "Tim");
      preparedStatement.setString(2, "Foo");
      rs = preparedStatement.executeQuery();

      // Print out data in rowsUpdated
      while(rs.next()){
        //Retrieve by column name
        int id  = rs.getInt("ID");
        String fName = rs.getString("fName");
        String lName = rs.getString("lName");
        int salary = rs.getInt("Salary");
        String department = rs.getString("Department");

        //Display values
        System.out.print("ID: " + id);
        System.out.print(", First Name: " + fName);
        System.out.print(", Last Name: " + lName);
        System.out.print(", Salary: " + salary);
        System.out.println(", Department: " + department);
      }

      // Delete data from Department Managers-----------------------------------
      String deleteStatement = "delete ignore from DeptManagers where id=?";
      preparedStatement = conn.prepareStatement(deleteStatement);
      preparedStatement.setInt(1, 1);
      int rowsDeleted = preparedStatement.executeUpdate();
      System.out.println("Rows Deleted: " + rowsDeleted);

      //STEP 6: Clean-up environment
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
   System.out.println("Goodbye!");
}//end main
}//end FirstExample
