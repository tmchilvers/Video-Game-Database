import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application
{
  //  CONSTANTS ----------------------------------------------------------------
  public static final String STAGE_TITLE = "Video Game Database";
  public static final int WIDTH = 800; // window x size
  public static final int LENGTH = 600; // window y size

  // Label Titles
  public static final String MAIN_LABEL = "Welcome to the Video Game Database";


  // Button Titles
  public static final String BACK_MAIN_BUTTON = "Back";


  //  VARIABLES ----------------------------------------------------------------
  public Stage window;
  public Button insertButton, deleteButton, updateButton, backMainButton;
  public Scene mainPage, insertPage, updatePage, deletePage;
  public Label mainLabel, insertLabel, updateLabel, deleteLabel;

  //  METHODS ==================================================================
  // Sets up program in Application as JavaFX, and then call "start"
  public static void main(String[] args) { launch(args); }

  // Main Functionality
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    // STUFF TO BE SET UP AT STARTUP ===========================================
    window = primaryStage;

    // BUTTONS -----------------------------------------------------------------
    // Back to Main Page
    backMainButton = new Button(BACK_MAIN_BUTTON);
    backMainButton.setOnAction(e -> window.setScene(mainPage));

    // LABELS ------------------------------------------------------------------
    mainLabel = new Label(MAIN_LABEL);

    // LAYOUTS -----------------------------------------------------------------
    VBox layout1 = new VBox(20);
    layout1.getChildren().addAll(mainLabel, backMainButton);

    // PAGES -------------------------------------------------------------------
    mainPage = new Scene(layout1, WIDTH, LENGTH);

    // STUFF TO BE CALLED ON STARTING
    window.setScene(mainPage);
    window.setTitle(STAGE_TITLE);
    window.show();
  }
}
