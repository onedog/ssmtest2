package com.mytest.crud.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {

    private int code;
    private String msg;
    private Map<String,Object> exttend=new HashMap<String,Object>();

    public static Msg success(){
        Msg result=new Msg();
        result.setCode(100);
        result.setMsg("success");
        return result;
    }
    public static Msg fail(){
        Msg result=new Msg();
        result.setCode(200);
        result.setMsg("fail");
        return result;
    }
    public Msg add(String key, Object value){
        this.getExttend().put(key, value);
        return this;
    }
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
        this.msg = msg;
    }

    public Map<String, Object> getExttend() {
        return exttend;
    }

    public void setExttend(Map<String, Object> exttend) {
        this.exttend = exttend;
    }

}
