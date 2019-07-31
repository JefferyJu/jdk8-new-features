package com.jujianfei.jdk8newfeatures.lambda.one;

import org.junit.Test;

import java.util.*;

/**
 * TODO
 *
 * @author jujianfei
 * @date 2019/7/30 21:03
 */
public class TestLambda {
    /**
     * 原来的匿名内部类
     */
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }


    /**
     * lambda 表达式
     */
    @Test
    public void test2() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }

    // 需求： 获取当前公司中员工年龄大于35的员工信息

    List<User> userList = Arrays.asList(
            new User("张三", 33, 3333.33),
            new User("李四", 44, 4444.44),
            new User("王五", 55, 5555.55),
            new User("赵六", 66, 6666.66),
            new User("田七", 77, 7777.77)
    );


    public List<User> filterUserByAge(List<User> userList) {
        List<User> users = new ArrayList<>();
        for (User user : userList) {
            if (user.getAge() >= 35) {
                users.add(user);
            }
        }
        return users;
    }

    @Test
    public void test3() {
        List<User> users = filterUserByAge(userList);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 优化方式一: 策略设计模式
     */
    @Test
    public void test4() {
        List<User> users = filterUser(userList, new FilterUserByAge());
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("---------------------------------------------");
        List<User> users1 = filterUser(userList, new FilterUserBySalary());
        for (User user : users1) {
            System.out.println(user);
        }
    }

    public List<User> filterUser(List<User> list, MyPredicate<User> mp) {
        List<User> users = new ArrayList<>();
        for (User user : list) {
            if (mp.test(user)) {
                users.add(user);
            }
        }
        return users;
    }


    /**
     * 优化方式二: 匿名内部类
     */
    @Test
    public void test5() {
        List<User> users = filterUser(userList, new MyPredicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getAge() < 55;
            }
        });
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 优化方式三: lambda表达式
     */
    @Test
    public void test6() {
        List<User> users = filterUser(userList, (user) -> user.getSalary() <= 4444.44);
        users.forEach(System.out::println);
    }

    /**
     * 优化方式四: streamAPI
     */
    @Test
    public void test7() {
        userList.stream().filter((user -> user.getSalary() <= 6444.44)).forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------");
        userList.stream().filter((user -> user.getSalary() <= 6444.44)).limit(2).forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------");

        userList.stream().map(User::getName).forEach(System.out::println);
    }
}
