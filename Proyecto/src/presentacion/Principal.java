package presentacion;
import logicaNegocio.Jugador;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jugador jugador1 = new Jugador("jugador 1");

        for (int i = 0; i < jugador1.getTamBarcos(); i++) {
            int tipoBarco = jugador1.getTiposBarcos()[i];
            boolean barcoColocado;

            do {
                System.out.print(String.format(jugador1.getMensajes()[i], i + 1));
                int filaUsuario = scanner.nextInt();
                int columnaUsuario = scanner.nextInt();

                System.out.println("Barco ubicado en la coordenada (" + filaUsuario + "," + columnaUsuario + ")");
                barcoColocado = jugador1.colocarBarcos(filaUsuario, columnaUsuario);
                jugador1.getTablero().imprimirMatriz();

            } while (!barcoColocado);

        }
        Jugador jugador2 = new Jugador("jugador 2");

        for (int i = 0; i < jugador2.getTamBarcos(); i++) {
            int tipoBarco = jugador2.getTiposBarcos()[i];
            boolean barcoColocado;

            do {
                System.out.print(String.format(jugador2.getMensajes()[i], i + 1));
                int filaUsuario = scanner.nextInt();
                int columnaUsuario = scanner.nextInt();

                System.out.println("Barco ubicado en la coordenada (" + filaUsuario + "," + columnaUsuario + ")");
                barcoColocado = jugador1.colocarBarcos(filaUsuario, columnaUsuario);
                jugador1.getTablero().imprimirMatriz();

            } while (!barcoColocado);


        }

        while (!jugador1.haPerdido() && !jugador2.haPerdido()) {
            int filaUsuario = scanner.nextInt();
            int columnaUsuario = scanner.nextInt();
            System.out.println("DISPARO JUGADOR 1");
            System.out.print("Ingrese una coordenada (fila columna): ");
            System.out.println("Ha disparado en la coordenada (" + filaUsuario + "," + columnaUsuario + ")");
            jugador1.disparar(jugador2, "jugador 1", 1, filaUsuario, columnaUsuario);
            if (jugador2.haPerdido()) {
                System.out.println("¡" + jugador1.getNombre() + " ha ganado!");

            }

            System.out.println("DISPARO JUGADOR 2");
            System.out.print("Ingrese una coordenada (fila columna): ");
            System.out.println("Ha disparado en la coordenada (" + filaUsuario + "," + columnaUsuario + ")");
            jugador2.disparar(jugador1, "jugador 2", 2, filaUsuario, columnaUsuario);
            if (jugador1.haPerdido()) {
                System.out.println("¡" + jugador2.getNombre() + " ha ganado!");

            }
        }
    }
}
