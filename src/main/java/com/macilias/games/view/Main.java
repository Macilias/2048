package com.macilias.games.view;

import com.macilias.games.controller.A2048Game;
import com.macilias.games.controller.Game;

import java.util.Scanner;

/**
 * Created by mac on 20.07.15.
 */
public class Main {


    public static void main (String... args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to 2048");
        A2048Game game = null;
        while (game == null) {
            System.out.println("how many fields do you wish to play?");
            if (!s.hasNextInt()) {
                game = Game.getInstance();
            } else {
                int size = s.nextInt();
                System.out.println(String.format("ok, let´s go with %d", size));
                try {
                    game = Game.getInstance(size);
                } catch (Exception e) {
                    System.out.println(String.format("the desired size %d is not valid, you should choose at least 4x4", size));
                }
            }
        }

    }
}
