package edu.yangao.entity;

import lombok.Data;

/**
 * 订单信息
 */
@Data
public class OrderInfo {

    /**
     * ID
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 订单名称
     */
    private String orderName;
}
