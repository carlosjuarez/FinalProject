package com.batch.mcs.finalproject.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.RecyclerviewChatUserItemFragmentChatUserListBinding;
import com.batch.mcs.finalproject.models.User;

import java.util.List;

public class GroupMemberListAdapter extends RecyclerView.Adapter<GroupMemberListAdapter.MyViewHolder>{
    public List<User> groupMemberList;
    private LayoutInflater layoutInflater;
    private GroupMemberListAdapsterListner listener;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final RecyclerviewChatUserItemFragmentChatUserListBinding binding;

        public MyViewHolder(final RecyclerviewChatUserItemFragmentChatUserListBinding itemBinding){
            super(itemBinding.getRoot());
            this.binding= itemBinding;
        }
    }

    public GroupMemberListAdapter(List<User> groupMemberList, GroupMemberListAdapsterListner listener){
        this.groupMemberList= groupMemberList;
        this.listener= listener;
    }

    @NonNull
    @Override
    public GroupMemberListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater==null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RecyclerviewChatUserItemFragmentChatUserListBinding binding=
                DataBindingUtil.inflate(layoutInflater, R.layout.recyclerview_chat_user_item_fragment_chat_user_list,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMemberListAdapter.MyViewHolder holder, final int position) {
        holder.binding.setGroupMember(groupMemberList.get(position));
        holder.binding.floatingActionButtonAddUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onMemberClicked(groupMemberList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupMemberList.size();
    }

    public interface GroupMemberListAdapsterListner{
        void onMemberClicked(User member);
    }
}
