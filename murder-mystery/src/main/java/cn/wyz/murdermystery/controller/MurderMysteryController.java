package cn.wyz.murdermystery.controller;

import cn.wyz.common.bean.request.PageVM;
import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.murdermystery.bean.bo.MurderMysteryBO;
import cn.wyz.murdermystery.bean.request.MurderMysteryPageRequest;
import cn.wyz.murdermystery.bean.response.MurderMysteryPageResponse;
import cn.wyz.murdermystery.convert.BeanConvert;
import cn.wyz.murdermystery.service.MurderMysteryService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @author wnx
 */
@RestController
@RequestMapping("/api/v1/murderMysteries")
@Tag(name = "剧本杀接口", description = "剧本杀接口")
public class MurderMysteryController {

    private final MurderMysteryService murderMysteryService;

    private final BeanConvert beanConvert;

    public MurderMysteryController(MurderMysteryService murderMysteryService, BeanConvert beanConvert) {
        this.murderMysteryService = murderMysteryService;
        this.beanConvert = beanConvert;
    }

    @Operation(description = "剧本杀分页查询")
    @PostMapping("/page")
    public ResponseResult<PageInfo<MurderMysteryPageResponse>> murderMysteryPage(@RequestBody PageVM<MurderMysteryPageRequest> pageRequest) {
        PageInfo<MurderMysteryBO> boPage = murderMysteryService.murderMysteryPage(pageRequest);
        PageInfo<MurderMysteryPageResponse> resPage = beanConvert.murderMysteryBOPageToMurderMysteryPageResponsePage(boPage);
        if (ObjectUtils.isNotEmpty(boPage.getList())) {
            resPage.setList(boPage.getList().stream().map(beanConvert::murderMysteryToMurderMysteryPageResponse).collect(Collectors.toList()));
        }

        return ResponseResult.success(resPage);
    }
}
