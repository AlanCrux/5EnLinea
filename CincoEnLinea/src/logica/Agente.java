package logica;

import java.util.Arrays;

/**
 *
 * @author Alex Cámara
 */
public class Agente {

    String bando;
    char[][] tablero;
    char[][] visitados = new char[10][10];
    int xPrimerTiro;
    int yPrimerTiro;
    char caracterAgente;
    char caracterUsuario;

    public Agente() {

    }

    public Agente(char[][] tablero) {
        this.tablero = tablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    public void setBando(String bando) {
        this.bando = bando;
        if (bando.equals("Conejo")) {
            caracterAgente = 'c';
            caracterUsuario = 'd';
        } else {
            caracterAgente = 'd';
            caracterUsuario = 'c';
        }
    }

    public boolean esPrimerTiro() {
        int contador = 0;
        boolean bandera = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 'c' || tablero[i][j] == 'd') {
                    xPrimerTiro = i;
                    yPrimerTiro = j;
                    contador++;
                }
            }
        }
        if (contador > 1) {
            bandera = false;
        }
        return bandera;
    }

    public Coordenada buscarConsecutivos(int x, int y) {
        Coordenada coordenadaDeTiro = null;
        coordenadaDeTiro = evaluarHorizontal(x);
        if (coordenadaDeTiro.getX() != -1) {
            return coordenadaDeTiro;
        } else {
            coordenadaDeTiro = evaluarVertical(y);
            if (coordenadaDeTiro.getX() != -1) {
                return coordenadaDeTiro;
            } else {
                coordenadaDeTiro = evaluarDiagonalDerecha(x, y);
                if (coordenadaDeTiro.getX() != -1) {
                    return coordenadaDeTiro;
                } else {
                    //agente.comprobarDiagonalIzquierda(x, y);
                    coordenadaDeTiro = evaluarDiagonalIzquierda(x, y);
                    if (coordenadaDeTiro.getX() != -1) {
                        return coordenadaDeTiro;
                    }
                }
            }
        }
        return coordenadaDeTiro;
    }

    public Coordenada buscarEstrategia() {
        Coordenada coordenadaDeTiro = null;
        Coordenada coordenadaEstrategiaHorizontal = null;
        Coordenada coordenadaEstrategiaVertical = null;
        //int longitud[] = new int[2];
        
        coordenadaDeTiro = evaluarEstrategiaConsecutivaHorizontal();
        /*coordenadaEstrategiaVertical = evaluarEstrategiaConsecutivaVertical();
        
        int maximaLongitud = Math.max(coordenadaEstrategiaHorizontal.getNivel(), coordenadaEstrategiaVertical.getNivel());
        
        if (coordenadaEstrategiaHorizontal.getNivel() == maximaLongitud) {
            coordenadaDeTiro = coordenadaEstrategiaHorizontal;
            System.out.println("horizontal es mas largo");
        } else{
            coordenadaDeTiro = coordenadaEstrategiaVertical;
            System.out.println("vertical es mas largo");
        }*/
        return coordenadaDeTiro;
    }

    public Coordenada evaluarHorizontal(int x) {
        int contador = 0;
        Coordenada coordenadaDeTiro = new Coordenada();
        coordenadaDeTiro.setX(-1);

        for (int i = 0; i < 10; i++) {
            if (tablero[x][i] == caracterUsuario) {
                contador++;
                if ((i == 9 && contador >= 2) && (tablero[x][i - contador]) != caracterAgente) {
                    int resta = (i - contador);
                    coordenadaDeTiro.setX(x);
                    coordenadaDeTiro.setY(resta);
                }

            } else {
                if (contador >= 2) {
                    if (tablero[x][i] != caracterAgente) {
                        coordenadaDeTiro.setX(x);
                        coordenadaDeTiro.setY(i);
                    } else {
                        int resta = (i - contador) - 1;
                        coordenadaDeTiro.setX(x);
                        coordenadaDeTiro.setY(resta);
                    }
                }
                contador = 0;
            }
        }
        return coordenadaDeTiro;
    }

    public Coordenada evaluarVertical(int y) {
        int contador = 0;
        Coordenada coordenadaDeTiro = new Coordenada();
        coordenadaDeTiro.setX(-1);

        for (int i = 0; i < 10; i++) {
            if (tablero[i][y] == caracterUsuario) {
                contador++;
                if ((i == 9 && contador >= 2) && (tablero[i - contador][y]) != caracterAgente) {
                    int resta = (i - contador);
                    coordenadaDeTiro.setX(resta);
                    coordenadaDeTiro.setY(y);
                }

            } else {
                if (contador >= 2) {
                    if (tablero[i][y] != caracterAgente) {
                        coordenadaDeTiro.setX(i);
                        coordenadaDeTiro.setY(y);
                    } else {
                        int resta = (i - contador) - 1;
                        coordenadaDeTiro.setX(resta);
                        coordenadaDeTiro.setY(y);
                    }
                }
                contador = 0;
            }
        }
        return coordenadaDeTiro;
    }

    public Coordenada evaluarDiagonalDerecha(int x, int y) {
        int contador = 0;
        Coordenada coordenadaDeTiro = new Coordenada();
        coordenadaDeTiro.setX(-1);

        while (x != 0 && y != 0) {
            x--;
            y--;
        }

        while (x < 10 && y < 10) {

            if (tablero[x][y] == caracterUsuario) {
                contador++;
                if (((x == 9 && contador >= 2) || (y == 9 && contador >= 2)) && (tablero[x - contador][y + contador] != caracterAgente)) {
                    int restaX = (x - contador);
                    int restaY = (y - contador);
                    coordenadaDeTiro.setX(restaX);
                    coordenadaDeTiro.setY(restaY);
                }

            } else {
                if (contador >= 2) {
                    if (tablero[x][y] != caracterAgente) {
                        coordenadaDeTiro.setX(x);
                        coordenadaDeTiro.setY(y);
                    } else {
                        int restaX = (x - contador) - 1;
                        int restaY = (y - contador) - 1;
                        coordenadaDeTiro.setX(restaX);
                        coordenadaDeTiro.setY(restaY);
                    }
                }
                contador = 0;
            }

            x++;
            y++;

        }
        return coordenadaDeTiro;
    }

    public Coordenada evaluarDiagonalIzquierda(int x, int y) {
        int contador = 0;
        Coordenada coordenadaDeTiro = new Coordenada();
        coordenadaDeTiro.setX(-1);

        while (y < 9 && x > 0) {
            x--;
            y++;
        }

        while (x < 10 && y >= 0) {
            if (tablero[x][y] == caracterUsuario) {
                contador++;
                //comprueba si esta en la ultima casilla de x o de y para colocar en la posicion mas anterior posible un caracter de bloqueo
                if (((x == 9 && contador >= 2) || (y == 0 && contador >= 2)) && (tablero[x - contador][y + contador] != caracterAgente)) {
                    int restaX = (x - contador);
                    int restaY = (y + contador);
                    coordenadaDeTiro.setX(restaX);
                    coordenadaDeTiro.setY(restaY);
                }

            } else {
                if (contador >= 2) {
                    if (tablero[x][y] != caracterAgente) {

                        coordenadaDeTiro.setX(x);
                        coordenadaDeTiro.setY(y);
                    } else {
                        int restaX = (x - contador) - 1;
                        int restaY = (y + contador) + 1;
                        coordenadaDeTiro.setX(restaX);
                        coordenadaDeTiro.setY(restaY);
                    }
                }
                contador = 0;
            }

            x++;
            y--;
        }
        return coordenadaDeTiro;
    }

    public void imprimirArreglo() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(tablero[i][j]);
            }
            System.out.println("");
        }
    }

    public Coordenada evaluarEstrategiaConsecutivaHorizontal() {
        Coordenada coordenadaDeTiro = new Coordenada();
        coordenadaDeTiro.setX(-1);
        int contador = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == caracterAgente) {
                    contador++;
                } else {
                    if (contador >= 2) {
                        if (tablero[i][j] != caracterAgente) {
                            coordenadaDeTiro.setX(i);
                            coordenadaDeTiro.setY(j);
                            coordenadaDeTiro.setNivel(contador);
                        } else {
                            int resta = (j - contador) - 1;
                            coordenadaDeTiro.setX(i);
                            coordenadaDeTiro.setY(resta);
                            coordenadaDeTiro.setNivel(contador);
                        }
                    }
                    contador = 0;
                }
            }
        }
        return coordenadaDeTiro;
    }

    public Coordenada evaluarEstrategiaConsecutivaVertical() {
        Coordenada coordenadaDeTiro = new Coordenada();
        coordenadaDeTiro.setX(-1);
        int contador = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[j][i] == caracterAgente) {
                    contador++;
                } else {
                    if (contador >= 2) {
                        if (tablero[j][i] != caracterAgente) {
                            coordenadaDeTiro.setX(j);
                            coordenadaDeTiro.setY(i);
                            coordenadaDeTiro.setNivel(contador);
                        } else {
                            int resta = (j - contador) - 1;
                            coordenadaDeTiro.setX(resta);
                            coordenadaDeTiro.setY(i);
                            coordenadaDeTiro.setNivel(contador);
                        }
                    }

                    contador = 0;
                }
            }

        }
        return coordenadaDeTiro;
    }

    /*public void evaluarEstrategiaConsecutivaDiagonalDerecha() {
        int contador = 0;
        boolean flag = false;

        int limiteDeX = 9;
        while (limiteDeX >= 4) {

            int y = 0;

            for (int x = limiteDeX; x >= 4; x--) {

                if (tablero[x][y] == caracterUsuario) {
                    contador++;
                    if (contador == 2) {
                        flag = true;
                        System.out.println("diagonal derecha");
                    }
                } else {
                    contador = 0;
                }
                y++;

            }
            limiteDeX--;
        }

        int limiteDeY = 1;

        while (limiteDeY <= 5) {
            int y = limiteDeY;
            int x = 9;
            while (y <= 9) {

                if (tablero[x][y] == caracterUsuario) {
                    contador++;
                    if (contador == 2) {
                        flag = true;
                        System.out.println("diagonal derecha");
                    }
                } else {
                    contador = 0;
                }
                x--;
                y++;
            }
            limiteDeY++;
        }
    }

    public void evaluarEstrategiaConsecutivaDiagonalzquierda() {
        int contador = 0;
        boolean flag = false;

        int limiteDeX = 9;
        while (limiteDeX >= 4) {

            int y = 9;

            for (int x = limiteDeX; x >= 4; x--) {

                if (tablero[x][y] == caracterUsuario) {
                    contador++;
                    if (contador == 2) {
                        flag = true;
                        System.out.println("diagonal izquierda");
                    }
                } else {
                    contador = 0;
                }
                y--;

            }
            limiteDeX--;
        }

        int limiteDeY = 4;

        while (limiteDeY <= 8) {
            int y = limiteDeY;
            int x = 9;
            while (y >= 0) {

                if (tablero[x][y] == caracterUsuario) {
                    contador++;
                    if (contador == 2) {
                        flag = true;
                        System.out.println("diagonal izquierda");
                    }
                } else {
                    contador = 0;
                }
                x--;
                y--;
            }
            limiteDeY++;
        }
    }*/
}
