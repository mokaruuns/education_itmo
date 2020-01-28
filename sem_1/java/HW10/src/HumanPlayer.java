import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private Scanner in;
    private int x = 0, y = 0;

    private HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            readIn();
            final Move move = new Move(x, y, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }

    private void readIn() {
        in = new Scanner(System.in);
        try {
            x = Integer.parseInt(myNextInt());
            y = Integer.parseInt(myNextInt());
        } catch (Exception e) {
            System.out.println("Введенные данные некорректны, пожалуйста, вводите числа");
            readIn();
        }
    }

    private String myNextInt() {
        if (in.hasNext()) {
            return String.valueOf(in.nextInt());
        } else {
            System.out.println("Конец ввода");
            System.exit(0);
        }
        return "";
    }
}
