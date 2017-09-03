package com.bigsys.auth.project.db.dao;

import com.bigsys.auth.project.db.model.BsTest;
import com.bigsys.auth.project.db.model.BsTestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BsTestMapper {
    long countByExample(BsTestExample example);

    int deleteByExample(BsTestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BsTest record);

    int insertSelective(BsTest record);

    List<BsTest> selectByExample(BsTestExample example);

    BsTest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BsTest record, @Param("example") BsTestExample example);

    int updateByExample(@Param("record") BsTest record, @Param("example") BsTestExample example);

    int updateByPrimaryKeySelective(BsTest record);

    int updateByPrimaryKey(BsTest record);
}