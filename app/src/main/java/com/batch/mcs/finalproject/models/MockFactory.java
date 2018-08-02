package com.batch.mcs.finalproject.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MockFactory {

    public static final String iamhungry = "I am hungry"; // 1
    public static final String hihungrymynamesjoe = "Hi Hungry, My name's Joe"; // 2
    public static final String youareamazing = "You are amazing"; // 3
    public static final String iamnotamzingiamcarlos = "I am not amazing, I am Carlos"; // 4
    public static final String iwillworkontheprojecttomorrow = "I will work on the project tomorrow"; // 5
    public static final String youdoyougirl = "You do you girl"; // 6
    public static final String itisverycommonyouknow = "It is very common, you know"; // 7
    public static final String noidontknow = "No, I don't know"; // 8

    private User userCarlos;
    private User userMohammad;
    private User userJoe;
    private User userAlvaro;

    private Group groupStarWars;
    private Group groupStarTrek;
    private Group groupBattlestarGalactica;

    private ArrayList<User> userList;
    private ArrayList<Group> groupList;
    private ArrayList<Event> eventList;
    private ArrayList<Chat> chatList;
    private ArrayList<Message> messageList;

    private ArrayList<User> createUsers(){
        ArrayList<User> list = new ArrayList<>();

        //user 1
        userCarlos = new User();
        userCarlos.setName("Carlos");
        userCarlos.setLastName("Juarez");
        userCarlos.setCity("Mexico City");
        userCarlos.setEmail("carlosjuarez@gmail.com");
        userCarlos.setId("10001");
        list.add(userCarlos);

        //user 2
        userMohammad = new User();
        userMohammad.setName("Mohammad");
        userMohammad.setLastName("Nafis");
        userMohammad.setCity("East Brunswick");
        userMohammad.setEmail("mohammadnafis@yahoo.com");
        userMohammad.setId("10025");
        list.add(userMohammad);

        //user 3
        userJoe = new User();
        userJoe.setName("Joe");
        userJoe.setLastName("Silva");
        userJoe.setCity("Kansas City");
        userJoe.setEmail("joesilva@hotmail.com");
        userJoe.setId("12142");
        list.add(userJoe);

        //user 4
        userAlvaro = new User();
        userAlvaro.setName("Alvaro");
        userAlvaro.setLastName("Lossada");
        userAlvaro.setCity("Miami");
        userAlvaro.setEmail("alvarolossada@outlook.com");
        userAlvaro.setId("13214");
        list.add(userAlvaro);

        return list;
    }

    private ArrayList<Group> createGroups(){
        ArrayList<Group> list = new ArrayList<>();


        //group 1
        groupStarWars = new Group();
        groupStarWars.setId("21101");
        groupStarWars.setIdAdmin(userAlvaro.getId());
        groupStarWars.setName("Team Star Wars");
        groupStarWars.setDescription("May the force be with you!");

        Map<String, Boolean> starwarsModerators = new HashMap<>();
        starwarsModerators.put(userMohammad.getId(), true);
        groupStarWars.setIdModerators(starwarsModerators);

        Map<String, Boolean> starwarsMembers = new HashMap<>();
        starwarsMembers.put(userJoe.getId(), true);
        starwarsMembers.put(userCarlos.getId(), true);
        starwarsMembers.put(userMohammad.getId(), true);
        groupStarWars.setIdMembers(starwarsMembers);

        list.add(groupStarWars);

        //group 2
        groupStarTrek = new Group();
        groupStarTrek.setId("21102");
        groupStarTrek.setIdAdmin(userCarlos.getId());
        groupStarTrek.setName("Team Star Trek");
        groupStarTrek.setDescription("Live Long and Prosper");

        Map<String, Boolean> startrekModerators = new HashMap<>();
        startrekModerators.put(userJoe.getId(), true);
        groupStarWars.setIdModerators(startrekModerators);

        Map<String, Boolean> startrekMembers = new HashMap<>();
        startrekMembers.put(userAlvaro.getId(), true);
        startrekMembers.put(userMohammad.getId(), true);
        startrekMembers.put(userJoe.getId(), true);
        groupStarTrek.setIdMembers(startrekMembers);

        list.add(groupStarTrek);

        //group 3
        groupBattlestarGalactica = new Group();
        groupBattlestarGalactica.setId("21103");
        groupBattlestarGalactica.setIdAdmin(userJoe.getId());
        groupBattlestarGalactica.setName("Team Battlestar Galactica");
        groupBattlestarGalactica.setDescription("Fleeing from the Cylon tyranny, the last battlestar, Galactica, leads a ragtag fugitive fleet on a lonely quest: a shining planet known as Earth");

        Map<String, Boolean> battlestarGalacticaModerators = new HashMap<>();
        battlestarGalacticaModerators.put(userAlvaro.getId(), true);
        groupBattlestarGalactica.setIdModerators(battlestarGalacticaModerators);

        Map<String, Boolean> battlestarGalacticaMembers = new HashMap<>();
        battlestarGalacticaMembers.put(userCarlos.getId(), true);
        battlestarGalacticaMembers.put(userMohammad.getId(), true);
        battlestarGalacticaMembers.put(userAlvaro.getId(), true);
        groupBattlestarGalactica.setIdMembers(battlestarGalacticaMembers);

        list.add(groupBattlestarGalactica);

        return list;
    }

    private ArrayList<Event> createEventList(){
        ArrayList<Event> list = new ArrayList<>();

        Event event1 = new Event();
        event1.setId("31125");
        event1.setAdminId(groupStarWars.getIdAdmin());
        event1.setName("Lightsaber Battles");
        event1.setDescription("Join us for an epic lightsaber battle");
        event1.setCity("Atlanta");
        event1.setDate("August 21, 2018");
        event1.setPrice(15);
        event1.setLocation("101 Marietta St NW #3110, Atlanta, GA 30303");

        list.add(event1);


        Event event2 = new Event();
        event2.setId("31154");
        event2.setAdminId(groupStarWars.getIdAdmin());
        event2.setName("Movie Marathon");
        event2.setDescription("Join us for an epic Star Wars Movie Marathon");
        event2.setCity("Decatur");
        event2.setDate("September 15, 2018");
        event2.setPrice(21);
        event2.setLocation("10 Rimington Ln, Decatur, GA 30030");

        list.add(event2);


        Event event3 = new Event();
        event3.setId("31487");
        event3.setAdminId(groupStarTrek.getIdAdmin());
        event3.setName("Movie Marathon");
        event3.setDescription("Join us for an epic Star Trek Movie Marathon");
        event3.setCity("East Point");
        event3.setDate("August 28, 2018");
        event3.setPrice(10);
        event3.setLocation("2924 Briarwood Blvd, East Point, GA 30344");

        list.add(event3);


        Event event4 = new Event();
        event4.setId("31478");
        event4.setAdminId(groupBattlestarGalactica.getIdAdmin());
        event4.setName("Movie Marathon");
        event4.setDescription("Join us for an epic Battlestar Galactica Movie Marathon");
        event4.setCity("Marietta");
        event4.setDate("October 20, 2018");
        event4.setPrice(8);
        event4.setLocation("1775 Parkway Pl, Marietta, GA 30067");

        list.add(event4);

        return list;
    }

    public ArrayList<Chat> createChat(){
        ArrayList<Chat> chats = new ArrayList<>();
        ArrayList<Message> messages = new ArrayList<>();

        Chat chat1 = new Chat();
        chat1.newMessage = false;
        chat1.setAdmin(userAlvaro.getId());
        chat1.setAdminName(userAlvaro.getName());
        chat1.setMember(userCarlos.getId());
        chat1.setMemberName(userCarlos.getName());
        chat1.setId("40001");

        Map<String, Boolean> chatMap1 = new HashMap<>();
        Message msg1 = new Message();
        msg1.setId("50001");
        msg1.setCreator(userAlvaro.getId());
        msg1.setChatId(chat1.getId());
        msg1.setContent(youareamazing);
        chatMap1.put(msg1.getId(), true);
        messages.add(msg1);

        Message msg2 = new Message();
        msg2.setId("50002");
        msg2.setCreator(userCarlos.getId());
        msg2.setChatId(chat1.getId());
        msg2.setContent(iamnotamzingiamcarlos);
        chatMap1.put(msg2.getId(), true);
        messages.add(msg2);

        chat1.setMessages(chatMap1);

        chats.add(chat1);

        /////////////////////////////

        Chat chat2 = new Chat();
        chat2.newMessage = false;
        chat2.setAdmin(userMohammad.getId());
        chat2.setAdminName(userMohammad.getName());
        chat2.setMember(userJoe.getId());
        chat2.setMemberName(userJoe.getName());
        chat2.setId("40002");

        Map<String, Boolean> chatMap2 = new HashMap<>();
        Message msg3 = new Message();
        msg3.setId("50003");
        msg3.setCreator(userMohammad.getId());
        msg3.setChatId(chat2.getId());
        msg3.setContent(iamhungry);
        chatMap2.put(msg3.getId(), true);
        messages.add(msg3);

        Message msg4 = new Message();
        msg4.setId("50004");
        msg4.setCreator(userJoe.getId());
        msg4.setChatId(chat2.getId());
        msg4.setContent(hihungrymynamesjoe);
        chatMap2.put(msg4.getId(), true);
        messages.add(msg4);

        chat2.setMessages(chatMap2);

        chats.add(chat2);

        //////////////////////////////

        Chat chat3 = new Chat();
        chat3.newMessage = false;
        chat3.setAdmin(userJoe.getId());
        chat3.setAdminName(userJoe.getName());
        chat3.setMember(userAlvaro.getId());
        chat3.setMemberName(userAlvaro.getName());
        chat3.setId("40003");

        Map<String, Boolean> chatMap3 = new HashMap<>();
        Message msg5 = new Message();
        msg5.setId("50005");
        msg5.setCreator(userJoe.getId());
        msg5.setChatId(chat3.getId());
        msg5.setContent(iwillworkontheprojecttomorrow);
        chatMap3.put(msg5.getId(), true);
        messages.add(msg5);

        Message msg6 = new Message();
        msg6.setId("50006");
        msg6.setCreator(userAlvaro.getId());
        msg6.setChatId(chat3.getId());
        msg6.setContent(youdoyougirl);
        chatMap3.put(msg6.getId(), true);
        messages.add(msg6);

        chat3.setMessages(chatMap3);

        chats.add(chat3);

        //////////////////////////////

        Chat chat4 = new Chat();
        chat4.newMessage = false;
        chat4.setAdmin(userMohammad.getId());
        chat4.setAdminName(userMohammad.getName());
        chat4.setMember(userCarlos.getId());
        chat4.setMemberName(userCarlos.getName());
        chat4.setId("40004");

        Map<String, Boolean> chatMap4 = new HashMap<>();
        Message msg7 = new Message();
        msg7.setId("50007");
        msg7.setCreator(userMohammad.getId());
        msg7.setChatId(chat4.getId());
        msg7.setContent(itisverycommonyouknow);
        chatMap4.put(msg7.getId(), true);
        messages.add(msg7);

        Message msg8 = new Message();
        msg8.setId("50008");
        msg8.setCreator(userCarlos.getId());
        msg8.setChatId(chat4.getId());
        msg8.setContent(noidontknow);
        chatMap4.put(msg8.getId(), true);
        messages.add(msg8);

        chat4.setMessages(chatMap4);

        chats.add(chat4);

        messageList = messages;

        return chats;
    }

    public MockFactory(){
        userList = createUsers();
        groupList = createGroups();

//        Map<String, Boolean> groups = new HashMap<>();
//
//        //since all users have the same groups but different roles, so they have the same grouplist
//        for(int i=0; i<groupList.size(); i++) {
//            groups.put(groupList.get(i).getId(), true);
//        }
//
//        //adding the grouplist to each user object
//        for(int j = 0; j< userList.size(); j++){
//            userList.get(j).setMyGroups(groups);
//        }

        Map<String, Boolean> mohammadAdminGroups = new HashMap<>();
        Map<String, Boolean> mohammadMemberGroups = new HashMap<>();
        mohammadMemberGroups.put(groupList.get(0).getId(), true);
        mohammadMemberGroups.put(groupList.get(1).getId(), true);
        mohammadMemberGroups.put(groupList.get(2).getId(), true);
        userMohammad.setMyGroups(mohammadAdminGroups);
        userMohammad.setGroups(mohammadMemberGroups);

        Map<String, Boolean> alvaroAdminGroups = new HashMap<>();
        Map<String, Boolean> alvaroMemberGroups = new HashMap<>();
        alvaroAdminGroups.put(groupList.get(0).getId(), true);
        alvaroMemberGroups.put(groupList.get(1).getId(), true);
        alvaroMemberGroups.put(groupList.get(2).getId(), true);
        userAlvaro.setMyGroups(alvaroAdminGroups);
        userAlvaro.setGroups(alvaroMemberGroups);

        Map<String, Boolean> carlosAdminGroups = new HashMap<>();
        Map<String, Boolean> carlosMemberGroups = new HashMap<>();
        carlosMemberGroups.put(groupList.get(0).getId(), true);
        carlosAdminGroups.put(groupList.get(1).getId(), true);
        carlosMemberGroups.put(groupList.get(2).getId(), true);
        userCarlos.setMyGroups(carlosAdminGroups);
        userCarlos.setGroups(carlosMemberGroups);

        Map<String, Boolean> joeAdminGroups = new HashMap<>();
        Map<String, Boolean> joeMemberGroups = new HashMap<>();
        joeMemberGroups.put(groupList.get(0).getId(), true);
        joeMemberGroups.put(groupList.get(1).getId(), true);
        joeAdminGroups.put(groupList.get(2).getId(), true);
        userJoe.setMyGroups(joeAdminGroups);
        userJoe.setGroups(joeMemberGroups);

        eventList = createEventList();

        //group events
        Map<String, Boolean> starwarsEventList = new HashMap<>();
        starwarsEventList.put(eventList.get(0).getId(), true);
        starwarsEventList.put(eventList.get(1).getId(), true);
        groupStarWars.setIdEvents(starwarsEventList);

        Map<String, Boolean> startrekEventList = new HashMap<>();
        startrekEventList.put(eventList.get(2).getId(), true);
        groupStarTrek.setIdEvents(startrekEventList);

        Map<String, Boolean> battlestarGalacticaEventList = new HashMap<>();
        battlestarGalacticaEventList.put(eventList.get(3).getId(), true);
        groupBattlestarGalactica.setIdEvents(battlestarGalacticaEventList);

        chatList = createChat();

    }

    public ArrayList<User> getUsersArrayList(){
        return userList;
    }

    public ArrayList<Group> getGroupsArrayList(){
        return groupList;
    }

    public ArrayList<Event> getEventsArrayList() {
        return eventList;
    }

    public ArrayList<Chat> getChatArrayList() { return chatList;  }

    public ArrayList<Message> getMessageArrayList() { return messageList; }
}
