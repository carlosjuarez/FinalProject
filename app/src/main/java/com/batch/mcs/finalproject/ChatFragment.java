package com.batch.mcs.finalproject;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.batch.mcs.finalproject.databinding.FragmentChatBinding;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.MockFactory;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatFragment extends BaseFragment {

    MockFactory mockFactory = new MockFactory();
    RecyclerView recyclerView;
    private ChatFragmentAdapter adapter;

    public static ChatFragment getInstance(){
        ChatFragment chatFragment = new ChatFragment();
        return chatFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChatBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat,container,false);
        AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        List<User> userList = mockFactory.getUsersArrayList();

        User user =  userList.get(0);
        binding.setUser(user);
        User recipient = userList.get(1);

        final Chat chat = new Chat();

        chat.setId("111111");
        chat.setAdmin(user.getId());
        chat.setAdminName(user.getName() +" "+user.getLastName() );
        chat.setMember(recipient.getId());
        chat.setMemberName(recipient.getName() +" "+recipient.getLastName() );
        Map<String, Boolean> map = new HashMap<>();
        Message message = new Message();
        message.setChatId(chat.getId());
        message.setId("22222");
        message.setCreator(user.getId());
        message.setContent("Hello");
        map.put(message.getId(),true);
        chat.setMessages(map);
        final List<Chat> chatList = new ArrayList<>();
        chatList.add(chat);
        Chat chat2 = new Chat();
        chat2.setId("11111");
        chat2.setAdmin(user.getId()+1);
        chat2.setMemberName("Juan Gomez");
        chatList.add(chat2);
        adapter = new ChatFragmentAdapter(getContext(), chatList);

        binding.svFragmentChatSearchPeople.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);

                Toast.makeText(getActivity(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        recyclerView = (RecyclerView) binding.rvFragmentChatChatList;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }


}
