package com.batch.mcs.finalproject;

import android.view.View;
import android.widget.TextView;

import com.batch.mcs.finalproject.databinding.RecycleviewChatUserInteractionReceiveItemBinding;

public class ViewHolderReceived extends ViewHolder{
    private final TextView mTextView;

    public ViewHolderReceived(View itemView) {
        super(itemView);

        mTextView = (TextView) itemView.findViewById(R.id.recycleview_chat_user_interaction_recieve_cardview_tv_message);
    }

    public void bindType(ChatItem item) {
        mTextView.setText(((ChatReceivedItem) item).getText());
    }
}