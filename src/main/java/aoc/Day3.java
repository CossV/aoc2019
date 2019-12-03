package aoc;

import utils.ReadUtils;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.*;

public class Day3 {

    public static void main(String[] args) {
        try {
            int sizeX = 50000;
            int sizeY = 50000;
            ClassLoader classLoader = ReadUtils.class.getClassLoader();
            File file = new File(classLoader.getResource("inputs/input.in").getFile());
            Scanner sc = new Scanner(file);
            String line1 = sc.nextLine();
            String line2 = sc.nextLine();
            String[] splitLine1 = line1.split(",");
            String[] splitLine2 = line2.split(",");
            int[][] board = new int[sizeX][sizeY];
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    board[i][j] = 0;
                }
            }

            int sp1 = sizeX / 2;
            int sp2 = sizeY / 2;

            for (int i = 0; i < splitLine1.length; i++) {
                String i1 = splitLine1[i];
                String instr = i1.substring(0, 1);
                Integer count = Integer.parseInt(i1.substring(1));
                if (instr.equalsIgnoreCase("R")) {
                    for (int j = 1; j <= count; j++) {
                        board[sp1][sp2 + j] = 1;
                    }
                    sp2 = sp2 + count;
                } else if (instr.equalsIgnoreCase("U")) {
                    for (int j = 1; j <= count; j++) {
                        board[sp1 - j][sp2] = 1;
                    }
                    sp1 = sp1 - count;
                } else if (instr.equalsIgnoreCase("D")) {
                    for (int j = 1; j <= count; j++) {
                        board[sp1 + j][sp2] = 1;
                    }
                    sp1 = sp1 + count;
                } else if (instr.equalsIgnoreCase("L")) {
                    for (int j = 1; j <= count; j++) {
                        board[sp1][sp2 - j] = 1;
                    }
                    sp2 = sp2 - count;
                }
            }

            int steps2 = 0;
            List<Point> points = new ArrayList<>();
            Point o = new Point(sizeX / 2, sizeY / 2);
            System.out.println("origin: " + o);
            sp1 = sizeX / 2;
            sp2 = sizeY / 2;
            Map<Point, Integer> pToD1 = new HashMap<>();
            for (int i = 0; i < splitLine2.length; i++) {
                String i2 = splitLine2[i];
                String instr = i2.substring(0, 1);
                Integer count = Integer.parseInt(i2.substring(1));
                if (instr.equalsIgnoreCase("R")) {
                    for (int j = 1; j <= count; j++) {
                        int newCol = sp2 + j;
                        if (board[sp1][newCol] == 1) {
                            Point point = new Point(sp1, newCol);
                            points.add(point);
                            board[sp1][newCol] = 3;
                            pToD1.put(point, steps2 + 1);
                        } else {
                            board[sp1][newCol] = 2;
                        }
                        steps2++;
                    }
                    sp2 = sp2 + count;
                } else if (instr.equalsIgnoreCase("U")) {
                    for (int j = 1; j <= count; j++) {
                        int newRow = sp1 - j;
                        if (board[newRow][sp2] == 1) {
                            Point point = new Point(newRow, sp2);
                            points.add(point);
                            board[newRow][sp2] = 3;
                            pToD1.put(point, steps2 + 1);
                        } else {
                            board[newRow][sp2] = 2;
                        }
                        steps2++;
                    }
                    sp1 = sp1 - count;
                } else if (instr.equalsIgnoreCase("D")) {
                    for (int j = 1; j <= count; j++) {
                        int newRow = sp1 + j;
                        if (board[newRow][sp2] == 1) {
                            Point point = new Point(newRow, sp2);
                            points.add(point);
                            board[newRow][sp2] = 3;
                            pToD1.put(point, steps2 + 1);
                        } else {
                            board[newRow][sp2] = 2;
                        }
                        steps2++;
                    }
                    sp1 = sp1 + count;
                } else if (instr.equalsIgnoreCase("L")) {
                    for (int j = 1; j <= count; j++) {
                        int newCol = sp2 - j;
                        if (board[sp1][newCol] == 1) {
                            Point point = new Point(sp1, newCol);
                            points.add(point);
                            board[sp1][newCol] = 3;
                            pToD1.put(point, steps2 + 1);
                        } else {
                            board[sp1][newCol] = 2;
                        }
                        steps2++;
                    }
                    sp2 = sp2 - count;
                }
            }



            sp1 = sizeX / 2;
            sp2 = sizeY / 2;
            steps2 = 0;
            Map<Point, Integer> pToD2 = new HashMap<>();
            for (int i = 0; i < splitLine1.length; i++) {
                String i1 = splitLine1[i];
                String instr = i1.substring(0, 1);
                Integer count = Integer.parseInt(i1.substring(1));
                if (instr.equalsIgnoreCase("R")) {
                    for (int j = 1; j <= count; j++) {
                        if (board[sp1][sp2 + j] == 3) {
                            Point point = new Point(sp1, sp2 + j);
                            pToD2.put(point, steps2 + 1);
                        }
                        steps2++;
                    }
                    sp2 = sp2 + count;
                } else if (instr.equalsIgnoreCase("U")) {
                    for (int j = 1; j <= count; j++) {
                        if (board[sp1 - j][sp2] == 3) {
                            Point point = new Point(sp1 - j, sp2);
                            pToD2.put(point, steps2 + 1);
                        }
                        steps2++;
                    }
                    sp1 = sp1 - count;
                } else if (instr.equalsIgnoreCase("D")) {
                    for (int j = 1; j <= count; j++) {
                        if (board[sp1 + j][sp2] == 3) {
                            Point point = new Point(sp1 + j, sp2);
                            pToD2.put(point, steps2 + 1);
                        }
                        steps2++;
                    }
                    sp1 = sp1 + count;
                } else if (instr.equalsIgnoreCase("L")) {
                    for (int j = 1; j <= count; j++) {
                        if (board[sp1][sp2 - j] == 3) {
                            Point point = new Point(sp1, sp2 - j);
                            pToD2.put(point, steps2 + 1);
                        }
                        steps2++;
                    }
                    sp2 = sp2 - count;
                }
            }

//            int minDist = Integer.MAX_VALUE;
//            for (int i = 0; i < points.size(); i++) {
//                Point point = points.get(i);
//                int dist = Math.abs(point.x - o.x) + Math.abs(point.y - o.y);
//                if (dist < minDist) {
//                    minDist = dist;
//                }
//            }
            System.out.println(points.size());
            System.out.println(pToD1.size());
            System.out.println(pToD2.size());
            int minSteps = Integer.MAX_VALUE;
            for (int i = 0; i < points.size(); i++) {
                Integer s1 = pToD1.get(points.get(i));
                Integer s2 = pToD2.get(points.get(i));
                if (s1+s2 < minSteps) {
                    minSteps = s1 + s2;
                }
            }
            System.out.println(minSteps);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
