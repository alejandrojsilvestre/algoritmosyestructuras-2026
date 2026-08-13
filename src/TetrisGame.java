import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TetrisGame {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    private final int[][] board = new int[HEIGHT][WIDTH];
    private final Scanner scanner;
    private final Random random = new Random();
    private final List<int[][]> pieces = Arrays.asList(
            new int[][]{{1, 1, 1, 1}},
            new int[][]{{1, 1}, {1, 1}},
            new int[][]{{1, 1, 1}, {0, 0, 1}},
            new int[][]{{1, 1, 1}, {1, 0, 0}},
            new int[][]{{0, 1, 1}, {1, 1, 0}},
            new int[][]{{1, 1, 0}, {0, 1, 1}},
            new int[][]{{1, 1, 1}, {0, 1, 0}}
    );

    private int[][] currentPiece;
    private int row;
    private int col;
    private boolean gameOver;

    public TetrisGame(Scanner scanner) {
        this.scanner = scanner;
    }

    public void iniciar() {
        limpiarTablero();
        gameOver = false;
        System.out.println("\n=== TETRIS === 2026!!!");
        System.out.println("Controles: a=izquierda, d=derecha, s=abajo, w=rotar, q=salir");

        while (!gameOver) {
            if (currentPiece == null) {
                crearNuevaPieza();
                if (gameOver) {
                    break;
                }
            }

            mostrarTablero();
            System.out.print("Acción: ");
            String accion = scanner.nextLine().trim().toLowerCase();

            switch (accion) {
                case "a":
                    mover(0, -1);
                    break;
                case "d":
                    mover(0, 1);
                    break;
                case "s":
                    mover(1, 0);
                    break;
                case "w":
                    rotar();
                    break;
                case "q":
                    System.out.println("Saliendo del Tetris...");
                    return;
                case "":
                    mover(1, 0);
                    break;
                default:
                    System.out.println("Acción no reconocida.");
            }

            if (!gameOver && !mover(1, 0)) {
                fijarPieza();
                eliminarLineas();
                currentPiece = null;
            }
        }

        mostrarTablero();
        System.out.println("Juego terminado. Presiona Enter para volver al menú.");
    }

    private void limpiarTablero() {
        for (int i = 0; i < HEIGHT; i++) {
            Arrays.fill(board[i], 0);
        }
    }

    private void crearNuevaPieza() {
        currentPiece = clonarPieza(pieces.get(random.nextInt(pieces.size())));
        row = 0;
        col = (WIDTH - currentPiece[0].length) / 2;
        if (!puedeColocar(currentPiece, row, col)) {
            gameOver = true;
        }
    }

    private void mostrarTablero() {
        System.out.println();
        for (int r = 0; r < HEIGHT; r++) {
            System.out.print("|");
            for (int c = 0; c < WIDTH; c++) {
                boolean ocupado = board[r][c] == 1;
                if (!ocupado && currentPiece != null) {
                    for (int pr = 0; pr < currentPiece.length; pr++) {
                        for (int pc = 0; pc < currentPiece[pr].length; pc++) {
                            if (currentPiece[pr][pc] == 1 && r == row + pr && c == col + pc) {
                                ocupado = true;
                                break;
                            }
                        }
                        if (ocupado) {
                            break;
                        }
                    }
                }
                System.out.print(ocupado ? "#" : ".");
            }
            System.out.println("|");
        }
        System.out.println("+----------+");
    }

    private boolean mover(int deltaRow, int deltaCol) {
        if (currentPiece == null) {
            return false;
        }

        if (puedeColocar(currentPiece, row + deltaRow, col + deltaCol)) {
            row += deltaRow;
            col += deltaCol;
            return true;
        }
        return false;
    }

    private void rotar() {
        if (currentPiece == null) {
            return;
        }

        int[][] rotada = rotarMatriz(currentPiece);
        if (puedeColocar(rotada, row, col)) {
            currentPiece = rotada;
        }
    }

    private void fijarPieza() {
        if (currentPiece == null) {
            return;
        }

        for (int r = 0; r < currentPiece.length; r++) {
            for (int c = 0; c < currentPiece[r].length; c++) {
                if (currentPiece[r][c] == 1) {
                    int boardRow = row + r;
                    int boardCol = col + c;
                    if (boardRow >= 0 && boardRow < HEIGHT && boardCol >= 0 && boardCol < WIDTH) {
                        board[boardRow][boardCol] = 1;
                    }
                }
            }
        }
    }

    private void eliminarLineas() {
        for (int r = HEIGHT - 1; r >= 0; r--) {
            boolean completa = true;
            for (int c = 0; c < WIDTH; c++) {
                if (board[r][c] == 0) {
                    completa = false;
                    break;
                }
            }
            if (completa) {
                for (int rr = r; rr > 0; rr--) {
                    System.arraycopy(board[rr - 1], 0, board[rr], 0, WIDTH);
                }
                Arrays.fill(board[0], 0);
                r++;
            }
        }
    }

    private boolean puedeColocar(int[][] pieza, int fila, int columna) {
        for (int r = 0; r < pieza.length; r++) {
            for (int c = 0; c < pieza[r].length; c++) {
                if (pieza[r][c] == 0) {
                    continue;
                }

                int nuevaFila = fila + r;
                int nuevaColumna = columna + c;
                if (nuevaFila < 0 || nuevaFila >= HEIGHT || nuevaColumna < 0 || nuevaColumna >= WIDTH) {
                    return false;
                }
                if (board[nuevaFila][nuevaColumna] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] clonarPieza(int[][] pieza) {
        int[][] copia = new int[pieza.length][];
        for (int i = 0; i < pieza.length; i++) {
            copia[i] = Arrays.copyOf(pieza[i], pieza[i].length);
        }
        return copia;
    }

    private int[][] rotarMatriz(int[][] pieza) {
        int filas = pieza.length;
        int columnas = pieza[0].length;
        int[][] rotada = new int[columnas][filas];

        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                rotada[c][filas - 1 - r] = pieza[r][c];
            }
        }
        return rotada;
    }
}
