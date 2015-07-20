package com.macilias.games.controller;

import com.macilias.games.model.Field;

/**
 * Created by mac on 20.07.15.
 */
public class Game implements A2048Game {

    private Field field;
    private static Game instance;

    private Game() {
        try {
            field = new Field();
        } catch (Exception e) {
            // should never ever happen
            e.printStackTrace();
        }
    }

    private Game(int size) throws Exception {
        field = new Field(size);
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public static Game getInstance(int size) throws Exception {
        if (instance == null) {
            instance = new Game(size);
        }
        return instance;
    }

    @Override
    public Field moveLeft() {
        return field;
    }

    @Override
    public Field moveRight() {
        return field;
    }

    @Override
    public Field moveUp() {
        return field;
    }

    @Override
    public Field moveDown() {
        return field;
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @VisibleForTesting
    public int[] manipulateRow(int[] row) {

        for (int i = row.length - 2; i >= 0; i--) {
            boolean collated = false;
            boolean stopped = false;
            int current = row[i];
            if (current != 0) {
                for (int j = i; j < row.length -1  && !collated && !stopped; j++) {
                    int next = row[j + 1];
                    if (current == next) {
                        collated = true;
                        row[j + 1] = current * 2;
                        row[j] = 0;
                    } else if (next != 0) {
                        stopped = true;
                    } else {
                        row[j + 1] = current;
                        row[j] = 0;
                    }
                }
            }
        }
        return row;
    }
}
