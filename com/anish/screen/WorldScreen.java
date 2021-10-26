package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.anish.calabashbros.*;

import asciiPanel.AsciiPanel;
import com.anish.calabashbros.Number;

public class WorldScreen implements Screen, SomeConstants {

    private World world;
    private Calabash guy;


    public WorldScreen() throws IOException {
        world = new World();
        guy = new Calabash(Color.yellow,0,world);
        world.put(guy,START_X,START_Y);
    }


    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }


    @Override
    public Screen respondToUserInput(KeyEvent key) throws InterruptedException {

        switch(key.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(!(world.get(guy.getX()-1,guy.getY()) instanceof Wall)) {
                    if(world.get(guy.getX(),guy.getY()) instanceof Calabash)
                        world.delete(guy.getX(),guy.getY());
                    guy.moveTo(guy.getX()-1,guy.getY());
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(!(world.get(guy.getX()+1,guy.getY()) instanceof Wall)) {
                    if(world.get(guy.getX(),guy.getY()) instanceof Calabash)
                        world.delete(guy.getX(),guy.getY());
                    guy.moveTo(guy.getX()+1,guy.getY());
                }
                break;
            case KeyEvent.VK_UP:
                if(!(world.get(guy.getX(),guy.getY()-1) instanceof Wall)) {
                    if(world.get(guy.getX(),guy.getY()) instanceof Calabash)
                        world.delete(guy.getX(),guy.getY());
                    guy.moveTo(guy.getX(),guy.getY()-1);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(!(world.get(guy.getX(),guy.getY()+1) instanceof Wall)) {
                    if(world.get(guy.getX(),guy.getY()) instanceof Calabash)
                        world.delete(guy.getX(),guy.getY());
                    guy.moveTo(guy.getX(),guy.getY()+1);
                }
                break;
            case KeyEvent.VK_0:
                if(八个蛋.getCount()==0) break;
                int X = guy.getX();
                int Y = guy.getY();
                Thread newThread = new Thread(
                    () -> {
                        八个蛋 boom = new 八个蛋(world);
                        world.put(boom,X,Y);
                        world.put(new Number(world,八个蛋.getCount()),COUNT_X,COUNT_Y);
                        Thing[] list = {world.get(X,Y+1),
                                world.get(X,Y-1),
                                world.get(X+1,Y),
                                world.get(X-1,Y)
                        };

                        try {
                            Thread.sleep(1000);
                            boom.changeStatus();
                            Thread.sleep(1000);
                            boom.changeStatus();
                            Thread.sleep(1000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for(Thing x : list) {
                            if(x instanceof Wall) {
                                if(((Wall)x).getIsBreakable()){
                                    world.delete(x.getX(),x.getY());
                                }
                            }
                        }
                        world.delete(X,Y);
                    });
                newThread.start();
        }
        if(guy.getX() == END_X && guy.getY() == END_Y) {
            world.delete(guy.getX(),guy.getY());
        }
        return this;
    }

}
