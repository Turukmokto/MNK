package MNK;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("enter m, n, k value");
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        /*System.out.println("enter type of game");
        String type = in.next();
        if (!type.equals("romb") && !type.equals("mnk")) {
            throw new AssertionError("Unknown type of game");
        }
        System.out.println("enter n, k value");
        final int ex = 4, n = in.nextInt(), k = in.nextInt();
            if (n <= 0 || k <= 0) {
                throw new AssertionError("Invalid n, k values");
            }
            final Game game = new Game(true, new RandomPlayer(), new RandomPlayer());
            if (type.equals("romb")) {
                game.play(new RombBoard(n, k, ex));
        } else {
            System.out.println("enter m value");
            final int m = in.nextInt();
            if (m <= 0) {
                throw new AssertionError("Invalid m values");
            }
            game.play(new NMKBoard(m, n, k, ex));
        }*/
        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        game.play(new RombBoard(3,5,3));
    }
}
