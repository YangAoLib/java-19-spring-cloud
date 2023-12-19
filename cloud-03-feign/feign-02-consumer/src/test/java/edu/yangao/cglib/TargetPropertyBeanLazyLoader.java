package edu.yangao.cglib;

import net.sf.cglib.proxy.LazyLoader;

public class TargetPropertyBeanLazyLoader implements LazyLoader {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("lazy loader 懒加载 PropertyBean");
        return new PropertyBean(20, 18.9);
    }
}
