package com.batch.mcs.finalproject.adapters;

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

import com.batch.mcs.finalproject.BR;
import com.batch.mcs.finalproject.ChatUserInteractionActivity;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.RecyclerviewChatUserItemBinding;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.User;

import java.util.ArrayList;
import java.util.List;

public class ChatFragmentAdapter extends RecyclerView.Adapter<ChatFragmentAdapter.BindingHolder> implements Filterable {

    private Context context;
    private List<Chat> chatList;
    private List<Chat> chatListFilter;
    User user;

    public ChatFragmentAdapter(User user,Context context, List<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
        this.chatListFilter = chatList;
        this.user = user;
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

        final Chat chat = chatList.get(position);
        bindingHolder.getBinding().setVariable(BR.Chat, chat);
        bindingHolder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("Chat",chat);
                bundle.putString("userId", user.getId());
                Intent intent = new Intent(context, ChatUserInteractionActivity.class);
                intent.putExtra("BUNDLE", bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    chatListFilter = chatList;
                } else {
                    List<Chat> filteredList = new ArrayList<>();
                    for (Chat row : chatList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getAdminName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    chatListFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = chatListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                chatListFilter = (ArrayList<Chat>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
