package com.bigsys.auth.service.impl;

import com.bigsys.auth.api.AppAuth;
import com.bigsys.auth.api.model.AppInfo;
import com.bigsys.auth.api.model.AppInfoExample;
import com.bigsys.auth.db.dao.AppInfoMapper;
import com.bigsys.auth.util.CreateExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appAuth")
public class AppAuthImpl implements AppAuth{

    @Resource
    private AppInfoMapper appInfoMapper;

    @Override
    public AppInfo auth(String name) {

        select((example, criteria) -> {

        });

        return null;
    }

    List<AppInfo> select(CreateExample<AppInfoExample, AppInfoExample.Criteria> example) {
        AppInfoExample appInfoExample = new AppInfoExample();
        AppInfoExample.Criteria criteria = appInfoExample.createCriteria();
        example.createExample(appInfoExample, criteria);
        return appInfoMapper.selectByExample(appInfoExample);
    }
}
