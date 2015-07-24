package org.ar.stat4j.printers;

import org.ar.stat4j.data.Statistic;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Andriy Rymar on 23.07.15.
 */
public class StringPrinter implements Printer{

    public static final String COMPONENT_OUTPUT = "%1s.%2s:\tCall times: %3s,\tMax: %4s"
        + "(ns)\t|\t%5s(ms),\tMin: %6s(ns)\t|\t%7s(ms),\tAvr: %8s(ns)\t|\t%9s(ms);\n";
    public static final String POINT_OUTPUT = "\t\t%1s:\tExecution date: %2s,\tExecution time: "
        + "%3s\t(ns)|\t%4s(ms);\n";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM.dd.yy HH:mm:ss.SSS");

    @Override
    public String print(Map<String, Map<String, Statistic>> statistic, boolean history) {
        final StringBuilder strBld = new StringBuilder();
        statistic.forEach((componentName, points) -> points.forEach((pointName, stats) -> strBld.append(generateComponentStatistic(componentName, pointName, stats, history))));
        return strBld.toString();
    }

    private String generateComponentStatistic(String componentName, String pointName, Statistic
        statistic, boolean history) {
        StringBuilder strBld = new StringBuilder();

        strBld.append(String.format(COMPONENT_OUTPUT, componentName, pointName, statistic.getPointSize(), statistic.getMaxExecutionTimeInNano(), statistic.getMaxExecutionTimeInMili(), statistic.getMinExecutionTimeInNano(), statistic.getMinExecutionTimeInMili(), statistic.getAverageExecutionTimeInNano(), statistic.getAverageExecutionTimeInMili()));

        if (history && statistic.getPoints().size() > 1) {
            statistic.getPoints().forEach((statPoint) -> strBld.append(String.format
                (POINT_OUTPUT,pointName,DATE_FORMAT.format(statPoint.getExecutionDate()),
                    statPoint.executionTimeInNanoseconds(),statPoint.executionTimeInMiliseconds()
                )));
        }

        return strBld.toString();
    }
}
