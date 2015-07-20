package org.ar.stat4j;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andriy Rymar on 06.07.15.
 */
public class Stat4J {

    public static final String METHOD_SIGNATURE = "()";
    public static final String TABULATION_AFTER_METHOD = "\t\t : \t";
    public static final String MILISECOND_SIGNATURE = "(ms)";
    public static final String NEW_LINE = "\n";
    public static final String NANOSECOND_SIGNATURE = "(ns)";
    public static final String TIME_TYPE_SEPARATOR = "\t|\t";
    public static final String CALL_TIMES = "Call times : ";
    public static final String MAX_TIME = "\t Max: ";
    public static final String MIN_TIME = "\t Min: ";
    public static final String DOT_SEPARATOR = ".";
    private static Stat4J instance;

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

    public void printStatIntoSystemOut() {
        System.out.println(getStatisticAsString());
    }

    public String getStatisticAsString() {
        final StringBuilder strBld = new StringBuilder();
        records.forEach((componentName, points) -> points.forEach((pointName, stats) -> strBld.append(generateStatLine(componentName, pointName, stats))));
        return strBld.toString();
    }

    public String getStatisticAsHTML() {
        return "";
    }

    private String generateStatLine(String componentName, String pointName, Statistic statistic) {
        StringBuilder strBld = new StringBuilder();

        strBld.append(componentName).append(DOT_SEPARATOR).append(pointName).append(METHOD_SIGNATURE).append
            (TABULATION_AFTER_METHOD).append(CALL_TIMES).append(statistic.getPointSize()).append(MAX_TIME).append(statistic.getMaxExecutionTimeInNano()).append(NANOSECOND_SIGNATURE).append(TIME_TYPE_SEPARATOR).append(statistic.getMaxExecutionTimeInMili()).append(MILISECOND_SIGNATURE).append(MIN_TIME).append(statistic.getMinExecutionTimeInNano()).append(MILISECOND_SIGNATURE).append(TIME_TYPE_SEPARATOR).append(statistic.getMinExecutionTimeInMili()).append(MILISECOND_SIGNATURE).append(NEW_LINE);
        if (statistic.getPoints().size() > 1) {
            statistic.getPoints().forEach((stat) -> strBld.append("\t\t").append(pointName).append(METHOD_SIGNATURE).append(TABULATION_AFTER_METHOD).append(stat.executionTimeInNanoseconds()).append(NANOSECOND_SIGNATURE).append(TIME_TYPE_SEPARATOR).append(stat.executionTimeInMiliseconds()).append(MILISECOND_SIGNATURE).append(NEW_LINE));
        }

        return strBld.toString();
    }

}
