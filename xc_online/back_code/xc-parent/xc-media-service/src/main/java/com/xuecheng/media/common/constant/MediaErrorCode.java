
package com.xuecheng.media.common.constant;


import com.xuecheng.common.domain.code.ErrorCode;

/**
 * 媒资管理服务业务异常枚举类
 * <hr>
 * 后四位错误代码由开发者开发中进行定义<br>
 * 媒资管理服务前两位错误服务标识为：14(在 {@link ErrorCode} 中定义)
 * 模块错误代码定义如下：
 * <ul>
 *   <li>媒资管理--定义模块代码为： 00</li>
 *   <li>直播管理--定义模块代码为： 01</li>
 * </ul>
 */
public enum MediaErrorCode implements ErrorCode {
	E_140001(140001,"保存媒资信息失败"),
	E_140002(140002,"删除媒资信息失败"),
	E_140003(140003,"存在正在使用此媒资信息的课程计划，不能删除"),
	E_140004(140004,"媒资信息类型错误"),
	E_140005(140005,"媒资信息类不存在"),
	E_140006(140006,"媒资文件名称不能为空"),
	E_140007(140007,"媒资的file_id不能为空"),
	E_140008(140008,"mediaId和companyId不能为空"),
	E_140009(140009,"无法删除正在审核的媒资"),


	E_140011(140011,"获取视频/文档上传凭证失败"),
	E_140012(140012,"获取视频播放地址失败"),
	E_140013(140013,"此媒资没有对应的播放资源"),
	E_140014(140014,"获取文档链接地址失败"),
	E_140015(140015,"刷新上传凭证失败"),


	E_140021(140021,"获取OSS上传凭证失败"),
	E_140022(140022,"验证七牛云资源可以失败"),


	E_140100(140100,"此直播未开始或已结束"),
	E_140101(140101,"该课程为付费课程，请支付后观看"),
	;

	private int code;
	private String desc;
		
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	MediaErrorCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	public static MediaErrorCode setErrorCode(int code) {
       for (MediaErrorCode errorCode : MediaErrorCode.values()) {
           if (errorCode.getCode()==code) {
               return errorCode;
           }
       }
	       return null;
	}
}
