package org.ar.stat4j;


import java.text.SimpleDateFormat;
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
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM.dd.yy HH:mm:ss.SSS");
    public static final String EXECUTION_DATE = "\t Execution date : ";
    public static final String EXECUTION_TIME = "\t Execution time : ";
    public static final String SPACE_FOR_HISTORY = "\t\t";
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

    public void printStatIntoSystemOut(boolean history) {
        System.out.println(getStatisticAsString(history));
    }

    public String getStatisticAsString(boolean history) {
        final StringBuilder strBld = new StringBuilder();
        records.forEach((componentName, points) -> points.forEach((pointName, stats) -> strBld.append(generateComponentStatistic(componentName, pointName, stats, history))));
        return strBld.toString();
    }

    public String getStatisticAsHTML(boolean history) {
        return "";
    }

    public String getStatisticAsJSON(boolean history){
        return "";
    }


    private String generateComponentStatistic(String componentName, String pointName, Statistic
        statistic, boolean history) {
        StringBuilder strBld = new StringBuilder();

        strBld.append(componentName).append(DOT_SEPARATOR).append(pointName).append(METHOD_SIGNATURE).append
            (TABULATION_AFTER_METHOD).append(CALL_TIMES).append(statistic.getPointSize()).append(MAX_TIME).append(statistic.getMaxExecutionTimeInNano()).append(NANOSECOND_SIGNATURE).append(TIME_TYPE_SEPARATOR).append(statistic.getMaxExecutionTimeInMili()).append(MILISECOND_SIGNATURE).append(MIN_TIME).append(statistic.getMinExecutionTimeInNano()).append(MILISECOND_SIGNATURE).append(TIME_TYPE_SEPARATOR).append(statistic.getMinExecutionTimeInMili()).append(MILISECOND_SIGNATURE).append(NEW_LINE);
        if (history && statistic.getPoints().size() > 1) {
            statistic.getPoints().forEach((statPoint) -> strBld.append(SPACE_FOR_HISTORY).append(pointName)
                .append(METHOD_SIGNATURE).append(EXECUTION_DATE).append(DATE_FORMAT
                    .format(statPoint.getExecutionDate())).append(EXECUTION_TIME).append(statPoint
                    .executionTimeInNanoseconds()).append(NANOSECOND_SIGNATURE).append(TIME_TYPE_SEPARATOR).append(statPoint.executionTimeInMiliseconds()).append(MILISECOND_SIGNATURE).append(NEW_LINE));
        }

        return strBld.toString();
    }


    //TODO add calculation of average value
    //TODO add JSON printer
    //TODO add HTML printer
    //TODO move all pronters into separate classes
}
