package cn.wyz.file.controller;

import cn.hutool.core.io.resource.FileResource;
import cn.hutool.core.io.resource.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zhouzhitong
 * @since 2024-03-17
 **/
@RestController
@RequestMapping("/static")
public class FileController {

    @GetMapping("/{filePath}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filePath") String filePath) throws Exception {
        // 查看操作系统, 如果是 linux 则使用 FileResource, 如果是 mac 则使用 ClassPathResource
        String osName = System.getProperty("os.name");
        Resource resource;
        if (osName.toLowerCase().contains("mac")) {
            String path = System.getProperty("user.dir");
            resource = new FileResource(path + "/static/" + filePath);
        } else {
            resource = new FileResource("/data/images/static/" + filePath);
        }
        Path path = Paths.get(resource.getUrl().toURI());
        byte[] imageBytes = Files.readAllBytes(path);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

}
