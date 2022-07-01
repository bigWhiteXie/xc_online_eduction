package com.xuecheng.common.enums;

import java.lang.annotation.*;

/**
 * <p>
 *     数据字典自定义注解StatuContent
 * </p>
 * 数据字典自定义注解StatuContent,会在服务器启动时,自动将com.xuecheng.common.enums的StatusEnum子类信息保存到数据字典表中,这样使得数据同步
 *
 * @Description: 数据字典自定义注解StatuContent,会在服务器启动时,自定将com.xuecheng.common.enums的StatusEnum子类信息保存到数据字典表中,这样使得数据同步
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StatusAnnotation {

    /**
     * name属性会作为数据字典的名称,会添加到数据库中的数据字典表的name字段里
     * @return 数据字典的名称
     */
    String name();

    /**
     * code属性会作为数据字典的编码,会添加到数据库中的数据字典表的code字段里
     * @return
     */
    String code();
}
