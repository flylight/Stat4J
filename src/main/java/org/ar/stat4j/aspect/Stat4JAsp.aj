package org.ar.stat4j.aspect;

import org.ar.stat4j.Point;
import org.ar.stat4j.Stat4J;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
@Aspect
public class Stat4JAsp {
    @Around("execution(* *(..)) && @annotation(org.ar.stat4j.annotations.Stat4JPoint)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Point statisticPoint =
            Stat4J.instance().startTrack(point.getTarget().getClass().getCanonicalName(), MethodSignature.class.cast(point.getSignature()).getMethod().getName());
        Object result = point.proceed();
        Stat4J.instance().stopTrack(statisticPoint);
        return result;
    }
}
