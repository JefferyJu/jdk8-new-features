# java8新特性-日期时间

[TOC]

## LocalDate  LocalTime   LocalDateTime 三个类的方法大致相同
```
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
        //截取日期时间
        System.out.println("LocalDateTime.now().truncatedTo(ChronoUnit.HOURS) = " + LocalDateTime.now().truncatedTo(ChronoUnit.HALF_DAYS));
        //两日期时间的差值(小值在前, 大值在后 为正值) --> 绝对值Math.abs()
        System.out.println("ldt1.until(ldt2, ChronoUnit.DAYS) = " + ldt1.until(ldt2, ChronoUnit.DAYS));
        System.out.println("ldt1.until(ldt2, ChronoUnit.YEARS) = " + ldt1.until(ldt2, ChronoUnit.MONTHS));
        System.out.println("ldt1.until(ldt2, ChronoUnit.YEARS) = " + ldt1.until(ldt2, ChronoUnit.YEARS));
    }
```
## Instant: 时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）

```
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
        System.out.println("Instant.ofEpochSecond(1000) = " + instant);
    }
```
## Duration：计算两个"时间"之间的间隔  Period：计算两个"日期"之间的间隔

```
    /**
     * Duration：计算两个"时间"之间的间隔
     * Period：计算两个"日期"之间的间隔
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
        System.out.println("ISO标准计数: " + period);
        System.out.println("年: " + period.getYears());
        System.out.println("月: " + period.getMonths());
        System.out.println("日: " + period.getDays());
        System.out.println("总月份: " + period.toTotalMonths());
        System.out.println("总天数: " + Math.abs(now.until(ld1, ChronoUnit.DAYS)));

    }


```

## TemporalAdjuster: 时间校正器 TemporalAdjusters: 工具类

```
    /**
     * TemporalAdjuster: 时间校正器
     * TemporalAdjusters: 工具类
     */
    @Test
    public void test4() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);
        //下个周日
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);
        //自定义: 下一个工作日
        System.out.println(ldt.with(temporal -> {
            LocalDateTime ldt4 = (LocalDateTime) temporal;
            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        }));
    }

```

## DateTimeFormatter: 格式化时间/日期

```
    /**
     * DateTimeFormatter: 格式化时间/日期
     */
    @Test
    public void test5() {
        //格式化
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now.format(dtf));

        System.out.println("-----------------------------------");
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(pattern.format(now));
        System.out.println(now.format(pattern));

        // 解析
        String dateStr = "2019-08-06 21:19:43";
        LocalDateTime ldt = LocalDateTime.parse(dateStr, pattern);
        System.out.println(ldt);
    }
```

## ZoneDate ZoneTime ZoneDateTime

```
    /**
     * ZoneDate ZoneTime ZoneDateTime
     */
    @Test
    public void test6() {
        // 显示所有时区
        //ZoneId.getAvailableZoneIds().forEach(System.out::println);
        System.out.println("---------------------------------------");

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Etc/UTC"));
        System.out.println("UTC时区的当前时间: " + localDateTime);// 2019-08-06T13:34:53.632
        System.out.println("---------------------------------------");


        LocalDateTime ldt2 = LocalDateTime.now();
        System.out.println("Asia/Shanghai时区: " + ldt2);//Asia/Shanghai时区: 2019-08-06T21:39:54.806
        ZonedDateTime zonedDateTime = ldt2.atZone(ZoneId.of("Etc/UTC"));
        System.out.println("UTC时区: " + zonedDateTime);//UTC时区: 2019-08-06T21:39:54.806Z[Etc/UTC]
        System.out.println("---------------------------------------");


        LocalDateTime ldt3 = LocalDateTime.now(ZoneId.of("Etc/UTC"));
        System.out.println("UTC时区的当前时间: " + ldt3);  //UTC时区的当前时间: 2019-08-06T13:39:54.808
        ZonedDateTime zonedDateTime2 = ldt3.atZone(ZoneId.of("Etc/UTC"));
        System.out.println("UTC时区的当前时间: " + zonedDateTime2); //UTC时区的当前时间: 2019-08-06T13:39:54.808Z[Etc/UTC]

    }

```