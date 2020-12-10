import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import java.util.*;

public class Interface extends Application
{
  //  CONSTANTS ================================================================
  private static final String STAGE_TITLE = "Video Game Database";
  private static final int WIDTH = 850; // window x size
  private static final int LENGTH = 500; // window y size

  // Label Titles
  private static final String MAIN_LABEL = "Welcome to the Video Game Database";
  private static final String INSERT_LABEL = "Insert a New Record into any Table";
  private static final String UPDATE_LABEL = "Update a Row of a Table";
  private static final String DELETE_LABEL = "Delete a Record from any Table";
  private static final String EDIT_SCHEMA_LABEL = "Edit Schema:";
  private static final String QUERY_SCHEMA_LABEL = "Query Schema:";
  private static final String INSERT_TABLE_LABEL = "Table";
  private static final String SUCCESS_LABEL = "Success!";

  // Button Titles
  private static final String BACK_MAIN_BUTTON = "Back";
  private static final String INSERT_BUTTON = "Insert a Record";
  private static final String UPDATE_BUTTON = "Update a Row from Table";
  private static final String DELETE_BUTTON = "Delete a Record";
  private static final String GET_TABLE_BUTTON = "Get Table Attributes";
  private static final String GET_KEY_BUTTON = "Get Primary Key";
  private static final String INSERT_RECORD_BUTTON = "Insert Record";
  private static final String DELETE_RECORD_BUTTON = "Delete Record";
  private static final String CONFIRM_INSERT_BUTTON = "Confirm Insert Statement";
  private static final String CONFIRM_DELETE_BUTTON = "Confirm Delete Statement";
  private static final String EXIT_BUTTON = "Exit";

  // Textfield
  private static final String INPUT_TABLE_TEXTFIELD_INSERT = "Games";
  private static final String INPUT_TABLE_TEXTFIELD_DELETE = "Franchises";

  // Errors
  private static final String INVALID_TABLE_ERROR = "The inputted table does not exist. Please enter a valid table.";
  private static final String INVALID_ATTRIBUTE_ERROR = "One or more of the attributes is invalid.";

  //  VARIABLES ================================================================
  private Stage window;
  private Button insertButton, deleteButton, updateButton,
                 backMainButton1, backMainButton2, backMainButton3, getTable,
                 insertRec, confirmInsert, getKey, deleteRec, confirmDelete, exitButton;
  private Scene mainPage, insertPage, updatePage, deletePage;
  private Label mainLabel, insertLabel, updateLabel, deleteLabel, tableLabel, tableLabel3,
                finalInsertLabel, finalDeleteLabel, successLabel, successLabel3,
                editSchemaLabel, querySchemaLabel;
  private TextField tableName, inputTable, inputTable3;
  private GridPane mainGrid, insertGrid, updateGrid, deleteGrid;
  private String table, insertStatement, deleteStatement;
  private int y; // position of the insert record button
  private Alert a;
  private List<String> key;

  //  CLASSES ------------------------------------------------------------------
  public JDBC jdbc;

  //  METHODS ==================================================================
  //  Sets up program in Application as JavaFX, and then call "start"
  public static void main(String[] args) { launch(args); }

  //  Properly exit program and call close window
  public void exitProgram()
  {
    Boolean ans = Close.display("Exit Program", "Are you sure you want to exit?");
    if (ans)
      window.close();
  }

  // Main Functionality Below ==================================================
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    // STUFF TO BE SET UP AT STARTUP ===========================================
    window = primaryStage;
    jdbc = new JDBC();
    a = new Alert();
    window.setOnCloseRequest(e -> {
      e.consume();
      exitProgram();
    });

    // BUTTONS -----------------------------------------------------------------
    // Back to Main Page
    backMainButton1 = new Button(BACK_MAIN_BUTTON);
    backMainButton1.setOnAction(e -> window.setScene(mainPage));

    backMainButton2 = new Button(BACK_MAIN_BUTTON);
    backMainButton2.setOnAction(e -> window.setScene(mainPage));

    backMainButton3 = new Button(BACK_MAIN_BUTTON);
    backMainButton3.setOnAction(e -> window.setScene(mainPage));

