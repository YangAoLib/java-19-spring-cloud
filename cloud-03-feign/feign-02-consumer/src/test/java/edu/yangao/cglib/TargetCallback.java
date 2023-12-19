package edu.yangao.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TargetCallback implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前");
        System.out.println("参数: " + Arrays.toString(objects));
        System.out.println("方法: " + method.getName());
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("调用后");
        return result;
    }
}
