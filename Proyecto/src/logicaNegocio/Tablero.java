package logicaNegocio;

public class Tablero {
    private char[][] matriz;
    private int TAMANO;

    /**
     * Constructor que recibe el tamaño del tablero como parametro
     * Inicializa la matriz con el tamaño especificado
     * Complejidad Temporal: O(1) Tiempo constante
     */
    public Tablero(int TAMANO) {
        this.TAMANO = TAMANO;
        matriz = new char[TAMANO][TAMANO];
        inicializarMatriz();
    }

    /**
     * Método para obtener la matriz
     *
     * @return Retorna la matriz
     * Complejidad Temporal: O(1) Tiempo constante
     */
    public char[][] getMatriz() {
        return matriz;
    }

    /**
     * Método para obtener el tamaño
     *
     * @return Retorna el tamaño
     * Complejidad Temporal: O(1) Tiempo constante
     */
    public int getTAMANO() {
        return TAMANO;
    }

    /**
     * Se inicia la matriz con asteriscos
     * Complejidad Temporal: O(N^2) Complejidad cuadrática
     */
    public void inicializarMatriz() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                matriz[i][j] = '*';
            }
        }
    }

    /**
     * Se imprime la matriz
     * Complejidad Temporal: O(N^2) Complejidad cuadrática
     */
    public void imprimirMatriz() {
        System.out.println("Matriz con barcos:");
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }


    /*
  Creamos un metodo boolean que se llama adicionar barco, alli nos ayudara
  a psoscionar el barco dentro de la matriz y sino nos retorna un false, evaluando los diferentes casos
   */
    public boolean adicionarBarco(int f1, int c1, int tipoBarco) {

        if (!posicionValida(f1) || !posicionValida(c1)) {
            System.out.println("Posición por fuera de la matriz");
            return false;
        }
     /*
      Entra a cada uno de los casos
      */
        switch (tipoBarco) {
            case 0:
                return barco1(f1, c1, matriz);
            case 1:
                return barco2(f1, c1, matriz);
            case 2:
                return barco3(f1, c1, matriz);
            case 3:
                return barco4(f1, c1, matriz);
            default:
                System.out.println("Barco inválido");
                return false;


        }

    }

    /*
    Nos ayuda a validar si la posicion del barco este dentro de la matriz
     */
    private boolean posicionValida(int coordenada) {
        return (coordenada >= 0 && coordenada < TAMANO);
    }
    /*
    Nos ayuda a posicionar el barco1 en la matriz con los parametros dados
     */
    private boolean barco1(int f1, int c1, char[][] matriz) {
        int fila = f1;
        int columna = c1;
        if (matriz[fila][columna] != '*') {
            System.out.println("Casilla ocupada");
            return false;
        }

        char barco = 'D';
        matriz[fila][columna] = barco;
        return true;
    }
    /*
    Nos ayuda a posicionar el barco2 en la matriz con los parametros dados
     */
    private boolean barco2(int f1, int c1, char[][] matriz) {
        int fila = f1;
        int columna = c1;
        if (fila > TAMANO - 2) {
            System.out.println("Espacio insuficiente");
            return false;
        }
        if (matriz[fila][columna] != ' ' || matriz[fila + 1][columna] != ' ') {
            System.out.println("Casilla ocupada");
            return false;
        }
        char barco = 'A';
        matriz[fila][columna] = barco;
        matriz[fila + 1][columna] = barco;
        return true;
    }
    /*
    Nos ayuda a posicionar el barco3 en la matriz con los parametros dados
     */
    private boolean barco3(int f1, int c1, char[][] matriz) {
        int fila = f1;
        int columna = c1;
        if (columna > TAMANO - 3) {
            System.out.println("Espacio insuficiente");
            return false;
        }
        if (matriz[fila][columna] != ' ' || matriz[fila][columna + 1] != ' ' || matriz[fila][columna + 2] != '*') {
            System.out.println("Casilla ocupada");
            return false;
        }
        char barco = 'C';
        matriz[f1][c1] = barco;
        matriz[f1][c1 + 1] = barco;
        matriz[f1][c1 + 2] = barco;
        return true;

    }

    /*
    Nos ayuda a posicionar el barco4 en la matriz con los parametros dados
     */
    private boolean barco4(int f1, int c1, char[][] matriz) {
        int fila = f1;
        int columna = c1;
        if (columna > TAMANO - 4) {
            System.out.println("Espacio insuficiente");
            return false;
        }
        if (matriz[fila][columna] != ' ' || matriz[fila][columna + 1] != ' ' || matriz[fila][columna + 2] != ' ' || matriz[fila][columna + 3] != ' ') {
            System.out.println("Casilla ocupada");
            return false;
        }

        char barco = 'F';
        matriz[fila][columna] = barco;
        matriz[fila][columna + 1] = barco;
        matriz[fila][columna + 2] = barco;
        matriz[fila][columna + 3] = barco;
        return true;

    }


    public boolean disparos (int filaUsuario, int columnaUsuario) {
        if (filaUsuario >= 0 && filaUsuario < TAMANO && columnaUsuario >= 0 && columnaUsuario < TAMANO) {
            char objeto = matriz[filaUsuario][columnaUsuario];
            if (objeto == '/') {
                System.out.println("Usted ya disparo aqui!");
                return false;
            }
            if (objeto == '.') {
                System.out.println("Usted ya disparo aqui!");
                return false;//Devuelve false si dispara en una coordenada que ya disparo
            }
            if (objeto != '*') {
                System.out.println("¡Hay un barco en esa coordenada! (" + objeto + ")");
                matriz[filaUsuario][columnaUsuario] = '/';

                return true; // Devuelve true si se acertó un barco
            }
            else {
                System.out.println("No hay un barco en esa coordenada.");
                matriz[filaUsuario][columnaUsuario] = '.';
            }
        } else {
            System.out.println("Coordenada fuera de rango.");
        }
        return false; // Devuelve false si no se acertó un barco o la coordenada estaba fuera de rango
    }
    public boolean todosBarcosDestruidos() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (matriz[i][j] == 'A' && matriz[i][j] == 'B' && matriz[i][j] == 'C' && matriz[i][j] == 'D'){
                    return false;
                }
            }
        }
        return true;
    }
}