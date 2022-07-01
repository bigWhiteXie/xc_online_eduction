package com.xuecheng.common.hystrix;

import com.xuecheng.common.domain.code.CommonErrorCode;
import com.xuecheng.common.domain.response.RestResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     Rest服务远程调用熔断器基类
 * </p>
 *
 * @Description: Rest服务远程调用熔断器基类,提取熔断类中的方法
 * @Author: Flippy
 * @Date: 2019/9/20 11:04
 * @since: 2019/9/20 11:04
 */
@Slf4j
public class BaseAPIHystrix {

    Throwable throwable;

    public BaseAPIHystrix(Throwable throwable) {
        super();
        this.throwable = throwable;
    }

    protected RestResponse baseFailResult() {
        log.error("远端访问失败",throwable);
        return RestResponse.validfail(CommonErrorCode.E_100106);
    }

}