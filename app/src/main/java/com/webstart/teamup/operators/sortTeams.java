package com.webstart.teamup.operators;

import com.webstart.teamup.models.Team;

import java.util.Comparator;

public class sortTeams implements Comparator<Team> {
    @Override
    public int compare(Team t1, Team t2) {
        return t2.getRank().compareTo(t1.getRank());
    }
}
