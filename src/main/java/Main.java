import java.util.Scanner;

public class Main {

    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Gerador gerador = new Gerador(keyboard);
        gerador.mainLoop();
        keyboard.close();
    }
}
