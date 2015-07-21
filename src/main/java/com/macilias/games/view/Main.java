package com.macilias.games.view;

import com.macilias.games.controller.Game;
import com.macilias.games.controller.GameImpl;
import com.macilias.games.model.Field;

import java.util.Scanner;

/**
 * Created by mac on 20.07.15.
 */
public class Main {


    public static void main(String... args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to 2048");
        Game game = null;

        while (game == null) {
            System.out.println("how many fields do you wish to play?");
            if (!s.hasNextInt()) {
                game = GameImpl.getInstance();
            } else {
                int size = s.nextInt();
                System.out.println(String.format("ok, let´s go with %d", size));
                try {
                    game = GameImpl.getInstance(size);
                } catch (Exception e) {
                    System.out.println(String.format("the desired size %d is not valid, you should choose at least 4x4", size));
                }
            }
        }
        printField(game.getField());
        boolean userTerminated = false;

        while (!game.isOver() && !userTerminated) {
            String move = s.next();
            if (move != null) {
                switch (move) {
                    case "a":
                        printField(game.moveLeft());
                        break;
                    case "d":
                        printField(game.moveRight());
                        break;
                    case "w":
                        printField(game.moveUp());
                        break;
                    case "s":
                        printField(game.moveDown());
                        break;
                    case "q":
                        userTerminated = true;
                        break;
                    default:
                        System.out.println("please use 'a' (left), 'd' (right), 'w' (up) and 's' (down) to play - or 'q' to quit ");

                }
            }
        }
        System.out.println(String.format("Your Score is %d", game.getScore()));
        s.close();
    }

    private static void printField(Field field) {
        for (int i = 0; i < field.field.length; i++) {
            printSeparators(field);
            for (int j = 0; j < field.field.length; j++) {
							  String value = String.valueOf(field.field[i][j]);
							  String space = "";
							  for(int k = 0; k < 4 - value.length(); k++) {
									space += " ";
								}
                System.out.print(String.format("| %s%s ", space, value));
                if (j == field.field.length - 1) {
                    System.out.println("|");
                }
            }
        }
        printSeparators(field);
    }

    private static void printSeparators(Field field) {
        for (int k = 0; k < field.field.length * 7; k++) {
            if (k == 0) {
                System.out.print("|");
            } else {
                System.out.print("-");
            }
        }
        System.out.println("|");
    }
}
