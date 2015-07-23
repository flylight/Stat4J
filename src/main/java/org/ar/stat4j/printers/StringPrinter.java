package org.ar.stat4j.printers;

import org.ar.stat4j.Statistic;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Andriy Rymar on 23.07.15.
 */
public class StringPrinter implements Printer{

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

    @Override
    public String print(Map<String, Map<String, Statistic>> statistic, boolean history) {
        final StringBuilder strBld = new StringBuilder();
        statistic.forEach((componentName, points) -> points.forEach((pointName, stats) -> strBld.append(generateComponentStatistic(componentName, pointName, stats, history))));
        return strBld.toString();
    }

    private String generateComponentStatistic(String componentName, String pointName, Statistic
        statistic, boolean history) {
        StringBuilder strBld = new StringBuilder();

        strBld.append(componentName).append(DOT_SEPARATOR).append(pointName).append(METHOD_SIGNATURE).append
            (TABULATION_AFTER_METHOD).append(CALL_TIMES).append(statistic.getPointSize()).append
            (MAX_TIME).append(statistic.getMaxExecutionTimeInNano()).append(NANOSECOND_SIGNATURE)
            .append(TIME_TYPE_SEPARATOR).append(statistic.getMaxExecutionTimeInMili()).append
            (MILISECOND_SIGNATURE).append(MIN_TIME).append(statistic.getMinExecutionTimeInNano())
            .append(NANOSECOND_SIGNATURE).append(TIME_TYPE_SEPARATOR).append(statistic
            .getMinExecutionTimeInMili()).append(MILISECOND_SIGNATURE).append("\t Avr: ").append
            (statistic.getAverageExecutionTimeInNano()).append(NANOSECOND_SIGNATURE).append
            (TIME_TYPE_SEPARATOR).append(statistic.getAverageExecutionTimeInMili()).append(MILISECOND_SIGNATURE).append
            (NEW_LINE);
        if (history && statistic.getPoints().size() > 1) {
            statistic.getPoints().forEach((statPoint) -> strBld.append(SPACE_FOR_HISTORY).append(pointName)
                .append(METHOD_SIGNATURE).append(EXECUTION_DATE).append(DATE_FORMAT
                    .format(statPoint.getExecutionDate())).append(EXECUTION_TIME).append(statPoint
                    .executionTimeInNanoseconds()).append(NANOSECOND_SIGNATURE).append
                    (TIME_TYPE_SEPARATOR).append(statPoint.executionTimeInMiliseconds()).append(MILISECOND_SIGNATURE).append(NEW_LINE));
        }

        return strBld.toString();
    }
}
