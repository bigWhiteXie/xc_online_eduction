package com.xuecheng.common.domain.code;

/**
 * <p>
 *     微服务业务异常编码规范接口
 * </p>
 * <hr/>
 * <p>
 *     学成在线业务异常要使用ErrorCode的实现子类来进行抛出业务异常，<br>
 *     异常代码Code定义为：服务名称+ErrorCode <br>
 *     而异常编码有6为数组成：
 *          <ul>
 *              <li>前两位:服务标识</li>
 *              <li>中间两位:模块标识</li>
 *              <li>后两位:异常标识</li>
 *          </ul>
 *      <font size='5' color='red'>后四位</font>需要开发者在开发的时候自行定义。
 * </p>
 * <p>
 *    对于学成在线的每个微服务的业务异常代码的<font size='5' color='red'>前两位</font>有一下定义：
 *      <ul>
 *       <li>通用错误异代码: 10</li>
 *       <li>系统管理服务: 11</li>
 *       <li>内容管理服务: 12</li>
 *       <li>教学管理服务: 13</li>
 *       <li>媒资管理服务: 14</li>
 *       <li>搜索服务: 15</li>
 *       <li>订单服务: 16</li>
 *       <li>缓存服务: 17</li>
 *       <li>评论服务: 18</li>
 *       <li>用户中心: 19</li>
 *       <li>学习中心: 20</li>
 *      </ul>
 * </p>
 *
 * <p>
 *     举例：以系统管理服务为例子<br/>
 *     异常代码Code定义为： SystemErrorCode <br>
 *     系统管理的错误代码为：11<br>
 *     系统管理的模块分为：(由开发者自行定义)
 *     <ul>
 *       <li>课程分类--定义模块代码为： 00</li>
 *       <li>数据字典--定义模块代码为： 01</li>
 *     </ul>
 *     上面确定了异常代码的中间两位数。<br><br>
 *
 *     添加课程分类保存失败的业务异常定义为：110001 (后两位由开发者自行定义)<br>
 *     可以参考 {@link com.xuecheng.system.common.constant.SystemErrorCode}
 * </p>
 */
public interface ErrorCode {

    int getCode();

    String getDesc();

}
