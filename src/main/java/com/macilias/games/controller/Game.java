package com.macilias.games.controller;

import com.macilias.games.model.Field;

import java.util.Random;

/**
 * Created by mac on 20.07.15.
 */
public class Game implements A2048Game {

    private Field field;
    private static Game instance;
    private boolean isOver = false;
    private Random random = new Random(467462421234L);
    private long score = 0L;

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
        for (int i = 0; i < field.field.length; i++) {
            field.field[i] = reverse(manipulateRow(reverse(field.field[i])));
        }
        addRandom();
        return field;
    }

    @Override
    public Field moveRight() {
        for (int i = 0; i < field.field.length; i++) {
            field.field[i] = manipulateRow(field.field[i]);
        }
        addRandom();
        return field;
    }


    @Override
    public Field moveUp() {
        int[][] temp = transpose(field.field);
        for (int i = 0; i < temp.length; i++) {
            temp[i] = reverse(manipulateRow(reverse(temp[i])));
        }
        field.field = transpose(temp);
        addRandom();
        return field;
    }

    @Override
    public Field moveDown() {
        int[][] temp = transpose(field.field);
        for (int i = 0; i < temp.length; i++) {
            temp[i] = manipulateRow(temp[i]);
        }
        field.field = transpose(temp);
        addRandom();
        return field;
    }

    @Override
    public Field getField() {
        addRandom();
        return field;
    }

    @Override
    public boolean isOver() {
        return isOver;
    }

    @Override
    public long getScore() {
        return score;
    }

    @VisibleForTesting
    public int[] manipulateRow(int[] row) {

        for (int i = row.length - 2; i >= 0; i--) {
            boolean collated = false;
            boolean stopped = false;
            int current = row[i];
            if (current != 0) {
                for (int j = i; j < row.length - 1 && !collated && !stopped; j++) {
                    int next = row[j + 1];
                    if (current == next) {
                        collated = true;
                        int tile = current * 2;
                        row[j + 1] = tile;
                        row[j] = 0;
                        score += tile;
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

    private int[] reverse(int[] row) {
        for (int i = 0; i < row.length / 2; i++) {
            int temp = row[i];
            row[i] = row[row.length - i - 1];
            row[row.length - i - 1] = temp;
        }
        return row;
    }

    private int[][] transpose(int[][] field) {
        int[][] result = new int[field.length][field[0].length];
        for (int i = 0; i < field[0].length; i++) {
            for (int j = 0; j < field.length; j++) {
                result[i][j] = field[j][i];
            }
        }
        return result;
    }

    private void addRandom() {
        if (slotsOpen()) {
            boolean inserted = false;
            while (!inserted) {
                int rx = random.nextInt(field.field.length);
                int ry = random.nextInt(field.field.length);
                if (field.field[rx][ry] == 0) {
                    int rv = random.nextInt(9) == 0 ? 4 : 2;
                    field.field[rx][ry] = rv;
                    inserted = true;
                }
            }
        }
    }

    private boolean slotsOpen() {
        for (int i = 0; i < field.field.length; i++) {
            for (int j = 0; j < field.field.length; j++) {
                if (field.field[i][j] == 0) {
                    return true;
                }
            }
        }
        isOver = true;
        return false;
    }
}
