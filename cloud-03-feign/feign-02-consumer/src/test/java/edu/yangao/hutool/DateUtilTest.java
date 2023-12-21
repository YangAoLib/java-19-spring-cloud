package edu.yangao.hutool;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

public class DateUtilTest {

    @Test
    void testDateFormat() {
        // 当前日期
        DateTime now = DateUtil.date();
        // 格式日期
        System.out.println(now.toString(DatePattern.NORM_DATE_PATTERN));
        // 往前一周
        System.out.println(now.offsetNew(DateField.WEEK_OF_YEAR, -1).toString(DatePattern.NORM_DATE_PATTERN));
        System.out.println(DateUtil.offsetWeek(now, -1).toString(DatePattern.NORM_DATE_PATTERN));
        // 检验是否有变化
        System.out.println(now.toString(DatePattern.NORM_DATE_PATTERN));
    }
}
