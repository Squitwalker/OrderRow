//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class TimeLogger {
//
//    private String name;
//    private String time;
//
//    public TimeLogger(String name, String time) {
//        this.name = name;
//        this.time = time;
//    }
//
//    public void writeToFile() {
//        try {
//            FileWriter fw = new FileWriter("C:\\Users\\jedib\\OneDrive\\Pictures\\Assignments\\CS-DATA\\OrderRow\\src\\leaderboard.txt", true); // true = append mode
//            PrintWriter pw = new PrintWriter(fw);
//
//            pw.println(name + " " + time); // each entry on its own line
//
//            pw.close();
//            System.out.println("Data successfully written to file.");
//
//        } catch (IOException e) {
//            System.out.println("An error occurred while writing to the file.");
//            e.printStackTrace();
//        }
//    }
//}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class TimeLogger {

    private String name;
    private String time;

    public TimeLogger(String name, String time) {
        this.name = name;
        this.time = time;
    }

    // Write a new entry to the file
    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter("./src/leaderboard.txt", true); // append mode
            PrintWriter pw = new PrintWriter(fw);

            pw.println(name + " " + time); // each entry on its own line

            pw.close();
            System.out.println("Data successfully written to file.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Read all entries and sort from fastest to slowest
    public static ArrayList<String> readAndSortFile() {

        ArrayList<String> lines = new ArrayList<>();

        try {
            File file = new File("./src/leaderboard.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        // Sort by total time in seconds
        Collections.sort(lines, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return extractTotalSeconds(s1) - extractTotalSeconds(s2);
            }
        });

        return lines;
    }

    // Helper method to convert "X minute : Y seconds" into total seconds
    private static int extractTotalSeconds(String line) {
        // Skip empty lines
        if (line.trim().isEmpty()) return Integer.MAX_VALUE;

        int firstSpace = line.indexOf(" ");
        if (firstSpace == -1) return Integer.MAX_VALUE; // invalid line

        String timePart = line.substring(firstSpace + 1); // everything after name
        // Expected format: "X minute : Y seconds"
        String[] parts = timePart.split(" "); // splits "1 minute : 3 seconds" -> ["1","minute",":","3","seconds"]

        if (parts.length < 5) return Integer.MAX_VALUE; // invalid line

        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[3]);

        return minutes * 60 + seconds;
    }
}