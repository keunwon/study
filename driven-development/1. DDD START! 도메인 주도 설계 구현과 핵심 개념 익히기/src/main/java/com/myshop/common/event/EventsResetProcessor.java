package com.myshop.common.event;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

//@Aspect
//@Order(0)
//@Component
public class EventsResetProcessor {
    private ThreadLocal<Integer> nestedCount = new ThreadLocal<>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    @Around("execution(public * com.myshop..*Service.*(..))")
    public Object doObject(ProceedingJoinPoint joinPoint) throws Throwable {
        nestedCount.set(nestedCount.get() + 1);

        try {
            return joinPoint.proceed();
        } finally {
            nestedCount.set(nestedCount.get() - 1);
            if (nestedCount.get() == 0) {
                //Events.reset();
            }
        }
    }
}
