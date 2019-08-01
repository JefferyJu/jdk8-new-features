package com.jujianfei.jdk8newfeatures.stream.day01;

import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤：
 * 1.创建 Stream
 * 2.中间操作
 * 3.终止操作（终端操作)
 *
 * @author Jeffery_Ju
 * @date 2019/8/1 15:27
 */
public class StreamTest1 {

    /**
     * 创建Stream
     */
    @Test
    public void test1() {

        //1.可以通过Collection系列集合提供的 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2. 通过Arrays 中的静态方法stream()获取数组流

        User[] users = new User[10];
        Stream<User> stream2 = Arrays.stream(users);

        //3. 通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4. 创建无限流
        //Stream.iterate()
    }


    @Test
    public void test2() {
        List<List<User>> lists = new ArrayList<>();
        List<User> users1 = new ArrayList<>();
        users1.add(new User("jujianfei", 19, 222));
        users1.add(new User("jujianfei2", 19, 222));

        List<User> users2 = new ArrayList<>();
        users2.add(new User("jujianfei", 19, 222));
        users2.add(new User("jujianfei2", 19, 222));

        List<User> users3 = new ArrayList<>();
        users3.add(new User("jujianfei", 19, 222));
        users3.add(new User("jujianfei2", 19, 222));
        users3.add(new User("jujianfei3", 19, 222));
        System.out.println("hash(users1) = " + hash(users1));
        System.out.println("hash(users2) = " + hash(users2));
        System.out.println("hash(users3) = " + hash(users3));
        lists.add(users1);
        lists.add(users2);
        lists.add(users3);

        Set<List<User>> set = new HashSet(lists);

        for (List<User> users : set) {
            for (User user : users) {
                System.out.println(user);
            }
            System.out.println("-----------");
        }
    }


    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


}
