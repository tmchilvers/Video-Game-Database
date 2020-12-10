import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

// CREDIT TO THENEWBOSTON FOR THE ALERT.JAVA CLASS *****************************

public class Close
{
  private static Boolean ans;

  public static Boolean display(String title, String message)
  {
    Stage window = new Stage();

    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMinWidth(250);

    Label label = new Label();
    label.setText(message);
    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    yesButton.setOnAction(e -> {
      ans = true;
      window.close();
    });
    noButton.setOnAction(e -> {
      ans = false;
      window.close();
    });

    VBox layout = new VBox(10);
    layout.setPadding(new Insets(25, 50, 25, 50));
    layout.getChildren().addAll(label, yesButton, noButton);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();

    return ans;
  }

  private static void setAns(Boolean response)
  {
    ans = response;
  }

}
