package tictactoe.game.user;

import java.util.Random;

// bot does random moves
public class EasyBot extends User{
    public EasyBot(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(char[][] table) {
        int i, j;
        Random random = new Random();
        do {
            i = random.nextInt(3);
            j = random.nextInt(3);
        } while ( table[i][j] != ' ');
        table[i][j] = symbol;
    }
}
