package com.anish.calabashbros;

import asciiPanel.AsciiPanel;

public class Number extends Thing{
    public Number(World world, int i) {
        super(AsciiPanel.white, toNum(i), world);
    }

}
