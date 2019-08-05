package com.jujianfei.jdk8newfeatures.stream.day03;

import java.util.concurrent.RecursiveTask;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 11:40
 */
public class ForkJoinCalculate extends RecursiveTask {
    private static final long serialVersionUID = -2059005605485623026L;

    private long start;
    private long end;

    private static final long THRESHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;

            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();//拆分子任务,同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();
            return (Long) left.join() + (Long) right.join();
        }
    }
}
