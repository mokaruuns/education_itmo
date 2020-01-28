import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoardPro implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    private final Cell[][] cells;
    private Cell turn;
    private int countEmpty;
    private int m, n, k;

    TicTacToeBoardPro(int n, int m, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        countEmpty = n * m;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        int x = move.getRow();
        int y = move.getColumn();
        cells[x][y] = move.getValue();
        countEmpty--;
        if (countElements(x, y, 0, 1) + countElements(x, y, 0, -1) + 1 >= k // горизонталь
                || countElements(x, y, 1, 0) + countElements(x, y, -1, 0) + 1 >= k // вертикаль
                || countElements(x, y, 1, 1) + countElements(x, y, -1, -1) + 1 >= k // побочная диагональ
                || countElements(x, y, 1, -1) + countElements(x, y, -1, 1) + 1 >= k // главная диагональ)
        ) {
            return Result.WIN;
        }
        if (countEmpty <= 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }

    private int countElements(int x, int y, int i, int j) {
        x += i;
        y += j;
        int countElement = 0;
        while (x >= 0 && x < m && y >= 0 && y < n && cells[x][y] == turn) {
            countElement++;
            x += i;
            y += j;
        }
        return countElement;
    }
}
