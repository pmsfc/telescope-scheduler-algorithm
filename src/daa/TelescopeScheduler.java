package daa;

/**
 * Created by pedro on 3/5/2017.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Caldeira 41945
 * @version 1.0
 */
public class TelescopeScheduler {

    private Integer[][] results;

    /**
     * Starts the best movements algorithm
     *
     * @param coordinates Integer []
     * @return List<Integer>
     */
    public List<Integer> calculateBestMovements(Integer[] coordinates) {
        int  size = coordinates.length - 1;
        results = new Integer[coordinates.length][2];
        results[size] = recursion(coordinates, size);
        return buildPathBasedOnResults(size);
    }

    /**
     * Recursion method, iterating from index to last (from back to front)
     * since last element had to be included).
     *
     * @param coordinates Integer []
     * @param last        int
     * @return Integer []
     */
    private Integer[] recursion(Integer[] coordinates, int last) {
        if (last == 0) {
            return new Integer[]{0, -1}; //does not count elem 0
        }
        int jumpCounter = 1;
        int jumpIndex = 0;
        for (int i = 0; i < last; i++) {
            if (distance(coordinates[i], coordinates[last]) <= last - i) {
                if (results[i][0] == null) //memoization
                    results[i] = recursion(coordinates, i); //only calculates when needed
                if (((results[i][0]) + 1) >= jumpCounter && distance(coordinates[i],coordinates[0]) <= i) { //if new value is bigger, choose this route
                    jumpCounter = (results[i][0]) + 1;
                    jumpIndex = i;
                }
            }
        }
        return new Integer[]{jumpCounter, jumpIndex};
    }

    /**
     * Unfolds path based on results
     *
     * @param index int
     * @return List<Integer>
     */
    private List<Integer> buildPathBasedOnResults(int index) {
        List<Integer> mlist = new ArrayList<>();
        while (index != 0 ) {
            mlist.add(0, index);
            index = results[index][1];
        }
        return mlist;
    }


    /**
     * Distance between two points on x axxis
     *
     * @param x1 int
     * @param x2 int
     * @return int
     */
    private static int distance(int x1, int x2) {
        return Math.abs(x2 - x1);
    }

}