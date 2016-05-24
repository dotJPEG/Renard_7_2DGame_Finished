/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renard_7_2dgame;

/**
 *
 * @author dotJPEG
 */
public class EnemyBasic {
    public int enemyx;
    public int enemyy;
    public boolean isalive;
    Player player;
    Game_Insults meme;
    boolean up,left,down,right,hit = false;
    public EnemyBasic(int enemyx, int enemyy, boolean alive){
        this.enemyx = enemyx;
        this.enemyy = enemyy;
        this.isalive = alive;
    }
    
    public int getEnemyx() {
        return this.enemyx;
    }

    public void setEnemyx(int enemyx) {
        this.enemyx = enemyx;
    }

    public int getEnemyy() {
        return enemyy;
    }

    public void setEnemyy(int enemyy) {
        this.enemyy = enemyy;
    }
    public void setdirection(){
        if(isalive){
            for (int i = enemyx-1; i < enemyx+1; i++) {
                for (int j = enemyy-1; j < enemyy+1; j++) {
                    if(Main.map[i][j]=="X"){
                        wallBust(i,j);
                    }
                }
            }
            if (enemyx < Player.playerx) {
                up = false;
                right = true;
                down = false;
                left = false;
                hit = false;
            } else if (enemyx > Player.playerx) {
                up = false;
                right = false;
                down = false;
                left = true;
                hit = false;
            } else if (enemyy < Player.playery) {
                up = false;
                right = false;
                down = true;
                left = false;
                hit = false;
            } else if (enemyy > Player.playery) {
                up = true;
                right = false;
                down = false;
                left = false;
                hit = false;
            } else {
                up = false;
                right = false;
                down = false;
                left = false;
                hit = true;
            }}
    }
    public void move(){
        if(up){
         Main.map[this.enemyx][this.enemyy] = ".";
         this.enemyy--;
         Main.map[this.enemyx][this.enemyy] = "M";
        }else if(right){
         Main.map[this.enemyx][this.enemyy] = ".";
         this.enemyx++;   
         Main.map[this.enemyx][this.enemyy] = "M"; 
        }else if(down){
         Main.map[this.enemyx][this.enemyy] = ".";
         this.enemyy++;   
         Main.map[this.enemyx][this.enemyy] = "M";   
        }else if(left){
         Main.map[this.enemyx][this.enemyy] = "."; 
         this.enemyx--;   
         Main.map[this.enemyx][this.enemyy] = "M";   
        }else{
         System.out.println("You take a hit cause youre bad m8, the enemy dies tho");   
         System.out.println(meme.getInsult());   
         Player.isalive = false;   
         isalive = false;   
        }
    }
    public void wallBust(int i, int j){
        Main.map[i][j]=".";
    }
}
