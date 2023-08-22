package pap.exercice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseFile {
    private final String filePath;

    public ParseFile(String filePath) {
        this.filePath = filePath;
    }

    public List<Integer> parseInt() throws IOException {
        List<Integer> nums = new ArrayList<>();
        Scanner sc = new Scanner(new File(filePath));

        while(sc.hasNextInt()) {
            nums.add(sc.nextInt());
        }

        sc.close();

        return nums;
    }
}
