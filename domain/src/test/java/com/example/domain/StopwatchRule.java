package com.example.domain;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

public class StopwatchRule {

    public static Stopwatch create() {
        return new Stopwatch() {
            @Override
            public long runtime(TimeUnit unit) {
                return super.runtime(unit);
            }
            @Override
            protected void succeeded(long nanos, Description description) {
                System.out.println(description.getMethodName() + " succeeded, time taken " + toSeconds(nanos));
            }
            @Override
            protected void failed(long nanos, Throwable e, Description description) {
                super.failed(nanos, e, description);
            }
            @Override
            protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
                super.skipped(nanos, e, description);
            }
            @Override
            protected void finished(long nanos, Description description) {
                System.out.println(description.getMethodName() + " finished, time taken " + toSeconds(nanos));
            }
            private double toSeconds(long nanos) {
                return (double) nanos / 1000000000.0;
            }
        };
    }

}
