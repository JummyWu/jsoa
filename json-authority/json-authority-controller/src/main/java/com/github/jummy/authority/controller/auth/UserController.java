package com.github.jummy.authority.controller.auth;

import com.github.jummy.authority.service.UserService;
import com.github.jummy.base.BaseController;
import com.github.jummy.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.jummy.common.constant.SwaggerConstants.*;

/**
 * @author: jummy
 * @create_date: 19/09/2021 9:17 下午
 * @desc:
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "User", tags = "用户")
@RequiredArgsConstructor
@Validated
public class UserController extends BaseController {

    private final UserService userService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "name", value = "名称", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "检测名称是否可用", notes = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam String name) {
        return success(userService.check(id, name));
    }
}
