package logica;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Alex Cámara
 */
public class Agente {
    String bando;
    char[][] tablero;
    char[][] visitados;
    Queue<Coordenada> cola = new LinkedList<>();
    
    public Agente(){
        
    }
    
    public Agente(char[][] tablero){
        this.tablero = tablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }
    
    public void setBando(String bando) {
        this.bando = bando;
    }
    
    public boolean esPrimerTiro(){
        int contador = 0;
        boolean bandera = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 'c' || tablero[i][j] == 'd') {
                    
                }
            }
        }
    }
}
