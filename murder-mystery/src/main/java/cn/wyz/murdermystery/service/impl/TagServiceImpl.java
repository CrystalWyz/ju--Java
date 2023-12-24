package cn.wyz.murdermystery.service.impl;

import cn.wyz.mapper.req.FiledQuery;
import cn.wyz.mapper.service.impl.MapperServiceImpl;
import cn.wyz.mapper.type.QueryType;
import cn.wyz.murdermystery.bean.Tag;
import cn.wyz.murdermystery.bean.dto.TagDTO;
import cn.wyz.murdermystery.bean.request.TagRequest;
import cn.wyz.murdermystery.mapper.TagMapper;
import cn.wyz.murdermystery.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouzhitong
 * @since 2023-12-24
 **/
@Service
@Slf4j
public class TagServiceImpl
        extends MapperServiceImpl<TagMapper, Tag, TagDTO>
        implements TagService {

    @Override
    public int addTags(List<TagDTO> tags) {
        List<String> names = tags.stream().map(TagDTO::getName).toList();
        TagRequest req = new TagRequest();
        FiledQuery fq = new FiledQuery();
        fq.setFiledName("name");
        fq.setType(QueryType.IN);
        fq.setValue(tags);


//        req.getFiledQueries().add()


        return 0;
    }

}
