import java.util.Scanner;

class Tournament {
    private static int m, n, k, l, c;
    private Scanner scanner;

    void startTournament() {
        int result = 0;
        System.out.println("Введите m, n, k, l - количество игроков, c - количество кругов");
        readIn();
        int[] table = new int[l];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < l; j++) {
                for (int p = j + 1; p < l; p++) {
                    if (j != p) {
                        final Game game = new Game(false, new HumanPlayer(), new RandomPlayer(m, n));
                        result = game.play(new TicTacToeBoardPro(m, n, k));
                        switch (result) {
                            case 1:
                                System.out.println("Player " + (j + 1) + " wins this round!");
                                table[j] += 3;
                                break;
                            case 2:
                                System.out.println("Player " + (p + 1) + " wins this round!");
                                table[p] += 3;
                                break;
                            case 0:
                                System.out.println("Draw");
                                table[j]++;
                                table[p]++;
                                break;
                        }

                    }
                }
            }
        }
        for (int i = 0; i < l; i++) {
            System.out.println("Player " + i + ": " + table[i]);
        }
    }

    private void readIn() {
        try {
            scanner = new Scanner(System.in);
            m = Integer.parseInt(myNextInt());
            n = Integer.parseInt(myNextInt());
            k = Integer.parseInt(myNextInt());
            l = Integer.parseInt(myNextInt());
            c = Integer.parseInt(myNextInt());
        } catch (Exception e) {
            System.out.println("Введенные данные некорректны, пожалуйста, вводите числа");
            readIn();
        }
    }

    private String myNextInt() {
        if (scanner.hasNext()) {
            return String.valueOf(scanner.nextInt());
        } else {
            System.out.println("Конец ввода");
            System.exit(0);
        }
        return "";
    }
}
