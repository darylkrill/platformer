package com.stexni.platformer;

public class World {
    private final int width;
    private final int height;

    public World() {
        this(32, 9);
    }

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int blockAt(int x, int y) {
        int block = 1;
        if(x < 0 || x >= width || y < 0 || y >= height) {
            block = 2;
        } else if(y == height - 3) {
            block = 3;
        } else if(y < height - 3) {
            block = 0;
        }

        return block;
    }
}
