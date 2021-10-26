package com.anish.calabashbros;

import asciiPanel.AsciiPanel;

public class Wall extends Thing {
    boolean breakable = true;

    Wall(World world) {
        super(AsciiPanel.white, (char) 177, world);
    }
    Wall(World world,boolean breakable) {
        super(AsciiPanel.white, (char) 177, world);
        this.breakable = breakable;
    }

    public boolean getIsBreakable(){ return breakable;}

}
