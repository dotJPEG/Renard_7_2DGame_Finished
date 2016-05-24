/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package renard_7_2dgame;

import java.util.*;

/**
 *
 * @author bob
 */
public class Main {
    static Random randy = new Random();
    static Scanner scan = new Scanner(System.in);
    static boolean left, right, down, up;
    static String[][] map = new String[1000][1000];
    static String[][] mapTreasure = new String[1000][1000];
    static String[][] canvas = new String[50][50];
    static Game_Insults meme;
    static Player player;
    static boolean enemyalive;
    static PaintBrush brush = new PaintBrush(1,1);
    static int timer=0;
    
    public static void main(String[] args) {
    Player player = new Player(randy.nextInt(999), randy.nextInt(999), true, 100, 1, 0,0);
    
    ArrayList<EnemyBasic> enemybasic = new ArrayList();
        System.out.println("you wake up in a spooky dungeon full of dots and X's, with the occasional T and E thrown in");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                         + "@@::::::::::@@@::::@@@@@@@@@:::::::::::::::@@@:::::::::::::::@@\n"
                         + "@@::@@@@@@::@@@::::@@@@@@@@@::::@@@@@@@::::@@@@@@@@@:::@@@@@@@@\n"
                         + "@@::::::::::@@@::::@@@@@@@@@::::@@@@@@@::::@@@@@@@@@:::@@@@@@@@\n"
                         + "@@::@@@@@@@@@@@::::::::::@@@:::::::::::::::@@@@@@@@@:::@@@@@@@@\n"
                         + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Welcome to Bob's game of concepts, where there is no real theme or story, just a few cool concepts I wanted to play with");
        System.out.println("Get 100 points to win, picking up treasure is worth 10 points");
        System.out.println("to play use the numberpad as a directional pad (i.e. 6 is left, 7 is up and left)");
        System.out.println("use '-' to end the game");
        System.out.println("would you like to play the dungeon crawler, or would you like to paint?");
        String answer = scan.next();
        if (answer.equalsIgnoreCase("d")) {
            player.setOnlevel(0);
        }else{
            player.setOnlevel(1);
        }
        
        if(player.getOnlevel()==0){
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                map[i][j] = ".";
                mapTreasure[i][j] = ".";
            }
        }
        for (int traps = 0; traps < 1500; traps++) {
            map[randy.nextInt(999)][randy.nextInt(999)] = "*";
        }
        for (int walls = 0; walls < 2000; walls++) {
            map[randy.nextInt(999)][randy.nextInt(999)] = "X";
        }
        for (int treasure = 0; treasure < 1500; treasure++) {
            map[randy.nextInt(999)][randy.nextInt(999)] = "T";
            mapTreasure[randy.nextInt(999)][randy.nextInt(999)] = "T";
        }
        
        map[player.getPlayerx()][player.getPlayery()] = "@";

