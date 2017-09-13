package com.bigsys.auth.project.util.response;

import java.util.HashMap;

public class BSResponse extends HashMap{

    private static final String OK = "OK";
    private static final String FAIL = "FAIL";
    private static final String NEEDLOGIN = "NEEDLOGIN";

    public static BSResponse ok() {
        BSResponse bsResponse = new BSResponse();
        bsResponse.put("status", OK);
        return bsResponse;
    }

    public static BSResponse ok(Object data) {
        BSResponse bsResponse = new BSResponse();
        bsResponse.put("status", OK);
        bsResponse.put("data", data);
        return bsResponse;
    }


    public static BSResponse error(String msg) {
        BSResponse bsResponse = new BSResponse();
        bsResponse.put("status", FAIL);
        bsResponse.put("msg", msg);
        return bsResponse;
    }

    public static BSResponse needLogin(String msg) {
        BSResponse bsResponse = new BSResponse();
        bsResponse.put("status", NEEDLOGIN);
        bsResponse.put("msg", msg);
        return bsResponse;
    }

}
