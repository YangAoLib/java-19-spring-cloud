package edu.yangao.hedgehog;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import edu.yangao.hedgehog.anno.HedgehogStatus;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class HedgehogModel {

    /**
     * 窗口期时间（秒）
     */
    public static final Integer WINDOWS_SLEEP_TIME = 300;

    /**
     * 最大失败次数
     */
    public static final Integer MAX_FAIL_COUNT = 3;

    /**
     * 失败次数
     */
    private AtomicInteger failCount = new AtomicInteger(0);

    /**
     * 初次失败的时间
     */
    private Date firstFailTime;

    /**
     * 熔断器状态
     */
    private HedgehogStatus status = HedgehogStatus.CLOSE;

    /**
     * 定时线程池
     */
    private ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(4);

    /**
     * 失败次数 +1
     * 并修改状态
     */
    public void addFailCount() {
        // 如果处于打开状态则不进行失败次数相关操作
        if (status == HedgehogStatus.OPEN) return;
        int failCountInt = this.failCount.incrementAndGet();
        // 第一次失败设置失败时间
        if (failCountInt == 1) {
            firstFailTime = DateUtil.date();
        }
        long between = DateUtil.between(firstFailTime, DateUtil.date(), DateUnit.SECOND, true);
        // 如果达到最大失败数
        if (failCountInt >= MAX_FAIL_COUNT) {
            // 在窗口时间内则打开熔断器
            if (between <= WINDOWS_SLEEP_TIME) {
                // 将当前状态设置为打开
                status = HedgehogStatus.OPEN;
                // 经过一个窗口期后设置为半开
                poolExecutor.schedule(() -> {
                    status = HedgehogStatus.HALF_OPEN;
                    // 清空失败次数与失败时间
                    firstFailTime = null;
                    failCount.set(0);
                }, 5, TimeUnit.SECONDS);
            } else {
                // 超出窗口时间 则设置失败次数为1并更新失败时间
                failCount.set(1);
                firstFailTime = DateUtil.date();
            }
        }
    }

    /**
     * 熔断器 关闭
     */
    public void close() {
        // 修改状态为关闭
        status = HedgehogStatus.CLOSE;
        // 清空失败次数与失败时间
        firstFailTime = null;
        failCount.set(0);
    }
}
