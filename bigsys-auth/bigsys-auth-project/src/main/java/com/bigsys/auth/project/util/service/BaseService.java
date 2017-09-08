package com.bigsys.auth.project.util.service;

import com.bigsys.auth.project.util.CreateExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseService<key, model, example, criteria> {

    long countByExample(CreateExample<example, criteria> createExample);

    int deleteByExample(CreateExample<example, criteria> createExample);

    int deleteByPrimaryKey(key id);

    int insert(model record);

    int insertSelective(model record);

    List<model> selectByExample(CreateExample<example, criteria> createExample);

    model selectByPrimaryKey(key id);

    int updateByExampleSelective(model record, CreateExample<example, criteria> createExample);

    int updateByExample(model record, CreateExample<example, criteria> createExample);

    int updateByPrimaryKeySelective(model record);

    int updateByPrimaryKey(model record);

    PageInfo<model> selectByExampleAndPage(PageInfo<model> page, CreateExample<example, criteria> createExample);
    
}
