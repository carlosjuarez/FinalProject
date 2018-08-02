package com.batch.mcs.finalproject;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.batch.mcs.finalproject.databinding.RecyclerviewChatUserItemBinding;
import com.batch.mcs.finalproject.models.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatFragmentAdapter extends RecyclerView.Adapter<ChatFragmentAdapter.BindingHolder> implements Filterable {

    private Context context;
    private List<Chat> chatList;
    private List<Chat> chatListFilter;
    private  FilterChat filterChat;



    public ChatFragmentAdapter(Context context, List<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
        this.chatListFilter = chatList;
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_chat_user_item, viewGroup, false);

        BindingHolder bindingHolder = new BindingHolder(v);

        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder bindingHolder, final int position) {

        Chat chat = chatList.get(position);
        bindingHolder.getBinding().setVariable(BR.Chat, chat);
        bindingHolder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("Chat", chatList.get(position));
                Intent intent = new Intent(context, ChatUserInteractionActivity.class);
                intent.putExtra("BUNDLE", bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public Filter getFilter() {
        if (filterChat == null) {
            filterChat = new FilterChat();
        }
        return filterChat;
    }

    private class FilterChat extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence == null || charSequence.length() == 0) {
                filterResults.values = chatList;
                filterResults.count = chatList.size();
            } else {
                List<Chat> chats = new ArrayList<>();
                for (Chat chat : chatList) {
                    if (chat.getAdminName().toUpperCase().contains(charSequence.toString().toUpperCase())) {
                        chats.add(chat);
                    }
                }
                filterResults.values = chats;
                filterResults.count = chats.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                chatListFilter = (ArrayList<Chat>) filterResults.values;
                notifyDataSetChanged();
            }
        }



    public static class BindingHolder extends RecyclerView.ViewHolder {

        private RecyclerviewChatUserItemBinding binding;

        public BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        public RecyclerviewChatUserItemBinding getBinding() {
            return binding;
        }

    }

}
