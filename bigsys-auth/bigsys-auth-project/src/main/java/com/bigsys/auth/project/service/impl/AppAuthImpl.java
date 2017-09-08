package com.bigsys.auth.project.service.impl;


import com.bigsys.auth.api.AppAuth;
import com.bigsys.auth.project.db.dao.AppInfoMapper;
import com.bigsys.auth.project.db.model.AppInfo;
import com.bigsys.auth.project.db.model.AppInfoExample;
import com.bigsys.auth.project.util.CreateExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appAuth")
public class AppAuthImpl implements AppAuth {

    @Resource
    private AppInfoMapper appInfoMapper;


    List<AppInfo> select(CreateExample<AppInfoExample, AppInfoExample.Criteria> example) {
        AppInfoExample appInfoExample = new AppInfoExample();
        AppInfoExample.Criteria criteria = appInfoExample.createCriteria();
        example.createExample(appInfoExample, criteria);
        return appInfoMapper.selectByExample(appInfoExample);
    }

    @Override
    public com.bigsys.auth.api.model.AppInfo auth(String name) {
        List<AppInfo> list = select((example, criteria) -> {});
        if (list.size() > 0) {
            com.bigsys.auth.api.model.AppInfo appInfo = new com.bigsys.auth.api.model.AppInfo();
            appInfo.setId(list.get(0).getId());
            appInfo.setName(list.get(0).getName());
            appInfo.setAuthTime(list.get(0).getAuthTime());
            return appInfo;
        }
        return null;
    }
}