    // Go to Insert Page
    insertButton = new Button(INSERT_BUTTON);
    insertButton.setOnAction(e -> window.setScene(insertPage));

    // Go to Update Page
    updateButton = new Button(UPDATE_BUTTON);
    updateButton.setOnAction(e -> window.setScene(updatePage));

    // Go to Delete Page
    deleteButton = new Button(DELETE_BUTTON);
    deleteButton.setOnAction(e -> window.setScene(deletePage));

    // exit Program
    exitButton = new Button(EXIT_BUTTON);
    exitButton.setOnAction(e -> exitProgram());

    // Grab table name from user
    getTable = new Button(GET_TABLE_BUTTON);
    getTable.setOnAction(e -> insertRecord(inputTable.getText()));

    // Grab table name from user to get primary key
    getKey = new Button(GET_KEY_BUTTON);
    getKey.setOnAction(e -> deleteRecord(inputTable3.getText()));

    // Insert new record into table
    insertRec = new Button(INSERT_RECORD_BUTTON);
    insertRec.setOnAction(e -> createInsertStatement());

    // Delete record from table
    deleteRec = new Button(DELETE_RECORD_BUTTON);
    deleteRec.setOnAction(e -> createDeleteStatement());

    // Confirm Insert Record
    confirmInsert = new Button(CONFIRM_INSERT_BUTTON);
    confirmInsert.setOnAction(e -> finalizeInsert());

    // Confirm Delete Record
    confirmDelete = new Button(CONFIRM_DELETE_BUTTON);
    confirmDelete.setOnAction(e -> finalizeDelete());

    // LABELS ------------------------------------------------------------------
    mainLabel = new Label(MAIN_LABEL);
    editSchemaLabel = new Label(EDIT_SCHEMA_LABEL);
    querySchemaLabel = new Label(QUERY_SCHEMA_LABEL);
    insertLabel = new Label(INSERT_LABEL);
    updateLabel = new Label(UPDATE_LABEL);
    deleteLabel = new Label(DELETE_LABEL);
    tableLabel = new Label(INSERT_TABLE_LABEL);
    tableLabel3 = new Label(INSERT_TABLE_LABEL);
    successLabel = new Label(SUCCESS_LABEL);
    successLabel3 = new Label(SUCCESS_LABEL);

    // TEXTFIELD ---------------------------------------------------------------
    inputTable = new TextField();
    inputTable.setPromptText(INPUT_TABLE_TEXTFIELD_INSERT);

    inputTable3 = new TextField();
    inputTable3.setPromptText(INPUT_TABLE_TEXTFIELD_DELETE);

    // GRIDS -------------------------------------------------------------------
    mainGrid = new GridPane();
    mainGrid.setPadding(new Insets(10,10,10,10));
    mainGrid.setVgap(8);
    mainGrid.setHgap(10);

    insertGrid = new GridPane();
    insertGrid.setPadding(new Insets(10,10,10,10));
    insertGrid.setVgap(8);
    insertGrid.setHgap(10);

    deleteGrid = new GridPane();
    deleteGrid.setPadding(new Insets(10,10,10,10));
    deleteGrid.setVgap(8);
    deleteGrid.setHgap(10);

    // Main Page ===============================================================
    GridPane.setConstraints(mainLabel, 1,0,3,1);
    GridPane.setConstraints(editSchemaLabel, 0,1);
    GridPane.setConstraints(insertButton, 0,2);
    GridPane.setConstraints(updateButton, 1,2);
    GridPane.setConstraints(deleteButton, 2,2);
    GridPane.setConstraints(querySchemaLabel, 0,3);
    GridPane.setConstraints(exitButton,0,5);
    mainGrid.setAlignment(Pos.CENTER);
    mainGrid.getChildren().addAll(mainLabel, editSchemaLabel, insertButton, updateButton, deleteButton, querySchemaLabel, exitButton);

