package cn.itcast.farming.controller;

import cn.itcast.farming.common.domain.RestResponse;
import cn.itcast.farming.entity.FileObject;
import cn.itcast.farming.entity.UploadInfo;
import cn.itcast.farming.entity.UploadStrategy;
import cn.itcast.farming.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Api(value = "文件上传接口，暴露给后端业务与客户端")
@RestController
public class UploadController {

    @Autowired
    private FileService fileService;

    @Autowired
    TokenController tokenController;

    @ApiOperation(value="上传文件", notes="上传文件")
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "origin", value = "存储源，如七牛云、阿里云、hdfs本地实现等，可扩展", required = true, dataType = "String", paramType="query"),
            @ApiImplicitParam(name = "resourceKey", value = "资源标识", required = false, dataType = "String", paramType="query"),
            @ApiImplicitParam(name = "flag", value = "正反面", required = true, dataType = "String", paramType="query"),
            @ApiImplicitParam(name = "uploadToken", value = "上传凭证", required = true, dataType = "String", paramType="query")
    })
    @CrossOrigin
    public RestResponse<FileObject> uploadFile(@RequestParam("origin") String origin,
                                               @RequestParam("resourceKey") String resourceKey,
                                               @RequestParam("uploadToken") String uploadToken,
                                               @RequestParam("flag") String flag,
                                               @RequestParam("file") MultipartFile file){

        FileObject fileObject=fileService.upload(new UploadInfo(origin,resourceKey,flag,uploadToken,file));
        RestResponse<FileObject> restResponse= RestResponse.success(fileObject);
        restResponse.setMsg("上传成功！");
        return restResponse;
    }

    @ApiOperation(value="申请获得凭证", notes="申请获得凭证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "origin", value = "存储源，如七牛云、阿里云、hdfs本地实现等，可扩展", required = true, dataType = "String", paramType="query"),
            @ApiImplicitParam(name = "params", value = "scope、key、flag、deadline、file、tokenType(只能为1)", required = true, paramType="body")
    })
    @PostMapping(value = "/upload-one")
    public RestResponse<FileObject> uploadFileOnece(@RequestParam("origin")String origin,
                                              @RequestBody Map<String,Object> params){
        //1.解析upload方法需要的参数
        String bucket = (String) params.get("scope");
        String key = (String) params.get("key");
        String flag = (String) params.get("flag");
        Long deadline = (Long) params.get("deadline");
        MultipartFile file = (MultipartFile) params.get("file");

        //2.生成token
        UploadStrategy uploadStrategy = new UploadStrategy();
        uploadStrategy.setDeadline(deadline);
        uploadStrategy.setScope(bucket);
        String token = fileService.generateToken(origin,uploadStrategy, params);

        //3.上传文件
        RestResponse<FileObject> response = uploadFile(origin, key, token, flag, file);


        return response;
    }


}
