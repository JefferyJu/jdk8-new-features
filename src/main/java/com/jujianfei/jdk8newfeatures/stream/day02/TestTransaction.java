package com.jujianfei.jdk8newfeatures.stream.day02;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 9:46
 */
public class TestTransaction {

    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /**
     * 1.找出2011年发生的所有交易，并按交易额排序《从低到高）
     */
    @Test
    public void test1() {
        transactions.stream().filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
    }

    /**
     * 2.交易员都在哪些不同的城市工作过?
     */
    @Test
    public void test2() {
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 3.查找所有来自Cambridge的交易员，并按姓名排序
     */
    @Test
    public void test3() {
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);

    }

    /**
     * 4.返回所有交易损的姓名字符串，按字母顺序排序
     */
    @Test
    public void test() {
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);
        System.out.println("------------------------------------");
        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .reduce("", String::concat));
        System.out.println("------------------------------------");
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted(String::compareToIgnoreCase)
                .forEach(System.out::print);
    }

    public static Stream<String> filterCharacter(String str) {
        List<String> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }
        return list.stream();
    }

    /**
     * 5.有没有交易员是在Milan工作的?
     */
    @Test
    public void test5() {
        boolean b = transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        System.out.println(b);
    }

    /**
     * 6.打印生活在Cambridge的交易员的总交易额
     */
    @Test
    public void test6() {
        Optional<Integer> reduce = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(reduce.get());
        System.out.println("----------------");

        int sum = transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity())).mapToInt(Transaction::getValue).sum();
        System.out.println(sum);
    }

    /**
     * 7.所有交易中，最高的交易颜是多少
     */
    @Test
    public void test7() {
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).max(Integer::compare);
        System.out.println(max.get());
    }

    /**
     * 8.找到交易额最小的交易
     */
    @Test
    public void test8() {
        Optional<Transaction> minTransaction = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        System.out.println(minTransaction.get());
    }

}
