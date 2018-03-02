package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Controlador del tablero
 *
 * @author Alan Yoset García C
 */
public class IUTableroController implements Initializable {

    @FXML
    private Label lbTiempo;
    @FXML
    private TextField tfTurno;
    @FXML
    private GridPane gridTablero;
    @FXML
    private ImageView imageTurno;
    @FXML
    private Hyperlink hpRendirse;

    /**
     * Inicializa los componentes del tablero. 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    @FXML
    void ponerFicha(ActionEvent event) {
        Button ficha = (Button) event.getSource();
        ficha.setStyle("-fx-background-color: #85C1E9;");
        ficha.setDisable(true);
    }
    
    
}
