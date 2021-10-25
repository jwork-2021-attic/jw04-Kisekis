package com.anish.calabashbros;

import asciiPanel.AsciiPanel;

public class Wall extends Thing {

    Wall(World world) {
        super(AsciiPanel.yellow, (char) 177, world);
    }

}
