package com.batch.mcs.finalproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.ViewHolder;
import com.batch.mcs.finalproject.ViewHolderReceived;
import com.batch.mcs.finalproject.ViewHolderSent;
import com.batch.mcs.finalproject.interfaces.ChatItem;

import java.util.List;

public class ChatUserInteractionAdapter extends RecyclerView.Adapter<ViewHolder>{

    private  Context context;
    private List<ChatItem> items;

    public ChatUserInteractionAdapter(List<ChatItem> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).geChatItemType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view = null;
        switch (type) {
            case ChatItem.SENT:
                view = LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.recycleview_chat_user_interaction_sent_item, viewGroup, false);
                return new ViewHolderSent(view);
            case ChatItem.RECEIVED:
                view = LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.recycleview_chat_user_interaction_receive_item, viewGroup, false);
                return new ViewHolderReceived(view) {
                };
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatItem item = items.get(position);
        holder.bindType(item);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

