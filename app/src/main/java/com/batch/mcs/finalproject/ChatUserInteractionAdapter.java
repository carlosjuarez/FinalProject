package com.batch.mcs.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.models.Chat;

import java.util.List;

public class ChatUserInteractionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  Context context;
    private List<ChatItem> items;

    public ChatUserInteractionAdapter(Context context,List<ChatItem> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).geChatItemType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        switch (type) {
            case ChatItem.SENT:
                view = LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.type_a, viewGroup, false);
                return new ViewHolderA(view);
            case ChatItem.RECEIVED:
                view = LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.type_b, viewGroup, false);
                return new ViewHolderB(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatItem item = items.get(position);
        holder.bindType(item);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

