[TOC]

java8新特性代码参考: [github](https://github.com/JefferyJu/jdk8-new-features)

###  一、Lambda 表达式的基础语法
Java8中引入了一个新的操作符  " ->" 该操作符称为箭头操作符或Lambda操作符。
箭头操作符将Lambda表达式拆分成两部分：

-   左侧：Lambda表达式的参数列表。
-   右侧：Lambda表达式中所需执行的功能，即Lambda体

---
**记忆总结顺口溜：**

> - 横批：能省则省
> - 上联：左右遇一括号省
> - 下联：左侧推断类型省

#### 语法格式一：无参数，无返回值

```
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
```



#### 语法格式二：有一个参数，无返回值

```
    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("哈哈哈哈,我是鲁班");
    }
```



#### 语法格式三：有一个参数，小括号省略不写

```
    @Test
    public void test3() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("哈哈哈哈,我是鲁班");
    }
```



#### 语法格式四：有两个以上的参数，有返回值，且Lambda体中有多条语句, 必须使用大括号

```
    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }
```



#### 语法格式五：若Lambda体中只有一条语句，return 和大括号都可以省略不写

```
    @Test
    public void test5() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        //Comparator<Integer> comparator2 = Integer::compare;
    }
```



#### 语法格式六：Lambdar表达式的参数列表的数据类型可以省略不写，因为]VM编译器通过上下文推断出，数据类型，即“类型推断”

```
    @Test
    public void test6() {
        //Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);
        Comparator<Integer> comparator2 = (x, y) -> Integer.compare(x, y);

    }
```

### 二、Lambda表达式需要“函数式接口”的支持函数式接口：

- **如果一个接口只有一个抽象方法,那么该接口就是一个函数式接口**.

- **如果我们在某个接口上声明了FunctionalInterface注解, 那么编译器就会按照函数式接口的定义来要求该接口.**
- **如果某个接口只有一个抽象方法,但我们并没有给该接口声明FunctionalInterface注解, 那么编译器依旧会将该接口看作是函数式接口**.

####  FunctionalInterface Java Doc

```java
An informative annotation type used to indicate that an interface
type declaration is intended to be a <i>functional interface</i> as
defined by the Java Language Specification
```

	一种信息注释类型，用于指示接口类型声明是由Java语言规范定义的<i>函数接口</i>。

> ```java
> Conceptually, a functional interface has exactly one abstract
> method. Since {@linkplain java.lang.reflect.Method#isDefault()
> default methods} have an implementation, they are not abstract.  If
> an interface declares an abstract method overriding one of the
> public methods of {@code java.lang.Object}, that also does
> <em>not</em> count toward the interface's abstract method count
> since any implementation of the interface will have an
> implementation from {@code java.lang.Object} or elsewhere.
> ```

	从概念上讲，**函数接口只有一个抽象方法**。因为 reflect 包中的Method下的默认方法有一个实现，

他不是抽象的。**如果一个接口声明一个抽象方法重写了对象的一个公共方法，那么它也不计入接口的抽象方法计数**。因

为接口的任何实现都将从{java.Lang.Object }或其他地方实现。


> Note that instances of functional interfaces can be created with lambda expressions, method references, or constructor references


	注意，可以使用lambda表达式、方法引用或构造函数引用创建函数接口的实例。

> If a type is annotated with this annotation type, compilers are required to generate an error message unless:
> 1.The type is an interface type and not an annotation type, enum, or class
> 2.The annotated type satisfies the requirements of a functional interface.
>
> 如果一种类型被注释为这种注释类型，编译器需要生成一条错误消息，除非:
1. 类型是接口类型，而不是注释类型、枚举或类
2. 带注释的类型满足函数式接口的要求。

### 三、 Java8内置的四大核心函数式接口

#### Consumer<T>:消费型接口

```
    void accept(T t);
```
示例:

```
    @Test
    public void testConsumer() {
        happy(1000, t -> System.out.println(t));
    }
    
    
    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
```
以上为消费型接口，有参数，无返回值类型的接口。

#### Supplier<T>:供给型接口

```
    T get();
```
示例:  产生指定个数的整数, 并放入集合

```
    @Test
    public void testSupplier() {
        List<Integer> list = getNumList(1000, () -> new Random().nextInt(100));
        for (Integer i : list) {
            System.out.println(i);
        }
    }


    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }
```
上面就是一个供给类型得接口，只有产出，没人输入，就是只有返回值，没有入参

#### Function<T，R>:函数型接口

```
    R apply(T t);
```
示例: 用于处理字符串

```
    @Test
    public void testFunc() {
        System.out.println(stringHandler(" \t\n\n\t hello_world", str -> str.trim()));
    }


    public String stringHandler(String str, Function<String, String> func) {
        return func.apply(str);
    }
```
上面就是一个函数型接口，输入一个类型得参数，输出一个类型得参数，当然两种类型可以一致。

#### Predicate<T>:断言型接口

```
boolean test(T t);
```
示例: 将满足条件的字符串，放入集合中

```
    @Test
    public void testPredicate() {
        List<String> list = Arrays.asList("luban", "wangqianying", "jujianfei");
        filterString(list, str -> str.contains("ing")).forEach(System.out::println);
    }

    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        List<String> strings = new ArrayList<>();

        for (String s : list) {
            if (predicate.test(s)) {
                strings.add(s);
            }
        }
        return strings;
    }
```
上面就是一个断言型接口，输入一个参数，输出一个boolean类型得返回值。

#### 其他类型的一些函数式接口

>   除了上述得4种类型得接口外还有其他的一些接口供我们使用：
>

##### BiFunction<T, U, R> 

　　　　　　参数类型有2个，为T，U，返回值为R，其中方法为
```
R apply(T t, U u)
```


##### UnaryOperator<T>(Function子接口)

　　　　　　参数为T，对参数为T的对象进行一元操作，并返回T类型结果，其中方法为T apply(T t)

##### BinaryOperator<T>(BiFunction子接口)

　　　　　　参数为T，对参数为T得对象进行二元操作，并返回T类型得结果，其中方法为
```
T apply（T t1， T t2）
```


##### BiConsumcr(T, U) 

　　　　　　参数为T，U无返回值，其中方法为 void accept(T t, U u)

##### ToIntFunction<T>、ToLongFunction<T>、ToDoubleFunction<T>

　　　　　　参数类型为T，返回值分别为int，long，double，分别计算int，long，double得函数。

##### IntFunction<R>、LongFunction<R>、DoubleFunction<R>

　　　　　　参数分别为int，long，double，返回值为R。

 

> 　　以上就是java8内置得核心函数式接口，其中包括了大部分得方法类型，所以可以在使用得时候根据不同得使用场景去选择不同得接口使用。

### 四、方法引用，构造器引用，数组引用

#### 方法引用

>   若 Lambda体中的内容有方法已经实现了,我们可以使用"方法引用"，可以理解为方法引用是 Lambda表达式的另外一种表观形式。

```
主要有三种语法格式:
 * 对象::实例方法名 new Object()::methodName
 * 类名::静态方法名 className::static methodName
 * 类名::实例方法名 className::methodName
```

注意:
 * 1、 Lambda体中调用方法的参数列表与返回值类型,要与函数式接口中抽象方法的函数列表和返回值类型保持一致!
 * 2、 若Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时,可以使用ClassName::method
 * 3、**==参数是实例方法的调用者即可使用类名::实例方法名==**

##### 对象::实例方法名

```
 	@Test
    public void test() {
        Consumer<String> consumer = x -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;

        Consumer<String> consumer2 = System.out::println;
    }
    
    @Test
    public void test2() {
        User user = new User("鲁班", 2, 3214);
        Supplier<String> supplier = () -> user.getName();
        String string = supplier.get();
        System.out.println(string);


        Supplier<Integer> supplier1 = user::getAge;
        System.out.println(supplier1.get());
    }
```

#####  类名::静态方法名

```
	@Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator2 = Integer::compare;
    }
```
##### 类名::实例方法名

```
	@Test
    public void test4() {
        //若Lambda参数列表中的第一参数是实例方法的调用者,而第二个参数是实例方法的参数时,可以使用ClassName::method
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;
        //貌似只满足参数是实例方法的调用者即可使用类名::实例方法名的方法引用形式
        Consumer<String> stringConsumer = String::toUpperCase;
    }
```

#### 构造器引用

```
格式：ClassName::new
```

>   注意：需要调用的构造器的参数列表要与的数式接口中抽象方法的参数列表保持一致！

```
/**
     * 无参构造器引用
     */
    @Test
    public void test5() {
        Supplier<User> supplier = () -> new User();

        //构造器引用方式(无参构造器)
        //用函数式接口的抽象方法的参数列表自动匹配对应的构造器
        Supplier<User> supplier1 = User::new;
        System.out.println(supplier1.get());
    }

    /**
     * 有参构造器引用
     */
    @Test
    public void test6() {
        Function<String, User> function = name -> new User(name);

        Function<String, User> function1 = User::new;

        System.out.println(function1.apply("luban"));
        //用函数式接口的抽象方法的参数列表自动匹配对应的构造器
        BiFunction<String, Integer, User> biFunction = User::new;
    }
```

#### 数组引用：

 ```
  格式: Type[]::new
 ```

   

```
/**
     * 数组引用
     */
    @Test
    public void test7() {
        Function<Integer, String[]> func = x -> new String[x];
        String[] strings = func.apply(10);
        System.out.println(strings.length);

        //数组引用

        Function<Integer, String[]> function = String[]::new;
        String[] strings1 = function.apply(20);
        System.out.println(strings1.length);

    }
```

