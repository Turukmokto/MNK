package MNK;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class NMKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.N, ' '
    );
    private final int n;
    private final int m;
    private final int k;
    protected int emptyNumber;
    protected int extraNumber;
    protected int digits;
    protected boolean availableMoves = false;

    protected final Cell[][] cells;
    private Cell turn;

    public NMKBoard(int m, int n, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        digits = Math.max(readDigits(n - 1), readDigits(m - 1));
        emptyNumber = n * m;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public NMKBoard(int m, int n, int k, int extraNum) {
        this(m, n, k);
        this.extraNumber = extraNum;
        availableMoves = true;
    }

    private int readDigits(int x) {
        int ans = 0;
        if (x == 0) {
            return 1;
        }
        while (x > 0) {
            ans++;
            x /= 10;
        }
        return ans;
    }

    public int getN() {
        return this.n;
    }

    public int getM() {
        return this.m;
    }

    public int getK() {
        return this.k;
    }


    public Position getPosition() {
        return new ProxyPosition(this);
    }

    public Cell getCell() {
        return turn;
    }

    boolean commonCheck(int sz, Move move) {
        return (numberOfCells(move, -1, 0) + numberOfCells(move, 1, 0) - 1 >= sz
                || numberOfCells(move, 0, -1) + numberOfCells(move, 0, 1) - 1 >= sz
                || numberOfCells(move, -1, -1) + numberOfCells(move, 1, 1) - 1 >= sz
                || numberOfCells(move, 1, -1) + numberOfCells(move, -1, 1) - 1 >= sz);
    }

    public Result doMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        emptyNumber--;

        if (commonCheck(k, move)) {
            return Result.WIN;
        }
        if (emptyNumber == 0) {
            return Result.DRAW;
        }
        if (availableMoves && commonCheck(extraNumber, move)) {
            return Result.NEW_MOVE;
        }
        if (turn == Cell.X) {
            turn = Cell.O;
        } else {
            turn = Cell.X;
        }
        return Result.UNKNOWN;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    private int numberOfCells(Move move, int deltaX, int deltaY) {
        int ans = 0, x = move.getRow(), y = move.getColumn();
        while (x >= 0 && y >= 0 && x < n && y < m && cells[x][y] == move.getValue()) {
            ans++;
            x += deltaX;
            y += deltaY;
        }
        return ans;
    }

    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    private void addNum(int x, StringBuilder sb) {
        for (int i = 0; i < digits - readDigits(x); i++) {
            sb.append(' ');
        }
        sb.append(x);
    }

    private void addChar(char x, StringBuilder sb) {
        for (int i = 0; i < digits - 1; i++) {
            sb.append(' ');
        }
        sb.append(x);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits + 1; i++) {
            sb.append(' ');
        }
        for (int i = 0; i < m; i++) {
            addNum(i, sb);
            sb.append(' ');
        }
        sb.append('\n');
        for (int i = 0; i < n; i++) {
            addNum(i, sb);
            sb.append(' ');
            for (int j = 0; j < m; j++) {
                addChar(SYMBOLS.get(cells[i][j]), sb);
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
