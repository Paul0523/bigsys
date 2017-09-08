package com.bigsys.auth.project.db.dao;

import com.bigsys.auth.project.db.model.AppInfo;
import com.bigsys.auth.project.db.model.AppInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppInfoMapper {
    long countByExample(AppInfoExample example);

    int deleteByExample(AppInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    List<AppInfo> selectByExample(AppInfoExample example);

    AppInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    int updateByExample(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);
}