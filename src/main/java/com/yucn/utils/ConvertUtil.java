package com.yucn.utils;

import javafx.scene.effect.SepiaTone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Administrator on 2018/7/17.
 */
public class ConvertUtil {
    public static Set<Long> string2Longs(String key) {
        // 截取字符串
        String[] strArr = key.split(",");
        // 转换Set
        Set<Long> ids = new HashSet<>();
        for (String str:strArr) {
            try {
                ids.add(Long.valueOf(str));
            } catch (Exception e) {
            }
        }
        return ids;
    }
}
