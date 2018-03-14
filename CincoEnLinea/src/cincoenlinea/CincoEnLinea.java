package cincoenlinea;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Miguel Alejandro Cámara
 * @author Alan Yoset García C
 */
public class CincoEnLinea extends Application {
    private Stage stagePrincipal;
    
    @Override
    public void start(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
	showMainWindows();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void showMainWindows() {
        AnchorPane rootPane = new AnchorPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentacion/IUBando.fxml"));

        try {
            rootPane = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CincoEnLinea.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(rootPane);

        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }
}
