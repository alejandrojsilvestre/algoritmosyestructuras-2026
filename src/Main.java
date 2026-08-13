import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer numero = null; // null = aún no se cargó ningún número
        boolean salir = false;

        while (!salir) {
            mostrarMenu(numero);
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    numero = cargarNumero(scanner);
                    break;
                case "2":
                    mostrarNumero(numero);
                    break;
                case "3":
                    TetrisGame juego = new TetrisGame(scanner);
                    juego.iniciar();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu(Integer numero) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("MENÚ PRINCIPAL");
        System.out.println("==============");
        System.out.println();
        System.out.println("Número cargado: " + (numero == null ? "(ninguno)" : numero));
        System.out.println();
        System.out.println("1. Cargar número");
        System.out.println("2. Mostrar número");
        System.out.println("3. Jugar Tetris");
        System.out.println("4. Salir");
    }

    private static Integer cargarNumero(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese un número entero: ");
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opción incorrecta");
            }
        }
    }

    private static void mostrarNumero(Integer numero) {
        if (numero == null) {
            System.out.println("No hay número cargado");
        } else {
            System.out.println("Número cargado: " + numero);
        }
    }
}
