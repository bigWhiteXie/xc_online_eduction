package com.xuecheng.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuecheng.common.domain.uaa.LoginUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 * 获取当前登录用户信息
 * 前端配置token，后续每次请求并通过Header方式发送至后端
 * 在后端controller中通过SecurityUtil.getUser()方法获取当前登录用户信息
 */
public class SecurityUtil {

	public static LoginUser getUser() {
		/**
		 * token 明文格式，将以下内容base64编码后，就是token
		 {
		 "aud": [
		 "xuecheng-resource"
		 ],
		 "payload": {
		 "1177144209463128125": {
		 "resources": [
		 ],
		 "user_authorities": {
		 "r_001": [
		 "xc_company_modify",
		 "xc_company_view",
		 "xc_course_base_del",
		 "xc_course_base_edit",
		 "xc_course_base_list",
		 "xc_course_base_save",
		 "xc_course_base_view",
		 "xc_course_publish",
		 "xc_market_save_modify",
		 "xc_market_view",
		 "xc_media_del",
		 "xc_media_list",
		 "xc_media_preview",
		 "xc_media_save",
		 "xc_teacher_list",
		 "xc_teacher_modify",
		 "xc_teacher_save",
		 "xc_workrecord_correction",
		 "xc_workrecord_list",
		 "xc_teachplanwork_del",
		 "xc_teachplanwork_list",
		 "xc_teachplanwork_save_modify",
		 "xc_teachplan_del",
		 "xc_teachplan_save_modify",
		 "xc_teachplan_view"
		 ],
		 "r_002": [
		 "xc_course_admin_list",
		 "xc_course_base_commit",
		 "xc_system_category",
		 "xc_m_media_list",
		 "xc_media_audit"
		 ]
		 }
		 }
		 },
		 "user_name": "xc-user-first",
		 "scope": [
		 "read"
		 ],
		 "mobile": "15012345678",
		 "exp": 1606525122,
		 "client_authorities": [
		 "ROLE_USER"
		 ],
		 "jti": "1eb7e987-d7c2-40f5-b0d6-cd6139cb18e0",
		 "client_id": "xc-com-platform",
		 "companyId": 1232141425
		 }
		 */


		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		String jsonToken = request.getHeader(LoginUser.GATEWAY_JSON_TOKEN);
		LoginUser user = convertTokenToLoginUser(jsonToken);
		return user;
	}

	/**
	 * 转换明文jsonToken为用户对象
	 * @param token
	 * @return
	 */
	public static LoginUser convertTokenToLoginUser(String token){
		token = EncryptUtil.decodeUTF8StringBase64(token);
		LoginUser user = new LoginUser();
		JSONObject jsonObject = JSON.parseObject(token);
		String payload = jsonObject.getString("payload");
		Map<String,Object> payloadMap = JSON.parseObject(payload, Map.class);
		user.setPayload(payloadMap);
		user.setClientId(jsonObject.getString("client_id"));
		user.setMobile(jsonObject.getString("mobile"));
		user.setUsername(jsonObject.getString("user_name"));
		return user;
	}


