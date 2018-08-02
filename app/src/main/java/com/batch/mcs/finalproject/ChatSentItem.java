package com.batch.mcs.finalproject;

public class ChatSentItem implements ChatItem {
    private String text;

    public ChatSentItem(String text){
        this.text = text;

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
