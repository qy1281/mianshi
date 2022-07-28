package cn.qq.bread.service;

import cn.qq.bread.entity.Bread;

public interface BreadService {
    void addBread(Bread bread);
    Bread getBreadbyId(Integer id);
    void deleteBreadbyId(Integer id);
}
