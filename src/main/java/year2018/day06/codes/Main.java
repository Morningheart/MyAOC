package year2018.day06.codes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.AOC();
    }

    private void AOC() {
        System.out.println("Partie 1 : " + this.partOne(this.customParsing("src/main/java/year2018/day06/inputs/input.txt")));
        System.out.println("Partie 1 Enonce : " + this.partOne(this.customParsing("src/main/java/year2018/day06/inputs/inputEnonce.txt")));

        System.out.println("Partie 2 : " + this.partTwo(this.customParsing("src/main/java/year2018/day06/inputs/input.txt"), 10000));
        System.out.println("Partie 2 Enonce : " + this.partTwo(this.customParsing("src/main/java/year2018/day06/inputs/inputEnonce.txt"), 32));
    }

    private List<Coordinate> customParsing(String filename){
        List<Coordinate> list = new ArrayList<>();

        String line;
        int xmin = Integer.MAX_VALUE;
        int ymin = Integer.MAX_VALUE;
        int xmax = Integer.MIN_VALUE;
        int ymax = Integer.MIN_VALUE;

        int idCoordinate = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                Pattern pattern = Pattern.compile("(?<x>\\d+), (?<y>\\d+)");
                Matcher matcher = pattern.matcher(line);
                matcher.matches();
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));

                list.add(new Coordinate(idCoordinate++, x, y));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return list;
    }

    private final int fieldSize = 1000;

    public String partOne(List<Coordinate> input) {
        int[][] grid = new int[this.fieldSize][this.fieldSize]; // Square Grid with a side lenght = max(deltaX, deltaY)

        for (int x = 0; x < this.fieldSize; x++) {
            for (int y = 0; y < this.fieldSize; y++) {
                grid[x][y] = findClosestCoordinateID(input, x, y);
            }
        }

        //filter out all that touch edges, they would be infinite
        Set<Integer> edges = gatherEdgeID(grid);

        //count all non infinite
        int[] count = new int[input.size()];
        for (int[] line : grid) {
            for (int idClosest : line) {
                if (!edges.contains(idClosest) && idClosest >= 0) {
                    count[idClosest]++;
                }
            }
        }

        int max = Arrays.stream(count)
                .max()
                .orElseThrow(NoSuchElementException::new);


        return String.valueOf(max);
    }

    private Set<Integer> gatherEdgeID(int[][] grid) {
        Set<Integer> res = new HashSet<>();

        for (int i = 0; i < fieldSize - 1; i++) {
            int edge = grid[fieldSize - 1][i];
            res.add(edge);

            edge = grid[i][fieldSize - 1];
            res.add(edge);

            edge = grid[i][0];
            res.add(edge);

            edge = grid[0][i];
            res.add(edge);
        }

        return res;
    }

    private int findClosestCoordinateID(List<Coordinate> input, int x, int y) {
        // Default value
        int closest = Integer.MAX_VALUE;
        int closestID = 0;

        for (Coordinate coordinate : input) {
            int distance = calculateManhattanDistance(coordinate.getX(), x, coordinate.getY(), y);

            if (distance == closest) {
                closestID = -1;
            }
            if (distance < closest) {
                closest = distance;
                closestID = coordinate.getId();
            }

        }

        return closestID;
    }

    public String partTwo(List<Coordinate> input, int less) {
        int cpt = 0;
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                int distance = 0;
                for (Coordinate coordinate : input) {
                    distance += calculateManhattanDistance(x, coordinate.getX(), y, coordinate.getY());
                }

                if (distance < less) {
                    cpt++;
                }
            }
        }


        return String.valueOf(cpt);
    }

    private int calculateManhattanDistance(int x0, int x1, int y0, int y1) {
        return Math.abs(x1 - x0) + Math.abs(y1 - y0);
    }
}
