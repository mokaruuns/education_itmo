import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;
    private static int m, n;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer(int m, int n) {
        this(new Random());
        RandomPlayer.m = m;
        RandomPlayer.n = n;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
