package edu.yangao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author YangAo
 */
@Data
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;
    private Double price;
}
