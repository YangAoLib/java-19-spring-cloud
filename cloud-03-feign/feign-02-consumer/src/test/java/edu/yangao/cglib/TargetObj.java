package edu.yangao.cglib;


import org.springframework.cglib.proxy.Enhancer;

/**
 * 被代理类
 */
public class TargetObj {

    /**
     * 懒加载 整数（只执行一次回调）
     */
    public PropertyBean lazyLoaderPropertyBean;

    /**
     * 懒加载 小数（每次都执行回调）
     */
    public PropertyBean dispatcherPropertyBean;

    public TargetObj() {
        lazyLoaderPropertyBean = (PropertyBean) Enhancer.create(PropertyBean.class, new TargetPropertyBeanLazyLoader());
        dispatcherPropertyBean = (PropertyBean) Enhancer.create(PropertyBean.class, new TargetPropertyBeanDispatcher());
    }

    public String method1(String param) {
        return param;
    }

    public int method2(int count) {
        return count;
    }

    public int method3(int count) {
        return count;
    }

    @Override
    public String toString() {
        return "TargetObj []" + getClass();
    }
}
