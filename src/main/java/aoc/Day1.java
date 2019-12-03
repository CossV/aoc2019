package aoc;

import utils.ReadUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = ReadUtils.class.getClassLoader();
            File file = new File(classLoader.getResource("inputs/input.in").getFile());
            Scanner sc = new Scanner(file);
            List<Long> lst = new ArrayList<>();
            while (sc.hasNextLine()) {
                lst.add(Long.parseLong(sc.nextLine()));
            }
            Long sum = 0L;
            for (int i = 0; i < lst.size(); i++) {
                Long mass = lst.get(i);
                mass = mass / 3;
                mass = mass - 2;
                sum += mass;
                while (mass > 0) {
                    mass = mass / 3;
                    mass = mass - 2;
                    if (mass >=0) {
                        sum += mass;
                    }
                }
            }
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
