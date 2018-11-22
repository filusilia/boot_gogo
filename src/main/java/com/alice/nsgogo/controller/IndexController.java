package com.alice.nsgogo.controller;

import com.alice.nsgogo.common.Constants;
import com.alice.nsgogo.result.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aozaki on 2018/10/24.
 * @version 1.0
 * @since 1.0
 */
@RestController
public class IndexController {
    private final Logger log = LoggerFactory.getLogger("nsgogo");

    @RequestMapping("app/index")
    public ResultResponse index(HttpServletRequest request) {
        log.info("ok this is demo log,{},{}",
                Constants.PROJECT_NAME, Constants.VER);
        return ResultResponse.success();
    }
}
