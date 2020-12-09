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
  private static final String UPDATE_LABEL = "Update a Record from any Table";
  private static final String DELETE_LABEL = "Delete a row from Table";
  private static final String INSERT_TABLE_LABEL = "Table";
  private static final String SUCCESS_LABEL = "Success!";

  // Button Titles
  private static final String BACK_MAIN_BUTTON = "Back";
  private static final String INSERT_BUTTON = "Insert a Record";
  private static final String UPDATE_BUTTON = "Update a Record";
  private static final String DELETE_BUTTON = "Delete a Row from Table";
  private static final String GET_TABLE_BUTTON = "Get Table Attributes";
  private static final String INSERT_RECORD_BUTTON = "Insert Record";
  private static final String CONFIRM_INSERT_BUTTON = "Confirm Insert Statement";

  // Textfield
  private static final String INPUT_TABLE_TEXTFIELD = "Games";

  // Errors
  private static final String INVALID_TABLE_ERROR = "The inputted table does not exist. Please enter a valid table.";
  private static final String INVALID_ATTRIBUTE_ERROR = "One or more of the attributes is invalid.";

  //  VARIABLES ================================================================
  private Stage window;
  private Button insertButton, deleteButton, updateButton;
  private Button backMainButton1, backMainButton2, backMainButton3;
  private Button getTable, insertRec, confirmInsert;
  private Scene mainPage, insertPage, updatePage, deletePage;
  private Label mainLabel, insertLabel, updateLabel, deleteLabel, tableLabel;
  private Label finalInsertLabel, successLabel;
  private TextField tableName, inputTable;
  private GridPane mainGrid, insertGrid, updateGrid, deleteGrid;
  private String table, insertStatement;
  private int y; // position of the insert record button
  private Alert a;

  //  CLASSES ------------------------------------------------------------------
  public JDBC jdbc;

  //  METHODS ==================================================================
  //  Sets up program in Application as JavaFX, and then call "start"
  public static void main(String[] args) { launch(args); }

  // Main Functionality Below ==================================================
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    // STUFF TO BE SET UP AT STARTUP ===========================================
    window = primaryStage;
    jdbc = new JDBC();
    a = new Alert();

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

    // Grab table name from user
    getTable = new Button(GET_TABLE_BUTTON);
    getTable.setOnAction(e -> insertRecord(inputTable.getText()));

    // Insert new record into table
    insertRec = new Button(INSERT_RECORD_BUTTON);
    insertRec.setOnAction(e -> createInsertStatement());

    // Confirm Insert Record
    confirmInsert = new Button(CONFIRM_INSERT_BUTTON);
    confirmInsert.setOnAction(e -> finalizeInsert());

    // LABELS ------------------------------------------------------------------
    mainLabel = new Label(MAIN_LABEL);
    insertLabel = new Label(INSERT_LABEL);
    updateLabel = new Label(UPDATE_LABEL);
    deleteLabel = new Label(DELETE_LABEL);
    tableLabel = new Label(INSERT_TABLE_LABEL);
    successLabel = new Label(SUCCESS_LABEL);

    // TEXTFIELD ---------------------------------------------------------------
    inputTable = new TextField();
    inputTable.setPromptText(INPUT_TABLE_TEXTFIELD);

    // GRIDS -------------------------------------------------------------------
    mainGrid = new GridPane();
    mainGrid.setPadding(new Insets(10,10,10,10));
    mainGrid.setVgap(8);
    mainGrid.setHgap(10);

    insertGrid = new GridPane();
    insertGrid.setPadding(new Insets(10,10,10,10));
    insertGrid.setVgap(8);
    insertGrid.setHgap(10);

    // ALERTS ------------------------------------------------------------------

    // Main Page ===============================================================
    GridPane.setConstraints(mainLabel, 1,0,3,1);
    GridPane.setConstraints(insertButton, 0,1);
    GridPane.setConstraints(updateButton, 1,1);
    GridPane.setConstraints(deleteButton, 2,1);
    mainGrid.setAlignment(Pos.CENTER);
    mainGrid.getChildren().addAll(mainLabel, insertButton, updateButton, deleteButton);

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
    deleteGrid = new GridPane();
    deleteGrid.setPadding(new Insets(10,10,10,10));
    deleteGrid.setVgap(8);
    deleteGrid.setHgap(10);
    GridPane.setConstraints(deleteLabel, 1,0);
    GridPane.setConstraints(backMainButton3, 0,1);
    deleteGrid.getChildren().addAll(deleteLabel, backMainButton3);

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

  //  INSERT HELP METHOD =======================================================
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
      insertGrid.getChildren().add(successLabel);
    else
    {
      insertGrid.getChildren().add(backMainButton1); // add last for correct tabbing
      a.display("ERROR", INVALID_ATTRIBUTE_ERROR);
      return;
    }
  }
}
