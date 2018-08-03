package com.batch.mcs.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.batch.mcs.finalproject.interfaces.ChatItem;

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindType(ChatItem item);

    }


