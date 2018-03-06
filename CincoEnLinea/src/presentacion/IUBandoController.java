package presentacion;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import presentacion.utilerias.Mensajes;

/**
 * FXML Controller class
 *
 * @author alancrux
 */
public class IUBandoController implements Initializable {

  @FXML
  private JFXButton botonCerezas;
  @FXML
  private JFXButton botonHoja;
  @FXML
  private JFXButton botonCarne;
  @FXML
  private JFXButton botonIniciar;
  
  private String nombreJugador = ""; 
  private String bando; 
  

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    botonHoja.setOnAction(event->{
      bando = "Conejo"; 
      botonIniciar.setDisable(false);
    });
    
    botonCarne.setOnAction(event->{
      bando = "Dinosaurio";
      botonIniciar.setDisable(false);
    });
    
    botonCerezas.setOnAction(event->{
      nombreJugador = Mensajes.displayTextInputDialog("Jugador", "Ingresa un nombre de jugador");
      System.out.println(nombreJugador);
    });
    
    botonIniciar.setOnAction(event->{
      iniciarPartida(nombreJugador, bando);
    });
  }  
  
  public void iniciarPartida(String jugador, String bando){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("IUTablero.fxml"));
    IUTableroController controller = new IUTableroController();
    loader.setController(controller);
    controller.setFichaUsuario(bando);
    System.out.println(jugador);
    if (jugador.equals("")) {
      controller.setJugador1("Jugador 1");
    }else {
      controller.setJugador1(jugador);
    }
    
    controller.abrirTablero(loader);
  }
}
