package org.ar.stat4j.printers;

import org.ar.stat4j.data.Statistic;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Andriy Rymar on 27.07.15.
 */
public class JSONPrinter implements Printer{

    public static final String RESULT_OUTPUT = "{\"stat4j_result\":[%s]}";
    public static final String COMPONENT_OUTPUT = "{ \"class\":\"%s\",\"method\":\"%s\","
        + "\"call_times\":\"%s\",\"max_exec_time_ns\":%s,\"max_exec_time_ms\":%s,"
        + "\"min_exec_time_ns\":%s,\"min_exec)time_ms\":%s,\"avr_exec_time_ns\":%s,"
        + "\"avr_exec_time_ms\":%s,\"call_history\":[%s]},";
    public static final String POINT_OUTPUT = "{\"execution_date\":\"%s\",\"exec_time_ns\":%s,"
        + "\"exec_time_ms\":%s},";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM.dd.yy HH:mm:ss.SSS");

    @Override
    public String print(Map<String, Map<String, Statistic>> statistic, boolean history) {

        final StringBuilder componentStatistic = new StringBuilder();
        statistic.forEach((componentName, points) -> points.forEach((pointName, stats) -> componentStatistic
            .append(generateComponentStatistic(componentName, pointName, stats, history))));

        return String.format(RESULT_OUTPUT, componentStatistic.deleteCharAt(componentStatistic.length()-1).toString());
    }

    private String generateComponentStatistic(String componentName, String pointName, Statistic
        statistic, boolean history) {
        StringBuilder componentHistory = new StringBuilder();


        if (history && statistic.getPoints().size() > 1) {
            statistic.getPoints().forEach((statPoint) -> componentHistory.append(String.format
                (POINT_OUTPUT,DATE_FORMAT.format(statPoint.getExecutionDate()),
                    statPoint.executionTimeInNanoseconds(),statPoint.executionTimeInMiliseconds()
                )));
        }

        return String.format(COMPONENT_OUTPUT, componentName, pointName, statistic.getPointSize()
            , statistic.getMaxExecutionTimeInNano(), statistic.getMaxExecutionTimeInMili(),
            statistic.getMinExecutionTimeInNano(), statistic.getMinExecutionTimeInMili(),
            statistic.getAverageExecutionTimeInNano(), statistic.getAverageExecutionTimeInMili(),
            componentHistory.deleteCharAt(componentHistory.length()-1));

    }
}
