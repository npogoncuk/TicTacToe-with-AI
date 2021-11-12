package tictactoe.game.user;

import java.util.Scanner;

public class Person extends User{

    public Person(char symbol) {
        super(symbol);
    }

    public void makeMove(char[][] table) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the coordinates: > ");
        // while coordinates is not correct, program will ask to enter coordinates
        while (!enter_coordinates(string_work(in), table)) {
            System.out.print("Enter the coordinates: > ");
        }
    }
    // checks if coordinates were input correctly
    private boolean enter_coordinates(String[] coordinates, char[][] table) {
        String coordinate1 = coordinates[0], coordinate2 = coordinates[1];
        if (coordinate1.equals("") || coordinate2.equals("")) {
            System.out.println("You should enter numbers!");
            return false;
        }
        for (int i = 0; i < coordinate1.length(); i++) {
            if (coordinate1.charAt(i) < '0' || coordinate1.charAt(i) > '9' ) {
                System.out.println("You should enter numbers!");
                return false;
            }
        }
        for (int i = 0; i < coordinate2.length(); i++) {
            if (coordinate2.charAt(i) < '0' || coordinate2.charAt(i) > '9') {
                System.out.println("You should enter numbers!");
                return false;
            }
        }
        int coordinate_i = Integer.parseInt(coordinate1) , coordinate_j = Integer.parseInt(coordinate2);
        if (coordinate_i > 3 || coordinate_j > 3 || coordinate_i < 1 || coordinate_j < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (table[coordinate_i-1][coordinate_j-1] == 'X' || table[coordinate_i-1][coordinate_j-1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        table[coordinate_i-1][coordinate_j-1] = symbol;
        return true;
    }
    // take input( line) and separate coordinates or words from each other
    private String[] string_work(Scanner in) {
        String[] result = new String[2];
        String str = in.nextLine();
        String str1, str2;
        if (str.contains(" ")) {
            str1 =str.substring(0, str.indexOf(" "));
            str2 = str.substring(str.indexOf(" ") + 1);
        } else {
            str1 = str;
            str2 = "";
        }
        result[0] = str1;
        result[1] = str2;
        return result;
    }

}