    // Insert Page =============================================================
    GridPane.setConstraints(insertLabel, 0,0,3,1);
    GridPane.setHalignment(insertLabel, HPos.CENTER);
    GridPane.setConstraints(backMainButton1, 0,8);
    GridPane.setConstraints(tableLabel, 0,1,1,1);
    GridPane.setHalignment(tableLabel, HPos.RIGHT);
    GridPane.setConstraints(inputTable, 1,1);
    GridPane.setConstraints(getTable, 0,2,2,1);
    GridPane.setConstraints(confirmInsert, 0,7,2,1);
    GridPane.setConstraints(successLabel, 0,3);
    insertGrid.setAlignment(Pos.CENTER);
    insertGrid.getChildren().addAll(insertLabel, tableLabel, inputTable, getTable, backMainButton1);

    // Update Page =============================================================
    updateGrid = new GridPane();
    updateGrid.setPadding(new Insets(10,10,10,10));
    updateGrid.setVgap(8);
    updateGrid.setHgap(10);
    GridPane.setConstraints(updateLabel, 1,0);
    GridPane.setConstraints(backMainButton2, 0,1);
    updateGrid.getChildren().addAll(updateLabel, backMainButton2);

    // Delete Page =============================================================
    GridPane.setConstraints(deleteLabel, 0,0,3,1);
    GridPane.setHalignment(deleteLabel, HPos.CENTER);
    GridPane.setConstraints(tableLabel3, 0,1,1,1);
    GridPane.setHalignment(tableLabel3, HPos.RIGHT);
    GridPane.setConstraints(inputTable3, 1,1);
    GridPane.setConstraints(getKey, 0,2,2,1);
    GridPane.setConstraints(backMainButton3, 0,8);
    GridPane.setConstraints(confirmDelete, 0,7,2,1);
    GridPane.setConstraints(successLabel3, 0,3);
    deleteGrid.setAlignment(Pos.CENTER);
    deleteGrid.getChildren().addAll(deleteLabel, tableLabel3, inputTable3, getKey, backMainButton3);

    // PAGES -------------------------------------------------------------------
    mainPage = new Scene(mainGrid, WIDTH, LENGTH);
    insertPage = new Scene(insertGrid, WIDTH, LENGTH);
    updatePage = new Scene(updateGrid, WIDTH, LENGTH);
    deletePage = new Scene(deleteGrid, WIDTH, LENGTH);

