package tictactoe.game;

import tictactoe.game.user.*;

import java.util.Arrays;

class Game {
    private final char[][] table = new char[3][3];
    private final User user1, user2;
    Game(GameMode modeForFirst, GameMode modeForSecond) {
        clean_table();
        show_table();
        user1 = createUser(modeForFirst, 'X');
        user2 = createUser(modeForSecond, 'O');
        start();
    }
    private void start() {
        while (game_not_finished()) {
            messageAboutMove(user1);
            user1.makeMove(table);
            show_table();
            if (show_gameEnded()) return;
            messageAboutMove(user2);
            user2.makeMove(table);
            show_table();
            if (show_gameEnded()) return;
        }
    }
    private void clean_table() {
        for (char[] row:
             table) {
            Arrays.fill(row, ' ');
        }
    }
    private void show_table() {
        System.out.println("---------");
        System.out.println("|" + " " + table[0][0] + " " + table[0][1] + " " + table[0][2] + " " + "|");
        System.out.println("|" + " " + table[1][0] + " " + table[1][1] + " " + table[1][2] + " " + "|");
        System.out.println("|" + " " + table[2][0] + " " + table[2][1] + " " + table[2][2] + " " + "|");
        System.out.println("---------");
    }
    private boolean player_won(char player) {
        //checks rows
        for (char[] row:
                table) {
            if (row[0] == row[1] && row[1] == row[2] && row[2] == player) return true;
        }
        //checks columns
        for (int j = 0; j < 3; j++)
            if (table[0][j] == table[1][j] && table[1][j] == table[2][j] && table[2][j] == player) return true;
        //checks diagonals
        if (table[0][0] == table[1][1] && table[1][1] == table[2][2] && table[2][2] == player) return true;
        if (table[0][2] == table[1][1] && table[1][1] == table[2][0] && table[2][0] == player) return true;
        return false;
    }
    private boolean draw() {
        //there is blank cell in the board -- it can't be draw
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (table[i][j] == ' ') return false;
        //if board is full
        return (!player_won('X')) && (!player_won('O'));
    }
    private boolean game_not_finished() {
        return !player_won('X') && !player_won('O') && !draw();
    }
    private boolean show_gameEnded() {
        if (player_won('X')) {
            System.out.println("X wins");
            return true;
        }
        if (player_won('O')) {
            System.out.println("O wins");
            return true;
        }
        if (draw()) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }
    private User createUser(GameMode gameMode, char symbol) {
        switch (gameMode) {
            case PERSON:
                return new Person(symbol);
            case EASY_BOT:
                return new EasyBot(symbol);
            case MEDIUM_BOT:
                return new MediumBot(symbol);
            case HARD_BOT:
                return new HardBot(symbol);
            case ZERO_BOT:
                return new ZeroBot(symbol);
        }
        return null;
    }

    private void messageAboutMove(User user) {
        switch (user.getClass().toString()) {
            case "class tictactoe.game.user.EasyBot":
                System.out.println("Making move level \"easy\"");
                break;
            case "class tictactoe.game.user.MediumBot":
                System.out.println("Making move level \"medium\"");
                break;
            case "class tictactoe.game.user.HardBot":
                System.out.println("Making move level \"hard\"");
                break;
            case "class tictactoe.game.user.ZeroBot":
                System.out.println("Making move level \"zero\"");
                break;
        }
    }
}