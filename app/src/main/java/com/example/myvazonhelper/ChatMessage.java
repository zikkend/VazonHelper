package com.example.myvazonhelper;

import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private String messageTime;

    public  ChatMessage(String messageText, String messageUser){
        this.messageText = messageText;
        this.messageUser = messageUser;
        Date date = new Date();
        String temp = date.toString().substring(10,16);
        messageTime = temp;
    }
    public ChatMessage(){}

    public String getMessageText(){return messageText;}

    public void  setMessageText(String messageText){this.messageText = messageText;}

    public String getMessageUser(){return messageUser;}

    public void  setMessageUser(String messageUser){this.messageUser = messageUser;}

    public String getMessageTime(){return messageTime;}

    public void  setMessageTime(String messageTime){this.messageTime = messageTime;}

}
