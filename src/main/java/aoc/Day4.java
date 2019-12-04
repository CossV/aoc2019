package aoc;

import utils.ReadUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day4 {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = ReadUtils.class.getClassLoader();
            File file = new File(classLoader.getResource("inputs/input.in").getFile());
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            String[] split = line.split("-");
            int min = Integer.parseInt(split[0]);
            int max = Integer.parseInt(split[1]);
            int count = 0;
            for (int i = min; i <= max; i++) {
                String aa = i + "";
                boolean cond1 = false;
                for (int j = 0; j < aa.length() - 1; j++) {
                    char c = aa.charAt(j);
                    if (c == aa.charAt(j+1)) {
                        if (aa.replace(c + "", "").length() >= aa.length() - 2) {
                            cond1 = true;
                            break;
                        }
                    }
                }
                if (cond1) {
                    boolean cond2 = true;
                    for (int j = 0; j < aa.length() - 1; j++) {
                        char c = aa.charAt(j);
                        char c2 = aa.charAt(j + 1);
                        if (Integer.parseInt(c + "") > Integer.parseInt(c2 + "")) {
                            cond2 = false;
                        }
                    }
                    if (cond2) {
                        count++;
                    }
                }

            }
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
