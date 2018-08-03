package com.batch.mcs.finalproject.firebase.firestore;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.batch.mcs.finalproject.models.Chat;
import com.batch.mcs.finalproject.models.Event;
import com.batch.mcs.finalproject.models.Group;
import com.batch.mcs.finalproject.models.Message;
import com.batch.mcs.finalproject.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabase {

    FirebaseFirestore db;

    public FirebaseDatabase(FirebaseFirestore db) {
        this.db = db;
    }

    public void saveUser(final User user, final MutableLiveData<User> userLiveData) {

        db.collection("users").document(user.getId()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                userLiveData.postValue(user);
            }
        });
    }

    public String saveGroup(Group group) {
        DocumentReference newGroupRef = db.collection("group").document();
        String myGId = newGroupRef.getId();
        group.setId(myGId);
        newGroupRef.set(group);
        return myGId;
    }

    public String saveEvent(Event event) {
        DocumentReference newEventRef = db.collection("events").document();
        String myEId = newEventRef.getId();
        event.setId(myEId);
        newEventRef.set(event);
        return myEId;

    }

    public String saveChat(User creator, User member, Chat chat) {

        String creatorId = creator.getId();
        String memberId = member.getId();
        chat.setAdmin(creatorId);
        DocumentReference newChatRef = db.collection("chats").document();
        String myCId = newChatRef.getId();
        chat.setId(myCId);
        newChatRef.set(chat);

        return myCId;
    }

    public String saveMessage(Chat chat, Message message) {

        String admin = chat.getId();
        DocumentReference newMessageRef = db.collection("messages").document();
        String myMId = newMessageRef.getId();
        message.setId(myMId);
        newMessageRef.set(message);

        return myMId;
    }

    public void loadUser(String idUser, final MutableLiveData<User> mutableLiveData) {

        final DocumentReference docRef = db.collection("users").document(idUser);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                try {
                    if (e != null) {
                        throw e;
                    } else {
                        User user = new Gson().fromJson(snapshot.getData().toString(), User.class);
                        mutableLiveData.setValue(user);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });
    }

    public void loadGroupsOwnedByUser(final User user, final MutableLiveData<List<Group>> mutableLiveData) {
        final List<Group> groups = new ArrayList<>();

        if(user.getMyGroups()!=null){
            for (String idGroup : user.getMyGroups().keySet()) {
                final DocumentReference docRef = db.collection("group").document(idGroup);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Group group = document.toObject(Group.class);
                                groups.add(group);
                                if(groups.size() == user.getMyGroups().size()){
                                    mutableLiveData.postValue(groups);
                                }
                            } else {
                                mutableLiveData.postValue(groups);
                            }
                        } else {
                            mutableLiveData.postValue(groups);
                        }
                    }
                });
            }
        }

        mutableLiveData.postValue(groups);
    }

    public void loadGroups(String idUser, final MutableLiveData<List<Group>> liveGroupMember) {
        db.collection("groups").whereEqualTo("idMembers."+idUser,true)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                //add error
                ArrayList<Group> groups = new ArrayList<>();
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Group group = new Gson().fromJson(snapshot.getData().toString(), Group.class);
                    groups.add(group);
                }
                liveGroupMember.postValue(groups);
            }
        });
    }

    public void loadEvents(final ArrayList<String> events, final MutableLiveData<List<Event>> mutableLiveData) {

        final ArrayList<Event> eventsList = new ArrayList<>();

        for (String idEvent : events) {
            final DocumentReference docRef = db.collection("events").document(idEvent);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Event event = document.toObject(Event.class);
                            eventsList.add(event);
                            if(events.size() == eventsList.size()){
                                mutableLiveData.postValue(eventsList);
                            }
                        } else {
                            mutableLiveData.postValue(eventsList);
                        }
                    } else {
                        mutableLiveData.postValue(eventsList);
                    }
                }
            });
        }
    }

    public MutableLiveData<List<User>> loadUserAll(final MutableLiveData<List<User>> mutableLiveData) {

        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                //add error
                ArrayList<User> users = new ArrayList<>();
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    User user = new Gson().fromJson(snapshot.getData().toString(), User.class);
                    users.add(user);
                }
                mutableLiveData.postValue(users);
            }
        });

        return mutableLiveData;
    }

    public void loadUsers(final ArrayList<String> userIds, final MutableLiveData<List<User>> mutableLiveData) {
        final ArrayList<User> usersList = new ArrayList<>();

        for (String idUser : userIds) {
            final DocumentReference docRef = db.collection("users").document(idUser);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            User user = document.toObject(User.class);
                            usersList.add(user);
                            if(userIds.size() == usersList.size()){
                                mutableLiveData.postValue(usersList);
                            }
                        } else {
                            mutableLiveData.postValue(usersList);
                        }
                    } else {
                        mutableLiveData.postValue(usersList);
                    }
                }
            });
        }
    }


    public void loadGroup(String idGroup, final MutableLiveData<Group> mutableLiveData) {

        final DocumentReference docRef = db.collection("groups").document(idGroup);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                try {
                    if (e != null) {
                        throw e;
                    } else {
                        Group group = new Gson().fromJson(snapshot.getData().toString(), Group.class);
                        mutableLiveData.setValue(group);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });
    }

    public MutableLiveData<Chat> loadChat(String idChat, final MutableLiveData<Chat> mutableLiveData) {

        final DocumentReference docRef = db.collection("chats").document(idChat);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                try {
                    if (e != null) {
                        throw e;
                    } else {
                        Chat chat = new Gson().fromJson(snapshot.getData().toString(), Chat.class);
                        mutableLiveData.setValue(chat);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });

        return mutableLiveData;
    }

    public MutableLiveData<List<Chat>> loadChatUsers(User user, final MutableLiveData<List<Chat>> mutableLiveData) {
        final List<Chat> chats = new ArrayList<>();

        for (String idChat : user.getChats().keySet()) {
            final DocumentReference docRef = db.collection("chats").document(idChat);
            docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot,
                                    @Nullable FirebaseFirestoreException e) {
                    try {
                        if (e != null) {
                            throw e;
                        } else {
                            Chat chat = new Gson().fromJson(snapshot.getData().toString(), Chat.class);
                            chats.add(chat);

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

            });
        }

        mutableLiveData.postValue(chats);
        return mutableLiveData;

    }

    public MutableLiveData<Message> loadMessage(String idChat, final MutableLiveData<Message> mutableLiveData) {

        final DocumentReference docRef = db.collection("messages").document(idChat);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                try {
                    if (e != null) {
                        throw e;
                    } else {
                        Message message = new Gson().fromJson(snapshot.getData().toString(), Message.class);
                        mutableLiveData.setValue(message);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });

        return mutableLiveData;
    }

    public void updateUser(User user) {
        db.collection("users").document(user.getId()).set(user, SetOptions.merge()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void updateGroup(Group group) {
        db.collection("groups").document(group.getId()).set(group, SetOptions.merge()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void updateEvent(Event event) {
        db.collection("events").document(event.getId()).set(event, SetOptions.merge()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void updateChat(Chat chat) {
        db.collection("chats").document(chat.getId()).set(chat, SetOptions.merge()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void updateMessage(Message message) {
        db.collection("messages").document(message.getId()).set(message, SetOptions.merge()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }



}