	public static void main(String[] args) {

		String stringBase64 = EncryptUtil.decodeUTF8StringBase64("eyJhdWQiOlsieHVlY2hlbmctcmVzb3VyY2UiXSwicGF5bG9hZCI6eyIxMjA2ODU3NDExMTMzMTgxOTU5Ijp7InJlc291cmNlcyI6bnVsbCwidXNlcl9hdXRob3JpdGllcyI6eyJyX3hjX2NvbV9hZG1pbiI6WyJ4Y19jb3Vyc2VfYmFzZV9saXN0IiwieGNfY291cnNlX2Jhc2Vfc2F2ZSIsInhjX2NvdXJzZV9iYXNlX2VkaXQiLCJ4Y19jb3Vyc2VfYmFzZV9kZWwiLCJ4Y19jb3Vyc2VfYmFzZV92aWV3IiwieGNfdGVhY2hwbGFuX3ZpZXciLCJ4Y190ZWFjaHBsYW5fc2F2ZV9tb2RpZnkiLCJ4Y190ZWFjaHBsYW5fZGVsIiwieGNfbWFya2V0X3NhdmVfbW9kaWZ5IiwieGNfbWFya2V0X3ZpZXciLCJ4Y19jb3Vyc2VfcHVibGlzaCIsInhjX21lZGlhX2xpc3QiLCJ4Y19tZWRpYV9zYXZlIiwieGNfbWVkaWFfcHJldmlldyIsInhjX21lZGlhX2RlbCIsInhjX2NvbXBhbnlfbW9kaWZ5IiwieGNfY29tcGFueV92aWV3IiwieGNfdGVhY2hlcl9saXN0IiwieGNfdGVhY2hlcl9zYXZlIiwieGNfdGVhY2hlcl9tb2RpZnkiLCJ4Y190ZWFjaHBsYW53b3JrX2xpc3QiLCJ4Y190ZWFjaHBsYW53b3JrX3NhdmVfbW9kaWZ5IiwieGNfdGVhY2hwbGFud29ya19kZWwiXX19fSwidXNlcl9uYW1lIjoid1Z1MTg2MTExMDY5ODQiLCJzY29wZSI6WyJyZWFkIl0sIm1vYmlsZSI6IjE4NjExMTA2OTg0IiwiZXhwIjoxNjUzNTk4ODU0LCJjbGllbnRfYXV0aG9yaXRpZXMiOlsiUk9MRV9YQ19BUEkiXSwianRpIjoiNmEzMjg4ZGUtNzYyNS00MjMxLTkyYWYtZTFiZmE0NjdmOGVjIiwiY2xpZW50X2lkIjoieGMtY29tLXBsYXRmb3JtIn0");
		System.out.println(stringBase64);

		// String jsonToken = "Bearer ewogICAgImF1ZCI6IFsKICAgICAgICAieHVlY2hlbmctcmVzb3VyY2UiCiAgICBdLAogICAgInBheWxvYWQiOiB7CiAgICAgICAgIjExNzcxNDQyMDk0NjMxMjgxMjUiOiB7CiAgICAgICAgICAgICJyZXNvdXJjZXMiOiBbCiAgICAgICAgICAgIF0sCiAgICAgICAgICAgICJ1c2VyX2F1dGhvcml0aWVzIjogewogICAgICAgICAgICAgICAgInJfMDAxIjogWwogICAgICAgICAgICAgICAgICAgICJ4Y19jb21wYW55X21vZGlmeSIsCgkJCQkJInhjX2NvbXBhbnlfdmlldyIsCgkJCQkJInhjX2NvdXJzZV9iYXNlX2RlbCIsCgkJCQkJInhjX2NvdXJzZV9iYXNlX2VkaXQiLAoJCQkJCSJ4Y19jb3Vyc2VfYmFzZV9saXN0IiwKCQkJCQkieGNfY291cnNlX2Jhc2Vfc2F2ZSIsCgkJCQkJInhjX2NvdXJzZV9iYXNlX3ZpZXciLAoJCQkJCSJ4Y19jb3Vyc2VfcHVibGlzaCIsCgkJCQkJInhjX21hcmtldF9zYXZlX21vZGlmeSIsCgkJCQkJInhjX21hcmtldF92aWV3IiwKCQkJCQkieGNfbWVkaWFfZGVsIiwKCQkJCQkieGNfbWVkaWFfbGlzdCIsCgkJCQkJInhjX21lZGlhX3ByZXZpZXciLAoJCQkJCSJ4Y19tZWRpYV9zYXZlIiwKCQkJCQkieGNfdGVhY2hlcl9saXN0IiwKCQkJCQkieGNfdGVhY2hlcl9tb2RpZnkiLAoJCQkJCSJ4Y190ZWFjaGVyX3NhdmUiLAoJCQkJCSJ4Y193b3JrcmVjb3JkX2NvcnJlY3Rpb24iLAoJCQkJCSJ4Y193b3JrcmVjb3JkX2xpc3QiLAoJCQkJCSJ4Y190ZWFjaHBsYW53b3JrX2RlbCIsCgkJCQkJInhjX3RlYWNocGxhbndvcmtfbGlzdCIsCgkJCQkJInhjX3RlYWNocGxhbndvcmtfc2F2ZV9tb2RpZnkiLAoJCQkJCSJ4Y190ZWFjaHBsYW5fZGVsIiwKCQkJCQkieGNfdGVhY2hwbGFuX3NhdmVfbW9kaWZ5IiwKCQkJCQkieGNfdGVhY2hwbGFuX3ZpZXciCiAgICAgICAgICAgICAgICBdLAogICAgICAgICAgICAgICAgInJfMDAyIjogWwogICAgICAgICAgICAgICAgICAgICJ4Y19jb3Vyc2VfYWRtaW5fbGlzdCIsCgkJCQkJInhjX2NvdXJzZV9iYXNlX2NvbW1pdCIsCgkJCQkJInhjX3N5c3RlbV9jYXRlZ29yeSIsCgkJCQkJInhjX21fbWVkaWFfbGlzdCIsCgkJCQkJInhjX21lZGlhX2F1ZGl0IgogICAgICAgICAgICAgICAgXQogICAgICAgICAgICB9CiAgICAgICAgfQogICAgfSwKICAgICJ1c2VyX25hbWUiOiAieGMtdXNlci1maXJzdCIsCiAgICAic2NvcGUiOiBbCiAgICAgICAgInJlYWQiCiAgICBdLAogICAgIm1vYmlsZSI6ICIxNTAxMjM0NTY3OCIsCiAgICAiZXhwIjogMTYwNjUyNTEyMiwKICAgICJjbGllbnRfYXV0aG9yaXRpZXMiOiBbCiAgICAgICAgIlJPTEVfVVNFUiIKICAgIF0sCiAgICAianRpIjogIjFlYjdlOTg3LWQ3YzItNDBmNS1iMGQ2LWNkNjEzOWNiMThlMCIsCiAgICAiY2xpZW50X2lkIjogInhjLWNvbS1wbGF0Zm9ybSIsCiAgICAiY29tcGFueUlkIjogMTIzMjE0MTQyNQp9";
		// if(StringUtils.isEmpty(jsonToken) || !jsonToken.startsWith("Bearer ")){
		// 	throw new RuntimeException("token is not as expected");
		// }
		// jsonToken = jsonToken.substring(7);
		// jsonToken = EncryptUtil.decodeUTF8StringBase64(jsonToken);
		//
		// LoginUser user = convertTokenToLoginUser(jsonToken);
		// System.out.println(user);
	}



	public static Long getCompanyId(){
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		String jsonToken = request.getHeader(LoginUser.GATEWAY_JSON_TOKEN);
		// jsonToken = jsonToken.substring(7);
		try {
			jsonToken = EncryptUtil.decodeUTF8StringBase64(jsonToken);
		} catch (Exception e) {
			throw new RuntimeException("无法获得令牌信息");
		}
		JSONObject jsonObject = JSON.parseObject(jsonToken);
		return jsonObject.getLong("companyId");
	}


}
