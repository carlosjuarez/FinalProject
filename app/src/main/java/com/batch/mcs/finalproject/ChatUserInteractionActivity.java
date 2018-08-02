package com.batch.mcs.finalproject;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.batch.mcs.finalproject.databinding.ActivityChatUserInteractionBinding;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.MockFactory;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.ChatViewModel;

import java.util.ArrayList;
import java.util.List;


public class ChatUserInteractionActivity extends AppCompatActivity{

    RecyclerView.Adapter adapter;
    RecyclerView recyclerview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChatViewModel chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);

        ActivityChatUserInteractionBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_chat_user_interaction);

        Bundle bundle = new Bundle();
        Chat chat1 = bundle.getParcelable("Chat");



        MockFactory mockFactory = new MockFactory();
        List<User> userList = mockFactory.getUsersArrayList();
        User user =  userList.get(0);
        User recipient = userList.get(1);
        Chat chat = new Chat();
        chat.setId("111111");
        chat.setAdmin(user.getId());
        chat.setAdminName(user.getName() +" "+user.getLastName() );
        chat.setMember(recipient.getId());
        chat.setMemberName(recipient.getName() +" "+recipient.getLastName() );
        Message message = new Message();
        message.setChatId(chat.getId());
        message.setId("22222");
        message.setCreator(user.getId());
        message.setContent("Hello you uck dicking bitch ace sucker luck bitch");

        Message message1 = new Message();
        message1.setChatId(chat.getId());
        message1.setId("2222211");
        message1.setCreator(user.getName());
        message1.setContent("Hello MyMessage");
        List<Message> messageList = new ArrayList<>();
        messageList.add(message);
        messageList.add(message1);

        List<ChatItem> chatItems = new ArrayList<>();
        ChatSentItem chatSentItem = new ChatSentItem(message);
        message.setContent("jolis perra");
        ChatReceivedItem chatReceivedItem= new ChatReceivedItem(message);
        chatItems.add(chatSentItem);
        chatItems.add(chatReceivedItem);



        adapter = new ChatUserInteractionAdapter(chatItems);
        recyclerview = (RecyclerView) binding.rvFragmentChatUserInteraction;
        recyclerview.setHasFixedSize(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);

    }
}


