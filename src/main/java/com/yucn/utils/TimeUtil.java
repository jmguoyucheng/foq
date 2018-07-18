package com.yucn.utils;

import java.util.Date;

/**
 * Created by Administrator on 2018/7/3.
 */
public class TimeUtil {
    public static boolean between(Date when, Date startTime, Date endTime) {
        if(when.compareTo(startTime)>=0 && when.compareTo(endTime)<=0)
            return true;
        else
            return false;
    }
}
