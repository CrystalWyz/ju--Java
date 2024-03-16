package cn.wyz.mapper.service.impl;

import cn.wyz.common.service.SystemProvider;
import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.mapper.bean.dto.PageInfo;
import cn.wyz.mapper.exception.NotFoundResourceException;
import cn.wyz.mapper.mapper.CrudMapper;
import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.mapper.service.MapperService;
import cn.wyz.mapper.vo.PageResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据库的 CRUD 基础服务实现类
 *
 * @author zhouzhitong
 * @since 2022/9/28
 */
@Slf4j
public abstract class MapperServiceImpl
        <Mapper extends CrudMapper<Entity>,
                Entity extends BaseEntity,
                DTO extends BaseDTO>
        extends ServiceImpl<Mapper, Entity>
        implements MapperService<Entity, DTO> {

    @Resource
    protected SystemProvider systemProvider;

    @Override
    public <Query extends BaseRequest> List<DTO> queryAll(Query query) {
        LOGGER.debug("queryAll request: {}", query);
        QueryWrapper<Entity> wrapper = buildQuery(query);
        List<Entity> list = this.list(wrapper);
        return list.stream().map(this::toDTO).toList();
    }

    @Override
    public <Query extends BaseRequest> PageResultVO<DTO> page(Query query) {
        LOGGER.debug("page request: {}", query);
        QueryWrapper<Entity> wrapper = buildQuery(query);
        // 在查询之前调用 PageHelper.startPage() 方法设置分页参数
        com.github.pagehelper.Page<Entity> objectPage = PageHelper.startPage(query.getPage(), query.getSize());
        List<Entity> list = list(wrapper);

        PageInfo pageInfo = new PageInfo(objectPage.getTotal(), query.size(), query.page());
        List<DTO> dtoList = list.stream().map(this::toDTO).toList();
        return PageResultVO.ok(dtoList, pageInfo);
    }

    @Override
    public DTO add(DTO dto) {
        LOGGER.info("add request: {}", dto);
        // 创建一个实体
        Entity entity = newEntity();
        // 赋值
        copyProperties(dto, entity);

        entity.setCreatedBy(systemProvider.getCurrentUserId());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setLastModifiedBy(systemProvider.getCurrentUserId());

        // 保存
        return save(entity)
                ? toDTO(entity)
                : null;
    }

    @Override
    public boolean remove(Long id) {
        LOGGER.info("remove request: {}", id);
        return removeById(id);
    }

    @Override
    public DTO update(Long id, DTO dto) {
        LOGGER.info("update request: {}", dto);
        Entity entity = getEntity(id);
        copyProperties(dto, entity);

        entity.setUpdateTime(LocalDateTime.now());
        entity.setLastModifiedBy(systemProvider.getCurrentUserId());

        boolean update = this.updateById(entity);
        return update
                ? toDTO(entity)
                : null;
    }

    @Override
    public DTO edit(Long id, DTO dto) {
        LOGGER.info("edit request: {}", dto);
        Entity entity = getEntity(id);
        copyProperties(dto, entity);

        entity.setUpdateTime(LocalDateTime.now());
        entity.setLastModifiedBy(systemProvider.getCurrentUserId());

        boolean update = this.updateById(entity);
        return update
                ? toDTO(entity)
                : null;
    }

    @Override
    public <Query extends BaseRequest> long count(Query query) {
        LOGGER.debug("count request: {}", query);
        return this.count(buildQuery(query));
    }

    @Override
    public DTO get(Long id) {
        LOGGER.debug("get request: {}", id);
        Entity entity = getEntity(id);
        if (entity == null) {
            throw new NotFoundResourceException();
        }
        return toDTO(entity);
    }

    protected Entity getEntity(Long id) {
        return this.getById(id);
    }

}
