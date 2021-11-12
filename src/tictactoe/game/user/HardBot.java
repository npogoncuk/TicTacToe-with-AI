package tictactoe.game.user;

import java.util.ArrayList;

public class HardBot extends User {
    public HardBot(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(char[][] table) {
        makeMove(table, true);
    }
    //this method was made for zero bot, which uses the same algorithm
    void makeMove(char[][] table, boolean wantsToWin) {
        int i_best = Integer.MIN_VALUE, j_best = Integer.MIN_VALUE, best_score = Integer.MIN_VALUE;
        //estimates all possible moves and chooses the best
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == ' ') {
                    char[][] position = the_same_as(table);
                    position[i][j] = symbol;
                    int score = minimax(position, !wantsToWin, 0);
                    if (score > best_score) {
                        best_score = score;
                        i_best = i;
                        j_best = j;
                    }
                }
            }
        }
        table[i_best][j_best] = symbol;
    }
    //minimax algorithm for estimating move
    private int minimax(char[][] position, boolean maximizingPlayer, int depth) {
        if (game_ended(position)) {
            return  maximizingPlayer ? evaluate(position) - depth : evaluate(position) + depth ;
        } else {
            if (evaluate(position) != 0) return maximizingPlayer ? evaluate(position) - depth : evaluate(position) + depth;
        }

        if (maximizingPlayer) {
            int maxEvaluation = Integer.MIN_VALUE;
            for (char[][] child:
                    childPositions(position, maximizingPlayer)) {
                int eval = minimax(child, false, depth + 1);
                maxEvaluation = Math.max(eval, maxEvaluation);

            }
            return maxEvaluation;
        } else {
            int minEvaluation = Integer.MAX_VALUE;
            for (char[][] child:
                    childPositions(position, maximizingPlayer)) {
                int eval = minimax(child, true, depth + 1);
                minEvaluation = Math.min(eval, minEvaluation);
            }
            return minEvaluation;
        }
    }
    // evaluate position, when game over
    private int evaluate(char[][] table) {
        if (player_won(table, symbol)) return 10;
        if (player_won(table, opponent_symbol)) return -10;
        return 0;
    }
    // returns list of all possible child position
    private ArrayList<char[][]> childPositions(char[][] table, boolean maximizingPlayer){
        ArrayList<char[][]> child = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == ' ') {
                    char[][] new_position = the_same_as(table);
                    new_position[i][j] = maximizingPlayer ? symbol : opponent_symbol;
                    child.add(new_position);
                }
            }
        }
        return child;
    }
    //copy the array
    private char[][] the_same_as(char[][] inp) {
        char[][] result = new char[3][3];
        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                result[i][j] = inp[i][j];
            }
        }
        return result;
    }
    private boolean player_won(char[][] table, char player) {
        for (char[] row:
                table) {
            if (row[0] == row[1] && row[1] == row[2] && row[2] == player) return true;
        }
        for (int j = 0; j < 3; j++)
            if (table[0][j] == table[1][j] && table[1][j] == table[2][j] && table[2][j] == player) return true;
        if (table[0][0] == table[1][1] && table[1][1] == table[2][2] && table[2][2] == player) return true;
        if (table[0][2] == table[1][1] && table[1][1] == table[2][0] && table[2][0] == player) return true;
        return false;
    }

    private boolean game_ended(char[][] table) {
        for (char[] row:
                table) {
            for (char i:
                    row) {
                if (i == ' ') return false;
            }
        }
        return true;
    }
}