    // STUFF TO BE CALLED ON STARTING
    window.setScene(mainPage);
    window.setTitle(STAGE_TITLE);
    window.show();
  }

  //  INSERT HELP METHODS ======================================================
  //  Shows list of attributes from requested table ----------------------------
  public void insertRecord(String tableName)
  {
    // Clear/refresh the page
    insertGrid.getChildren().clear();
    insertGrid.getChildren().addAll(insertLabel, tableLabel, inputTable, getTable);

    //  Get list of attributes from the table, returned by JDBC
    List<String> list = new ArrayList<String>();
    list = jdbc.getTableAttributes(tableName);

    if(list.get(0) == "-1")
    {
      insertGrid.getChildren().add(backMainButton1); // add last for correct tabbing
      a.display("ERROR", INVALID_TABLE_ERROR);
      return;
    }

    //  Print each attribute onto the page
    int x = 0;
    y = 3;
    for(String s:list)
    {
      TextField tempTextField = new TextField();
      tempTextField.setPromptText(s);
      insertGrid.add(tempTextField, x, y);
      x++;
      // Every 7 attribues move to next row
      if (x==7)
      {
        x = 0;
        y++;
      }
    }
    // Add the insert record button after attributes have been found
    insertGrid.add(insertRec, 0, ++y);
    insertGrid.getChildren().add(backMainButton1); // add last for correct tabbing
  }

  //  Grab values from user and create statement -------------------------------
  public void createInsertStatement()
  {
    insertGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == y);
    insertGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 8);
    // Grab table and create skeleton of insert statement
    table = inputTable.getText();
    insertStatement = String.format("INSERT INTO %s VALUES( ", table);

    // Grab each attribute input from user
    for (Node node : insertGrid.getChildren())
    {
      if(node instanceof TextField && (GridPane.getRowIndex(node) == 3 || GridPane.getRowIndex(node) == 4))
      {
        insertStatement = insertStatement.concat(((TextField)node).getText());
        insertStatement = insertStatement.concat(",");
      }
    }
    // Remove the extra comma at the end and finish the statement
    insertStatement = insertStatement.substring(0, insertStatement.length() - 1);
    insertStatement = insertStatement.concat(");");
    // Show final statement to user
    finalInsertLabel = new Label(insertStatement);
    GridPane.setConstraints(finalInsertLabel, 0,6,7,1);
    insertGrid.getChildren().addAll(finalInsertLabel, confirmInsert, backMainButton1);
  }

  // Last step of inserting record, try to insert and write back result --------
  public void finalizeInsert()
  {
    int success = jdbc.insertIntoDatabase(insertStatement);
    // Clear/refresh the page
    insertGrid.getChildren().clear();
    insertGrid.getChildren().addAll(insertLabel, tableLabel, inputTable, getTable);

    // Print to page if successful or not.
    if (success == 1)
    {
      insertGrid.getChildren().add(successLabel);
      insertGrid.getChildren().add(backMainButton1); // add last for correct tabbing
    }
    else
    {
      insertGrid.getChildren().add(backMainButton1); // add last for correct tabbing
      a.display("ERROR", INVALID_ATTRIBUTE_ERROR);
      return;
    }
  }

  //  DELETE HELP METHODS ======================================================
  //
  public void deleteRecord(String tableName)
  {
    // Clear/refresh the page
    deleteGrid.getChildren().clear();
    deleteGrid.getChildren().addAll(deleteLabel, tableLabel3, inputTable3, getKey);

    key = new ArrayList<String>();
    key = jdbc.getPrimaryKey(tableName);

    if(key.get(0) == "-1")
    {
      deleteGrid.getChildren().add(backMainButton3); // add last for correct tabbing
      a.display("ERROR", INVALID_TABLE_ERROR);
      return;
    }

    //  Print each attribute onto the page
    int x = 0;
    for(String s:key)
    {
      TextField tempTextField = new TextField();
      tempTextField.setPromptText(s);
      deleteGrid.add(tempTextField, x, 3);
      x++;
    }

    // Add the insert record button after attributes have been found
    deleteGrid.add(deleteRec, 0, 4);
    deleteGrid.getChildren().add(backMainButton3); // add last for correct tabbing
  }

  //  Grab values from user and create statement -------------------------------
  public void createDeleteStatement()
  {
    deleteGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 4);
    deleteGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 8);
    // Grab table and create skeleton of insert statement
    table = inputTable3.getText();

    deleteStatement = String.format("DELETE FROM %s WHERE ", table);

    int i = 0;
    // Grab each attribute input from user
    for (Node node : deleteGrid.getChildren())
    {

      if(node instanceof TextField && (GridPane.getRowIndex(node) == 3))
      {
        deleteStatement = deleteStatement.concat(key.get(i));
        deleteStatement = deleteStatement.concat("=");
        deleteStatement = deleteStatement.concat(((TextField)node).getText());
        deleteStatement = deleteStatement.concat(" and ");
        i++;
      }
    }
    deleteStatement = deleteStatement.substring(0, deleteStatement.length() - 5);
    deleteStatement = deleteStatement.concat(";");

    // Show final statement to user
    finalDeleteLabel = new Label(deleteStatement);
    GridPane.setConstraints(finalDeleteLabel, 0,6,7,1);
    deleteGrid.getChildren().addAll(finalDeleteLabel, confirmDelete, backMainButton3);
  }

  // Last step of deleting record, try to delete and write back result ---------
  public void finalizeDelete()
  {
    int success = jdbc.deleteFromDatabase(deleteStatement);
    // Clear/refresh the page
    deleteGrid.getChildren().clear();
    deleteGrid.getChildren().addAll(deleteLabel, tableLabel3, inputTable3, getKey);

    // Print to page if successful or not.
    if (success == 1)
    {
      deleteGrid.getChildren().add(backMainButton3); // add last for correct tabbing
      deleteGrid.getChildren().add(successLabel3);
    }
    else
    {
      deleteGrid.getChildren().add(backMainButton3); // add last for correct tabbing
      a.display("ERROR", INVALID_ATTRIBUTE_ERROR);
      return;
    }
  }
}
