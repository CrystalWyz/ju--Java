package cn.wyz.common.util;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * 加密工具类
 *
 * @author zhouzhitong
 * @since 2023/2/15
 */
public class EncryptUtils {

    /**
     * 图片转为base64编码
     */
    public static String getBase64(byte[] data) {
        // 对字节数组Base64编码
        // 返回Base64编码过的字节数组字符串
        return String.copyValueOf(Base64Coder.encode(data));
    }

    /**
     * 获取文件的MD5值
     *
     * @param path
     * @return
     */
    public static String getMd5(String path) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(path);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            fis.close();
            byte[] byteArray = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : byteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param pwd
     * @return
     */
    public static String encrypt16(String pwd) {
        return encrypt32(pwd).substring(8, 24);
    }

    /**
     * @Description:加密-32位小写
     * @time:2016年5月23日 上午11:15:33
     */
    private static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuilder hexValue = new StringBuilder();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

}
