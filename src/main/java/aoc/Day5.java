package aoc;

import utils.ReadUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = ReadUtils.class.getClassLoader();
            File file = new File(classLoader.getResource("inputs/input.in").getFile());
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            String[] split = line.split(",");
            int[] ints = Arrays.asList(split).stream().mapToInt(Integer::parseInt).toArray();
            int index = 0;
            try {
                while (index < ints.length) {
                    int opcode = ints[index] % 100;
                    int paramType1 = (ints[index] / 100) % 10;
                    int paramType2 = (ints[index] / 1000) % 10;
                    int paramType3 = (ints[index] / 10000) % 10;
                    if (paramType1 > 1 || paramType2 > 1) {
                        System.out.println("!!!!!!!!!!!");
                    }
                    if (opcode == 3 || opcode == 4) {
                        if (ints[index] == 3) {
                            Scanner sc2 = new Scanner(System.in);
                            int newIn = sc2.nextInt();
                            if (paramType1 == 0) {
                                ints[ints[index + 1]] = newIn;
                            } else {
                                ints[index + 1] = newIn;
                            }
                        } else if (ints[index] == 4) {
                            System.out.println(ints[ints[index + 1]]);
                        }
                        index += 2;
                    } else if (opcode == 1 || opcode == 2 || opcode == 99) {
                        // Extract opcode and param types
                        int sum1 = paramType1 == 0 ? ints[ints[index + 1]] : ints[index + 1];
                        int sum2 = paramType2 == 0 ? ints[ints[index + 2]] : ints[index + 2];
                        if (opcode == 1) {
                            int sum = sum1 + sum2;
                            if (paramType3 == 0) {
                                ints[ints[index + 3]] = sum;
                            } else {
                                ints[index + 3] = sum;
                            }
                        } else if (opcode == 2) {
                            int sum = sum1 * sum2;
                            if (paramType3 == 0) {
                                ints[ints[index + 3]] = sum;
                            } else {
                                ints[index + 3] = sum;
                            }
                        } else if (opcode == 99) {
                            break;
                        }
                        index += 4;
                    } else if (opcode == 5) {
                        int val = paramType1 == 0 ? ints[ints[index + 1]] : ints[index + 1];
                        int val2 = paramType2 == 0 ? ints[ints[index + 2]] : ints[index + 2];
                        if (val != 0) {
                            index = val2;
                        } else {
                            index += 3;
                        }
                    } else if (opcode == 6) {
                        int val = paramType1 == 0 ? ints[ints[index + 1]] : ints[index + 1];
                        int val2 = paramType2 == 0 ? ints[ints[index + 2]] : ints[index + 2];
                        if (val == 0) {
                            index = val2;
                        } else {
                            index += 3;
                        }
                    } else if (opcode == 7) {
                        int val1 = paramType1 == 0 ? ints[ints[index + 1]] : ints[index + 1];
                        int val2 = paramType2 == 0 ? ints[ints[index + 2]] : ints[index + 2];
                        if (val1 < val2) {
                            if (paramType3 == 0) {
                                ints[ints[index + 3]] = 1;
                            } else {
                                ints[index + 3] = 1;
                            }
                        } else {
                            if (paramType3 == 0) {
                                ints[ints[index + 3]] = 0;
                            } else {
                                ints[index + 3] = 0;
                            }
                        }
                        index += 4;
                    } else if (opcode == 8) {
                        int val1 = paramType1 == 0 ? ints[ints[index + 1]] : ints[index + 1];
                        int val2 = paramType2 == 0 ? ints[ints[index + 2]] : ints[index + 2];
                        if (val1 == val2) {
                            if (paramType3 == 0) {
                                ints[ints[index + 3]] = 1;
                            } else {
                                ints[index + 3] = 1;
                            }
                        } else {
                            if (paramType3 == 0) {
                                ints[ints[index + 3]] = 0;
                            } else {
                                ints[index + 3] = 0;
                            }
                        }
                        index += 4;
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
