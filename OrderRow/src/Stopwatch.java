import java.util.Scanner;

public class Stopwatch {

    private static long startTime = 0;
    private static long elapsedTime = 0;
    private static boolean running = false;

    // Start stopwatch
    public static void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    // Stop stopwatch
    public static void stop() {
        elapsedTime += System.currentTimeMillis() - startTime;
        running = false;
            System.out.println("Stopwatch stopped.");

    }

    // Reset stopwatch
    public static void reset() {
        startTime = 0;
        elapsedTime = 0;
        running = false;
    }

    // Show elapsed time
    public static String showTime() {
        long totalTime = elapsedTime;

        if (running) {
            totalTime += System.currentTimeMillis() - startTime;
        }

        long seconds = totalTime / 1000;
        long minutes = seconds / 60;
        seconds %= 60;

        return minutes + " minute : " + String.format("%02d", seconds) + " seconds";
    }
}