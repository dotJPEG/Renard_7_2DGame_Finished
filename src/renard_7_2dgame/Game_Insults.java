/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renard_7_2dgame;
import java.util.*;
/**
 *
 * @author dotJPEG
 */
public class Game_Insults {
    static Random randy = new Random();
    static String[] insultArray = { 
    "a loser", "smelly", "a dink",
    "a nerd", "a lame-o", "a memer", 
    "garbage", "a pee-wee", "weak-sauce" 
    };

public static String getInsult(){
    return "You are " + insultArray[randy.nextInt(9)-1];
}
}