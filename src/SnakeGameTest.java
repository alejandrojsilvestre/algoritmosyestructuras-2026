import java.util.Scanner;

public class SnakeGameTest {
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame(new Scanner("d\n"));
        if (game == null) {
            throw new AssertionError("SnakeGame no pudo inicializarse");
        }
        System.out.println("Prueba de SnakeGame OK");
    }
}
