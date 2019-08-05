package com.jujianfei.jdk8newfeatures.stream.day05;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 16:22
 */
public class SonClass /*extends MyClass*/ implements MyInterface, MyInterface2 {

    @Override
    public String getName() {
        //return getName();                    //--------异常 java.lang.StackOverflowError
        //return super.getName();              //--------正确, 调用父类方法        结果: SupperClass
        //return MyInterface.super.getName();  //------- 屏蔽 extends SupperClass 结果: MyInterface
        return MyInterface2.super.getName();  //---------屏蔽 extends SupperClass 结果: MyInterface2
    }
}
