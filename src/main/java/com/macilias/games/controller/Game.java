package com.macilias.games.controller;

import com.macilias.games.model.Field;

/**
 * Created by mac on 20.07.15.
 */
public class Game implements A2048Game{

    private Field field;
    private static Game instance;

    private Game() {
        field = new Field();
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
        return null;
    }

    @Override
    public Field moveRight() {
        return null;
    }

    @Override
    public Field moveUp() {
        return null;
    }

    @Override
    public Field moveDown() {
        return null;
    }


}
