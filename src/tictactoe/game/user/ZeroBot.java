package tictactoe.game.user;

public class ZeroBot extends HardBot {
    public ZeroBot(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(char[][] table) {
        super.makeMove(table, false);
    }
}
