package aoc;

import utils.ReadUtils;

import java.io.File;
import java.util.*;

public class Day7 {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = ReadUtils.class.getClassLoader();
            File file = new File(classLoader.getResource("inputs/input2.in").getFile());
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            String[] split = line.split(",");

            int max = Integer.MIN_VALUE;
            String phases = "";
            for (int i = 5; i < 10; i++) {
                for (int j = 5; j < 10; j++) {
                    for (int k = 5; k < 10; k++) {
                        for (int l = 5; l < 10; l++) {
                            for (int m = 5; m < 10; m++) {
                                // One combination
                                HashSet<Integer> set = new HashSet<>();
                                set.add(i);
                                set.add(j);
                                set.add(k);
                                set.add(l);
                                set.add(m);
                                if (set.size() == 5) {
                                    System.out.println("Trying: " + i + "" + j + "" + k + "" + l + "" + m);
                                    int currInput = 0;
                                    int thE = 0;

                                    int[] ints = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                                    int[] intsA = Arrays.copyOf(ints, ints.length);
                                    int[] intsB = Arrays.copyOf(ints, ints.length);
                                    int[] intsC = Arrays.copyOf(ints, ints.length);
                                    int[] intsD = Arrays.copyOf(ints, ints.length);
                                    int[] intsE = Arrays.copyOf(ints, ints.length);
                                    int n = 0;
                                    while (true) {
                                        if (n == 0) {
//                                            System.out.println("A input: " + currInput);
                                            currInput = intcode(intsA, i, currInput);
//                                            System.out.println("A output: " + currInput);
                                        }
                                        if (n == 1) {
//                                            System.out.println("B input: " + currInput);
                                            currInput = intcode(intsB, j, currInput);
//                                            System.out.println("B output: " + currInput);
                                        }
                                        if (n == 2) {
//                                            System.out.println("C input: " + currInput);
                                            currInput = intcode(intsC, k, currInput);
//                                            System.out.println("C output: " + currInput);
                                        }
                                        if (n == 3) {
//                                            System.out.println("D input: " + currInput);
                                            currInput = intcode(intsD, l, currInput);
//                                            System.out.println("D output: " + currInput);
                                        }
                                        if (n == 4) {
//                                            System.out.println("E input: " + currInput);
                                            currInput = intcode(intsE, m, currInput);
//                                            System.out.println("E output: " + currInput);
                                            if (currInput != Integer.MAX_VALUE) {
                                                thE = currInput;
                                            }
                                        }
                                        if (currInput == Integer.MAX_VALUE) {
                                            // halted
                                            break;
                                        }
                                        n++;
                                        n = n % 5;
                                    }
                                    if (max < thE) {
                                        max = thE;
                                        phases = i + "" + j + "" + k + "" + l + "" + m;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(max + " - " + phases);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int intcode(int[] ints, int phase, int input) {
        int index = 0;
        int readPointer = 0;
        int breakCode = 0;
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
                        int newIn = input;
                        if (readPointer == 0) {
                            newIn = phase;
                        }
                        readPointer++;
                        if (paramType1 == 0) {
                            ints[ints[index + 1]] = newIn;
                        } else {
                            ints[index + 1] = newIn;
                        }
                    } else if (ints[index] == 4) {
                        return ints[ints[index + 1]];
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
                        return Integer.MAX_VALUE;
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
        } catch (Exception ex) {
        }

        return Integer.MAX_VALUE;
    }
}
