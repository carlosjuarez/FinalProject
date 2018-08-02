package com.batch.mcs.finalproject.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.RecyclerviewGroupInformationItemFragmentGroupListBinding;
import com.batch.mcs.finalproject.models.Group;

import java.util.List;

public class UserGroupListAdapter extends RecyclerView.Adapter<UserGroupListAdapter.ViewHolder> {

    private List<Group> groupList;

    public UserGroupListAdapter(List<Group> groups){
        this.groupList = groups;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewGroupInformationItemFragmentGroupListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_group_information_item_fragment_group_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = groupList.get(position);
        holder.binding.setGroup(group);
    }


    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerviewGroupInformationItemFragmentGroupListBinding binding;

        public ViewHolder(final RecyclerviewGroupInformationItemFragmentGroupListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
