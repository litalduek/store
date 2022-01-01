package com.vmware.store.dto;

public class Message {

    Object response;
    boolean success;

    public Message(){}

    public Message(Object response, boolean success){
        this.response = response;
        this.success = success;
    }

    public Object getResponse() {
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

}