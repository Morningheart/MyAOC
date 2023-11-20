package year2021.day06.codes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // Puzzle input
        int days = 80;

        List<Integer> initialFishAges = parser("src/main/java/year2021/day06/inputs/inputEnonce.txt");
        // List<Integer> initialFishAges = parser("src/main/java/year2021/day06/inputs/input.txt");

        // Simulating lanternfish growth
        List<LanternFish> lanternfishPart1 = simulateLanternfishEx1(initialFishAges, 80);
        List<LanternFish> lanternfishPart2Try1 = simulateLanternfishEx2Essai1(initialFishAges, days);
        List<List<LanternFish>> lanternfishPart2Try2 = simulateLanternfishEx2Essai2(initialFishAges, days);

        // Displaying the result
        System.out.println("Part 1 : After " + days + " days, there are " + lanternfishPart1.size() + " lanternfish.");
        System.out.println("Part 2 - Try 1 : After " + days + " days, there are " + lanternfishPart2Try1.size() + " lanternfish.");
        System.out.println("Part 2 - Try 1 : After " + days + " days, there are " + countTotalLanternfish(lanternfishPart2Try2).toString() + " lanternfish.");
    }

    private static List<Integer> parser(String filename) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            return Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
    }

    private static List<LanternFish> simulateLanternfishEx1(List<Integer> initialAges, int days) {
        List<LanternFish> lanternfish = initialAges.stream()
                .map(LanternFish::new)
                .collect(Collectors.toList());

        for (int day = 1; day <= days; day++) {
            List<LanternFish> newLanternfish = lanternfish.stream()
                    .peek(LanternFish::oneDayPasses)
                    .filter(LanternFish::hasProducedNewFish)
                    .map(fish -> new LanternFish(8))
                    .toList();

            lanternfish.addAll(newLanternfish); // Adding the new fish to the total list

            // System.out.println("Day " + day + ": " + lanternfish.size() + " lanternfish.");
        }

        return lanternfish;
    }

    private static List<LanternFish> simulateLanternfishEx2Essai1(List<Integer> initialAges, int days) {
    List<LanternFish> lanternfish = initialAges.stream()
            .map(LanternFish::new)
            .collect(Collectors.toCollection(LinkedList::new));

        for (int day = 1; day <= days; day++) {
        List<LanternFish> newLanternfish = lanternfish.stream()
                .peek(LanternFish::oneDayPasses)
                .filter(LanternFish::hasProducedNewFish)
                .map(fish -> new LanternFish(8))
                .collect(Collectors.toCollection(LinkedList::new));

        lanternfish.addAll(newLanternfish); // Adding the new fish to the total list

        // System.out.println("Day " + day + ": " + lanternfish.size() + " lanternfish.");
    }

        return lanternfish;
}

    private static List<List<LanternFish>> simulateLanternfishEx2Essai2(List<Integer> initialAges, int days) {
        List<List<LanternFish>> lanternfish = new ArrayList<>();
        lanternfish.add(initialAges.stream()
                .map(LanternFish::new)
                .collect(Collectors.toList()));

        List<List<LanternFish>> updatedListOfLanternfish = new ArrayList<>(lanternfish);
        for (int iteration = 1; iteration <= days; iteration++) {

            for (List<LanternFish> laternfishOfTheDay : updatedListOfLanternfish) {

                List<LanternFish> oneDayPassed = simulateLanternfishEx1(laternfishOfTheDay
                        .stream()
                        .map(LanternFish::getAge)
                        .collect(Collectors.toList()), 1);

                List<LanternFish> updatedLanternfish = new ArrayList<>(oneDayPassed.subList(0, laternfishOfTheDay.size()));
                List<LanternFish> newLanternfish = new ArrayList<>(laternfishOfTheDay.subList(laternfishOfTheDay.size(), oneDayPassed.size()));

                // FF
            }
            System.out.println("Day " + iteration + ": " + lanternfish.get(iteration).size() + " lanternfish.");
        }

        return lanternfish;
    }

    private static Double countTotalLanternfish(List<List<LanternFish>> lanternfish) {
        double total = 0.0;
        for (List<LanternFish> day : lanternfish) {
            total += day.size();
        }
        return total;
    }

}
