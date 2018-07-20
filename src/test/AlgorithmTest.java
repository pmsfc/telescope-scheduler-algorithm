package test;


import daa.TelescopeScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by PedroCaldeira on 02/05/2017.
 */

@RunWith(Parameterized.class)
public class AlgorithmTest {

    private static Integer[][] allCoordinates = {
            {0, 1, -4, -1, 4, 5, -4, 6, 7, -2},
            {0, 1, 2, 2, 2, -1, 0},
            {0, 1, -6, 2, -2, -3, 9, -5, 4, 9, -5},
            {0, 1, 2, 3, 4, 5, 2, 1, 2, 4, 3},
            {0,0,-3,-3}};

    private static Integer[][] allExpected = {
            {1, 3, 6, 9},
            {1, 2, 3, 4, 6},
            {1, 4, 5, 7, 10},
            {1, 2, 3, 4, 6, 7, 8, 10},
            {3}};


    @Parameterized.Parameter
    public Integer[] coordinates;
    @Parameterized.Parameter(value = 1)
    public Integer[] expected;

    private final boolean debug = true;


    @Parameterized.Parameters
    public static Collection<Object[]> parse() {
        Collection<Object[]> params = new ArrayList<>();
        for (int i = 0; i < allCoordinates.length; i++) {
            params.add(new Object[]{allCoordinates[i], allExpected[i]});
        }
        return params;
    }


    @Test
    public void calculateBestMovementsTest() {
        TelescopeScheduler d = new TelescopeScheduler();
        List pl =  d.calculateBestMovements(coordinates);
        assertEquals(Arrays.asList(expected), pl);
        if (debug) {
            System.out.println(pl);
            System.out.println(Arrays.asList(expected) + "\n");
        }
    }

}