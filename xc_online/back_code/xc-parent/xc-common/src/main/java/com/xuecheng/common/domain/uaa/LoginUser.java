package com.xuecheng.common.domain.uaa;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <P>
 * 当前登录用户
 * </p>
 */
@Data
public class LoginUser {

    /**
     * 通过网关转码 token后的明文 token Header名
     */
    public static final String GATEWAY_JSON_TOKEN = "jsonToken";


    /**
     * request 中存储user信息的key名称
     */
    public static final String REQUEST_USER = "uaa_request_user";


    /**
     * tenantId : 1
     * departmentId : 1
     * payload : {"res":"res1111111"}
     * username : admin
     * mobile : 18611106983
     * userAuthorities : {"ROLE1":["p1","p2"]}
     * clientId : wanxin-p2p-web-admin
     */
    private String mobile;
    private Map<String, Object> payload = new HashMap<>();
    private String clientId;
    private String username;
    private Long tenantId;
}