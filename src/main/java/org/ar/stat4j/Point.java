package org.ar.stat4j;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
public class Point implements Comparable<Point>{
    public static final int NANO_IN_MILIS = 1000000;
    private long startTrack;
    private long finishTrack;

    public Point(long startTrack) {
        this.startTrack = startTrack;
    }

    public void finish(long finishTrack) {
        this.finishTrack = finishTrack;
    }

    public long executionTimeInNanoseconds() {
        return finishTrack-startTrack;
    }

    public long executionTimeInMiliseconds(){
        return executionTimeInNanoseconds() / NANO_IN_MILIS;
    }

    @Override
    public int compareTo(Point point) {
        return this.executionTimeInNanoseconds() > point.executionTimeInNanoseconds() ? 1 :
            this.executionTimeInNanoseconds() < point.executionTimeInNanoseconds() ? -1 : 0;
    }
}
