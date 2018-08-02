package com.batch.mcs.finalproject;

public class ChatReceivedItem implements ChatItem {
    private String text;

    public ChatReceivedItem(String text){
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
        return ChatItem.RECEIVED;
    }
}
