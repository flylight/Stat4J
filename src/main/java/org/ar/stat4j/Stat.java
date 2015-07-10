package org.ar.stat4j;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
public class Stat {
    private long startTrack;
    private long finishTrack;

    public Stat(long startTrack) {
        this.startTrack = startTrack;
    }

    public void finish(long finishTrack) {
        this.finishTrack = finishTrack;
    }

    public long executionTime() {
        return finishTrack-startTrack;
    }
}
