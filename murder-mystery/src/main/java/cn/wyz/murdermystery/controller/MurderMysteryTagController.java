package cn.wyz.murdermystery.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/murderMystery")
@Tag(name = "剧本杀标签接口", description = "剧本杀标签接口")
public class MurderMysteryTagController {
}
