package com.bigsys.auth.api;


import com.bigsys.auth.api.model.AppInfo;

public interface AppAuth {

    /**
     * 根据应用名称获取授权信息
     * @param name
     * @return
     */
    AppInfo auth(String name);


}
