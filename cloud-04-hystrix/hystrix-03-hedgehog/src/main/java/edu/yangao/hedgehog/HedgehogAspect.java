package edu.yangao.hedgehog;

import cn.hutool.core.util.RandomUtil;
import edu.yangao.hedgehog.anno.Hedgehog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class HedgehogAspect {

    private static Map<String, HedgehogModel> serviceMap = new HashMap<>();

    @Around("@annotation(edu.yangao.hedgehog.anno.Hedgehog)")
    public Object hedgehogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取被环绕的方法的反射信息 - 获取方法上面的注解
        Signature signature = joinPoint.getSignature();
        Method method = signature.getDeclaringType().getMethod(signature.getName());
        Hedgehog hedgehog = method.getAnnotation(Hedgehog.class);
        // 访问注解的属性
        String serviceName = hedgehog.serviceName();
        // 获取对应熔断器
        HedgehogModel hedgehogModel = serviceMap.merge(serviceName, new HedgehogModel(), (oldValue, newValue) -> oldValue);
        // 根据熔断器状态 进行操作
        switch (hedgehogModel.getStatus()) {
            case OPEN: // 熔断器开启
                // 直接返回备用数据
                return "刺猬熔断器: 租车失败";
            case CLOSE: // 熔断器关闭
                // 执行正常操作
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    // 如果出现错误, 熔断器记数
                    hedgehogModel.addFailCount();
                    // 返回备用数据
                    return "刺猬熔断器: 租车失败";
                }
            case HALF_OPEN: // 熔断器半开状态
                // 只选取部分(20%)流量进行请求 其余部分直接返回备用数据
                if (RandomUtil.randomInt(5) == 0) {
                    log.info("执行部分请求");
                    // 20% 概率进入此块代码
                    // 执行正常操作
                    try {
                        Object result = joinPoint.proceed();
                        // 如果正常执行了 那么将熔断器关闭
                        hedgehogModel.close();
                        return result;
                    } catch (Throwable e) {
                        // 如果出现错误, 熔断器记数
                        hedgehogModel.addFailCount();
                        // 返回备用数据
                        return "刺猬熔断器: 租车失败";
                    }
                } else {
                    // 直接返回备用数据
                    return "刺猬熔断器: 租车失败";
                }
            default:
                // 直接返回备用数据
                return "刺猬熔断器: 租车失败";
        }
    }
}
