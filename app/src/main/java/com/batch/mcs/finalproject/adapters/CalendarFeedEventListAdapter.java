package com.batch.mcs.finalproject.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.batch.mcs.finalproject.R;
import com.batch.mcs.finalproject.databinding.RecyclerviewFeedItemBinding;
import com.batch.mcs.finalproject.databinding.RecyclerviewGroupInformationItemFragmentGroupListBinding;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.views.CalendarDisplayFragment;

import java.util.ArrayList;
import java.util.List;

public class CalendarFeedEventListAdapter extends RecyclerView.Adapter<CalendarFeedEventListAdapter.ViewHolder> implements Filterable {

    private List<Event> eventList;
    private List<Event> eventListFiltered;

    public CalendarFeedEventListAdapter(List<Event> events){
        this.eventList = events;
        eventListFiltered = eventList;
    }


    @NonNull
    @Override
    public CalendarFeedEventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewFeedItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_feed_item, parent, false);
        return new CalendarFeedEventListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventListFiltered.get(position);
        holder.binding.setEvent(event);
    }

    @Override
    public int getItemCount() {
        return eventListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    eventListFiltered = eventList;
                } else {
                    List<Event> filteredList = new ArrayList<>();
                    for (Event row : eventList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getDate().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    eventListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = eventListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                eventListFiltered = (ArrayList<Event>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerviewFeedItemBinding binding;

        public ViewHolder(final RecyclerviewFeedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
