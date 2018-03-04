package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logica.ControlTablero;

/**
 * Controlador del tablero
 *
 * @author Miguel Alejandro Cámara
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
    
    private int DURACION_TURNO = 30; 
    private boolean turno; 
    
    private String fichaUno; //Para guardar la selección de ficha del jugador uno.
    private String fichaDos; //Para guardar la selección de ficha del jugador dos.
    
    ControlTablero control = new ControlTablero();
    
    /**
     * Inicializa los componentes del tablero. 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarCronometro();
    }  
    
  @FXML
  public void ponerFicha(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    Node nodo = (Node) event.getSource();

    Integer x = GridPane.getColumnIndex(nodo);
    Integer y = GridPane.getRowIndex(nodo);

    //-----PARCHE FEO :V -- por alguna razón los métodos anteriores dan null cuando deberían dar 0
    if (x == null) {
      x = 0;
    }
    if (y == null) {
      y = 0;
    }
    
    control.agregarPosición(y, x, turno);
    
    if (turno) {
      ficha.setImage(new Image("/recursos/iconos/conejo.png"));
      turno = false;
    } else {
      ficha.setImage(new Image ("/recursos/iconos/dinosaurio.png"));
            turno = true;
        }
        
        ficha.setDisable(true);
    }
    
    @FXML
    public void efectoMouseSobre(MouseEvent event) {
        ImageView ficha = (ImageView) event.getSource();
        if (!ficha.isDisable()) {
            if (turno) {
                ficha.setImage(new Image("/recursos/iconos/conejo_transparente.png"));
            } else {
                ficha.setImage(new Image("/recursos/iconos/dinosaurio_transparente.png"));
            }
        }

    }
    
    @FXML
    public void efectoMouseFuera(MouseEvent event){  
        ImageView ficha = (ImageView) event.getSource();
        if (!ficha.isDisable()) {
            ficha.setImage(null);
        }
    }
    
    public void iniciarCronometro() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (DURACION_TURNO > 9) {
                    lbTiempo.setText("00:"+DURACION_TURNO);
                } else {
                   lbTiempo.setText("00:0"+DURACION_TURNO); 
                   lbTiempo.setTextFill(Color.web("#EC7063"));
                }                
                DURACION_TURNO--; 
            }
        }));
        
        fiveSecondsWonder.setCycleCount(DURACION_TURNO+1);
        fiveSecondsWonder.play();
    }
    
}
