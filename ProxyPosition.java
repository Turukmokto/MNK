package MNK;

public class ProxyPosition implements Position {
    private final Position position;

    ProxyPosition(Position board) {
        this.position = board;
    }

    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    public Cell getCell(int r, int c) {
        return position.getCell(r, c);
    }

    public int getN() {
        return position.getN();
    }

    public int getM() {
        return position.getM();
    }

    public int getK() {
        return position.getK();
    }

    public String toString() {
        return position.toString();
    }
}