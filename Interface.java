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
import java.util.*;

public class Interface extends Application
{
  //  CONSTANTS ================================================================
  public static final String STAGE_TITLE = "Video Game Database";
  public static final int WIDTH = 800; // window x size
  public static final int LENGTH = 500; // window y size

  // Label Titles
  public static final String MAIN_LABEL = "Welcome to the Video Game Database";
  public static final String INSERT_LABEL = "Insert a New Record into any Table";
  public static final String UPDATE_LABEL = "Update a Record from any Table";
  public static final String DELETE_LABEL = "Delete a row from Table";
  public static final String INSERT_TABLE_LABEL = "Table";

  // Button Titles
  public static final String BACK_MAIN_BUTTON = "Back";
  public static final String INSERT_BUTTON = "Insert a Record";
  public static final String UPDATE_BUTTON = "Update a Record";
  public static final String DELETE_BUTTON = "Delete a Row from Table";
  public static final String GET_TABLE_BUTTON = "Get Table Attributes";

  // Textfield
  public static final String INPUT_TABLE_TEXTFIELD = "Games";

  //  VARIABLES ================================================================
  public Stage window;
  public Button insertButton, deleteButton, updateButton;
  public Button backMainButton1, backMainButton2, backMainButton3;
  public Button getTable;
  public Scene mainPage, insertPage, updatePage, deletePage;
  public Label mainLabel, insertLabel, updateLabel, deleteLabel, tableLabel;
  public TextField tableName, inputTable;
  public GridPane mainGrid, insertGrid, updateGrid, deleteGrid;

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
    getTable.setOnAction(e -> setList(inputTable.getText()));

    // LABELS ------------------------------------------------------------------
    mainLabel = new Label(MAIN_LABEL);
    insertLabel = new Label(INSERT_LABEL);
    updateLabel = new Label(UPDATE_LABEL);
    deleteLabel = new Label(DELETE_LABEL);
    tableLabel = new Label(INSERT_TABLE_LABEL);

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

    // Main Page ===============================================================
    GridPane.setConstraints(mainLabel, 1,0);
    GridPane.setConstraints(insertButton, 0,1);
    GridPane.setConstraints(updateButton, 1,1);
    GridPane.setConstraints(deleteButton, 2,1);
    mainGrid.getChildren().addAll(mainLabel, insertButton, updateButton, deleteButton);

    // Insert Page =============================================================
    GridPane.setConstraints(insertLabel, 1,0);
    GridPane.setConstraints(backMainButton1, 0,8);
    GridPane.setConstraints(tableLabel, 0,1);
    GridPane.setConstraints(inputTable, 1,1);
    GridPane.setConstraints(getTable, 0,2);
    insertGrid.getChildren().addAll(insertLabel, tableLabel, inputTable, getTable, backMainButton1);

    // Update Page =============================================================
    updateGrid = new GridPane();
    updateGrid.setPadding(new Insets(10,10,10,10));
    updateGrid.setVgap(8);
    updateGrid.setHgap(10);
    GridPane.setConstraints(updateLabel, 1,0);
    GridPane.setConstraints(backMainButton2, 0,1);
    updateGrid.getChildren().addAll(updateLabel, backMainButton2);

    // Delete Page
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
  //  Shows list of attributes from requested table
  public void setList(String tableName)
  {
    //  Remove any attributes listed on the page
    insertGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 3);
    insertGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 4);

    //  Get list of attributes from the table, returned by JDBC
    List<String> list = new ArrayList<String>();
    list = jdbc.getTableAttributes(tableName);

    //  Print each attribute onto the page
    int x = 0, y = 3;
    for(String s:list)
    {
      insertGrid.add(new Label(s), x, y);
      x++;
      // Every 8 attribues move to next row
      if (x==8)
      {
        x = 0;
        y++;
      }
    }
  }

  
}
