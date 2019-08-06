package com.jujianfei.jdk8newfeatures.stream.day06;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/6 10:30
 */
public class LocalDateTimeTest {
    /**
     * 1. LocalDate LocalTime LocalDateTime
     * 三个类的方法大致相同
     */
    @Test
    public void test() {

        // 创建日期时间
        System.out.println("现在日期与时间 : " + LocalDateTime.now());
        System.out.println("指定时间 :  " + LocalDateTime.of(2019, 8, 6, 14, 55, 56));

        // 日期比较
        LocalDateTime ldt1 = LocalDateTime.of(2018, 8, 6, 14, 55, 56);
        LocalDateTime ldt2 = LocalDateTime.now();
        System.out.println("现在时间在指定时间之后 :  " + ldt2.compareTo(ldt1));
        System.out.println("现在时间在指定时间之后 : " + ldt2.isAfter(ldt1));
        System.out.println("现在时间在指定时间之前 : " + ldt2.isBefore(ldt1));
        System.out.println("时间是否相等 : " + ldt2.equals(ldt1));

        //格式化打印
        System.out.println("现在时间(未格式化) : " + LocalDateTime.now());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在时间(格式化后) : " + LocalDateTime.now().format(dtf));
        //解析为日期时间
        TemporalAccessor temporalAccessor = dtf.parse("2019-08-06 22:20:22");
        LocalDateTime.from(temporalAccessor);
        System.out.println(LocalDateTime.parse("2019-08-06T22:20:22"));
        System.out.println(LocalDateTime.parse("2019-08-06 22:20:22", dtf));

        //获取(各种get方法)
        System.out.println("LocalDateTime.now().getDayOfMonth() = " + LocalDateTime.now().getDayOfMonth());
        System.out.println("LocalDateTime.now().get(ChronoField.HOUR_OF_DAY) = " + LocalDateTime.now().get(ChronoField.HOUR_OF_DAY));

        //转换
        System.out.println("LocalDateTime.now().toLocalDate() = " + LocalDateTime.now().toLocalDate());
        System.out.println("LocalDateTime.now().toLocalTime() = " + LocalDateTime.now().toLocalTime());
        System.out.println("LocalDateTime.now().toInstant(ZoneOffset.UTC) = " + LocalDateTime.now().toInstant(ZoneOffset.UTC));

        //更改(各种with方法)
        System.out.println("LocalDateTime.now().withYear(2020) = " + LocalDateTime.now().withYear(2020));

        //加减(各种plus方法和minus方法)
        System.out.println("LocalDateTime.now().plusYears(1) = " + LocalDateTime.now().plusYears(1));
        System.out.println("LocalDateTime.now().minusYears(1) = " + LocalDateTime.now().minusYears(1));

        //获取有效期
        System.out.println("LocalDateTime.now().range(ChronoField.HOUR_OF_DAY) = " + LocalDateTime.now().range(ChronoField.HOUR_OF_DAY));
        System.out.println("LocalDateTime.now().range(ChronoField.DAY_OF_WEEK) = " + LocalDateTime.now().range(ChronoField.DAY_OF_WEEK));
        System.out.println("LocalDateTime.now().range(ChronoField.MONTH_OF_YEAR) = " + LocalDateTime.now().range(ChronoField.MONTH_OF_YEAR));
        //截取
        System.out.println("LocalDateTime.now().truncatedTo(ChronoUnit.HOURS) = " + LocalDateTime.now().truncatedTo(ChronoUnit.HALF_DAYS));
    }

    /**
     * Instant: 时间戳（以Unix元年：1970年1月1日00：00：00到某个时间之间的毫秒值）
     */
    @Test
    public void test2() {
        //默认获取 UTC时区
        Instant now = Instant.now();
        System.out.println(now);
        //时区偏移
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        //时间戳
        System.out.println(now.toEpochMilli());
        //从毫秒获取Instant
        Instant instant = Instant.ofEpochSecond(1);
        System.out.println("Instant.ofEpochSecond(1000) = " + instant );
    }

    /**
     *  Duration：计算两个"时间"之间的间隔
     *  Period：计算两个"日期"之间的间隔
     */
    @Test
    public void test3() throws InterruptedException {

        Instant ins1 = Instant.now();

        Thread.sleep(1000);

        Instant ins2 = Instant.now();

        Duration duration = Duration.between(ins1, ins2);
        System.out.println("duration.toMillis() = " + duration.toMillis());
        System.out.println("duration.getSeconds() = " + duration.getSeconds());
        System.out.println("duration.getNano() = " + duration.getNano());
        System.out.println("duration.getUnits() = " + duration.getUnits());
        System.out.println("duration.toNanos() = " + duration.toNanos());
        System.out.println("duration.toMinutes() = " + duration.toMinutes());


        System.out.println("------------------------------------------------");

        LocalTime lt1 = LocalTime.now();

        Thread.sleep(1000);

        LocalTime lt2 = LocalTime.now();

        Duration duration1 = Duration.between(lt1, lt2);
        System.out.println("duration.toMillis() = " + duration1.toMillis());
        System.out.println("duration.getSeconds() = " + duration1.getSeconds());
        System.out.println("duration.getNano() = " + duration1.getNano());
        System.out.println("duration.getUnits() = " + duration1.getUnits());
        System.out.println("duration.toNanos() = " + duration1.toNanos());
        System.out.println("duration.toMinutes() = " + duration1.toMinutes());

        System.out.println("------------------------------------------------");

        LocalDate ld1 = LocalDate.of(1992, 2, 11);

        LocalDate now = LocalDate.now();

        Period period = Period.between(ld1, now);
        System.out.println(period);
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(period.getYears());
        System.out.println(period.toTotalMonths());

    }
}
