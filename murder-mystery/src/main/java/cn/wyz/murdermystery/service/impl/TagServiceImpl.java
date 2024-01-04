package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.murdermystery.bean.Tag;
import cn.wyz.murdermystery.bean.dto.TagDTO;
import cn.wyz.murdermystery.bean.request.TagRequest;
import cn.wyz.murdermystery.mapper.TagMapper;
import cn.wyz.murdermystery.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhouzhitong
 * @since 2023-12-24
 **/
@Service
@Slf4j
public class TagServiceImpl extends MapperServiceImpl<TagMapper, Tag, TagDTO> implements TagService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addTags(List<TagDTO> tags) {
        List<String> names = tags.stream().map(TagDTO::getName).toList();
        TagRequest req = TagRequest.findInByNames(names);
        List<TagDTO> exitTags = this.queryAll(req);
        Set<String> exitTagNames = exitTags.stream().map(TagDTO::getName).collect(Collectors.toSet());

        List<TagDTO> needAddTags = tags.stream().filter(t -> !exitTagNames.contains(t.getName())).toList();
        if (needAddTags.isEmpty()) {
            return 0;
        }
        List<Tag> list = needAddTags.stream().map(tag -> copyProperties(tag, newEntity())).toList();
        this.saveBatch(list);
        return list.size();
    }

}
