package org.ar.stat4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andriy Rymar on 20.07.15.
 */
public class Statistic {
    private List<Point> stats = new ArrayList<>();
    private long sum = 0;
    public int getPointSize(){
        return stats.size();
    }

    public long getMaxExecutionTimeInNano(){
        if(!stats.isEmpty()) {
            Collections.sort(stats);
            return stats.get(stats.size()-1).executionTimeInNanoseconds();
        }
        return 0;
    }

    public long getMaxExecutionTimeInMili(){
        if(!stats.isEmpty()) {
            Collections.sort(stats);
            return stats.get(stats.size()-1).executionTimeInMiliseconds();
        }
        return 0;
    }

    public long getMinExecutionTimeInNano(){
        if(!stats.isEmpty()) {
            Collections.sort(stats);
            return stats.get(0).executionTimeInNanoseconds();
        }
        return 0;
    }

    public long getAverageExecutionTimeInNano(){
        sum = 0;
        stats.forEach(stat -> sum += stat.executionTimeInNanoseconds());
        return sum / stats.size();
    }

    public long getAverageExecutionTimeInMili(){
        sum = 0;
        stats.forEach(stat -> sum += stat.executionTimeInMiliseconds());
        return sum / stats.size();
    }

    public long getMinExecutionTimeInMili(){
        if(!stats.isEmpty()) {
            Collections.sort(stats);
            return stats.get(0).executionTimeInMiliseconds();
        }
        return 0;
    }


    public List<Point> getPoints() {
        return stats;
    }
}
