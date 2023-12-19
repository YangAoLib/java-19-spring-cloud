package edu.yangao.cglib;


import org.springframework.cglib.proxy.FixedValue;

/**
 * {@link FixedValue} 实现该接口表明: 锁定方法的返回值, 无论被代理类的方法返回什么值, 回调方法都返回固定值
 */
public class TargetFixedValue implements FixedValue {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("锁定结果");
        return 999;
    }
}
