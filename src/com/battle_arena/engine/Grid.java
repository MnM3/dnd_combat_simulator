package com.battle_arena.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grid extends GameObject {

    BufferedImage bi;
    BufferedImage grid;
    int dim_x;
    int dim_y;

    public Grid(int x, int y, int dim_x, int dim_y ,ID id) {
        super(x, y, id);
        this.dim_x = dim_x;
        this.dim_y = dim_y;
        System.out.println(System.getProperty("user.dir") + "\\Assets\\");
        String path = System.getProperty("user.dir") + "\\Assets\\";
        try {
            //bi = ImageIO.read(new File(path , "Number_1.png"));
            bi = ImageIO.read(new File(path, "Grid_cell.png"));
            grid = new BufferedImage(bi.getWidth() * 20, bi.getWidth() * 20, BufferedImage.TYPE_INT_ARGB);
            Graphics g = grid.getGraphics();
            for (int i = 0; i < dim_y; i++) {
                for(int h = 0; h< dim_x; h++) {
                    g.drawImage(bi, h * 32, i*32, null);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(bi, 0,0, null);
        g.drawImage(grid, this.x, this.y, null);

    }
}
