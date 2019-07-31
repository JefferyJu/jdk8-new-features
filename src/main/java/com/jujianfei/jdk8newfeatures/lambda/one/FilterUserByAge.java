package com.jujianfei.jdk8newfeatures.lambda.one;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 9:13
 */
public class FilterUserByAge implements MyPredicate<User> {


    @Override
    public boolean test(User user) {
        return user.getAge() >= 35;
    }
}
