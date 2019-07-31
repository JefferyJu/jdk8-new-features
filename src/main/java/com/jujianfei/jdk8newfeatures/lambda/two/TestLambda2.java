package com.jujianfei.jdk8newfeatures.lambda.two;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或Lambda操作符
 * <p>
 * 箭头操作符将Lambda表达式拆分成两部分：
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，即Lambda体
 * <p>
 * 语法格式一：无参数，无返回值
 * ()-> System.out.println("hello");
 * <p>
 * 语法格式二：有一个参数，无返回值
 * (x)-> System.out.println(x);
 * <p>
 * 语法格式三：有一个参数，小括号省略不写
 * x -> System.out.println(x);
 * <p>
 * 语法格式四：有两个以上的参数，有返回值，且Lambda体中有多条语句, 必须使用大括号
 * <p>
 * 语法格式五：若Lambda体中只有一条语句，return 和大括号都可以省略不写
 * <p>
 * 语法格式六：Lambdar表达式的参数列表的数据类型可以省略不写，因为]VM编译器通过上下文推断出，数据类型，即“类型推断”
 * <p>
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 9:49
 */
public class TestLambda2 {


    /**
     * 语法格式一：无参数，无返回值
     * ()-> System.out.println("hello");
     */
    @Test
    public void test1() {
        int num = 0; // jdk1.7前, 必须声明是final , jdk1.8,不必写,默认
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world" + num);
            }
        };
        run1.run();

        System.out.println("---------------------------");

        Runnable run2 = () -> System.out.println("hello world" + num);
        run2.run();
    }

    /**
     * 语法格式二：有一个参数，无返回值
     * (x)-> System.out.println(x);
     */
    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("哈哈哈哈,我是鲁班");
    }

    /**
     * 语法格式三：有一个参数，小括号省略不写
     * x -> System.out.println(x);
     */
    @Test
    public void test3() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("哈哈哈哈,我是鲁班");
    }


    /**
     * 语法格式四：语法格式四：有两个以上的参数，有返回值，且Lambda体中有多条语句
     */
    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    /**
     * 语法格式五：若Lambda体中只有一条语句，return 和大括号都可以省略不写
     */
    @Test
    public void test5() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        //Comparator<Integer> comparator2 = Integer::compare;
    }

    /**
     * 语法格式六：Lambdar表达式的参数列表的数据类型可以省略不写，因为]VM编译器通过上下文推断出，数据类型，即“类型推断”
     */
    @Test
    public void test6() {
        //Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);
        Comparator<Integer> comparator2 = (x, y) -> Integer.compare(x, y);

    }
}
