package com.anish.calabashbros;

public interface SomeConstants {
    static final int num = 16;
    static final int mazeNum = 40;
    static final int WIDTH = 50;
    static final int HEIGHT = 50;
    static final int START_X = (WIDTH-mazeNum)>>1;
    static final int START_Y = (HEIGHT-mazeNum)>>1;
    static final int END_X = WIDTH-(WIDTH-mazeNum)/2-1;
    static final int END_Y = HEIGHT-(WIDTH-mazeNum)/2-1;
    static final int COUNT_X = WIDTH/2;
    static final int COUNT_Y = (WIDTH-mazeNum)>>2;

}
