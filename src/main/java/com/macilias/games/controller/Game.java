package com.macilias.games.controller;

import com.macilias.games.model.Field;

/**
 * Created by mac on 20.07.15.
 */
public class Game implements A2048Game{

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
}
