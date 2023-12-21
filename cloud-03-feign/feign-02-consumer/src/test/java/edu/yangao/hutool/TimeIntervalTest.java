package edu.yangao.hutool;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

public class TimeIntervalTest {

    @Test
    void testInterval() {
        final TimeInterval timer = new TimeInterval();

        // 分组1
        timer.start("1");
        ThreadUtil.sleep(800);

        // 分组2
        timer.start("2");
        ThreadUtil.sleep(900);

        Console.log("Timer 1 took {} ms", timer.interval("1"));
        Console.log("Timer 2 took {} ms", timer.interval("2"));

    }
}
