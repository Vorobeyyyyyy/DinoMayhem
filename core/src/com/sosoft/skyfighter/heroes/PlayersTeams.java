package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.utils.Array;

public class PlayersTeams {
    private Array<String> namesOfTeams;
    public Array<Hero> players;
    public String nameTeam;
    public int teamNumber;

    PlayersTeams(int teamNumber) {
        //namesOfTeams.add("Ello");
        players = new Array<Hero>();
        this.teamNumber = teamNumber;
    }

    public void AddPlayer(Hero hero) {
        players.add(hero);
    }

    public int GetCountPlayers() {
        return players.size;
    }
}
