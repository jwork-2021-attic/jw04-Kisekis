package com.anish.calabashbros;

import com.anish.mazegen.MazeGenerator;

public class World implements SomeConstants{



    private Tile<Thing>[][] tiles;

    public World() {

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        MazeGenerator mazeGenerator = new MazeGenerator(mazeNum);
        mazeGenerator.generateMaze();
        System.out.println("SYMBOLIC MAZE\n" + mazeGenerator.getSymbolicMaze());

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setThing(new Floor(this));
            }
        }
        int d = (WIDTH - mazeNum) >> 1;

        for (int i = 0; i < mazeNum; i++) {
            for (int j = 0; j < mazeNum; j++) {
                if(mazeGenerator.getMazeBlock(i,j) != 1) {
                    tiles[i + d][j + d].setThing(new Wall(this));
                }
            }
        }
        for(int i = 0;i<mazeNum+2;i++) {
            tiles[d-1][d-1+i].setThing(new Wall(this,false));
            tiles[d+mazeNum][d-1+i].setThing(new Wall(this,false));
            tiles[d-1+i][d-1].setThing(new Wall(this,false));
            tiles[d-1+i][d+mazeNum].setThing(new Wall(this,false));
        }

        tiles[COUNT_X][COUNT_Y].setThing(new Number(this,八个蛋.getCount()));
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }
    public void delete(int x, int y) {
        this.tiles[x][y].setThing(new Floor(this));
    }

}
