package com.sms.international.admin.service;

import com.sms.international.admin.mapper.BaseMapper;
import com.sms.international.admin.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 37985 on 2016/7/11.
 */
public abstract class BaseService<T extends BaseEntity,Mapper extends BaseMapper<Integer,T>>{

    @Autowired
    protected Mapper mapper;

    /**
     * 通用分页方法
     * @param t
     * @return
     */
  public List<T> findByT(T t)
  {
      return  mapper.findByT(t);
  }

    /**
     * 通过Id查找唯一对象
     * @param id
     * @return
     */
    public T findOne(Integer id)
    {
        return mapper.findOne(id);
    }

    /**
     * 保存或修改
     * @param t
     * @return
     */
    public Integer saveOrUpdate(T t)
    {
        Integer result=null;
        if(t.getId()!=null&&!"".equals(t.getId()))
        {
            result=mapper.update(t);
        }
        else
        {
            result=mapper.save(t);
        }
        return result;
    }

    /**
     * 根据Id删除
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        int result=mapper.deleteById(id);
        return result>0?true:false;
    }
}
