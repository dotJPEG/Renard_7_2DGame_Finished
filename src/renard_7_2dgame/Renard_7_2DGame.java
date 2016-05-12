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
public class Renard_7_2DGame {

    static Random randy = new Random();
    static Scanner scan = new Scanner(System.in);
    static boolean left, right, down, up;
    static String[][] map = new String[1000][1000];
    static int playerx = randy.nextInt(1000);
    static int playery = randy.nextInt(1000);
    static int score = 0;
    static int ex = playerx + randy.nextInt(20);
    static int ey = playery - randy.nextInt(20);
static Game_Insults meme;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("you wake up in a spooky dungeon full of dots and X's");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
                         + "@@::::::::::@@@::::::@@@@@@@:::::::::::::::@@@:::::::::::::::@@\n"
                         + "@@::@@@@@@::@@@::::::@@@@@@@::::@@@@@@@::::@@@@@@@@@:::@@@@@@@@\n"
                         + "@@::::::::::@@@::::::@@@@@@@::::@@@@@@@::::@@@@@@@@@:::@@@@@@@@\n"
                         + "@@::@@@@@@@@@@@::::::::::@@@:::::::::::::::@@@@@@@@@:::@@@@@@@@\n"
                         + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Get 100 points to win, picking up treasure is worth 10 points");
        System.out.println("to play use the numberpad as a directional pad (i.e. 6 is left, 7 is up and left)");
        System.out.println("use '-' to end the game");
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                map[i][j] = ".";
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
        }
        map[playerx][playery] = "@";
        map[ex][ey] = "M";
        boolean game = true;

        if (playerx - 25 < 0) {
            printmap(0, playerx + 25, playery - 25, playery + 25);
        } else if (playerx + 25 > 999) {
            printmap(playerx - 25, 0, playery - 25, playery + 25);
        } else if (playery - 25 < 0) {
            printmap(playerx - 25, playerx + 25, 0, playery + 25);
        } else if (playery + 25 > 999) {
            printmap(playerx - 25, playerx + 25, playery - 25, 0);
        } else {
            for (int i = playerx - 25; i < playerx + 24; i++) {
                System.out.println("");
                for (int j = playery - 25; j < playery + 24; j++) {
                    System.out.print(map[i][j] + " ");
                }
            }
        }
        while (game) {
            String action = scan.next();
            if (action.equalsIgnoreCase("7") && map[playerx - 1][playery + 1] != "X") {
                map[playerx][playery] = ".";
                playerx -= 1;
                playery -= 1;
            } else if (action.equalsIgnoreCase("8") && map[playerx - 1][playery] != "X") {
                map[playerx][playery] = ".";
                playerx -= 1;
            } else if (action.equalsIgnoreCase("9") && map[playerx + 1][playery + 1] != "X") {
                map[playerx][playery] = ".";
                playerx -= 1;
                playery += 1;
            } else if (action.equalsIgnoreCase("4") && map[playerx][playery - 1] != "X") {
                map[playerx][playery] = ".";
                playery -= 1;
            } else if (action.equalsIgnoreCase("5")) {
                System.out.println("You sit still for a turn");
            } else if (action.equalsIgnoreCase("6") && map[playerx][playery + 1] != "X") {
                map[playerx][playery] = ".";
                playery += 1;
            } else if (action.equalsIgnoreCase("1") && map[playerx - 1][playery - 1] != "X") {
                map[playerx][playery] = ".";
                playerx += 1;
                playery -= 1;
            } else if (action.equalsIgnoreCase("2") && map[playerx + 1][playery] != "X") {
                map[playerx][playery] = ".";
                playerx += 1;
            } else if (action.equalsIgnoreCase("3") && map[playerx + 1][playery - 1] != "X") {
                map[playerx][playery] = ".";
                playerx += 1;
                playery += 1;
            } else if (action.equalsIgnoreCase("-")) {
                game = false;
            } else {
                System.out.println("nope cant do that boss");
            }

            if (ex < playerx) {
                map[ex][ey] = ".";
                ex++;
                map[ex][ey] = "M";
            } else if (ex > playerx) {
                map[ex][ey] = ".";
                ex--;
                map[ex][ey] = "M";
            } else if (ey < playery) {
                map[ex][ey] = ".";
                ey++;
                map[ex][ey] = "M";
            } else if (ey > playery) {
                map[ex][ey] = ".";
                ey--;
                map[ex][ey] = "M";
            } else {
                System.out.println("You take a hit cause youre bad m8, the enemy dies tho");
                System.out.println(meme.getInsult());
                score -= 10;
            }

            if (map[playerx][playery] == "*") {
                System.out.println("your die because " + meme.getInsult());
                game = false;
            } else if (map[playerx][playery] == "T") {
                score += 10;
                map[playerx][playery] = "@";
            } else {
                map[playerx][playery] = "@";
            }
            System.out.println("your score is " + score);
            if (playerx - 25 < 0) {
                printmap(0, playerx + 25, playery - 25, playery + 25);
            } else if (playerx + 25 > 999) {
                printmap(playerx - 25, 0, playery - 25, playery + 25);
            } else if (playery - 25 < 0) {
                printmap(playerx - 25, playerx + 25, 0, playery + 25);
            } else if (playery + 25 > 999) {
                printmap(playerx - 25, playerx + 25, playery - 25, 0);
            } else {
                for (int i = playerx - 25; i < playerx + 24; i++) {
                    System.out.println("");
                    for (int j = playery - 25; j < playery + 24; j++) {
                        System.out.print(map[i][j] + " ");
                    }
                }
            }
            if (score >= 100) {
                System.out.println("you got enough points to win, continue?");
                if (action.contains("y") || action.contains("Y")) {
                    System.out.println("Be free my sheeple");
                } else {
                    System.out.println("Thank you for playing");
                    game = false;
                    win();
                }
            } else if (score < 0) {
                System.out.println("you die because " + meme.getInsult());
                game = false;
                lose();
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
