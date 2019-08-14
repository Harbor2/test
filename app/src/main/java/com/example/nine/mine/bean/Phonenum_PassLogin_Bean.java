package com.example.nine.mine.bean;

/**
 * {
 * "code": 0,
 * "data": {
 * "token": "",
 * "userId": 1
 * },
 * "msg": "success"
 * }
 */

/**
 * 账号密码登陆
 */
public class Phonenum_PassLogin_Bean {
    public int code;
    public String msg;
    public Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = (msg != null) ? msg : "";
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        public String token;
        public int userId;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = (token != null) ? token : "";
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
