package com.alice.boot.controller;

import com.alice.boot.common.Constants;
import com.alice.boot.common.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aozaki on 2018/10/24.
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
public class IndexController {

    @RequestMapping("app/index")
    public ResultResponse<String> index(HttpServletRequest request) {
        return ResultResponse.success(Constants.PROJECT);
    }
}
