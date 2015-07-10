package org.ar.sta4j;

import org.ar.stat4j.annotations.Stat4JPoint;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
public class PerformanceTestObject {

    @Stat4JPoint
    public void method2MS() throws InterruptedException {
        Thread.sleep(2);
    }

    @Stat4JPoint
    public void method25MS() throws InterruptedException {
        Thread.sleep(25);
    }

    @Stat4JPoint
    public int method155MS() throws InterruptedException {
        Thread.sleep(155);
        return 155;
    }
}
