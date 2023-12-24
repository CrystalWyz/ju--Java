package cn.wyz.murdermystery.service;

import cn.wyz.mapper.service.MapperService;
import cn.wyz.murdermystery.bean.Tag;
import cn.wyz.murdermystery.bean.dto.TagDTO;

import java.util.List;

/**
 * @author zhouzhitong
 * @since 2023-12-24
 **/
public interface TagService extends MapperService<Tag, TagDTO> {

    int addTags(List<TagDTO> tags);

    default TagDTO newDTO() {
        return new TagDTO();
    }

    default Tag newEntity() {
        return new Tag();
    }
}
