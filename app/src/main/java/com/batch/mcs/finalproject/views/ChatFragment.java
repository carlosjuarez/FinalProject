package com.batch.mcs.finalproject.views;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.adapters.ChatFragmentAdapter;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.FragmentChatBinding;
import com.batch.mcs.finalproject.firebase.firestore.FirebaseDatabase;
import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.User;
import com.batch.mcs.finalproject.viewmodel.AppViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class  ChatFragment extends BaseFragment {

    ChatFragmentAdapter adapter;
    RecyclerView recyclerView;

    public static ChatFragment getInstance(){
        ChatFragment chatFragment = new ChatFragment();
        return chatFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final FragmentChatBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat,container,false);
        final AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        appViewModel.initUserChats();
        recyclerView = binding.rvFragmentChatChatList;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseDatabase fb = new FirebaseDatabase(firebaseFirestore);

        appViewModel.getLiveUserChats().observe(this, new Observer<List<Chat>>() {
            @Override
            public void onChanged(@Nullable List<Chat> chats) {
                setupRecyclerView(appViewModel.getLiveUser().getValue(),chats);
            }
        });

        binding.svFragmentChatSearchPeople.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return binding.getRoot();
    }

    private void setupRecyclerView(User user, List<Chat> chats) {
        adapter = new ChatFragmentAdapter(user, getContext(), chats);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }


}
