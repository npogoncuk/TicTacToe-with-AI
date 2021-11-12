package tictactoe.game.user;

public abstract class User {
    char symbol, opponent_symbol;

    public User(char symbol) {
        this.symbol = symbol;
        opponent_symbol = (symbol == 'X') ? 'O' : 'X';
    }

    abstract public void makeMove(char[][] table);
}
