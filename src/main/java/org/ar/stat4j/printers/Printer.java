package org.ar.stat4j.printers;

import org.ar.stat4j.Statistic;

import java.util.Map;

/**
 * Created by Andriy Rymar on 23.07.15.
 */
public interface Printer {
    String print(Map<String, Map<String, Statistic>> statistic, boolean history);
}
