package cn.wyz.murdermystery.mapper;

import cn.wyz.mapper.mapper.CrudMapper;
import cn.wyz.murdermystery.bean.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouzhitong
 * @since 2023-12-24
 **/
@Mapper
public interface TagMapper extends CrudMapper<Tag> {
}
