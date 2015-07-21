package com.macilias.games.model;

/**
 * Created by mac on 20.07.15.
 */
public class Field {

    public int[][] field;
    private boolean changed = false;

    public Field() throws Exception {
        this(4);
    }

    public Field(int size) throws Exception {
        if (size < 4) {
            throw new Exception("Field size must be at least 4x4");
        }
        field = new int[size][size];
    }


    public boolean isChanged() {
        return changed;
    }


    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
