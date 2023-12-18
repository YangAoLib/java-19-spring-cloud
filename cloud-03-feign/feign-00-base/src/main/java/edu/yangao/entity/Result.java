package edu.yangao.entity;

import edu.yangao.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调用结果 包装类
 * @param <T> 数据类型
 * @author YangAo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 操作信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 通过状态枚举构建结果信息
     * @param status 状态
     * @param data 数据
     */
    public Result(ResultStatus status, T data) {
        this(status.getCode(), status.getMsg(), data);
    }

    /**
     * 成功
     * @param data 数据
     * @return 包装类
     * @param <V> 数据类型
     */
    public static <V> Result<V> success(V data) {
        return new Result<>(ResultStatus.SUCCESS, data);
    }

    /**
     * 成功
     * @param msg 操作信息
     * @param data 数据
     * @return 包装类
     * @param <V> 数据类型
     */
    public static <V> Result<V> success(String msg, V data) {
        return new Result<>(ResultStatus.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败
     * @param msg 失败信息
     * @return 包装类
     * @param <V> 数据类型
     */
    public static <V> Result<V> fail(String msg) {
        return new Result<>(ResultStatus.FAIL.getCode(), msg, null);
    }
}
