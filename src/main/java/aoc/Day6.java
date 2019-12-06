package aoc;

import utils.ReadUtils;

import java.io.File;
import java.util.*;

public class Day6 {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = ReadUtils.class.getClassLoader();
            File file = new File(classLoader.getResource("inputs/input.in").getFile());
            Scanner sc = new Scanner(file);
            HashSet<String> allP = new HashSet<>();
            Map<String, List<String>> mapP = new HashMap<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\\)");
                String p1 = split[0];
                String p2 = split[1];
                allP.add(p1);
                allP.add(p2);
                List<String> currP = new ArrayList<>();
                mapP.putIfAbsent(p1, currP);
                List<String> strings = mapP.get(p1);
                strings.add(p2);
            }
            int counter = 0;
            allP.clear();
            allP.add("YOU");
            allP.add("SAN");
            List<String> visitedPlanets = new ArrayList<>();
            String commonPlanet = null;
            xx: for (String p : allP) {
                String currPlanet = p;
                while (currPlanet != null) {
                    Set<Map.Entry<String, List<String>>> entries = mapP.entrySet();
                    boolean foundOrbiting = false;
                    for (Map.Entry<String, List<String>> entry : entries) {
                        List<String> value = entry.getValue();
                        if (value.contains(currPlanet)) {
                            currPlanet = entry.getKey();
                            if (visitedPlanets.contains(currPlanet)) {
                                commonPlanet = currPlanet;
                                break xx;
                            }
                            visitedPlanets.add(currPlanet);
                            counter++;
                            foundOrbiting = true;
                        }
                    }
                    if (!foundOrbiting) {
                        currPlanet = null;
                    }
                }
            }

            counter = 0;
            for (String p : allP) {
                String currPlanet = p;
                aa: while (currPlanet != null) {
                    Set<Map.Entry<String, List<String>>> entries = mapP.entrySet();
                    for (Map.Entry<String, List<String>> entry : entries) {
                        List<String> value = entry.getValue();
                        if (value.contains(currPlanet)) {
                            currPlanet = entry.getKey();
                            counter++;
                            if (currPlanet.equalsIgnoreCase(commonPlanet)) {
                                break aa;
                            }
                        }
                    }
                }
            }
            System.out.println(counter - 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
