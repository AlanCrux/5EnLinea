package logica;

/**
 * @author Miguel Alejandro Cámara
 * @author Alan Yoset García C
 */
public class ControlTablero {

  char conejo = 'c';
  char dinosaurio = 'd';
  char[][] tablero = new char[10][10];
  int LONGITUD_TABLERO = 10;

  public void bloquearCelda(int x, int y) {

  }

  public String agregarPosición(int x, int y, boolean turno) {
    String ganador;
    if (turno) {
      tablero[x][y] = conejo;
      ganador = "Conejo";
    } else {
      tablero[x][y] = dinosaurio;
      ganador = "Dinosaurio";
    }

    boolean ganadorHorizontal = comprobarPosiciónHorizontal(x);
    boolean ganadorVertical = comprobarPosiciónVertical(y);
    boolean ganadorDiagonalDerecha = comprobarPosicionDiagonalDerecha(x, y);
    boolean ganadorDiagonalIzquierda = comprobarPosicionDiagonalIzquierda(x, y);
    boolean hayEmpate = comprobarMatrizVacía();

    if ((ganadorHorizontal || ganadorVertical || ganadorDiagonalDerecha || ganadorDiagonalIzquierda) && !hayEmpate) {
      return ganador; 
    } else  if (hayEmpate) {
      return "empate";
    } else {
      return "@"; 
    }
  }

  public boolean comprobarPosiciónHorizontal(int x) {
    boolean ganador = false;
    int contadorConejos = 0;
    int contadorDinosaurios = 0;

    for (int i = 0; i < LONGITUD_TABLERO; i++) {
      if (tablero[x][i] == conejo) {
        contadorConejos++;
        if (contadorConejos == 5) {
          ganador = true;
        }
      } else {
        contadorConejos = 0;
      }

      if (tablero[x][i] == dinosaurio) {
        contadorDinosaurios++;
        if (contadorDinosaurios == 5) {
          ganador = true;
        }
      } else {
        contadorDinosaurios = 0;
      }
    }

    return ganador;
  }

  public boolean comprobarPosiciónVertical(int y) {
    boolean ganador = false;
    int contadorConejos = 0;
    int contadorDinosaurios = 0;

    for (int i = 0; i < LONGITUD_TABLERO; i++) {
      if (tablero[i][y] == conejo) {
        contadorConejos++;
        if (contadorConejos == 5) {
          ganador = true;
        }
      } else {
        contadorConejos = 0;
      }

      if (tablero[i][y] == dinosaurio) {
        contadorDinosaurios++;
        if (contadorDinosaurios == 5) {
          ganador = true;
        }
      } else {
        contadorDinosaurios = 0;
      }
    }

    return ganador;
  }

  public boolean comprobarPosicionDiagonalDerecha(int x, int y) {
    boolean ganador = false;
    int contadorConejos = 0;
    int contadorDinosaurios = 0;

    while (x != 0 && y != 0) {
      x--;
      y--;
    }

    while (x < 10 && y < 10) {

      if (tablero[x][y] == conejo) {
        contadorConejos++;
        if (contadorConejos == 5) {
          ganador = true;
        }
      } else {
        contadorConejos = 0;
      }

      if (tablero[x][y] == dinosaurio) {
        contadorDinosaurios++;
        if (contadorDinosaurios == 5) {
          ganador = true;
        }
      } else {
        contadorDinosaurios = 0;
      }

      x++;
      y++;
    }
    return ganador;
  }

  public boolean comprobarPosicionDiagonalIzquierda(int x, int y) {
    boolean ganador = false;
    int contadorConejos = 0;
    int contadorDinosaurios = 0;

    while (y < 9 && x > 0) {
      x--;
      y++;
    }

    while (x < 10 && y >= 0) {
      if (tablero[x][y] == conejo) {
        contadorConejos++;
        if (contadorConejos == 5) {
          ganador = true;
        }
      } else {
        contadorConejos = 0;
      }

      if (tablero[x][y] == dinosaurio) {
        contadorDinosaurios++;
        if (contadorDinosaurios == 5) {
          ganador = true;
        }
      } else {
        contadorDinosaurios = 0;
      }

      x++;
      y--;
    }
    return ganador;
  }
  
  public boolean comprobarMatrizVacía(){
    boolean vacío = true;
    
    for (int i = 0; i < LONGITUD_TABLERO; i++) {
      for (int j = 0; j < LONGITUD_TABLERO; j++) {
        if (!Character.isAlphabetic(tablero[i][j])) {
          vacío = false;
        }
      }
    }
    return vacío;
  }
}
