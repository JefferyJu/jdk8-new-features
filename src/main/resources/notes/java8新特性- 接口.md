# java8新特性- 接口

## 接口中的默认方法
==接口默认方法的”类优先”原则==
若一个接口中定义了一个默认方法，而另外一个父类或接口中又定义了一个同名的方法时
- ==选择父类中的方法==: 如果一个父类提供了具体的实现，那么接口中具有相同名称和参数的默认方法会被忽略。
- ==接口冲突==: 如果一个父接口提供一个默认方法，而另一个接口也提供了一个具有相同名称和参数列表的方法（不管方法是否是默认方法），那么必须覆盖该方法来解决冲突


- 接口1(接口中可以有静态方法与默认实现)

```
public interface MyInterface {
    
    default String getName() {
        return "MyInterface";
    }

    /**
     * 接口中的静态方法
     */
    static void show() {
        System.out.println("接口中的静态方法");
    }
}

```
- 接口2

```
public interface MyInterface2 {

    default String getName() {
        return "MyInterface2";
    }
}

```
- 父类

```
public class SupperClass {
    public String getName() {
        return "SupperClass";
    }
}
```

- 子类

```
public class SonClass /*extends SupperClass*/ implements MyInterface, MyInterface2 {

    @Override
    public String getName() {
        //return getName();  ----------异常 java.lang.StackOverflowError
        //return super.getName();  -----------正确, 调用父类方法 结果: SupperClass
        //return MyInterface.super.getName(); ---------屏蔽 extends SupperClass 结果: MyInterface
        return MyInterface2.super.getName();  ---------屏蔽 extends SupperClass 结果: MyInterface2
    }
}
```