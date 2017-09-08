package com.bigsys.auth.project.util;

import java.util.HashMap;

public class BSResponse extends HashMap{

    private static final String OK = "OK";

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
        bsResponse.put("status", OK);
        bsResponse.put("error", msg);
        return bsResponse;
    }

}
