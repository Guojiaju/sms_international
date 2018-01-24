package com.sms.international.admin.mapper;

import com.sms.international.admin.model.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by 37985 on 2016/7/11.
 */
@NoRepositoryBean
public interface BaseMapper<ID ,T extends BaseEntity> {

    /**
     * 自定义条件查找
     * @param t
     * @return
     */
    List<T> findByT(T t);

    /**
     * 自定义条件统计数量
     * @param t
     * @return
     */
    int countTotal(T t);

    /**
     * 根据Id查找唯一对象
     * @param id
     * @return
     */
    T findOne(ID id);

    /**
     * 保存新数据
     * @param t
     * @return
     */
    int save(T t);

    /**
     * 更新数据
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 根据Id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);
    
}
