package logicaNegocio;

public class Jugador {

    private Tablero tablero;
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tablero = new Tablero(7);

    }

    public Tablero getTablero() {
        return tablero;
    }

    /*
           Creamos un array de mensajes
           */
    private String[] mensajes = {
            "Ingrese la coordenada del barco %d (fila columna) tenga en cuenta que el barco ocupa 1 casilla",
            "Ingrese la coordenada del barco %d (fila columna) tenga en cuenta que el barco ocupa 1 casilla",
            "Ingrese la coordenada del barco %d (fila columna) tenga en cuenta que el barco ocupa 2 casillas verticalmente",
            "Ingrese la coordenada del barco %d (fila columna) tenga en cuenta que el barco ocupa 2 casillas verticalmente",
            "Ingrese la coordenada del barco %d (fila columna) tenga en cuenta que el barco ocupa 3 casillas horizontalmente",
            "Ingrese la coordenada del barco %d (fila columna) tenga en cuenta que el barco ocupa 4 casillas horizontalmente"
    };
    /*
    Creamos un arreglo de tipoBarcos para que el switch evalue cada caso
     */
    private int[] tiposBarcos = {0, 0, 1, 1, 2, 3};

    private int tamBarcos = tiposBarcos.length;

    public int[] getTiposBarcos() {
        return tiposBarcos;
    }

    public int getTamBarcos() {
        return tamBarcos;
    }

    public String[] getMensajes() {
        return mensajes;
    }

    public boolean colocarBarcos(int filaUsuario, int columnaUsuario) {
        boolean posicionValida = false;
        int f1= filaUsuario;
        int c1= columnaUsuario;
        /*
        Creamos un ciclo for para hacer un recorrido de tipo barcos
        */
        for (int i = 0; i < 6; i++) {
            int tipoBarco = getTiposBarcos()[i];
            /*
            y dentro del ciclo for creamos un ciclo do-while donde le va a mostar
            al usuario el mensaje para que ingrese la coordenada del barco y después le
            a pintar la matriz con el barco, pero si posicion valida esta fuera de la matriz
            nos va a retornar un falso, y se va arepetir hasta que posicion valida sea verdadero.
             */

            do {
                System.out.print(String.format(getMensajes()[i], i + 1));
                System.out.println("Barco ubicado en la coordenada (" + f1 + "," + c1 + ")");
                posicionValida = tablero.adicionarBarco(f1, c1, tipoBarco);
                tablero.imprimirMatriz();

            } while (!posicionValida);
            break;
        }
        return posicionValida;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean disparar(Jugador oponente, String nombre, int jugador,int filaUsuario, int columnaUsuario) {
        System.out.println("disparo " + nombre);
        boolean disparoAcertado = false;
        switch (jugador) {
            case 1:
                System.out.print("Ingrese una coordenada para disparar (fila columna): ");
                disparoAcertado = oponente.tablero.disparos(filaUsuario, columnaUsuario);
                oponente.tablero.imprimirMatriz();
                return todosBarcosDestruidos(disparoAcertado);

            case 2:
                System.out.print("Ingrese una coordenada para disparar (fila columna): ");
                disparoAcertado = oponente.tablero.disparos(filaUsuario, columnaUsuario);
                oponente.tablero.imprimirMatriz();
                return todosBarcosDestruidos(disparoAcertado);
        }
        return todosBarcosDestruidos(disparoAcertado);
    }
    public boolean todosBarcosDestruidos (boolean disparoAcertado) {
        int barcosRestantes = 13;
        boolean jugadorPerdio = false;

        /*
        se crea el siguiente bucle para que el juego continue si los barcos restantes son mayores a 0.
        complejidad temporal: O(N) Tiempo Lineal.
        */
        while(barcosRestantes >0) {
            if (disparoAcertado == true) {
                barcosRestantes--;

                if (barcosRestantes == 0) {
                    System.out.println("¡Has hundido todos los barcos!");
                    tablero.imprimirMatriz();
                    jugadorPerdio = true;
                    return jugadorPerdio;

                }
            }
            tablero.imprimirMatriz();

        }

        return jugadorPerdio;
    }

    public boolean haPerdido() {
        return tablero.todosBarcosDestruidos();

    }
}