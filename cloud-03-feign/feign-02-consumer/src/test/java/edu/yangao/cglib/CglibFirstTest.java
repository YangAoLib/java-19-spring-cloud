package edu.yangao.cglib;


import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class CglibFirstTest {


    @Test
    void testCglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObj.class);
        enhancer.setCallbacks(new Callback[]{NoOp.INSTANCE, new TargetInterceptor(), new TargetFixedValue()});
        enhancer.setCallbackFilter(new TargetCallbackFilter());
        TargetObj targetObj = (TargetObj) enhancer.create();
        System.out.println(targetObj);
        System.out.println(targetObj.method1("测试"));
        System.out.println(targetObj.method2(10));
        System.out.println(targetObj.method3(19));
        System.out.println(targetObj.method3(20));
        System.out.println(targetObj.lazyLoaderPropertyBean);
        System.out.println(targetObj.lazyLoaderPropertyBean);
        System.out.println(targetObj.dispatcherPropertyBean);
        System.out.println(targetObj.dispatcherPropertyBean);
    }
}
