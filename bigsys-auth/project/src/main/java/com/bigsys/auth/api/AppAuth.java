package com.bigsys.auth.api;

import com.bigsys.auth.api.model.AppInfo;

public interface AppAuth {

    /**
     * 根据应用名称获取应用名称获取应用信息
     * @param name 应用名称，全系统唯一
     * @return
     */
    AppInfo auth(String name);

}
