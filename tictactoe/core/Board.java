package tictactoe.core;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public class Board {

    public static final int SIZE = 3;

    private final Symbol[][] matrix = new Symbol[SIZE][SIZE];

    public Board() {
        for (Symbol[] symbols : matrix) {
            Arrays.fill(symbols, Symbol.NONE);
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);

        for (int i = 0; i < SIZE; i++) {

            boolean first = true;
            for (int j = 0; j < SIZE; j++) {
                if (!first) {
                    out.print(" | ");
                }
                out.print(matrix[i][j]);
                first = false;
            }
            out.println();

            if (i + 1 < SIZE) {
                for (int j = 0; j < SIZE; j++) {
                    out.print("---");
                }
                out.println();
            }
        }

        return sw.toString();
    }

    public Symbol update(Symbol symbol, Coord coord) {
        requireNonNull(symbol);
        requireNonNull(coord);

        if (symbol == Symbol.NONE) {
            throw new IllegalArgumentException("None cannot be added to board");
        }

        if (matrix[coord.i()][coord.j()] != Symbol.NONE) {
            throw new IllegalArgumentException("Play is not possible");
        }

        matrix[coord.i()][coord.j()] = symbol;
        return findSequence();
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix[i][j] == Symbol.NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    private Symbol findSequence() {
        Symbol symbol = findSequenceInRows();
        if (symbol != null) {
            return symbol;
        }

        symbol = findSequenceInColumns();
        if (symbol != null) {
            return symbol;
        }

        return findSequenceInDiagonals();
    }

    private Symbol findSequenceInRows() {
        for (int i = 0; i < SIZE; i++) {
            Symbol symbol = findSequenceInRow(i);
            if (symbol != null) {
                return symbol;
            }
        }

        return null;
    }

    private Symbol findSequenceInRow(int i) {
        return matrix[i][0] == matrix[i][1]
            && matrix[i][1] == matrix[i][2]
            && matrix[i][0] != Symbol.NONE
            ? matrix[i][0]
            : null;
    }

    private Symbol findSequenceInColumns() {
        for (int j = 0; j < SIZE; j++) {
            Symbol symbol = findSequenceInColumn(j);
            if (symbol != null) {
                return symbol;
            }
        }
        
        return null;
    }

    private Symbol findSequenceInColumn(int j) {
        return matrix[0][j] == matrix[1][j]
            && matrix[1][j] == matrix[2][j]
            && matrix[0][j] != Symbol.NONE
            ? matrix[0][j]
            : null;
    }

    private Symbol findSequenceInDiagonals() {
        if (matrix[0][0] == matrix[1][1]
            && matrix[1][1] == matrix[2][2]
            && matrix[0][0] != Symbol.NONE
        ) {
            return matrix[0][0];
        }

        if (matrix[0][2] == matrix[1][1]
            && matrix[1][1] == matrix[2][0]
            && matrix[0][2] != Symbol.NONE
        ) {
            return matrix[0][2];
        }

        return null;
    }
}
