package cn.wyz.mapper.controller;

import cn.wyz.common.anno.Idempotence;
import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.mapper.bean.dto.BaseDTO;
import cn.wyz.mapper.req.BaseRequest;
import cn.wyz.mapper.service.MapperService;
import cn.wyz.mapper.vo.PageResultVO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 基础的 controller 层
 *
 * @author zhouzhitong
 * @since 2023/1/7
 */
public abstract class BaseController
        // 操作实体
        <Entity extends BaseEntity
                , DTO extends BaseDTO
                // 分页参数
                , Query extends BaseRequest
                // 操作service
                , Service extends MapperService<Entity, DTO>> {

    @Getter
    @Autowired
    private Service service;

    @Value("${lib.mapper.size-limit:1000}")
    private Integer sizeLimit;

    public Service service() {
        return service;
    }


    /**
     * 查询所有(如果带了分页参数,则分页查询, 否则查询所有)
     * <p>
     * 但是为了安全起见, 查询所有的时候还是限制一千条
     *
     * @param page 分页参数
     * @return 分页结果
     */
    @GetMapping({"", "/page"})
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public PageResultVO<DTO> page(Query page) {
        // 为了安全起见, 查询所有的时候还是限制一千条
        if (page.getSize() == null) {
            page.setSize(sizeLimit);
        }
        return service.page(page);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseResult<DTO> get(@PathVariable("id") Long id) {
        return ResponseResult.ok(this.service.get(id));
    }

    /**
     * 新增数据
     *
     * @param dto 实体
     * @return 新增结果
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Idempotence
    public ResponseResult<DTO> add(@RequestBody DTO dto) {
        return ResponseResult.ok(this.service.add(dto));
    }

    /**
     * 编辑数据
     *
     * @param id  编号
     * @param dto 实体
     * @return 编辑结果
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Idempotence
    public ResponseResult<DTO> update(@PathVariable("id") Long id, @RequestBody DTO dto) {
        return ResponseResult.ok(this.service.update(id, dto));
    }

    /**
     * 编辑部分字段
     *
     * @param id  编号
     * @param dto 实体
     * @return 编辑结果
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Idempotence
    public ResponseResult<DTO> edit(@PathVariable("id") Long id, @RequestBody DTO dto) {
        return ResponseResult.ok(this.service.edit(id, dto));
    }


    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Idempotence
    public ResponseResult<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseResult.ok(this.service.remove(id));
    }

}
