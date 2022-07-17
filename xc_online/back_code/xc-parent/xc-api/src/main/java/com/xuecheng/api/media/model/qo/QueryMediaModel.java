package com.xuecheng.api.media.model.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p></p>
 *
 * @Description:
 */
@Data
@ApiModel("媒资查询查询封装对象")
public class QueryMediaModel {

    @ApiModelProperty("媒资文件名称")
    private String filename;
    @ApiModelProperty("媒资类型")
    private String type;
    @ApiModelProperty("审核状态")
    private String auditStatus;

}