        if (player.getPlayerx() - 25 < 0) {
            printmap(0, player.getPlayerx() + 25, player.getPlayery() - 25, player.getPlayery() + 25);
        } else if (player.getPlayerx() + 25 > 999) {
            printmap(player.getPlayerx() - 25, 0, player.getPlayery() - 25, player.getPlayery() + 25);
        } else if (player.getPlayery() - 25 < 0) {
            printmap(player.getPlayerx() - 25, player.getPlayerx() + 25, 0, player.getPlayery() + 25);
        } else if (player.getPlayery() + 25 > 999) {
            printmap(player.getPlayerx() - 25, player.getPlayerx() + 25, player.getPlayery() - 25, 0);
        } else {
            for (int i = player.getPlayerx() - 25; i < player.getPlayerx() + 24; i++) {
                System.out.println("");
                for (int j = player.getPlayery() - 25; j < player.getPlayery() + 24; j++) {
                    System.out.print(map[i][j] + " ");
                }
            }
        }
        for (int enemies = 0; enemies < 250; enemies++) {
            EnemyBasic e = new EnemyBasic(randy.nextInt(999),randy.nextInt(999),true);
                enemybasic.add(e);
        }
        }
        while (player.getOnlevel()==0) {
            if(timer<10){
                for (int enemyadd = 0; enemyadd < 10; enemyadd++) {
                    EnemyBasic e = new EnemyBasic(randy.nextInt(999),randy.nextInt(999),true);
                enemybasic.add(e);
                }
                System.out.println("10 more enemies enter the fray");
            }
            System.out.println("press / to teleport, - to dig for treasure, * to kill all enemies around you, and 5 to wait");
            System.out.println("You wont be able to teleport until you gain strength though. When you pick up treasure you will");
            System.out.println("Gain a level, you start at level 0, and when you reach level 2 you will unlock a new skill.");
            String action = scan.next();
            if (action.equalsIgnoreCase("7") && map[player.getPlayerx() - 1][player.getPlayery() + 1] != "X") {
                map[player.getPlayerx()][player.getPlayery()] = ".";
                player.setPlayerx(player.getPlayerx() - 1);
                player.setPlayery(player.getPlayery() - 1);
            } else if (action.equalsIgnoreCase("8") && map[player.getPlayerx() - 1][player.getPlayery()] != "X") {
                map[player.getPlayerx()][player.getPlayery()] = ".";
                player.setPlayerx(player.getPlayerx() - 1);
            } else if (action.equalsIgnoreCase("9") && map[player.getPlayerx() + 1][player.getPlayery() + 1] != "X") {
                map[player.getPlayerx()][player.getPlayery()] = ".";
                player.setPlayerx(player.getPlayerx() - 1);
                player.setPlayery(player.getPlayery() + 1);
            } else if (action.equalsIgnoreCase("4") && map[player.getPlayerx()][player.getPlayery() - 1] != "X") {
                map[player.getPlayerx()][player.getPlayery()] = ".";
                player.setPlayery(player.getPlayery() - 1);
            } else if (action.equalsIgnoreCase("5")) {
                System.out.println("You sit still for a turn");
            } else if (action.equalsIgnoreCase("6") && map[player.getPlayerx()][player.getPlayery() + 1] != "X") {
                map[player.getPlayerx()][player.getPlayery()] = ".";
                player.setPlayery(player.getPlayery() + 1);
            } else if (action.equalsIgnoreCase("1") && map[player.getPlayerx() - 1][player.getPlayery() - 1] != "X") {
                map[player.getPlayerx()][player.getPlayery()] = ".";
                player.setPlayerx(player.getPlayerx() + 1);
                player.setPlayery(player.getPlayery() - 1);
            } else if (action.equalsIgnoreCase("2") && map[player.getPlayerx() + 1][player.getPlayery()] != "X") {
                map[player.getPlayerx()][player.getPlayery()] = ".";
                player.setPlayerx(player.getPlayerx() + 1);
            } else if (action.equalsIgnoreCase("3") && map[player.getPlayerx() + 1][player.getPlayery() - 1] != "X") {
                map[player.getPlayerx()][player.getPlayery()] = ".";
                player.setPlayerx(player.getPlayerx() + 1);
                player.setPlayery(player.getPlayery() + 1);
            } else if (action.equalsIgnoreCase("/")) {
                if (player.getLevel()<2) {
                    System.out.println("nope youre too weak to teleport, you first much reach player level two");
                }else{
                player.skill1();
                System.out.println("You teleport to a random location");
                }
            } else if(action.equalsIgnoreCase("*")) {
                player.skill2();
                System.out.println("You smash the ground, killing all enemies around you");
            } else if(action.equalsIgnoreCase("-")) {
                player.skill3();
                System.out.println("You dig a whole in the ground and move up one tile to avoid the whole.");
                map[player.getPlayerx()][player.getPlayery()] = "#";
                player.setPlayerx(player.getPlayerx()-1);
            }else{
                System.out.println("nope cant do that boss");
            }
            
            for (EnemyBasic e : enemybasic){e.setdirection();e.move();}   
            
            if (map[player.getPlayerx()][player.getPlayery()] == "*") {
                System.out.println("your die because " + meme.getInsult());
                player.setIsalive(false);
            } else if(map[player.getPlayerx()][player.getPlayery()] == "#") {
                System.out.println("You cover up the whole in the ground");
                map[player.getPlayerx()][player.getPlayery()] = "@";
            }else if(map[player.getPlayerx()][player.getPlayery()] == "T") {
                player.setScpre(player.getScore()+10);
                player.addLevel();
                if(player.getLevel()<5){
                    map[player.getPlayerx()][player.getPlayery()] = "@";
                }else if(player.getLevel()>=5){
                    map[player.getPlayerx()][player.getPlayery()] = "P";
                }
            } else {
                if(player.getLevel()<5){
                    map[player.getPlayerx()][player.getPlayery()] = "@";
                }else if(player.getLevel()>=5){
                    map[player.getPlayerx()][player.getPlayery()] = "P";
            }   }
            System.out.println("your score is " + player.getScore());
            System.out.println("Your inventory consists of " + player.getInvetory());
            if (player.getPlayerx() - 25 < 0) {
                printmap(0, player.getPlayerx() + 25, player.getPlayery() - 25, player.getPlayery() + 25);
            } else if (player.getPlayerx() + 25 > 999) {
                printmap(player.getPlayerx() - 25, 0, player.getPlayery() - 25, player.getPlayery() + 25);
            } else if (player.getPlayery() - 25 < 0) {
                printmap(player.getPlayerx() - 25, player.getPlayerx() + 25, 0, player.getPlayery() + 25);
            } else if (player.getPlayery() + 25 > 999) {
                printmap(player.getPlayerx() - 25, player.getPlayerx() + 25, player.getPlayery() - 25, 0);
            } else {
                for (int i = player.getPlayerx() - 25; i < player.getPlayerx() + 24; i++) {
                    System.out.println("");
                    for (int j = player.getPlayery() - 25; j < player.getPlayery() + 24; j++) {
                        System.out.print(map[i][j] + " ");
                    }
                }
            }
            boolean play4ever = false;
            if (player.getScore() >= 100 && !play4ever) {
                System.out.println("you got enough points to win, continue?");
                action = scan.nextLine();
                if (action.contains("y") || action.contains("Y")) {
                    System.out.println("Be free my sheeple");
                    play4ever = true;
                } else {
                    System.out.println("To the next level");
                    player.setOnlevel(2);
                }
            } else if (player.getScore() < 0) {
                System.out.println("you die because " + meme.getInsult());
                player.setIsalive(false);
                lose();
            }
            timer++;
        }
        
        for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    canvas[i][j] = " ";
                }
        }
        while (player.getOnlevel()==1) {
            System.out.println("To play the game use the 8 to move up, 6 to move left, 2 to move down,");
            System.out.println("4 to move left, 7 to paint, and 9 to erase. If you wish to quit use -");
            printcanvas();
            String action = scan.next();
            if (action.equalsIgnoreCase("8")) {
                brush.setBrushy(brush.getBrushy()-1);
            }else if(action.equalsIgnoreCase("6")){
                brush.setBrushx(brush.getBrushx()+1);
            }else if(action.equalsIgnoreCase("2")){
                brush.setBrushy(brush.getBrushy()+1);
            }else if(action.equalsIgnoreCase("4")){
                brush.setBrushx(brush.getBrushx()-1);
            }else if(action.equalsIgnoreCase("7")){
                brush.paint();
            }else if(action.equalsIgnoreCase("9")){
                brush.erase();
            }else if(action.equalsIgnoreCase("-")){
                player.setOnlevel(2);
            }else{
                System.out.println("nope, cant do that boss");
            }
            
        }

    }

    public static void printmap(int bound1x, int bound2x, int bound1y, int bound2y) {
        for (int i = bound1x; i < bound2x; i++) {
            System.out.println("");
            for (int j = bound1y; j < bound2y; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
    }
    
    public static void printcanvas(){
        for (int i = 0; i < 50; i++) {
            if(i == brush.getBrushy()){
                System.out.print("X");
            }else{
                System.out.print("|");
            }
            for (int j = 0; j < 50; j++) {
                if(i == 0 && j == brush.getBrushx()){
                    System.out.print("X");
                }else if(i==0){
                    System.out.print("_");
                }else{
                    System.out.print(canvas[j][i]);
                }
            }
            System.out.println("");
        }
    }

    public static void win() {
        System.out.println("\n"
                + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "@@:;:::@@@@+::::;@@@@@::::::::::::#@@@@'::::;@@@@@@+::::;@@@@@@@@@@@'::::;@@@@@@+::::;@@@@@::::::::::::::;@@'::::;@@@@@@+::::;@\n"
                + "@#:;:::@@@@'::::;@@@@#::::::::::::#@@@@;:::::@@@@@@'::::;@@@@@@@@@@@;:::::@@@@@@'::::;@@@@@::::::::::::::;@@;:::::@@@@@@'::::;@\n"
                + "@@:;:::@@@@+::::;@@@@@::::::::::::#@@@@'::::;@@@@@@+::::;@@@@@@@@@@@'::::;@@@@@@+::::;@@@@@::::::::::::::;@@'::::;@@@@@@+::::;@\n"
                + "@#:;:::@@@@'::::;@@;:::::@@@@@@'::::;@@;:::::@@@@@@'::::;@@@@@@@@@@@;:::::@@@@@@'::::;@@@@@@@@@#:::::@@@@@@@;:::::::@@@@'::::;@\n"
                + "@#:;:::@@@@'::::;@@;:::::@@@@@@'::::;@@;:::::@@@@@@'::::;@@@@@@@@@@@;:::::@@@@@@'::::;@@@@@@@@@#:::::@@@@@@@;:::::::@@@@'::::;@\n"
                + "@@:;:::@@@@#::::;@@'::::;@@@@@@#::::;@@'::::;@@@@@@#::::;@@@@@@@@@@@'::::;@@@@@@#::::;@@@@@@@@@#:::::@@@@@@@':::::::::#@#::::;@\n"
                + "@#:;:::@@@@'::::;@@;:::::@@@@@@'::::;@@;:::::@@@@@@'::::;@@@@@@@@@@@;:::::@@@@@@'::::;@@@@@@@@@#:::::@@@@@@@;:::::::::;@'::::;@\n"
                + "@@:;:::@@@@+::::;@@'::::;@@@@@@+::::;@@'::::;@@@@@@+::::;@@@@@@@@@@@'::::;@@@@@@+::::;@@@@@@@@@#:::::@@@@@@@':::::::::'@+::::;@\n"
                + "@@@@;:::;:::::#@@@@;:::::@@@@@@'::::;@@;:::::@@@@@@'::::;@@@@@@@@@@@;:::::@#::'@'::::;@@@@@@@@@#:::::@@@@@@@;::::::::::::::::;@\n"
                + "@@@@;:::;:::::#@@@@;:::::@@@@@@'::::;@@;:::::@@@@@@'::::;@@@@@@@@@@@;:::::@#::;@'::::;@@@@@@@@@#:::::@@@@@@@;::::::::::::::::;@\n"
                + "@@@@@@@:::::@@@@@@@;:::::@@@@@@'::::;@@;:::::@@@@@@'::::;@@@@@@@@@@@;::::::::::::::::;@@@@@@@@@@:::::@@@@@@@;::::::::::::::::;@\n"
                + "@@@@@@#:;:::@@@@@@@;:::::@@@@@@'::::;@@;:::::@@@@@@'::::;@@@@@@@@@@@;::::::::::::::::;@@@@@@@@@#:::::@@@@@@@;:::::@#:::::::::;@\n"
                + "@@@@@@#:;:::@@@@@@@':::::@@@@@@'::::;@@':::::@@@@@@'::::;@@@@@@@@@@@'::::::::::::::::;@@@@@@@@@#:::::@@@@@@@':::::@#:::::::::;@\n"
                + "@@@@@@#:;:::@@@@@@@':::::@@@@@@'::::;@@':::::@@@@@@'::::;@@@@@@@@@@@':::::::@@:::::::;@@@@@@@@@#:::::@@@@@@@':::::@@@@:::::::;@\n"
                + "@@@@@@#:;:::@@@@@@@;:::::@@@@@@'::::;@@;:::::@@@@@@'::::;@@@@@@@@@@@;:::::::@@:::::::;@@@@@@@@@#:::::@@@@@@@;:::::@@@@:::::::;@\n"
                + "@@@@@@@:;:::@@@@@@@@@;:::::@@@;::::;@@@@@;::::::::::::;@@@@@@@@@@@@@;::::::@@@@::::::;@@@@@:::::::::::::::@@;:::::@@@@@::::::;@\n"
                + "@@@@@@#:;:::@@@@@@@@@#::::::::::::#@@@@@@#::::::::::::#@@@@@@@@@@@@@;:::::@@@@@@'::::;@@@@@::::::::::::::;@@;:::::@@@@@@'::::;@\n"
                + "@@@@@@#:;:::@@@@@@@@@@::::::::::::#@@@@@@@::::::::::::#@@@@@@@@@@@@@;:::::@@@@@@'::::;@@@@@::::::::::::::;@@;:::::@@@@@@'::::;@\n"
                + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    public static void lose() {
        System.out.println(""
                + "+@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@+`@@@@`@@@@@@@@@@@@@@@@@@@@@@@ ;@@@@@@@@@@@@@@@@@@@@`.@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@`:@@` @@@@@@@@@@@@@@@@@@@@@@@ ;@@@@@@@@@@@@@@@@@@@@`,@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@+`@@`@@@@`.,`@@@':@@@`@@@@@@@ ;@@@@@'`:` @@@` :.`@;`` @@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@`.` @@@`.@@@`@@':@@@`@@@@@@@ ;@@@@@`@@@ :@@`@@@@@@`,@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@'``@@@@`@@@@`@@':@@@`@@@@@@@`;@@@@@`@@@' @@ `+@@@@`:@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@` @@@@`@@@@`@@'.@@@`@@@@@@@`;@@@@@`@@@; @@@@:``@@`;@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@` @@@@` @@.`@@+`@#``@@@@@@@`;@@@@@`,@@`.@@@@@@`@@ .@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@` @@@@@````,@@@`````@@@@@@@`````.@`````@@@`` ` @@,`` @@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@:,@@@@@@,.@@@@@@.,@;@@@@@@@,::::;@@+`,@@@@#,`;@@@@+::@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@GET@REKD@M8@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "+@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                + "");
    }

}
