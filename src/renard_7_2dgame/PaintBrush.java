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
public class PaintBrush {
    static int brushx, brushy;
    static int lastTileIntx = 0;
    static int lastTileInty = 0;

public PaintBrush(int brushx, int brushy){
    this.brushx = brushx;
    this.brushy = brushy;
}
    
    public  int getBrushx() {
        return brushx;
    }

    public  void setBrushx(int brushx) {
        PaintBrush.brushx = brushx;
    }

    public  int getBrushy() {
        return brushy;
    }

    public  void setBrushy(int brushy) {
        PaintBrush.brushy = brushy;
    }

    

    public  int getLastTileIntx() {
        return lastTileIntx;
    }

    public  void setLastTileIntx(int lastTileIntx) {
        PaintBrush.lastTileIntx = lastTileIntx;
    }

    public  int getLastTileInty() {
        return lastTileInty;
    }

    public  void setLastTileInty(int lastTileInty) {
        PaintBrush.lastTileInty = lastTileInty;
    }
    
public void paint(){
    Main.canvas[brushx][brushy] = "█";
}
public void erase(){
    Main.canvas[brushx][brushy] = " ";
}
}
