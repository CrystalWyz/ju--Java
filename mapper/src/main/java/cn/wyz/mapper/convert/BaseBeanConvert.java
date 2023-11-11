package cn.wyz.mapper.convert;

import cn.wyz.mapper.bean.BaseEntity;
import cn.wyz.mapper.bean.bo.BaseBO;
import cn.wyz.mapper.bean.dto.BaseDTO;

/**
 * @author zhouzhitong
 * @since 2023-11-11
 **/
public interface BaseBeanConvert<E extends BaseEntity, DTO extends BaseDTO, BO extends BaseBO> {

    E dtoToBean(DTO d);

    E boDtoBean(BO b);

    DTO beanToDTO(E e);

    DTO boToDTO(BO b);

    BO beanToBo(E e);

    BO dtoToBo(DTO d);

}
