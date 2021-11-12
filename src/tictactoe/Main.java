package tictactoe;

import tictactoe.game.GameStart;

public class Main {

    public static void main(String[] args) {
        // method start() takes boolean parameter
        // if it is true, then user will be able to see rules and information about the game
        GameStart.start(true);
    }
}
