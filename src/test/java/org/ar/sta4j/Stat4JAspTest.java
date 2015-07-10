package org.ar.sta4j;

import org.junit.Test;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
public class Stat4JAspTest {

    @Test
    public void testPerformanceMeassuring(){
        PerformanceTestObject performanceTestObject = new PerformanceTestObject();

        performanceTestObject.method1();
        performanceTestObject.method2();
        performanceTestObject.method3();
    }
}
