package com.example.nine.mine.mybean;

public class FeedBack_Item {
    public String content;
    public String reply;

    public FeedBack_Item(String content) {
        this.content = content;
    }

    public FeedBack_Item(String content, String reply) {
        this.content = content;
        this.reply = reply;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
