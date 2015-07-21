package com.macilias.games.controller;

import com.macilias.games.model.Field;

/**
 * Created by mac on 20.07.15.
 */
public interface A2048Game {

    Field moveLeft();

    Field moveRight();

    Field moveUp();

    Field moveDown();

    Field getField();

    boolean isOver();

    long getScore();
}
