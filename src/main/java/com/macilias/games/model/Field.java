package com.macilias.games.model;

/**
 * Created by mac on 20.07.15.
 */
public class Field {

    int[][] field;

    public Field () {
        try {
            new Field(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Field (int size) throws Exception {
        if (size < 4) {
            throw new Exception("Field size must be at least 4x4");
        }
        field = new int[size -1][size -1];
    }

}
