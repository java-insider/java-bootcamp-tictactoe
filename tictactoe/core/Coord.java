package tictactoe.core;

import static java.util.Objects.requireNonNull;
import static tictactoe.core.Board.SIZE;
import static tictactoe.util.ValidationUtils.require;

public record Coord(int i, int j) {

    public Coord {
        require(i >= 0 && i < SIZE, "i is out of range");
        require(j >= 0 && j < SIZE, "j is out of range");
    }

    public static Coord parse(String text) {
        requireNonNull(text);

        String[] tokens = text.split(",");

        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid format");
        }

        try {
            return new Coord(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number");
        }
    }
}
