package com.batch.mcs.finalproject.repository.models;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseListener extends LiveData{

    class RealtimeListener implements ValueEventListener {

        Query query;

        public RealtimeListener(){

        }

        public void setQuery(Query query) {
            this.query = query;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("FIREBASE ", "Can't listen to query " + query, databaseError.toException());
        }
    }



}
