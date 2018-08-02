package com.batch.mcs.finalproject.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.batch.mcs.finalproject.BR;
import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.models.Group;

import java.util.ArrayList;
import java.util.List;

public class SearchFragmentRecyclerViewAdapter extends RecyclerView.Adapter<SearchFragmentRecyclerViewAdapter.RecyclerViewHolder> implements Filterable {

    private List<Group> groups;
    private List<Group> filteredGroups;
    private Context context;

    public SearchFragmentRecyclerViewAdapter(List<Group> groups, Context context){
        this.groups = groups;
        this.filteredGroups = groups;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.recyclerview_group_information_item,viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Group group = filteredGroups.get(position);
        holder.getBinding().setVariable(BR.groupItem, group);
    }

    @Override
    public int getItemCount() {
        return filteredGroups.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if(charString.isEmpty()){
                    filteredGroups = groups;
                } else {
                    List<Group> fGroups = new ArrayList<>();
                    for(Group g:fGroups){
                        if(g.getName().toLowerCase().contains(charString)){
                            fGroups.add(g);
                        }
                        filteredGroups = fGroups;
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredGroups;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredGroups = (ArrayList<Group>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
