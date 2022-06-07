package com.webstart.teamup;

import java.util.ArrayList;

public class User {
    private int id, nb_ratings;
    private String name, email, password, phone, description, profile_picture;
    private double average_rating;
    private ArrayList<Game> games;
    private ArrayList<Comment> comments;
    private ArrayList<Team> teams;
    private ArrayList<Conversation> conversations;
    private ArrayList<Subscription> subscriptions;
}
