package org.ar.stat4j.printers;

import org.ar.stat4j.data.Statistic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Andriy Rymar on 27.07.15.
 */
public class HTMLPrinter implements Printer{
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM.dd.yy HH:mm:ss.SSS");
    public static final String RESULT_OUTPUT = "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table, th, td {\n" +
            "    border: 1px solid black;\n" +
            "    border-collapse: collapse;\n" +
            "}\n" +
            "th, td {\n" +
            "    padding: 5px;\n" +
            "    text-align: left;    \n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<h2>Stat4J Results:</h2>\n" +
            "<div>%date%</div>\n" +
            "<table style=\"width:100%\">\n" +
            "  <tr>\n" +
            "    <th>Class</th>\n" +
            "    <th>Method</th>\n" +
            "    <th>Call times</th>\n" +
            "    <th>Max (ns)</th>\n" +
            "    <th>Min (ns)</th>\n" +
            "    <th>Avr (ns)</th>\n" +
            "    <th>Max (ms)</th>\n" +
            "    <th>Min (ms)</th>\n" +
            "    <th>Avr (ms)</th>\n" +
            "  </tr>\n" +
            "%data%"+
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
    public static final String COMPONENT_OUTPUT = "  <tr>\n" +
            "    <th rowspan=\"2\">%s</th>\n" +
            "    <th rowspan=\"2\">%s</th>\n" +
            "    <td>%s</td>\n" +
            "    <td>%s</td>\n" +
            "    <td>%s</td>\n" +
            "    <td>%s</td>\n" +
            "    <td>%s</td>\n" +
            "    <td>%s</td>\n" +
            "    <td>%s</td>\n" +
            "  </tr>\n"+
            "  <tr>"+
            "  %s"+
            "  </tr>";
    public static final String POINT_OUTPUT = "    <td colspan=\"7\">\n" +
            "      <table style=\"width:100%\">\n" +
            "        <tr>\n" +
            "          <th>Date</th>\n" +
            "          <th>Execution time (ns)</th>\n" +
            "          <th>Execution time (ms)</th>\n" +
            "        </tr>\n" +
            "        %1s" +
            "      </table>\n" +
            "    </td>";
    public static final String POINT_INFO = "        <tr>\n" +
            "          <td>%s</td>\n" +
            "          <td>%s</td>\n" +
            "          <td>%s</td>\n" +
            "        </tr>";

    @Override
    public String print(Map<String, Map<String, Statistic>> statistic, boolean history) {

        final StringBuilder componentStatistic = new StringBuilder();
        statistic.forEach((componentName, points) -> points.forEach((pointName, stats) -> componentStatistic
                .append(generateComponentStatistic(componentName, pointName, stats, history))));

        return RESULT_OUTPUT.replaceAll("%date%" ,DATE_FORMAT.format(new Date(System.currentTimeMillis()))).replaceAll("%data%", componentStatistic.toString());
    }

    private String generateComponentStatistic(String componentName, String pointName, Statistic
        statistic, boolean history) {
        StringBuilder componentHistory = new StringBuilder();


        if (history && statistic.getPoints().size() > 1) {
            StringBuilder historyBlock = new StringBuilder();
            statistic.getPoints().forEach((statPoint) -> historyBlock.append(String.format
                    (POINT_INFO, DATE_FORMAT.format(statPoint.getExecutionDate()),
                    statPoint.executionTimeInNanoseconds(),statPoint.executionTimeInMiliseconds()
                )));
            componentHistory.append(POINT_OUTPUT.replaceAll("%1s", historyBlock.toString()));
        }

        return String.format(COMPONENT_OUTPUT, componentName, pointName, statistic.getPointSize()
            , statistic.getMaxExecutionTimeInNano(), statistic.getMinExecutionTimeInNano(),
            statistic.getAverageExecutionTimeInNano(), statistic.getMaxExecutionTimeInMili(),
            statistic.getMinExecutionTimeInMili(), statistic.getAverageExecutionTimeInMili(),
            componentHistory);

    }
}
