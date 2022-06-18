package com.hspjava.qqProject.qqCommon;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1";
    String MESSAGE_LOGIN_FAIL = "2";
    String MESSAGE_COMM_MES = "3";//普通聊天
    String MESSAGE_GET_ONLINE_USER = "4";
    String MESSAGE_RET_ONLINE_USER = "5";
    String MESSAGE_CLIENT_EXIT = "6";
    String MESSAGE_TO_ALL_MES = "7";//群发消息
    String FILE_COMM_FILE = "8";//发送文件
    String SERVER_COMM_MES = "9";//服务器群发通知
}
