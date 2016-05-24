/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renard_7_2dgame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author dotJPEG
 */
public class Player {
    private Random randy = new Random();
    static int playerx;
    static int playery;
    static boolean isalive;
    static int health;
    static int onlevel;
    static int score;
     int level;
    public ArrayList<String> inventory = new ArrayList();
    public Player(int x, int y, boolean isAlive, int helth, int level, int scre, int playerlevel){
        playerx = x;
        playery = y;
        isalive = isAlive;
        health = helth;
        onlevel = level;
        score = scre;
        playerlevel = playerlevel;
    }

    public int getPlayerx() {
        return playerx;
    }

    public void setPlayerx(int playerx) {
        Player.playerx = playerx;
    }

    public int getPlayery() {
        return playery;
    }

    public void setPlayery(int playery) {
        Player.playery = playery;
    }

    public boolean getIsalive() {
        return isalive;
    }

    public void setIsalive(boolean isalive) {
        Player.isalive = isalive;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        Player.health = health;
    }

    public int getOnlevel() {
        return onlevel;
    }

    public void setOnlevel(int onlevel) {
        Player.onlevel = onlevel;
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScpre(int score){
        Player.score = score;
    }
    public ArrayList<String> getInvetory(){
        return inventory;
    }
    public void skill1(){
        Player.playerx = randy.nextInt(999);
        Player.playery = randy.nextInt(999);
    }
    public void skill2(){
        for (int i = playerx-1; i < playerx+1; i++) {
            for (int j = playery-1; j < playery+1; j++) {
                if (Main.map[i][j]=="M") {
                    Main.map[i][j]=".";
                    Main.enemyalive = false;
                    Player.score += 10;
                }
            }
        }
    }
    public void skill3(){
        if(Main.mapTreasure[playerx][playery].equals("T")){
            inventory.add(Game_Insults.treasureGen());
            System.out.println("You dug up a burried treasure!");
            score += 10;
        }else{
            System.out.println("You found nothing.");
        }
    }
    public void addLevel(){
        level++;
    }
    public int getLevel(){
        return level;
    }
}

