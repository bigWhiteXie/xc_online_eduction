import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.xuecheng.content.ContentApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@SpringBootTest(classes = ContentApplication.class)
public class QiNiuYunTest {


    @Test
    public void uploadTest(){
        Configuration configuration = new Configuration(Zone.huabei());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "3TXiPfgyRqQ_ddudAJnljppLYI1upXNMQnb405XX";
        String secretKey = "tezQ5Rmy05w7yCecjlEVADxYd_kouJ7YhWCekYxz";
        String bucket = "xc-onlie";
        String filePath = "D:\\img\\head2.jpeg";
        String key = "test.jpeg";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(filePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    @Test
    public void downloadTest()  {
        String fileName = "test.jpeg";
        String domainOfBucket = "resc0fcn3.hb-bkt.clouddn.com";
        try {
            String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
            System.out.println(finalUrl);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
