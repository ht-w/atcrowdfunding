package io.hongting.crowd.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectResult;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.exception.LoginFailedException;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author hongting
 * @create 2021 09 27 11:42 AM
 */
public class Util {
    /**
     * Determine whether the current request is an Ajax request.
     * @param request
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request){
        String header1 = request.getHeader("Accept");
        String header2 = request.getHeader("X-Requested With");
        return (header1!=null && header1.contains("application/json"))
                || (header2 != null && header2.equals("XmlHttpRequest"));
    }

    /**
     * MD5 Password Encryption
     * @param source
     * @return
     */

    public static String MD5encrypt (String source){

        if (source.length()==0 || source==null){
            throw new LoginFailedException(ResultConstant.MESSAGE_LOGIN_NULLPSWD);
        }
        try {
            char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            byte[] bytes = source.getBytes();
            String algo = "MD5";
            MessageDigest md = MessageDigest.getInstance(algo);
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(ResultConstant.MESSAGE_MD5_FAILED);
        }

    }

    public static R<String> sendCodeByShortMessage(String apiKey, String apiSecret, String phoneNum, String host){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++){
            int random = (int)(Math.random()*10);
            builder.append(random);
        }

        String code = builder.toString();

        NexmoClient client = NexmoClient.builder().apiKey(apiKey).apiSecret(apiSecret).build();

        TextMessage message = new TextMessage(host,
                phoneNum,
                "The verification number is: "+ code
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            return R.success(code);
        } else {
            return R.fail("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }

    }

    public static R<String> uploadFileToOSS(
            String endPoint,
            String accessKeyId,
            String accessKeySecret,
            InputStream inputStream,
            String bucketName,
            String bucketDomain,
            String fileName ){


        OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);

        if(!ossClient.doesBucketExist(bucketName)){
            ossClient.createBucket(bucketName);
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 生成上传文件在OSS服务器上保存的文件名,通过uuid生成随机uuid，将其中的“-”删去（替换成空字符串）
        String fileMainName = UUID.randomUUID().toString().replace("-", "");

        // 从原始文件名中获取文件扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf("."));

        String objectName = folderName + "/" + fileMainName + extensionName;


        try {
            // 调用OSS客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName,objectName,inputStream);

            // 从响应结果中获取具体的响应消息
            ResponseMessage responseMessage = putObjectResult.getResponse();

            // 根据响应状态判断是否成功
            if (responseMessage == null) {
                // 拼接访问刚刚上传的文件的路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;

                // 返回成功，并带上访问路径
                return R.success(ossFileAccessPath);
            }else {
                // 获取响应状态码
                int statusCode = responseMessage.getStatusCode();
                // 没有成功 获取错误消息
                String errorMessage = responseMessage.getErrorResponseAsString();

                return R.fail("当前响应状态码=" + statusCode + " 错误消息=" + errorMessage);
            }
        } catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }

    }
}
