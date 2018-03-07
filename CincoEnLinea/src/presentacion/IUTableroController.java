package presentacion;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import presentacion.utilerias.Mensajes;

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

  private int RELOJ_TURNO = 15;
  private final int LIMITE_TURNO = 15; 

  private final String SONIDO_FIN_TURNO = "/recursos/sonidos/timer_3beeps.mp3";
  private final String FICHA_CONEJO = "/recursos/iconos/conejo.png";
  private final String FICHA_DINOSAURIO = "/recursos/iconos/dinosaurio.png";
  private final String FICHA_CONEJO_TRANSPARENTE = "/recursos/iconos/conejo_transparente.png";
  private final String FICHA_DINOSAURIO_TRANSPARENTE = "/recursos/iconos/dinosaurio_transparente.png";
  
  private boolean turno; //Si es falso es dinosaurio, si es verdadero es conejo. 
  ControlTablero control = new ControlTablero();
  
  private Timeline cronometro; 
  /*Para cuando se implemente inteligencia, el usuario selecciona su ficha.
   *Por ahora recibe la entrada de la primera ficha. */
  
  private String fichaUsuario = "Dinosaurio"; //Almacena la ficha elegida por el jugador
  private String jugador1; //Almacena el nombre del jugador
  
  private final String jugadorPC = "IA PC";
  /**
   * Inicializa los componentes del tablero.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
        
    turno = fichaUsuario.equals("Conejo"); //Si es conejo el turno va a iniciar como true
    if (turno) {
      imageTurno.setImage(new Image(FICHA_CONEJO));
    } else{
      imageTurno.setImage(new Image(FICHA_DINOSAURIO));
    } 
    iniciarCronometro();
    tfTurno.setText(jugador1);
    hpRendirse.setOnAction(event->{
      rendirse(turno);
    });
  }

  @FXML
  public void ponerFicha(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();

    Integer x = GridPane.getColumnIndex(ficha);
    Integer y = GridPane.getRowIndex(ficha);
    
    if (x == null) {
      x = 0;
    }
    if (y == null) {
      y = 0;
    }
    
    String estatusTablero = control.agregarPosición(y, x, turno);
    
    switch(estatusTablero){
      case "Conejo":
      case "Dinosaurio":
        mostrarGanador(estatusTablero);
        break;
      case "empate":
        Mensajes.displayConfirmationAlert("Fin del juego", "¡Empate!");
        break;
      default:
 
        if (turno) {
          ficha.setImage(new Image(FICHA_CONEJO));
          cambiarDatosTurno(turno);
          turno = false;
        } else {
          ficha.setImage(new Image(FICHA_DINOSAURIO));
          cambiarDatosTurno(turno);
          turno = true;
        }
        RELOJ_TURNO = LIMITE_TURNO;
        ficha.setDisable(true);
    }

  }
  
  @FXML
  public void efectoMouseSobre(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    if (!ficha.isDisable()) {
      if (turno) {
        ficha.setImage(new Image(FICHA_CONEJO_TRANSPARENTE));
      } else {
        ficha.setImage(new Image(FICHA_DINOSAURIO_TRANSPARENTE));
      }
    }

  }

  @FXML
  public void efectoMouseFuera(MouseEvent event) {
    ImageView ficha = (ImageView) event.getSource();
    if (!ficha.isDisable()) {
      ficha.setImage(null);
    }
  }

  public void iniciarCronometro() {
    cronometro = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (RELOJ_TURNO > 9) {
          lbTiempo.setText("00:" + RELOJ_TURNO);
          lbTiempo.setTextFill(Color.web("black"));
        } else if(RELOJ_TURNO > 0){
          lbTiempo.setText("00:0" + RELOJ_TURNO);
          lbTiempo.setTextFill(Color.web("#EC7063"));
        } else {
          cambiarDatosTurno(turno);
          Mensajes.showNotification("Se acabo el tiempo", "Perdiste tu turno", Pos.CENTER);
          turno = !turno;
          RELOJ_TURNO = LIMITE_TURNO + 1;
        }
        RELOJ_TURNO--;
      }
    }));

    cronometro.setCycleCount(Timeline.INDEFINITE);
    cronometro.play();
  }
  
  public void reiniciarTablero(){
    
  }
  
  public void reproducirSonido(){
     try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource(SONIDO_FIN_TURNO);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
  }
  
  /*public void reproducirSonido(String musicFile) {
    Media sound = null;
    try {
      sound = new Media(new File(musicFile).toURI().toURL().toExternalForm());
    } catch (MalformedURLException ex) {
      Logger.getLogger(IUTableroController.class.getName()).log(Level.SEVERE, null, ex);
    }
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.play();
  }*/
  
  /**
   * Si el turno es verdadero cambia la imagen a dinosaurio, de lo contrario la cambia a conejo.
   * @param turno 
   */
  public void cambiarDatosTurno(boolean turno){
    if (turno) {
      imageTurno.setImage(new Image(FICHA_DINOSAURIO));
    } else {
      imageTurno.setImage(new Image(FICHA_CONEJO));
    }
    if (tfTurno.getText().equals(jugador1)) {
      tfTurno.setText(jugadorPC);
    } else {
      tfTurno.setText(jugador1);
    }
  }
  
  public void rendirse(boolean turno){
    if (turno) {
      mostrarGanador("Dinosaurio");
    } else {
      mostrarGanador("Conejo");
    }
  }
  
  public void mostrarGanador(String ganador) {
    cronometro.stop();
    if (ganador.equals(fichaUsuario)) {
      Mensajes.displayConfirmationAlert("Fin del juego", "El ganador es: " + jugador1);
    }else{
      Mensajes.displayConfirmationAlert("Fin del juego", "El ganador es: " + jugadorPC);
    }
    //reiniciarTablero();
    Stage stage = (Stage) tfTurno.getScene().getWindow();
    stage.close();
  }

  public String getFichaUsuario() {
    return fichaUsuario;
  }

  public void setFichaUsuario(String fichaUsuario) {
    this.fichaUsuario = fichaUsuario;
  }

  public String getJugador1() {
    return jugador1;
  }

  public void setJugador1(String jugador1) {
    this.jugador1 = jugador1;
  }

  /**
   * Muestra la ventana asociada a un loader. 
   * @param loader FXMLLloader del cual se quiere mostrar la ventana.
   */
  public void abrirTablero(FXMLLoader loader){
    try {
      Stage stagePrincipal = new Stage();
      Parent root = (Parent) loader.load();
      Scene scene = new Scene(root);
      stagePrincipal.setScene(scene);
      stagePrincipal.setResizable(false);
      stagePrincipal.show();
    } catch (IOException ex) {
      Logger.getLogger(IUTableroController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
