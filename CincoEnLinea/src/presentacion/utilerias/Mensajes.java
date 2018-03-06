package presentacion.utilerias;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Miguel Alejandro Cámara Árciga
 */
public class Mensajes {

  private static final String BUTTONACEPT = "buttonAccept"; 
  
  private Mensajes() {
  
  }
  
  /**
   * Muestra una alerta de tipo advertencia
   * @param title
   * @param message 
   */
  public static void displayWarningAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING, message);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.show();
  }
  
  /**
   * Muestra una alerta del tipo Confirmación
   * @param title
   * @param message 
   */
  public static void displayConfirmationAlert(String title, String message) {
    ButtonType btAccept = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, btAccept);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.showAndWait();
  }
  
  /**
   * Muestra una alerta personalizada 
   * @param title
   * @param message 
   */
  public static void displayInformation(String title, String message) {
    Stage primaryStage = new Stage();
    StackPane stackPane = new StackPane();
    stackPane.setStyle("-fx-background-color: #0F1F38;");
    JFXDialogLayout content = new JFXDialogLayout();
    content.setHeading(new Text(title));
    content.setBody(new Text(message));

    JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

    JFXButton button = new JFXButton("Okay");
    button.setOnAction((ActionEvent event) -> {
      dialog.close();
      primaryStage.close();
    });
    content.setActions(button);

    Scene scene = new Scene(stackPane);

    primaryStage.setScene(scene);
    primaryStage.initStyle(StageStyle.TRANSPARENT);
    dialog.show();
    primaryStage.show();
  }
  
  public static void showNotification(String title, String text, Pos pos) {
	Notifications notification;
	notification = Notifications.create().title(title).text(text).hideAfter(Duration.seconds(1.5)).position(pos);
	notification.show();
  }
  
    /**
   * Muestra una alerta con la opcion de ingresar información
   * @param title
   * @param content
   * @return 
   */
  public static String displayTextInputDialog(String title, String content) {
	TextInputDialog dialog = new TextInputDialog("");
	dialog.setTitle(title);
	dialog.setContentText(content);
	dialog.setHeaderText(null);

	Optional<String> result = dialog.showAndWait();
	if (result.isPresent()) {
	  if (!result.get().trim().isEmpty()) {
		return result.get();
	  } else{
		return "";
	  }
	} else {
	  return ""; 
	}
  }
 
}
