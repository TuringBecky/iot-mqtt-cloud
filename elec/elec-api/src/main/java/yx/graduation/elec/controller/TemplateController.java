package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yx.graduation.utils.ApiJsonResult;


@Api(value = "模板", tags = "模板——相关接口")
@RestController
@RequestMapping
public class TemplateController {

    /**
     * 模板方法
     */
    @ApiOperation(value = "模板方法", notes = "模板方法", httpMethod = "GET")
    @GetMapping("/hello")
    public ApiJsonResult login() {
        return ApiJsonResult.ok().ok("Hello ELEC！");
    }

}
