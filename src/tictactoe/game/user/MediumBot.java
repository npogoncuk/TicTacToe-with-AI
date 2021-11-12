package tictactoe.game.user;

import java.util.Arrays;

public class MediumBot extends User{
    public MediumBot(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(char[][] table) {
        //if bot can win in row/column/diagonal, he will do that
        if (win(table)) return;
        //then if bot can stop opponent from winning in row/column/diagonal, he will do that
        if (not_lose(table)) return;
        //else he will do random move
        new EasyBot(symbol).makeMove(table);
    }
    private boolean win(char[][] table) {
        // win in a row
        for (char[] row:
                table) {
            if (hasTwoInLine(row, symbol)) {
                Arrays.fill(row, symbol);
                return true;
            }
        }
        // win in a column
        for (int j = 0; j < 3; j++) {
            char[] column = new char[]{table[0][j], table[1][j], table[2][j]};
            if (hasTwoInLine(column, symbol) ) {
                table[indexOf(column, ' ')][j] = symbol;
                return true;
            }
        }
        // win in a diagonal
        char[] diagonal = new char[]{table[0][0], table[1][1], table[2][2]};
        if (hasTwoInLine(diagonal, symbol) ) {
            table[indexOf(diagonal, ' ')][indexOf(diagonal, ' ')] = symbol;
            return true;
        }
        // win in another diagonal
        diagonal = new char[]{table[0][2], table[1][1], table[2][0]};
        if (hasTwoInLine(diagonal , symbol) ) {
            table[indexOf(diagonal, ' ')][2 - indexOf(diagonal, ' ')] = symbol;
            return true;
        }
        return false;
    }
    private boolean not_lose(char[][] table) {
        // stop from losing in a row
        for (char[] row:
                table) {
            if (hasTwoInLine(row, opponent_symbol)) {
                row[indexOf(row, ' ')] = symbol;
                return true;
            }
        }
        // stop from losing in a column
        for (int j = 0; j < 3; j++) {
            char[] column = new char[]{table[0][j], table[1][j], table[2][j]};
            if (hasTwoInLine(column, opponent_symbol) ) {
                table[indexOf(column, ' ')][j] = symbol;
                return true;
            }
        }
        // stop from losing in a diagonal
        char[] diagonal = new char[]{table[0][0], table[1][1], table[2][2]};
        if (hasTwoInLine(diagonal, opponent_symbol) ) {
            table[indexOf(diagonal, ' ')][indexOf(diagonal, ' ')] = symbol;
            return true;
        }
        // stop from losing in another diagonal
        diagonal = new char[]{table[0][2], table[1][1], table[2][0]};
        if (hasTwoInLine(diagonal , opponent_symbol) ) {
            table[indexOf(diagonal, ' ')][2 - indexOf(diagonal, ' ')] = symbol;
            return true;
        }
        return false;
    }
    private boolean hasTwoInLine(char[] line, char player) {
        return Arrays.compare(line, new char[]{player, player, ' '}) == 0 ||
                Arrays.compare(line, new char[]{player, ' ', player}) == 0 ||
                Arrays.compare(line, new char[]{' ', player, player}) == 0;
    }
    private int indexOf(char[] line, char element) {
        return new String(line).indexOf(element);
    }
}