package com.macilias.games;

import com.macilias.games.controller.GameImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mac on 20.07.15.
 */
public class GameTests {

    GameImpl game = null;

    @Before
    public void init() {
        game = GameImpl.getInstance();
    }


    @Test
    public void testCollisionWithDistance() {
        int[] row1 = {0, 2, 0, 2};
        int[] row1_result = {0, 0, 0, 4};
        Assert.assertArrayEquals("Collision with distance", row1_result, game.manipulateRow(row1));

        int[] row2 = {4, 0, 0, 4};
        int[] row2_result = {0, 0, 0, 8};
        Assert.assertArrayEquals("Collision with distance", row2_result, game.manipulateRow(row2));
    }

    @Test
    public void testCollisionWithoutDistance() {
        int[] row1 = {0, 2, 2, 0};
        int[] row1_result = {0, 0, 0, 4};
        Assert.assertArrayEquals("Collision without distance", row1_result, game.manipulateRow(row1));

        int[] row2 = {4, 4, 0, 0};
        int[] row2_result = {0, 0, 0, 8};
        Assert.assertArrayEquals("Collision without distance", row2_result, game.manipulateRow(row2));
    }

    @Test
    public void testDoubleCollision() {
        int[] row1 = {2, 2, 2, 2};
        int[] row1_result = {0, 0, 4, 4};
        Assert.assertArrayEquals("Double collision", row1_result, game.manipulateRow(row1));

        int[] row2 = {4, 4, 8, 8};
        int[] row2_result = {0, 0, 8, 16};
        Assert.assertArrayEquals("Double collision", row2_result, game.manipulateRow(row2));
    }

    @Test
    public void testCollisionWithStopWithDistance() {
        int[] row1 = {2, 0, 2, 4};
        int[] row1_result = {0, 0, 4, 4};
        Assert.assertArrayEquals("Collision with stop and with distance", row1_result, game.manipulateRow(row1));

        int[] row2 = {4, 0, 4, 16};
        int[] row2_result = {0, 0, 8, 16};
        Assert.assertArrayEquals("Collision with stop and with distance", row2_result, game.manipulateRow(row2));
    }

    @Test
    public void testCollisionWithStopWithoutDistance() {
        int[] row1 = {0, 2, 2, 4};
        int[] row1_result = {0, 0, 4, 4};
        Assert.assertArrayEquals("Collision with stop and without distance", row1_result, game.manipulateRow(row1));

        int[] row2 = {4, 4, 0, 16};
        int[] row2_result = {0, 0, 8, 16};
        Assert.assertArrayEquals("Collision with stop and without distance", row2_result, game.manipulateRow(row2));
    }

    @Test
    public void testStopWithDistance() {
        int[] row1 = {0, 2, 0, 4};
        int[] row1_result = {0, 0, 2, 4};
        Assert.assertArrayEquals("Collision stop with distance", row1_result, game.manipulateRow(row1));

        int[] row2 = {4, 0, 0, 16};
        int[] row2_result = {0, 0, 4, 16};
        Assert.assertArrayEquals("Collision stop with distance", row2_result, game.manipulateRow(row2));
    }

    @Test
    public void testStopWithoutDistance() {
        int[] row1 = {2, 4, 0, 0};
        int[] row1_result = {0, 0, 2, 4};
        Assert.assertArrayEquals("Collision stop without distance", row1_result, game.manipulateRow(row1));

        int[] row2 = {0, 4, 16, 0};
        int[] row2_result = {0, 0, 4, 16};
        Assert.assertArrayEquals("Collision stop without distance", row2_result, game.manipulateRow(row2));
    }

    // The resulting tile cannot merge with another tile again in the same move.
    @Test
    public void testOnlyOneCollisionPerTile() {
        int[] row1 = {8, 4, 2, 2};
        int[] row1_result = {0, 8, 4, 4};
        Assert.assertArrayEquals("Collision stop without distance", row1_result, game.manipulateRow(row1));

        int[] row2 = {4, 2, 2, 0};
        int[] row2_result = {0, 0, 4, 4};
        Assert.assertArrayEquals("Collision stop without distance", row2_result, game.manipulateRow(row2));
    }


}
