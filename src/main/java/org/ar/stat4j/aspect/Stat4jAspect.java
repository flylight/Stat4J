package org.ar.stat4j.aspect;

import org.ar.stat4j.Stat4J;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Andriy Rymar on 10.07.15.
 */
@Aspect
public class Stat4jAspect {
    @Around("execution(* *(..)) && @annotation(Stat4JPoint)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Stat4J.instance().startTrack(MethodSignature.class.getName(), MethodSignature.class.cast(point.getSignature()).getMethod().getName());
        Object result = point.proceed();
        Stat4J.instance().stopTrack(MethodSignature.class.getName(), MethodSignature.class.cast(point.getSignature()).getMethod().getName());
        return result;
    }
}
