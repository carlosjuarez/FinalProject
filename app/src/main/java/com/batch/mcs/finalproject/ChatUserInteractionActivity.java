package com.batch.mcs.finalproject;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.batch.mcs.finalproject.adapters.UserGroupListAdapter;
import com.batch.mcs.finalproject.databinding.ActivityChatUserInteractionBinding;
import com.batch.mcs.finalproject.interfaces.ChatItem;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.MockFactory;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.ChatViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class ChatUserInteractionActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    MutableLiveData<Chat> liveChat = new MutableLiveData<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ChatViewModel chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
        ActivityChatUserInteractionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_user_interaction);
        User user = getIntent().getBundleExtra("BUNDLE").getParcelable("User");
        final String chatId = getIntent().getBundleExtra("BUNDLE").getString("chatId");
        final String userId = user.getId();

        chatViewModel.initChat(chatId);
        chatViewModel.initMessages(this,chatId);

        recyclerView = (RecyclerView) binding.rvFragmentChatUserInteraction;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chatViewModel.getLiveUserMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> messages) {
                setupRecyclerView(messages,userId);
            }
        });

    }

    private void setupRecyclerView(List<Message> messages, String userId) {
        List<ChatItem> chatItems = getChatItems(messages,userId);
        adapter = new ChatUserInteractionAdapter(chatItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }

    public List<ChatItem> getChatItems(List<Message> messages, String userId){

        List<ChatItem> chatItems = new ArrayList<>();

        for(Message message : messages){
            if(message.getCreator().equalsIgnoreCase(userId)){
                ChatSentItem chatSentItem = new ChatSentItem(message);
                chatItems.add(chatSentItem);
            }else {
                ChatReceivedItem chatReceivedItem = new ChatReceivedItem(message);
                chatItems.add(chatReceivedItem);
            }
        }

        return chatItems;

    }
}


