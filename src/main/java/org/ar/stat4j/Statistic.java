package org.ar.stat4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andriy Rymar on 20.07.15.
 */
public class Statistic {
    private List<Point> stats = new ArrayList<>();

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
