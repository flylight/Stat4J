package org.ar.sta4j;

import org.ar.stat4j.Stat4J;
import org.junit.Test;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
public class Point4JAspTest {

    @Test
    public void testPerformanceMeassuringStringOutput() throws InterruptedException {
        PerformanceTestObject performanceTestObject = new PerformanceTestObject();

        performanceTestObject.method2MS();
        performanceTestObject.method25MS();
        performanceTestObject.method25MS();
        performanceTestObject.method25MS();
        //performanceTestObject.method1Min();
        performanceTestObject.method25MS();
        performanceTestObject.method1Sec();
        performanceTestObject.method155MS();

        System.out.println(Stat4J.instance().getStatistic(Stat4J.OutputFormatType.STRING,true));
    }

    @Test
    public void testPerformanceMeassuringJSONOutput() throws InterruptedException {
        PerformanceTestObject performanceTestObject = new PerformanceTestObject();

        performanceTestObject.method2MS();
        performanceTestObject.method25MS();
        performanceTestObject.method25MS();
        performanceTestObject.method25MS();
        //performanceTestObject.method1Min();
        performanceTestObject.method25MS();
        performanceTestObject.method1Sec();
        performanceTestObject.method155MS();

        System.out.println(Stat4J.instance().getStatistic(Stat4J.OutputFormatType.JSON,true));
    }
}
