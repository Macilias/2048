package com.macilias.games.controller;

import com.macilias.games.model.Field;
import com.macilias.games.model.IllegalSizeException;

import java.util.Random;

/**
 * Created by mac on 20.07.15.
 */
public class GameImpl implements Game {

    private static GameImpl instance;
    private Field field;
    private boolean isOver = false;
    private Random random = new Random(467462421234L);
    private long score = 0L;

    private GameImpl() {
        try {
            field = new Field();
        } catch (IllegalSizeException e) {
            // should never ever happen
            e.printStackTrace();
        }
    }

    private GameImpl(int size) throws IllegalSizeException {
        field = new Field(size);
    }

    public static GameImpl getInstance() {
        if (instance == null) {
            instance = new GameImpl();
        }
        return instance;
    }

    public static GameImpl getInstance(int size) throws IllegalSizeException {
        if (instance == null) {
            instance = new GameImpl(size);
        }
        return instance;
    }

    @Override
    public Field moveLeft() {
        resetField();
        for (int i = 0; i < field.field.length; i++) {
            field.field[i] = reverse(manipulateRow(reverse(field.field[i])));
        }
        return returnField();
    }

    @Override
    public Field moveRight() {
        resetField();
        for (int i = 0; i < field.field.length; i++) {
            field.field[i] = manipulateRow(field.field[i]);
        }
        return returnField();
    }

    @Override
    public Field moveUp() {
        resetField();
        int[][] temp = transpose(field.field);
        for (int i = 0; i < temp.length; i++) {
            temp[i] = reverse(manipulateRow(reverse(temp[i])));
        }
        field.field = transpose(temp);
        return returnField();
    }

    @Override
    public Field moveDown() {
        resetField();
        int[][] temp = transpose(field.field);
        for (int i = 0; i < temp.length; i++) {
            temp[i] = manipulateRow(temp[i]);
        }
        field.field = transpose(temp);
        return returnField();
    }


    private void resetField() {
        field.setChanged(false);
    }

    private Field returnField() {
        if (field.isChanged() && slotsOpen()) {
            addRandom();
        }
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

        int collisionAt = 0;
        for (int i = row.length - 2; i >= 0; i--) {
            boolean collated = false;
            boolean stopped = false;
            int current = row[i];
            if (current != 0) {
                for (int j = i; j < row.length - 1 && !collated && !stopped; j++) {
                    int next = row[j + 1];
                    if (current == next && collisionAt != j + 1) {
                        collated = true;
                        collisionAt = j + 1;
                        int tile = current * 2;
                        row[j + 1] = tile;
                        row[j] = 0;
                        score += tile;
                        field.setChanged(true);
                    } else if (next != 0) {
                        stopped = true;
                    } else {
                        row[j + 1] = current;
                        row[j] = 0;
                        field.setChanged(true);
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
