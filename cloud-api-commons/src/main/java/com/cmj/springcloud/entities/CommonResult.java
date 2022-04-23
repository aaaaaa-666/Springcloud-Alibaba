package com.cmj.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    // 404 not_found
    private Integer code;
    private String message;
    private T data;

    // 因为全参和空参的构造器通过注解声明了，所以这里在加一个两个参数的构造器
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
