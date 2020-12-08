import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Interface extends Application
{
  //  CONSTANTS ----------------------------------------------------------------
  public static final String STAGE_TITLE = "Video Game Database";

  // Label Titles
  public static final String MAIN_LABEL = "Welcome to the Video Game Database";

  // Button Titles
  public static final String INSERT_BUTTON = "Insert a Record";
  public static final String BACK_MAIN_BUTTON = "Back";

  //  VARIABLES ----------------------------------------------------------------
  Button insertButton, deleteButton, updateButton, backMainButton;
  Stage window;
  Scene mainPage, page2;
  Label mainLabel;

  //  METHODS ==================================================================
  // Sets up program in Application as JavaFX, and then call "start"
  public static void main(String[] args) { launch(args); }

  // Main Functionality
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    window = primaryStage;

    // Main Setup --------------------------------------------------------------
    mainLabel = new Label(MAIN_LABEL);
    insertButton = new Button(INSERT_BUTTON);
    insertButton.setOnAction(e -> window.setScene(page2));

    VBox layout1 = new VBox(20);
    layout1.getChildren().addAll(mainLabel, insertButton);
    mainPage = new Scene(layout1, 800, 600);

    // Page2 Setup -------------------------------------------------------------

    backMainButton = new Button(BACK_MAIN_BUTTON);
    backMainButton.setOnAction(e -> window.setScene(mainPage));

    StackPane layout2 = new StackPane();
    layout2.getChildren().add(backMainButton);
    page2 = new Scene(layout2, 800, 600);

    window.setScene(mainPage);
    window.setTitle(STAGE_TITLE);
    window.show();
  }
}
