package com.batch.mcs.finalproject;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import com.batch.mcs.finalproject.databinding.ActivityChatUserInteractionBinding;
import com.batch.mcs.finalproject.viewmodel.ChatViewModel;


public class ChatUserInteractionActivity extends AppCompatActivity{

    RecyclerView.Adapter adapter;
    RecyclerView recyclerview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChatViewModel chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);

        ActivityChatUserInteractionBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_chat_user_interaction);



    }
}


