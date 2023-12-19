package edu.yangao.cglib;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试 懒加载 类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyBean {

    private Integer integer;


    private Double aDouble;
}
