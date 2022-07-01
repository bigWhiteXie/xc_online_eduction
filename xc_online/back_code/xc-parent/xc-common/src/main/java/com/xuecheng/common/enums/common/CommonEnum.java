package com.xuecheng.common.enums.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *     公共的枚举信息
 * </p>
 *
 * @Description: 项目工程中公共的枚举类型，项目中的公共的枚举类型此类定义
 */
@StatusAnnotation(name = "公共属性类型", code = "000")
public enum CommonEnum implements StatusEnum {

    USING_FLAG(1,"使用态"),
    DELETE_FLAG(0,"删除态"),
    TRANSIENT_FLAG(-1,"暂时态");

    Integer code;
    String desc;

    CommonEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JSONField(serializeUsing = ToStringSerializer.class)
    public String getCode() {
        return code.toString();
    }
    public Integer getCodeInt() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
