package edu.yangao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结果状态
 * @author YangAo
 */
@Getter
@AllArgsConstructor
public enum ResultStatus {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 失败
     */
    FAIL(500, "操作失败");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 操作信息
     */
    private final String msg;
}
