package tictactoe.core;

import tictactoe.io.Input;
import tictactoe.io.Output;

public class Game {

    private final Board board = new Board();
    private final Players players = new Players();

    public void start() {

        Symbol winner = null;

        while (winner == null && !board.isFull()) {
            Output.writeNewLine();
            Output.write(board);
            winner = play(players.next());
        }

        Output.write(board);

        if (board.isFull()) {
            Output.write("No winner");
        } else {
            Output.write(String.format("%s is the winner!", winner));
        }
    }

    private Symbol play(Symbol symbol) {
        while (true) {
            try {
                String play = Input.read(String.format("'%s' Play =>", symbol));
                Coord coord = Coord.parse(play);
                return board.update(symbol, coord);
            } catch (RuntimeException e) {
                Output.write("ERROR: " + e.getMessage());
            }
        }
    }
}
