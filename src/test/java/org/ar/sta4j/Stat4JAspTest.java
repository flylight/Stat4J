package org.ar.sta4j;

import org.ar.stat4j.Stat4J;
import org.junit.Test;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
public class Stat4JAspTest {

    @Test
    public void testPerformanceMeassuring() throws InterruptedException {
        PerformanceTestObject performanceTestObject = new PerformanceTestObject();

        performanceTestObject.method2MS();
        performanceTestObject.method25MS();
        performanceTestObject.method25MS();
        performanceTestObject.method25MS();
        performanceTestObject.method25MS();
        performanceTestObject.method155MS();

        Stat4J.instance().printStatIntoSystemOut();
    }
}
