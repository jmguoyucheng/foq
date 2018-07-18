package com.yucn.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2018/1/12.
 */
public class KeyUtil {
    //生成唯一的主键
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer a = random.nextInt(900) + 1000;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date()) + String.valueOf(a);
    }
}
