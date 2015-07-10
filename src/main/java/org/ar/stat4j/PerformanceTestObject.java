package org.ar.stat4j;

import org.ar.stat4j.annotations.Stat4JPoint;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
public class PerformanceTestObject {

    @Stat4JPoint
    public void method1(){}

    @Stat4JPoint
    public void method2(){}

    @Stat4JPoint
    public int method3(){
        return 0;
    }
}
