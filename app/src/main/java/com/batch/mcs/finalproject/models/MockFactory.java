package com.batch.mcs.finalproject.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MockFactory {

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
        event1.setDate(getDateTime(new GregorianCalendar(2018,8,14,15,21)));
        event1.setPrice(15);
        event1.setLocation("101 Marietta St NW #3110, Atlanta, GA 30303");

        list.add(event1);


        Event event2 = new Event();
        event2.setId("31154");
        event2.setAdminId(groupStarWars.getIdAdmin());
        event2.setName("Movie Marathon");
        event2.setDescription("Join us for an epic Star Wars Movie Marathon");
        event2.setCity("Decatur");
        event2.setDate(getDateTime(new GregorianCalendar(2018,8,21,18,10)));
        event2.setPrice(21);
        event2.setLocation("10 Rimington Ln, Decatur, GA 30030");

        list.add(event2);


        Event event3 = new Event();
        event3.setId("31487");
        event3.setAdminId(groupStarTrek.getIdAdmin());
        event3.setName("Movie Marathon");
        event3.setDescription("Join us for an epic Star Trek Movie Marathon");
        event3.setCity("East Point");
        event3.setDate(getDateTime(new GregorianCalendar(2018,9,5,18,0)));
        event3.setPrice(10);
        event3.setLocation("2924 Briarwood Blvd, East Point, GA 30344");

        list.add(event3);


        Event event4 = new Event();
        event4.setId("31478");
        event4.setAdminId(groupBattlestarGalactica.getIdAdmin());
        event4.setName("Movie Marathon");
        event4.setDescription("Join us for an epic Battlestar Galactica Movie Marathon");
        event4.setCity("Marietta");
        event4.setDate(getDateTime(new GregorianCalendar(2018,9,11,17,0)));
        event4.setPrice(8);
        event4.setLocation("1775 Parkway Pl, Marietta, GA 30067");

        list.add(event4);

        return list;
    }

    public MockFactory(){
        userList = createUsers();
        groupList = createGroups();
        Map<String, Boolean> groups = new HashMap<>();

        //since all users have the same groups but different roles, so they have the same grouplist
        for(int i=0; i<groupList.size(); i++) {
            groups.put(groupList.get(i).getId(), true);
        }

        //adding the grouplist to each user object
        for(int j = 0; j< userList.size(); j++){
            userList.get(j).setMyGroups(groups);
        }

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

    private static String getDateTime(GregorianCalendar dateTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, y 'at' hh:mma 'Local Time'");
        System.out.println(dateFormat.format(dateTime.getTime()));
        return dateFormat.format(dateTime.getTime());
    }
}
