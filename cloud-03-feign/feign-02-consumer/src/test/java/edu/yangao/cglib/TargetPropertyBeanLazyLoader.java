package edu.yangao.cglib;

import net.sf.cglib.proxy.LazyLoader;

/**
 * lazy loader 懒加载
 * 只有第一次获取变量会被执行
 */
public class TargetPropertyBeanLazyLoader implements LazyLoader {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("lazy loader 懒加载 PropertyBean");
        return new PropertyBean(20, 18.9);
    }
}
