package com.jujianfei.jdk8newfeatures.stream.day04;

import com.jujianfei.jdk8newfeatures.lambda.day01.Status;
import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 14:18
 */
public class OptionalTest {
    /*
        Optionl.of(T t) : 创建一个Optionl实例
        Optionl.empty() : 创建一个空的Optionl实例
        Optionl. ofNullable(T t): 若t不为nul1,创建Optionl实例,否则创建空实例
        isPresent(): 判断是否包含值
        orElse(T t): 如果调用对象包含值,返回该值,否则返回t
        orElseget(Supplier s): 如果调用对象包含值,返回该值,否则返回s获取的值
        map(Function f): 如果有值对其处理,并返回处理后的Optionl,否则返回 Optiona1. empty
        flatMap(Function mapper): 与map类似,要求返回值必须是Optionl
     */
    @Test
    public void test() {
        Optional<User> userOptional = Optional.of(new User());
        User user = userOptional.get();
        System.out.println(user);
    }

    @Test
    public void test2() {
        Optional<User> op = Optional.empty();
        System.out.println(op.get());

    }

    /**
     * of() 与 empty() 方法的综合
     */
    @Test
    public void test3() {
        Optional<User> op = Optional.ofNullable(null);
        if (op.isPresent()) {
            System.out.println(op.get());
        } else {
            User user = op.orElse(new User("null", 18, 999, Status.BUSY));
            System.out.println(user);
        }

        System.out.println("----------------------------");

        Optional<User> op1 = Optional.ofNullable(new User("not null", 18, 999, Status.BUSY));
        // 非空true 此处打印
        op1.ifPresent(System.out::println);
        //op1非空此处不生效
        User user1 = op1.orElse(new User("null", 18, 999, Status.BUSY));
        //所以依旧打印 not null
        System.out.println(user1);

        System.out.println("-------------------------------");
        // 与orElse()区别在于可以用lambda表达式传一些想要的功能
        op.orElseGet(User::new);
    }

    /**
     * map 与 flatMap
     */
    @Test
    public void test4() {

        //map(Function f): 如果有值对其处理,并返回处理后的Optionl,否则返回 Optiona1. empty
        Optional<User> op = Optional.ofNullable(new User("not null", 18, 999, Status.BUSY));
        Optional<String> name = op.map(User::getName);
        System.out.println(name.get());

        // flatMap(Function mapper): 与map类似,要求返回值必须是 Optionl
        Optional<String> name2 = op.flatMap(e -> Optional.of(e.getName()));
        System.out.println(name2.get());

    }

    /**
     * 需求:获取一个男人心中女神的名字
     */
    @Test
    public void test5() {
        Man man = new Man();
        String godnessName = getGodnessName(man);
        System.out.println(godnessName);

        System.out.println("------------------------");
        Optional<Godness> gn = Optional.ofNullable(new Godness("luban"));
        Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));
        String str = getGodnessName2(op);
        System.out.println(str);
    }

    public String getGodnessName(Man man) {
        if (man == null) {
            return null;
        }
        if (man.getGodness() == null) {
            return null;
        }
        return man.getGodness().getName();
    }

    public String getGodnessName2(Optional<NewMan> newMan) {
        return newMan.orElse(new NewMan())
                .getGodness().orElse(new Godness("wangqianying"))
                .getName();
    }

}
