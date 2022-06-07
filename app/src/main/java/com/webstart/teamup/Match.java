package com.webstart.teamup;

import java.util.ArrayList;
import java.util.Date;

public class Match {
    private int id, winner_score, looser_score, best_of;
    private ArrayList<Team> teams;
    private Date start_at;
    private Team winner;
    private Team looser;
}
