package cn.wyz.common.util;

import cn.wyz.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author zhouzhitong
 * @since 2023-11-12
 **/
@Slf4j
public class FileUtils {

    public static void tryCreateFile(String filePath) {
        File file = new File(filePath);
        // 判断文件是否存在
        if (file.isDirectory()) {
            throw new BaseException(filePath + " 不是文件目录");
        }
        if (file.exists()) {
            LOGGER.debug("文件已存在, 跳过创建: {}", filePath);
            return;
        }
        // 创建文件
        // 先判断目录是否存在, 如果不存在就先创建目录
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            boolean mkdirs = parentFile.mkdirs();
            if (!mkdirs) {
                throw new BaseException("创建目录失败: " + parentFile.getAbsolutePath());
            }
        }
        // 保证了目录存在, 开始创建文件
        try {
            boolean newFile = file.createNewFile();
            if (!newFile) {
                throw new BaseException("创建文件失败: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

}
