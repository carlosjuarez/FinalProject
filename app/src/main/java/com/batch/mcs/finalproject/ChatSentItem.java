package com.batch.mcs.finalproject;

import com.batch.mcs.finalproject.models.Message;

public class ChatSentItem implements ChatItem {
    private String text;
    Message message;

    public ChatSentItem(Message message){
        this.message = message;
        this.text = message.getContent();
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public int geChatItemType() {
        return ChatItem.SENT;
    }
}
