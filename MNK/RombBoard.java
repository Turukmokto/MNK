package MNK;

import java.util.Arrays;

public class RombBoard extends NMKBoard {
    private void build(int n) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(cells[i], 0, n - i - 1, Cell.N);
            Arrays.fill(cells[i], n + i, 2 * n - 1, Cell.N);
        }
        for (int i = n; i < 2 * n - 1; i++) {
            Arrays.fill(cells[i], 0, n - (2 * n - i) + 1, Cell.N);
            Arrays.fill(cells[i], 3 * n - 2 - i, 2 * n - 1, Cell.N);
        }
        emptyNumber = n * n + (n - 1) * (n - 1);
    }

    public RombBoard(int n, int k) {
        super(2 * n - 1, 2 * n - 1, k);
        build(n);
    }

    public RombBoard(int n, int k, int extraNum) {
        super(2 * n - 1, 2 * n - 1, k, extraNum);
        build(n);
    }
}
