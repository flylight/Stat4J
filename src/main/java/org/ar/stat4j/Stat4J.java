package org.ar.stat4j;


import org.ar.stat4j.exception.Stat4JNoComponentException;
import org.ar.stat4j.exception.Stat4JNoPointException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andriy Rymar on 06.07.15.
 */
public class Stat4J {

    private static Stat4J instance;

    private Map<String, Map<String, List<Stat>>> records;

    private Stat4J() {
        records = new HashMap<>();
    }

    public static Stat4J instance() {
        if (instance == null) {
            synchronized (Stat4J.class) {
                if (instance == null) {
                    instance = new Stat4J();
                }
            }
        }
        return instance;
    }

    public Stat startTrack(String componentName, String pointName) {
        Stat stat = new Stat(System.nanoTime());
        if (!records.containsKey(componentName)) {
            List<Stat> statList = new ArrayList<>();
            statList.add(stat);
            Map<String, List<Stat>> points = new HashMap<>();
            points.put(pointName, statList);
            records.put(componentName, points);
        } else if (!records.get(componentName).containsKey(pointName)) {
            List<Stat> statList = new ArrayList<>();
            statList.add(stat);
            records.get(componentName).put(pointName, statList);
        } else {
            records.get(componentName).get(pointName).add(stat);
        }
        return stat;
    }

    public void stopTrack(Stat stat) {
        stat.finish(System.nanoTime());
    }

    public void printStatIntoSystemOut() {
        System.out.println(getStatSource());
    }

    public StringBuilder getStatSource() {
        final StringBuilder strBld = new StringBuilder();
        records.forEach((comonentName, points) -> points.forEach((pointName, stats) -> stats.forEach((stat) -> strBld.append(
            comonentName + "." + pointName + "() \t: \t" + stat.executionTime() + "(ns)\t|\t"
                + stat.executionTime() / 1000000 + "(ms)\n"))));
        return strBld;
    }
}
