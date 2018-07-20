package test;


import daa.TelescopeScheduler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by PedroCaldeira on 02/05/2017.
 */

@RunWith(Parameterized.class)
public class AlgorithmGeneratedTest {


    @Parameterized.Parameter
    public Integer[] coordinates;


    @Parameterized.Parameters
    public static Collection<Object[]> parse() {
        Collection<Object[]> params = new ArrayList<>();
        int size = 10, innerSize = 0; // 10 testes = size
        for (int i = 0; i < size; i++) {
            innerSize = ThreadLocalRandom.current().nextInt(2, 1000);
            Integer[] coordinates = new Integer[innerSize];
            coordinates[0] = 1;
            for (int j = 1; j < innerSize; j++) {
                coordinates[j] = ThreadLocalRandom.current().nextInt(-innerSize, innerSize); //ultimo tem de estar ao alcance
            }
            params.add(new Object[]{coordinates});
        }
        return params;
    }


    @Test
    public void generatedcalculateBestMovementsTest() {

        long elapsedTime = 0, memory = 0;
        int size = 5;
        Runtime runtime = Runtime.getRuntime();
        for (int i = 0; i < size; i++) {
            System.gc();
            long initialm = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.nanoTime();
            TelescopeScheduler d = new TelescopeScheduler();
            List pl = d.calculateBestMovements(coordinates);

            long stopTime = System.nanoTime();
            elapsedTime += stopTime - startTime;
            System.gc();
            long stopm = runtime.totalMemory() - runtime.freeMemory();

            // Calculate the used memory
            memory += (stopm - initialm);
        }
        System.out.println("Media of "+size+" Times -> Input Size: "+coordinates.length);
        System.out.println("Time nano: " + elapsedTime/size + " mili "+ (elapsedTime/size) / 1e6);
        System.out.println("Memory used in bytes: "+ memory/size);
        System.out.println("\n");


    }

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }


}