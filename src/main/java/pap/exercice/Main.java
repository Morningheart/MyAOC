package pap.exercice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(Main.stepSolver(false));
        System.out.println(Main.stepSolver(true));
    }

    public static int stepSolver(boolean isPart2) throws IOException {
        // Parse file to an Integer List
        ParseFile parseFile = new ParseFile("C:\\prjet\\AOC\\src\\main\\java\\pap\\exercice\\input.txt");
        List<Integer> numberList = parseFile.parseInt();

        // Copy list to still have a copy of the original input
        List<Integer> tempNumberList = new ArrayList<>(numberList);

        // Init while's variables
        int steps = 0; // Step counter
        int index = 0; // Current index

        // Loop until index exits list
        while(index >= 0 && index < tempNumberList.size()) {
            int increaseBy = 1;
            int previousIndex = index;
            index += tempNumberList.get(index);

            // Condition of the part 2
            if(isPart2 && index - previousIndex >= 3) {
                increaseBy = -1;
            }

            // Process the value
            tempNumberList.set(previousIndex, tempNumberList.get(previousIndex) + increaseBy);

            // Increse steps
            steps++;
        }

        return steps;
    }
}