package com.sdgs.controller;

import com.sdgs.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/ping") // 接口路径是 /test/ping
    public Result<String> ping() {
        // 直接返回一个成功的、带有一段文本的 Result 对象
        return Result.success("Pong! 后端服务响应正常。");
    }
}
