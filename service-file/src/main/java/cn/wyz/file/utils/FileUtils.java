package cn.wyz.file.utils;

import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.util.RandomUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhouzhitong
 * @since 2024-03-17
 **/
public class FileUtils {

    // 绝对路径
    private static final String absolutePath;

    static {
        String osName = System.getProperty("os.name");
        Resource resource;
        if (osName.toLowerCase().contains("mac")) {
            String path = System.getProperty("user.dir");
            absolutePath = path + "/static/";
        } else {
            absolutePath = "/data/images/static/";
        }
        // 创建文件夹
        tryMkdirs(new File(absolutePath));
    }

    public static String upload(MultipartFile file) {
        if (file == null) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        //
        originalFilename = getUUID() + "-" + originalFilename;

        // 存储文件
        File dest = new File(absolutePath + originalFilename);
        try {
            file.transferTo(dest);
            return originalFilename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getUUID() {
        return RandomUtil.randomString(8);
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH_mm_ss");

    private static String getDateTimeNow() {
        return LocalDateTime.now().format(formatter);
    }

    private static void tryMkdirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
