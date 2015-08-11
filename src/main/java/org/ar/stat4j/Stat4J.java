package org.ar.stat4j;


import org.ar.stat4j.data.Point;
import org.ar.stat4j.data.Statistic;
import org.ar.stat4j.printers.HTMLPrinter;
import org.ar.stat4j.printers.JSONPrinter;
import org.ar.stat4j.printers.StringPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andriy Rymar on 06.07.15.
 */
public class Stat4J {
    private static Stat4J instance;
    private final StringPrinter stringPrinter = new StringPrinter();
    private final JSONPrinter jsonPrinter = new JSONPrinter();
    private final HTMLPrinter htmlPrinter = new HTMLPrinter();

    private Map<String, Map<String, Statistic>> records;

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

    public Point startTrack(String componentName, String pointName) {
        Point point = new Point(System.nanoTime());
        if (!records.containsKey(componentName)) {
            Statistic statistic = new Statistic();
            statistic.getPoints().add(point);
            Map<String, Statistic> pointToStat = new HashMap<>();
            pointToStat.put(pointName, statistic);
            records.put(componentName, pointToStat);
        } else if (!records.get(componentName).containsKey(pointName)) {
            Statistic statistic = new Statistic();
            statistic.getPoints().add(point);
            records.get(componentName).put(pointName, statistic);
        } else {
            records.get(componentName).get(pointName).getPoints().add(point);
        }
        return point;
    }

    public void stopTrack(Point point) {
        point.finish(System.nanoTime());
    }

    public String getStatistic(OutputFormatType outputFormatType, boolean history){
        switch(outputFormatType){
            case STRING:
                return stringPrinter.print(records,history);
            case HTML:
                return htmlPrinter.print(records,history);
            case JSON:
                return jsonPrinter.print(records,history);
            default:
                return stringPrinter.print(records,false);
        }

    }

    public enum OutputFormatType{
        STRING, HTML, JSON;
    }

    //TODO add JSON printer
    //TODO add HTML printer
}
