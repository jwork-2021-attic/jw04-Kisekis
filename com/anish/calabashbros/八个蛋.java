package com.anish.calabashbros;

import asciiPanel.AsciiPanel;

import java.awt.*;

public class 八个蛋 extends Thing{
    private int status = 0;
    private static int count = 3;
    public 八个蛋(World world) {
        super(Color.pink, (char) 150, world);
        count--;
    }
    public void changeStatus() {
        status++;
        if(status==1) {
            super.changeColor(Color.yellow);
        }else if(status==2){
            super.changeColor(AsciiPanel.red);
        }
    }
    public static int getCount(){return count;}
}
