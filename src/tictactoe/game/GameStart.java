package tictactoe.game;

import java.util.Scanner;

public class GameStart {
    // this fields contains GameMode for first user(X) with index 0
    //                          and for second user(O) with index 1
    private static final GameMode[] gameModes = new GameMode[2];
    // this field contains 3 commands. For example, [start, easy, easy]
    private static final String[] commands = new String[3];
    public static void start(boolean additionalInformationAboutGame) {
        if (additionalInformationAboutGame) {
            printRules();
            printGameExample();
        }
        while (true) {
            while (!is_commands_correct()) {}
            if (commands[0].equals("start")) {
                new Game(gameModes[0], gameModes[1]);
                System.out.print("\n");
            }
            else {
                return;
            }
        }
    }
    // this method reads line and convert it into command
    // !!! between each command should be inputted space !!!
    // if command is missed,it will be blank( "" ) in field "commands"
    private static void get_commands(){
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.contains(" ")) {
            commands[0] = input.substring(0, input.indexOf(" "));
            String help = input.substring( input.indexOf(" ")+1);
            if (help.contains(" ")) {
                commands[1] = help.substring(0, help.indexOf(" "));
                commands[2] = help.substring(help.indexOf(" ") + 1);
            }else {
                commands[1] = help;
                commands[2] = "";
            }
        }else {
            commands[0] = input;
            commands[1] = "";
            commands[2] = "";
        }
    }
    // this methods ask you to print command
    // then checks whether command is correct
    // if not returns false and print "Bad parameters!"
    private static boolean is_commands_correct() {
        System.out.print("Input command: > ");
        get_commands();
        if (!commands[0].equals("start") && !commands[0].equals("exit")) {
            System.out.println("Bad parameters!");
            return false;
        } else {
            if (commands[0].equals("exit")) return true;
        }
        for (int i = 1; i < 3; i++) {
            if (commands[i].isEmpty()) {
                System.out.println("Bad parameters!");
                return false;
            } else {
                if (commands[i].equals("user") || commands[i].equals("zero") || commands[i].equals("easy") ||
                        commands[i].equals("medium") || commands[i].equals("hard")) gameModes[i-1]= into_GameMode(commands[i]);
                else {
                    System.out.println("Bad parameters!");
                    return false;
                }
            }
        }
        return true;
    }

    private static GameMode into_GameMode(String command) {
        switch (command) {
            case "user":
                return GameMode.PERSON;
            case "zero":
                return GameMode.ZERO_BOT;
            case "easy":
                return GameMode.EASY_BOT;
            case "medium":
                return GameMode.MEDIUM_BOT;
            case "hard":
                return GameMode.HARD_BOT;
        }
        return null;
    }
    public static void printRules() {
        System.out.print("Print \"Y\" (without quotes) if you want to see the rules. Otherwise press any other key: > ");
        try {
            int input = System.in.read();
            System.in.read();
            if ( input - 'Y' != 0 && input - 'y' != 0) return;
        }catch (Exception e) {}

        System.out.println("____________________________________________________________________________________________________________________________________________________________");
        System.out.println("You probably already know how to play Tic-Tac-Toe. It's a really simple game.\n" +
                "RULES FOR TIC-TAC-TOE\n" +
                "1. The game is played on a grid that's 3 squares by 3 squares.\n" +
                "\n" +
                "2. You are X or O, your friend (or the computer) is symbol, which have been left. Players take turns putting their marks in empty squares.\n" +
                "\n" +
                "3. The winner is the first player to get 3 of his marks in a row (up, down, across, or diagonally).\n" +
                "\n" +
                "4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.\n" +
                "HOW TO START GAME\n" +
                "You should input the command after \"Input command: >\".\nIf you want to start a game you should enter \"start\", space, \"user\" or \"zero\" or \"easy\" or \"medium\" or \"hard\",  \"zero\" or \"easy\" or \"medium\" or \"hard\".\nSo you can both play with another person and with a bot( there are 4 different bots in this game: zero, easy, medium and hard)\n" +
                "For example \n" +
                "Input command: > start user hard.\n" +
                "That means you will play with a hard bot.\n" +
                "After that you will see \"Enter coordinates: > \". You should enter two numbers, for example, \"1 2\".\nThe first number is a number of row and the second one is a number of column.\n" +
                "By the way, \"start user zero\" means that person is X and computer is O and \"start zero user\" means that computer is X and person is O.\n" +
                "If you want to watch the battle between bots, you should input, for instance, \"start medium hard\".\nYou should input \"exit\" if you don't want to paly anymore.");
        System.out.println("____________________________________________________________________________________________________________________________________________________________");
    }
    public static void printGameExample() {
        System.out.print("Print \"Y\" (without quotes) if you want to see the example of a game. Otherwise press any other key: > ");
        try {
            int input = System.in.read();
            System.in.read();
            if ( input - 'Y' != 0 && input - 'y' != 0) return;
        }catch (Exception e) {}
        System.out.println("Input command: > start user medium\n" +
                "---------\n" +
                "|       |\n" +
                "|       |\n" +
                "|       |\n" +
                "---------\n" +
                "Enter the coordinates: > 1 1\n" +
                "---------\n" +
                "| X     |\n" +
                "|       |\n" +
                "|       |\n" +
                "---------\n" +
                "Making move level \"medium\"\n" +
                "---------\n" +
                "| X     |\n" +
                "| O     |\n" +
                "|       |\n" +
                "---------\n" +
                "Enter the coordinates: > 1 3\n" +
                "---------\n" +
                "| X   X |\n" +
                "| O     |\n" +
                "|       |\n" +
                "---------\n" +
                "Making move level \"medium\"\n" +
                "---------\n" +
                "| X O X |\n" +
                "| O     |\n" +
                "|       |\n" +
                "---------\n" +
                "Enter the coordinates: > 2 2\n" +
                "---------\n" +
                "| X O X |\n" +
                "| O X   |\n" +
                "|       |\n" +
                "---------\n" +
                "Making move level \"medium\"\n" +
                "---------\n" +
                "| X O X |\n" +
                "| O X   |\n" +
                "|     O |\n" +
                "---------\n" +
                "Enter the coordinates: > 3 1\n" +
                "---------\n" +
                "| X O X |\n" +
                "| O X   |\n" +
                "| X   O |\n" +
                "---------\n" +
                "X wins");
        System.out.println("____________________________________________________________________________________________________________________________________________________________");
    }
}
