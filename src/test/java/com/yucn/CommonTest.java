package com.yucn;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/6/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {
    @Test
    public void dateTest(){
        Date now=new Date();
        //这种方式太慢，不建议使用
        String week = DateFormatUtils.format(now,"EEE");
        System.out.println(week);

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayOfWeek);

        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        week=dateFm.format(now);
        System.out.println(week);
    }
    @Test
    public void arrayTest(){
        List<String> weekdays = Arrays.asList("星期一,星期二".split(","));
        for (String weekday:weekdays){
            System.out.println(weekday);
        }
    }


}
