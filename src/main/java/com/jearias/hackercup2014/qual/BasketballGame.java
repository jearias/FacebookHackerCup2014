package com.jearias.hackercup2014.qual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BasketballGame {
    private static final String EVEN_TEAM = "evenTeam";
    private static final String ODD_TEAM = "oddTeam";

    static class Player implements Comparable<Player> {
        String name;
        int shotPercentage;
        int height;
        int draftNumber;
        int minutesPlayed;

        public int compareTo(Player p) {
            if (shotPercentage > p.shotPercentage) {
                return -1;
            }
            if (shotPercentage < p.shotPercentage) {
                return 1;
            } else {
                return height > p.height ? -1 : 1;
            }
        }

    }

    public static void main(String[] args) {
        Scanner in = null;
        in = new Scanner(System.in);
        int t = in.nextInt();
        for (int indexT = 1; indexT <= t; indexT++) {
            Map<String, List<Player>> court = new HashMap<String, List<Player>>();
            court.put(ODD_TEAM, new ArrayList<Player>());
            court.put(EVEN_TEAM, new ArrayList<Player>());
            List<Player> players = new ArrayList<Player>();
            List<Player> evenTeam = new ArrayList<Player>();
            List<Player> oddTeam = new ArrayList<Player>();
            int n = in.nextInt();
            int m = in.nextInt();
            int p = in.nextInt();
            // create players
            for (int indexN = 0; indexN < n; indexN++) {
                Player player = new Player();
                player.name = in.next();
                player.shotPercentage = in.nextInt();
                player.height = in.nextInt();
                players.add(player);
            }
            // sort player list
            Collections.sort(players);
            // assign players to teams
            for (int i = 1; i <= players.size(); i++) {
                Player player = players.get(i - 1);
                player.draftNumber = i;
                if (i % 2 == 0) {
                    evenTeam.add(player);
                } else {
                    oddTeam.add(player);
                }
            }
            // start the match
            for (int i = 0; i < p; i++) {
                court.get(ODD_TEAM).add(oddTeam.get(0));
                oddTeam.remove(0);
                court.get(EVEN_TEAM).add(evenTeam.get(0));
                evenTeam.remove(0);
            }
            // play the match
            for (int i = 1; i <= m; i++) {
                increaseTimePlayed(court.get(ODD_TEAM));
                increaseTimePlayed(court.get(EVEN_TEAM));
                if (oddTeam.size() != 0) {
                    Player playerToChange = choosePlayerToChange(court.get(ODD_TEAM));
                    court.get(ODD_TEAM).remove(playerToChange);
                    Player substituteToChange = chooseSubstituteToChange(oddTeam);
                    court.get(ODD_TEAM).add(substituteToChange);
                    oddTeam.remove(substituteToChange);
                    oddTeam.add(playerToChange);
                }
                if (evenTeam.size() != 0) {
                    Player playerToChange = choosePlayerToChange(court.get(EVEN_TEAM));
                    court.get(EVEN_TEAM).remove(playerToChange);
                    Player substituteToChange = chooseSubstituteToChange(evenTeam);
                    court.get(EVEN_TEAM).add(substituteToChange);
                    evenTeam.remove(substituteToChange);
                    evenTeam.add(playerToChange);
                }
            }
            System.out.format("Case #%d: %s\n", indexT, printFinalPlayers(court));
        }
    }

    private static String printFinalPlayers(Map<String, List<Player>> court) {
        List<Player> playersList = court.get(ODD_TEAM);
        playersList.addAll(court.get(EVEN_TEAM));
        List<String> names = new ArrayList<String>();
        for (Player player : playersList) {
            names.add(player.name);
        }
        Collections.sort(names);
        StringBuilder finalNames = new StringBuilder();
        for (String string : names) {
            finalNames.append(string + " ");
        }
        return finalNames.toString();
    }

    private static void increaseTimePlayed(List<Player> list) {
        for (Player player : list) {
            player.minutesPlayed++;
        }
    }

    private static Player choosePlayerToChange(List<Player> list) {
        Player playerSelected = list.get(0);
        if (list.size() == 1) {
            return playerSelected;
        }
        for (Player player : list) {
            if (player.minutesPlayed > playerSelected.minutesPlayed) {
                playerSelected = player;
            } else if (player.minutesPlayed == playerSelected.minutesPlayed) {
                playerSelected = player.draftNumber > playerSelected.draftNumber ? player : playerSelected;
            }
        }
        return playerSelected;
    }

    private static Player chooseSubstituteToChange(List<Player> list) {
        Player playerSelected = list.get(0);
        if (list.size() == 1) {
            return playerSelected;
        }
        for (Player player : list) {
            if (player.minutesPlayed < playerSelected.minutesPlayed) {
                playerSelected = player;
            } else if (player.minutesPlayed == playerSelected.minutesPlayed) {
                playerSelected = player.draftNumber < playerSelected.draftNumber ? player : playerSelected;
            }
        }
        return playerSelected;
    }

}
