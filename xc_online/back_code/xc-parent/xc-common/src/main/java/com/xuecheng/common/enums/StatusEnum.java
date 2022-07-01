package com.xuecheng.common.enums;

/**
 * <p>
 *     微服务业务常量状态编码接口规范
 * </p>
 * <hr/>
 * <p>
 *     学成在线业务常量要使用StatusEnum的实现子类来在项目中的使用，<br>
 *     常量代码Code定义为：业务名称+Enum <br>
 *     而常量代码分两部分组成：
 *          <ul>
 *              <li>前3-4位:服务标识</li>
 *              <li>后三位:异常标识</li>
 *          </ul>
 *      <font size='5' color='red'>前3-4位服务标识</font>需要开发者在开发的时候自行定义,但不能超过其范围,  例如:搜索服务的服务标识只能在 500-599之间<br>
 *      <font size='5' color='red'>后3位</font>需要开发者在开发的时候自行定义。
 * </p>
 * <p>
 *    对于学成在线的每个微服务的业务常量代码的<font size='5' color='red'>前3-4位</font>有以下定义：
 *      <ul>
 *       <li>通用错误异代码: 000</li>
 *       <li>系统管理服务: 100</li>
 *       <li>内容管理服务: 200</li>
 *       <li>教学管理服务: 300</li>
 *       <li>媒资管理服务: 400</li>
 *       <li>搜索服务: 500</li>
 *       <li>订单服务: 600</li>
 *       <li>缓存服务: 700</li>
 *       <li>评论服务: 800</li>
 *       <li>用户中心: 900</li>
 *       <li>学习中心: 1100</li>
 *      </ul>
 * </p>
 *
 * <p>
 *     举例：以内容服务中的课程模式为例子<br/>
 *     常量代码Code定义为： CourseModelEnum <br>
 *     内容服务管理的服务标识常量代码为：200-299之间<br>
 *     后三位由开发者自行定义,例如
 *     <ul>
 *       <li>课程模式--定义模块代码为： 200服务标识+业务常量标识00x
 *          <ul>
 *              <li>200001--普通</li>
 *              <li>200002--录播</li>
 *              <li>200003--直播</li>
 *          </ul>
 *       </li>
 *     </ul>
 *     上面确定了内容管理下的课程模式常量信息。<br><br>
 *
 *     每一个微服务的模块的前3-4位只能在自己的范围内中,不能超过其范围<br>
 *     可以参考 {@link com.xuecheng.common.enums.content.CourseModeEnum}
 * </p>
 */
public interface StatusEnum {

    String getCode();

    String getDesc();

}
