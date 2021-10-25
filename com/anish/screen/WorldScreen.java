package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import com.anish.calabashbros.*;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen, SomeConstants {

    private World world;
    private Calabash[][] bros;
    String[] sortSteps;

    public WorldScreen() throws IOException {
        world = new World();
        bros = new Calabash[num][num];
        ReadPic p = new ReadPic();
        for(int i=0; i<num; i++) {
            for(int j=0; j<num; j++) {
                int r = p.getRGB(num*i+j)[0];
                int g = p.getRGB(num*i+j)[1];
                int b = p.getRGB(num*i+j)[2];
                bros[i][j] = new Calabash(new Color(r,g,b),num*i+j+1,world);
            }
        }
        Random rand = new Random(1);
        for(int i =0;i<num*num;i++) {
            int r = rand.nextInt(num*num);
            Calabash temp = bros[i/num][i%num];
            bros[i/num][i%num] = bros[r/num][r%num];
            bros[r/num][r%num] = temp;
        }

        for(int i=0;i<num;i++) {
            for(int j=0;j<num;j++) {
                world.put(bros[i][j],10+2*i,10+2*j);
            }
        }

        QuickSorter<Calabash> b = new QuickSorter<>();
        b.load(bros);
        b.sort();
//        for(Calabash[] x : bros) {
//            for(Calabash y : x) {
//                System.out.println(y.getRank());
//            }
//        }
        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[][] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[][] bros, int rank) {
        for (Calabash bro[] : bros) {
            for(Calabash x : bro) {
                if (x.getRank() == rank) {
                    return x;
                }
            }

        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}
