package year2021.day06.codes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // Puzzle input
        int days = 80;

        List<Integer> initialFishAges = parser("src/main/java/year2021/day06/inputs/inputEnonce.txt");
        // List<Integer> initialFishAges = parser("src/main/java/year2021/day06/inputs/input.txt");

        // Simulating lanternfish growth
        List<LanternFish> lanternfish = simulateLanternfish(initialFishAges, days);

        // Displaying the result
        System.out.println("After " + days + " days, there are " + lanternfish.size() + " lanternfish.");
    }

    private static List<Integer> parser(String filename) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            return Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
    }

    private static List<LanternFish> simulateLanternfish(List<Integer> initialAges, int days) {
        List<LanternFish> lanternfish = initialAges.stream()
                .map(LanternFish::new)
                .collect(Collectors.toList());

        for (int day = 1; day <= days; day++) {
            List<LanternFish> newLanternfish = lanternfish.stream()
                    .peek(LanternFish::age)
                    .filter(LanternFish::hasProducedNewFish)
                    .map(fish -> new LanternFish(8))
                    .toList();

            lanternfish.addAll(newLanternfish); // Adding the new fish to the total list

            // System.out.println("Day " + day + ": " + lanternfish.size() + " lanternfish.");
        }

        return lanternfish;
    }
}
