package com.batch.mcs.finalproject.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.RecyclerviewGroupInformationItemFragmentGroupListBinding;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.views.CalendarDisplayFragment;

import java.util.List;

public class CalendarFeedEventListAdapter extends RecyclerView.Adapter<CalendarFeedEventListAdapter.ViewHolder> {

    private List<Event> eventList;

    public CalendarFeedEventListAdapter(List<Event> events){
        this.eventList = events;
    }


    @NonNull
    @Override
    public CalendarFeedEventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //RecyclerViewFeedItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_feed_item, parent, false);
        return new CalendarFeedEventListAdapter.ViewHolder(null);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);
        //holder.binding.setEvent(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //private final RecyclerViewFeedItemBinding binding;

        public ViewHolder(Object object){//final RecyclerViewFeedItemBinding binding) {
            super(null);
            //this.binding = binding;*/
        }
    }

}
