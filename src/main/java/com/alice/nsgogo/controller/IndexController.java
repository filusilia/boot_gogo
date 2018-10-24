package com.alice.nsgogo.controller;

import com.alice.nsgogo.result.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aozaki on 2018/10/24.
 * @version 1.0
 * @since 1.0
 */
@RestController
public class IndexController {
    @GetMapping("index")
    public ResultResponse index(){
        return ResultResponse.success();
    }
}
