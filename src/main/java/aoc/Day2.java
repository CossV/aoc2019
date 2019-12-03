package aoc;

import utils.ReadUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = ReadUtils.class.getClassLoader();
            File file = new File(classLoader.getResource("inputs/input.in").getFile());
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            String[] split = line.split(",");
            int noun = 0;
            int verb = 0;
            while (true) {
                int[] ints = Arrays.asList(split).stream().mapToInt(Integer::parseInt).toArray();
                System.out.println("Trying " + noun + " " + verb);
                ints[1] = noun;
                ints[2] = verb;
                int index = 0;
                try {
                    while (index < ints.length) {
                        if (ints[index] == 1) {
                            int sum = ints[ints[index + 1]] + ints[ints[index + 2]];
                            ints[ints[index + 3]] = sum;
                        } else if (ints[index] == 2) {
                            int sum = ints[ints[index + 1]] * ints[ints[index + 2]];
                            ints[ints[index + 3]] = sum;
                        } else if (ints[index] == 99) {
                            break;
                        }
                        index += 4;
                    }
                } catch (Exception e) {

                }
                if (ints[0] == 19690720) {
                    long res = 100*noun + verb;
                    System.out.println(res);
                    break;
                } else {
                    if (verb < 99) {
                        verb ++;
                    } else {
                        noun++;
                        verb = 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
