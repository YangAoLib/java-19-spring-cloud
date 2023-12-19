package edu.yangao.cglib;

import net.sf.cglib.proxy.Dispatcher;

/**
 * dispatcher 懒加载 （每次获取变量都会运行）
 */
public class TargetPropertyBeanDispatcher implements Dispatcher {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("dispatcher 懒加载 PropertyBean");
        return new PropertyBean(10, 20.0);
    }
}
