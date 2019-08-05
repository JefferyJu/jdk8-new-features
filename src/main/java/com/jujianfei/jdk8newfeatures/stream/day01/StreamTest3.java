package com.jujianfei.jdk8newfeatures.stream.day01;

import com.jujianfei.jdk8newfeatures.lambda.day01.Status;
import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 一、Stream的三个操作步骤：
 * 1.创建 Stream
 * 2.中间操作
 * 3.终止操作（终端操作)
 *
 * @author jujianfei
 * @date 2019/8/1 22:04
 */
public class StreamTest3 {

    List<User> users = Arrays.asList(
            new User("张三", 33, 3333.33, Status.BUSY),
            new User("李四", 44, 4444.44, Status.VACATION),
            new User("王五", 55, 5555.55, Status.FREE),
            new User("赵六", 66, 6666.66, Status.VACATION),
            new User("田七", 77, 7777.77, Status.BUSY),
            new User("田七", 77, 7777.77, Status.BUSY)
    );

    /**
     * 终止操作
     * <p>
     * 查找与匹配
     * allMatch-----检查是否匹配所有元素
     * anyMatch-----检查是否至少匹配一个元素
     * noneMatch----检查是否没有匹配所有元素
     * findFirst----返回第一个元素
     * findAny------返回当前流中的任意元素
     * count--------返回流中元素的总个数
     * max---------返回流中最大值
     * min----------返回流中最小值
     */
    @Test
    public void test1() {
        // 匹配所有status == busy 则为true
        System.out.println("allMatch() : " + users.stream().allMatch(user -> Status.BUSY == user.getStatus()));
        // 至少一个status == busy 则为true
        System.out.println("anyMatch() : " + users.stream().anyMatch(user -> Status.BUSY == user.getStatus()));
        // 没有一个status == busy 则为true
        System.out.println("noneMatch() : " + users.stream().noneMatch(user -> Status.BUSY == user.getStatus()));

        System.out.println("---------------------");

        Optional<User> op = users.stream().sorted(Comparator.comparingDouble(User::getSalary)).findFirst();
        System.out.println(op.get());

        System.out.println("---------------------");

        System.out.println(users.stream().filter(user -> user.getStatus() == Status.VACATION).findAny().get());

    }

    @Test
    public void test2() {
        long count = users.stream().count();
        System.out.println("-->" + count);

        Optional<User> max = users.stream().max(Comparator.comparingDouble(User::getSalary));
        System.out.println(max.get());

        Optional<Double> optionalDouble = users.stream().map(User::getSalary).min(Double::compare);
        System.out.println(optionalDouble.get());
    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ----可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test3() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 0 : 起始值 --> 0 + 1 + 2 .......
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println("和:" + sum);
        System.out.println("-------------------------------------------");
        Optional<Double> salarySum = users.stream().map(User::getSalary).reduce(Double::sum);
        System.out.println("累计工资:" + salarySum.get());
    }

    /**
     * 收集
     * collect ---将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test4() {
        // 收集为List
        List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
        names.forEach(System.out::println);
        System.out.println("-------------------------------------");

        // 收集为set
        users.stream().map(User::getName).collect(Collectors.toSet()).forEach(System.out::println);
        System.out.println("-------------------------------------");

        // 收集为hashSet
        users.stream().map(User::getName).collect(Collectors.toCollection(HashSet::new)).forEach(System.out::println);

        // 总数
        Long collect = users.stream().collect(Collectors.counting());
        Long collect1 = users.stream().count();

        // 平均值
        System.out.println(users.stream().collect(Collectors.averagingDouble(User::getSalary)));

        // 工资总和
        System.out.println(users.stream().collect(Collectors.summarizingDouble(User::getSalary)));

        //最大值
        Optional<User> maxUser = users.stream().collect(Collectors.maxBy(Comparator.comparingDouble(User::getSalary)));
        System.out.println(maxUser.get());

        System.out.println(users.stream().map(User::getSalary).collect(Collectors.minBy(Double::compare)));

    }

    /**
     * 分组
     */
    @Test
    public void test6() {
        Map<Status, List<User>> statusListMap = users.stream().collect(Collectors.groupingBy(User::getStatus));

        statusListMap.forEach((status, users1) -> System.out.println(status + ":" + users1));

    }


    /**
     * 多级分组
     */
    @Test
    public void test7() {
        Map<Status, Map<String, List<User>>> collect = users.stream().collect(Collectors.groupingBy(User::getStatus, Collectors.groupingBy(u -> {
            if (u.getAge() <= 44) {
                return "青年";
            } else if (u.getAge() <= 66) {
                return "中年";
            } else {
                return "老年";
            }
        })));

        System.out.println(collect);
    }


    /**
     * 分区
     */
    @Test
    public void test8() {
        Map<Boolean, List<User>> collect = users.stream().collect(Collectors.partitioningBy(user -> user.getSalary() >= 7777));
        System.out.println("工资不足7777的员工");
        collect.get(false).forEach(System.out::println);
        System.out.println("工资大于等于7777的员工");
        collect.get(true).forEach(System.out::println);
    }

    /**
     * 总结
     */
    @Test
    public void test9() {
        DoubleSummaryStatistics collect = users.stream().collect(Collectors.summarizingDouble(User::getSalary));
        System.out.println(collect.getMax());
        System.out.println(collect.getAverage());
        System.out.println(collect.getMin());
    }

    /**
     * 连接
     */
    @Test
    public void test10() {
        //  o 张三 | 李四 | 王五 | 赵六 | 田七 | 田七 $
        String s = users.stream().map(User::getName).collect(Collectors.joining(" | ", " o ", " $ "));
        System.out.println(s);
    }


}
