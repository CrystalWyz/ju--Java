package cn.wyz.file.service.impl;

import cn.wyz.file.service.FileService;
import cn.wyz.file.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhouzhitong
 * @since 2024-03-17
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {
        if (file == null) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        LOGGER.info("上传文件: {}", originalFilename);
        return FileUtils.upload(file);
    }

}
