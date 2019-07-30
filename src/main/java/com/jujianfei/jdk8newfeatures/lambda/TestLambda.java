package com.jujianfei.jdk8newfeatures.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * TODO
 *
 * @author jujianfei
 * @date 2019/7/30 21:03
 */
public class TestLambda {
    //原来的匿名内部类
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
            new User("张三", 18, 3333.33),

            new User("李四", 18, 4444.44),
            new User("王五", 18, 5555.55),
            new User("赵六", 18, 6666.66),
            new User("田七", 18, 7777.77)
    );


}
