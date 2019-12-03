package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadUtils {

    public static int[] readFileAsVectorInts(String fileName, String separator) throws IOException {
        ClassLoader classLoader = ReadUtils.class.getClassLoader();
        File file = new File(classLoader.getResource("inputs/" + fileName).getFile());
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        String[] lineElements = line.split(separator);
        int[] ints = Arrays.asList(lineElements).stream().mapToInt(Integer::parseInt).toArray();
        br.close();

        return ints;
    }

    public static int[][] readFileAsMatrixInts(String fileName, String separator) throws IOException {
        ClassLoader classLoader = ReadUtils.class.getClassLoader();
        File file = new File(classLoader.getResource("inputs/" + fileName).getFile());
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<int[]> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] lineElements = line.split(separator);
            int[] ints = Arrays.asList(lineElements).stream().mapToInt(Integer::parseInt).toArray();
            lines.add(ints);
        }
        br.close();

        return lines.toArray(new int[0][0]);
    }

    public static String[][] readFileAsMatrixStrings(String fileName, String separator) throws IOException {
        ClassLoader classLoader = ReadUtils.class.getClassLoader();
        File file = new File(classLoader.getResource("inputs/" + fileName).getFile());
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<String[]> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] lineElements = line.split(separator);
            lines.add(lineElements);
        }
        br.close();

        return lines.toArray(new String[0][0]);
    }

    public static boolean isInBoundsInMatrix(int[][] m, int row, int col) {
        return row >=0 && row < m.length && col >=0 && col < m[0].length;
    }
}
