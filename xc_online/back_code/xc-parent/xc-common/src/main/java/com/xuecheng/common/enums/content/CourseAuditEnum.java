package com.xuecheng.common.enums.content;

import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 *     课程审核状态
 * </p>
 *
 * @Description: 对课程发布状态常量信息使用枚举进行描述
 */
@StatusAnnotation(name = "课程审核状态", code="202")
public enum CourseAuditEnum implements StatusEnum {

	AUDIT_DISPAST_STATUS("202001","审核未通过"),
	AUDIT_UNPAST_STATUS("202002","未提交"),
	AUDIT_COMMIT_STATUS("202003","已提交"),
	AUDIT_PASTED_STATUS("202004","审核通过"),

	AUDIT_PUBLISHED_STATUS("202005","课程已发布"),
	AUDIT_UNLINE_STATUS("202006","课程已下线"),
	;

	String code;
	String desc;

	CourseAuditEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public static CourseAuditEnum getAuditStatus (String statusCode) {
		if (StringUtils.isBlank(statusCode)) {
			return null;
		}
		for (CourseAuditEnum cae : CourseAuditEnum.values()) {
			if(statusCode.equals(cae.getCode())) {
				return cae;
			}
		}
		return null;
	}
}

