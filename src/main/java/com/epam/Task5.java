package com.epam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kslisenko on 25.2.16.
 */
public class Task5 {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Task 5");

        List<String> data = new ArrayList<String>();

        // Start reading file
        BufferedReader reader = new BufferedReader(new FileReader("task5-data.txt"));
        String line = reader.readLine();
        while (line != null) {
            // Take first 3 characters

            /**
             * Memory leak was when we didn't put string to pool and created separate object
             * for each same string
             * String threeCharacters = line.substring(0, 3);
             */

            // Intern string for putting it to pool
            String threeCharacters = line.substring(0, 3).intern();
            data.add(threeCharacters);

            line = reader.readLine();
        }

        System.out.println("Reading file finished, totally readed lines = " + data.size());
        // Wait for taking heap dump
        Thread.sleep(50000);
    }
}
