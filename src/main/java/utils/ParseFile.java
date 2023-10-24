package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseFile {

    public static List<Integer> parseInt(String filename) throws IOException {
        List<Integer> nums = new ArrayList<>();
        Scanner sc = new Scanner(new File(filename));

        while(sc.hasNextInt()) {
            nums.add(sc.nextInt());
        }

        sc.close();

        return nums;
    }


    public static List<String> parseString(String filename) {
        List<String> list = new ArrayList<>();

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return list;
    }

}
