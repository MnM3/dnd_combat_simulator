package com.battle_arena.engine;

import java.awt.*;

public class Player extends GameObject {
    public Player(int x, int y, ID id) {
        super(x,y,id);
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y, 32, 32);
    }
}
