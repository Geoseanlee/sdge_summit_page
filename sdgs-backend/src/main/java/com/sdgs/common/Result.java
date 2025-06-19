package com.sdgs.common;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code; // 状态码, 200-成功, 其他-失败
    private String message; // 提示信息
    private T data; // 数据载荷

    // 成功的静态方法
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    // 无参的成功静态方法
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(null); // data 字段显式设置为 null
        return result;
    }

    // 失败的静态方法
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
