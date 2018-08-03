package com.batch.mcs.finalproject;

import android.view.View;
import android.widget.TextView;

import com.batch.mcs.finalproject.interfaces.ChatItem;

public class ViewHolderSent extends ViewHolder{
    private final TextView mTextView;

    public ViewHolderSent(View itemView) {
        super(itemView);

        mTextView = (TextView) itemView.findViewById(R.id.recycleview_chat_user_interaction_sent_cardview_tv_message);
    }

    public void bindType(ChatItem item) {
        mTextView.setText(((ChatSentItem) item).getText());
    }
}

