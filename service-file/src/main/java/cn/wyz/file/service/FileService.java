package cn.wyz.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhouzhitong
 * @since 2024-03-17
 **/
public interface FileService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 访问路径
     */
    String upload(MultipartFile file);

}
