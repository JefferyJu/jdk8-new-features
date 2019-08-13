# Java8新特性 - StreamApi & 并行流

[TOC]

Stream的三个操作步骤：

1.  创建 Stream
2.  中间操作
3.  终止操作（终端操作)

---

 ## 创建 Stream

### 可以通过Collection系列集合提供的串行流 `stream()` 或 并行流`parallelStream()`

```
List<String> list = new ArrayList<>();
Stream<String> stream1 = list.stream();
```

### 通过Arrays 中的静态方法`stream()`获取数组流

```
User[] users = new User[10];
Stream<User> stream2 = Arrays.stream(users);
```

### 通过Stream类中的静态方法`of()`

```
Stream<String> stream3 = Stream.of("aa", "bb", "cc");
```

### 创建无限流

```
// 迭代
Stream.iterate(0, x -> x + 2).limit(10).forEach(System.out::println);

// 生成
Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
```

---

>   数据前提



![](https://raw.githubusercontent.com/JefferyJu/images/master/20190802165711.png)

```
List<User> users = Arrays.asList(        new User("张三", 33, 3333.33, Status.BUSY),        new User("李四", 44, 4444.44, Status.VACATION),        new User("王五", 55, 5555.55, Status.FREE),        new User("赵六", 66, 6666.66, Status.VACATION),        new User("田七", 77, 7777.77, Status.BUSY),        new User("田七", 77, 7777.77, Status.BUSY));
```

---

## 中间操作

#### 筛选与切片

* **`filter` ---接收Lambda,从中排除某些元素**
 * **`limit`---截断流,使其元素不超过给定数量**
 * **`skip(n)`---跳过元素,返回一个扔掉了前n个元素的流·若流中元素不足n个,则回一个空流,与limit(n)互补**
 * **`distinct`---筛选,通过所生成元素的 hashCode()和 equals()去除重复元素**

```
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
```

```
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
```

```
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
```

#### 映射（抽取信息）

-   `map`------接收 Lambda,将元素转换成其他形式或提取信息,接收一个函数作为参数, 该函数会被应用到每个元素上,并将其映射成一个新的元素

*   `flatMap`------接收一个的数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个淡

```
	@Test
    public void test5() {

        System.out.println("------------1----------");
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        // {a,a,a} - {A,A,A} , {b,b,b} - {B,B,B} ...... 再合为新元素
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        System.out.println("------------2----------");

        //map() 可以提取User中的姓名
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

```

#### 排序

-   `sorted()` ----- 自然排序
-   `sorted( Comparator com)` ------- 定制排序

```
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
```

---

## 终止操作

#### 查找与匹配

 * `allMatch`-----检查是否匹配所有元素
* `anyMatch`-----检查是否至少匹配一个元素
* `noneMatch`----检查是否没有匹配所有元素
* `findFirst`----返回第一个元素
* `findAny`------返回当前流中的任意元素
* `count`--------返回流中元素的总个数
* `max`---------返回流中最大值
* `min`----------返回流中最小值

```
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
```

#### 归约

*   `reduce(T identity, BinaryOperator)` ` reduce(BinaryOperator) `----可以将流中元素反复结合起来，得到一个值

```
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
```

#### 收集

*   collect ---将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
*   Collectors --- 为其提供工具类

```
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
```

#### 分组

```
@Test
    public void test6() {
        Map<Status, List<User>> statusListMap = users.stream().collect(Collectors.groupingBy(User::getStatus));

        statusListMap.forEach((status, users1) -> System.out.println(status + ":" + users1));

    }
```

#### 多级分组

```
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
```

#### 分区

```
	@Test
    public void test8() {
        Map<Boolean, List<User>> collect = users.stream().collect(Collectors.partitioningBy(user -> user.getSalary() >= 7777));
        System.out.println("工资不足7777的员工");
        collect.get(false).forEach(System.out::println);
        System.out.println("工资大于等于7777的员工");
        collect.get(true).forEach(System.out::println);
    }
```

#### 总结

```
 	@Test
    public void test9() {
        DoubleSummaryStatistics collect = users.stream().collect(Collectors.summarizingDouble(User::getSalary));
        System.out.println(collect.getMax());
        System.out.println(collect.getAverage());
        System.out.println(collect.getMin());
    }
```

#### 连接

```
	@Test
    public void test10() {
        String s = users.stream().map(User::getName).collect(Collectors.joining(" | ", " o ", " $ "));
        System.out.println(s); //  o 张三 | 李四 | 王五 | 赵六 | 田七 | 田七 $
    }
```
---
## 并行流和串行流

### 串行流 sequential()
```
        Instant start = Instant.now();
        // 串行流 sequential() 或者不写
        long sum = LongStream.rangeClosed((0), 10000000000L)
                .sequential()
                .reduce(0, Long::sum);
    
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis()); // 耗时4629ms

     
```
### 并行流 parallel()

```
  Instant start1 = Instant.now();
        // 并行流 parallel()
        long sum1 = LongStream.rangeClosed((0), 10000000000L)
                .parallel()
                .reduce(0, Long::sum);
     
        Instant end1 = Instant.now();
        System.out.println(Duration.between(start1, end1).toMillis()); //耗时: 1258ms
```
