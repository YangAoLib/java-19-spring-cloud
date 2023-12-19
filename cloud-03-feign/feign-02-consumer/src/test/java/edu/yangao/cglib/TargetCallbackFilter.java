package edu.yangao.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 回调过滤器 在对象
 */
public class TargetCallbackFilter implements CallbackFilter {

    /**
     * 过滤方法
     * @param method 方法信息
     * @return 执行的callback的索引
     */
    @Override
    public int accept(Method method) {
        switch (method.getName()) {
            case "method1":
                System.out.println("filter method1 执行第一个Callback");
                return 0;
            case "method2":
                System.out.println("filter method2 执行第二个Callback");
                return 1;
            case "method3":
                System.out.println("filter method3 执行第三个Callback");
                return 2;
            default:
                return 0;
        }
    }
}
