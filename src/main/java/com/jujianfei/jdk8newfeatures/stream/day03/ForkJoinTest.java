package com.jujianfei.jdk8newfeatures.stream.day03;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 13:32
 */
public class ForkJoinTest {
    /**
     * Fork-Join 框架
     */
    @Test
    public void test() {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 10000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }


    @Test
    public void test2() {
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0L; i <= 10000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }

    /**
     * java8并行流
     */
    @Test
    public void test3() {
        Instant start = Instant.now();
        // 串行流 sequential() 或者不写
        long sum = LongStream.rangeClosed((0), 10000000000L)
                .sequential()
                .reduce(0, Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

        Instant start1 = Instant.now();
        // 并行流 parallel()
        long sum1 = LongStream.rangeClosed((0), 10000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(sum1);
        Instant end1 = Instant.now();
        System.out.println(Duration.between(start1, end1).toMillis());


    }
}
