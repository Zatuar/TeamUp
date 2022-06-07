package com.webstart.teamup;

import java.util.ArrayList;
import java.util.Date;

public class Tournament {
    private int id, max_teams;
    private String name, organizer, logo;
    private double cash_prize;
    private Date start_at, end_at;
    private ArrayList<Match> matches;
    private ArrayList<Team> teams;
}
