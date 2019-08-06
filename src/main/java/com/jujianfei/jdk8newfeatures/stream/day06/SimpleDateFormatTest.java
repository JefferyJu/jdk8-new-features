package com.jujianfei.jdk8newfeatures.stream.day06;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 17:15
 */
public class SimpleDateFormatTest {


    /**
     * SimpleDateFormat 存在线程安全问题
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> task = () -> sdf.parse("20190805");

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //通用线程池
        ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        //pool.execute(() -> System.out.println(Thread.currentThread().getName()));

        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future : results) {
            System.out.println(future.get());
        }

        pool.shutdown();//优雅地关机
    }

    /**
     * SimpleDateFormat 加锁
     */
    @Test
    public void test2() throws Exception {

        Callable<Date> task = () -> DateFormatThreadLoad.convert("20190805");

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //通用线程池
        ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future : results) {
            System.out.println(future.get());
        }
        pool.shutdown();//优雅地关机

    }


    /**
     *
     */
    @Test
    public void test3() throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = () -> LocalDate.parse("20190805", dtf);


        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //通用线程池
        ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        List<Future<LocalDate>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future : results) {
            System.out.println(future.get());
        }
        pool.shutdown();//优雅地关机

    }
}
