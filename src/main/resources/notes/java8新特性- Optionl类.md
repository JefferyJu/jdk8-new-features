# java8新特性- Optionl类

[TOC]

## 介绍

> 快速锁定空指针异常位置

> Optionl<T>类(java.uti1.Optionl)是一个容器类,代表一个值存在或不存在,原来用null表示一个值不存在,现在Optional可以更好的表达这个概念.并且可以避免空指针异常.

## 常用方法:  
- Optionl.of(T t) : 创建一个Optionl实例
- Optionl.empty() : 创建一个空的Optionl实例
- Optionl. ofNullable(T t): 若t不为nul1,创建Optionl实例,否则创建空实例
- isPresent(): 判断是否包含值
- orElse(T t): 如果调用对象包含值,返回该值,否则返回t 
- orElseget(Supplier s): 如果调用对象包含值,返回该值,否则返回s获取的值
- orElseThrow(): 不存在抛出异常
- map(Function f): 如果有值对其处理,并返回处理后的Optionl,否则返回 Optiona1. empty 
- flatMap(Function mapper): 与map类似,要求返回值必须是Optionl
- 
```
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
```

```
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
```

## [如何正确使用Optional](https://lw900925.github.io/java/java8-optional.html)

通过上面的例子可以看出，Optional类可以优雅的避免NullPointerException带来的各种问题，不过，你是否真正掌握了Optional的用法？假设你试图使用Optional来避免可能出现的NullPointerException异常，编写了如下代码：

```
Optional<User> userOpt = Optional.ofNullable(user);
if (userOpt.isPresent()) {
    User user = userOpt.get();
    // do something...
} else {
    // do something...
}
```
坦白说，上面的代码与我们之前的使用if语句判断空值没有任何区别，没有起到Optional的正真作用：

```
if (user != null) {
    // do something...
} else {
    // do something...
}
```

当我们从之前版本切换到Java 8的时候，不应该还按照之前的思维方式处理null值，Java 8提倡函数式编程，新增的许多API都可以用函数式编程表示，Optional类也是其中之一。这里有几条关于Optional使用的建议：

- ==尽量避免在程序中直接调用Optional对象的`get()`和`isPresent()`方法；==
- ==避免使用`Optional`类型声明实体类的属性；==

>第一条建议中直接调用`get()`方法是很危险的做法，如果`Optional`的值为空，那么毫无疑问会抛出`NullPointerException`异常，而为了调用`get()`方法而使用`isPresent()`方法作为空值检查，这种做法与传统的用if语句块做空值检查没有任何区别。

>第二条建议避免使用`Optional`作为实体类的属性，它在设计的时候就没有考虑过用来作为类的属性，如果你查看`Optional`的源代码，你会发现它没有实现`java.io.Serializable`接口，这在某些情况下是很重要的（比如你的项目中使用了某些序列化框架），使用了`Optional`作为实体类的属性，意味着他们不能被序列化。

下面我们通过一些例子讲解Optional的正确用法：

### 正确创建Optional对象

上面提到创建Optional对象有三个方法，empty()方法比较简单，没什么特别要说明的。主要是of()和ofNullable()方法。当你很确定一个对象不可能为null的时候，应该使用of()方法，否则，尽可能使用ofNullable()方法，比如：

```
    public static void method(Role role) {
    // 当Optional的值通过常量获得或者通过关键字new初始化，可以直接使用of()方法
    Optional<String> strOpt = Optional.of("Hello World");
    Optional<User> userOpt = Optional.of(new User());

    // 方法参数中role值不确定是否为null，使用ofNullable()方法创建
    Optional<Role> roleOpt = Optional.ofNullable(role);
    }
```

#### 简化if嵌套代码示例

- 多层嵌套if
```
    User user = ...
    if (user != null) {
        String userName = user.getUserName();
        if (userName != null) {
            return userName.toUpperCase();
        } else {
            return null;
        }
    } else {
        return null;
    }
```

- 多步if判断

```
    User user = ...
    if (user == null) {
        return null;
    }
    String userName = user.getUserName();
    if(username == null){
        return null;
    }
    return userName.toUpperCase();
    }
```

- Optional类简化

```
    User user = ...
    Optional<User> userOpt = Optional.ofNullable(user);
    
    return userOpt.map(User::getUserName)
                .map(String::toUpperCase)
                .orElse(null);
```