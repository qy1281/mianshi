package cn.qq.bread.mapper;

import cn.qq.bread.entity.Bread;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BreadMapper {
    void addBread(Bread bread);
    Bread getBreadbyId(Integer id);
    void deleteBreadbyId(Integer id);
}
