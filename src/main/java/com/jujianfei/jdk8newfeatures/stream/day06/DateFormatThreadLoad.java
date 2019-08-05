package com.jujianfei.jdk8newfeatures.stream.day06;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 17:47
 */
public class DateFormatThreadLoad {

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };


    public static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }
}
