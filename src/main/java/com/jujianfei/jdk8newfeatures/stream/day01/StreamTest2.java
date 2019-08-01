package com.jujianfei.jdk8newfeatures.stream.day01;

import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤：
 * 1.创建 Stream
 * 2.中间操作
 * 3.终止操作（终端操作)
 *
 * @author jujianfei
 * @date 2019/8/1 19:55
 */
public class StreamTest2 {


    List<User> users = Arrays.asList(
            new User("张三", 33, 3333.33),
            new User("李四", 44, 4444.44),
            new User("王五", 55, 5555.55),
            new User("赵六", 66, 6666.66),
            new User("田七", 77, 7777.77),
            new User("田七", 77, 7777.77)
    );

    /**
     * 中间操作
     * <p>
     * 筛选与切片
     * filter一接收Lambda,从中排除某些元素
     * limit一截断流,使其元素不超过给定数量
     * skip(n)一跳过元素,返回一个扔掉了前n个元素的流·若流中元素不足n个,则回一个空流,与limit(n)互补
     * distinct一筛选,通过所生成元素的 hashCode()和 equals()去除重复元素
     */

    /**
     * filter一接收Lambda,从中排除某些元素
     * 内部选代:选代操作由 Stream API完成
     */
    @Test
    public void test1() {
        //多个中间操作可以连接起来形成一个流水线,除非流水
        //线上触发终止操作,否则中间操作不会执行任何的处理!
        Stream<User> userStream = users.stream()
                .filter(user -> {
                    System.out.println("StreamAPI的中间操作");
                    return user.getAge() > 35;
                });
        //而在终止操作时一次性全部处理,称为“惰性求值”。
        userStream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void test2() {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * limit一截断流,使其元素不超过给定数量
     */
    @Test
    public void test3() {
        users.stream().filter(user -> {
            System.out.println("短路"); // 满足limit(2)后,则不进行迭代
            return user.getSalary() > 3000;
        }).limit(2).forEach(System.out::println);
    }

    /**
     * skip(n)一跳过元素,返回一个扔掉了前n个元素的流·若流中元素不足n个,则回一个空流,与limit(n)互补
     * distinct一筛选,通过所生成元素的 hashCode()和 equals()去除重复元素
     */
    @Test
    public void test4() {
        users.stream().filter(user -> {
            System.out.println("短路"); // 满足limit(2)后,则不进行迭代
            return user.getSalary() > 3000;
        }).skip(2).distinct().forEach(System.out::println);
    }


    /**
     * 中间操作
     * <p>
     * 映射
     * map------接收 Lambda,将元素转换成其他形式或提取信息,接收一个函数作为参数,
     * 该函数会被应用到每个元素上,并将其映射成一个新的元素
     * flatMap------接收一个的数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个淡
     */
    @Test
    public void test5() {

        System.out.println("------------1----------");
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        // {a,a,a} - {A,A,A} , {b,b,b} - {B,B,B} ...... 再合为新元素
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        System.out.println("------------2----------");

        users.stream().map(User::getName).forEach(System.out::println);
        System.out.println("-----------3----------");

        Stream<Stream<Character>> doubleStream = list.stream().map(StreamTest2::filterCharacter);

        doubleStream.forEach(stream -> {
            stream.forEach(System.out::println);
        });

        System.out.println("------------4--------");

        Stream<Character> characterStream = list.stream().
                //{a,a,a,b,b,b,c,c,c....}
                        flatMap(StreamTest2::filterCharacter);
        characterStream.forEach(System.out::println);
    }

    /**
     * map 与flatMap 的区别类似于 list
     */
    @Test
    public void test6() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        List list2 = new ArrayList();
        list2.add("111");
        list2.add("222");
        //list2.add(list); // [111, 222, [aaa, bbb, ccc, ddd]]
        //list2.addAll(list); // [111, 222, aaa, bbb, ccc, ddd]
        System.out.println("list2: " + list2);


    }


    public static Stream<Character> filterCharacter(String string) {
        List<Character> list = new ArrayList<>();

        for (char c : string.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }


    /**
     * 中间操作
     * <p>
     * 排序
     * sorted()-----自然排序
     * sorted( Comparator com)-------定制排序
     */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("bbb", "aaa", "eee", "ccc", "ddd");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("------------");

        users.stream().sorted((u1, u2) -> {
            if (u1.getAge() == u2.getAge()) {
                return u1.getName().compareTo(u2.getName());
            } else {
                return -Integer.compare(u1.getAge(), u2.getAge());
            }
        }).forEach(System.out::println);

    }


}
