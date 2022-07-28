package cn.qq.bread.service;

import cn.qq.bread.mapper.BreadMapper;
import cn.qq.bread.entity.Bread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BreadServiceImpl implements  BreadService {
    @Autowired
    private BreadMapper breadMapper;

    @Override
    public void addBread(Bread bread) {
        breadMapper.addBread(bread);
    }

    @Override
    public Bread getBreadbyId(Integer id) {
        return breadMapper.getBreadbyId(id);
    }

    @Override
    public void deleteBreadbyId(Integer id) {
        breadMapper.deleteBreadbyId(id);
    }
}
