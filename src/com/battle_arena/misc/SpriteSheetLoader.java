package com.battle_arena.misc;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheetLoader {
	private BufferedImage spriteSheet;
	private String pathToSpriteSheet;
	
	public SpriteSheetLoader(String pathToSpriteSheet) {
		this.pathToSpriteSheet = pathToSpriteSheet;
	}
	
	public SpriteSheetLoader() throws IOException {
        String path = System.getProperty("user.dir") + File.separator + File.separator + "Assets" + File.separator;
        String file = "Sprites" + File.separator + "Sprites.png";
        this.pathToSpriteSheet = path + file;
        this.spriteSheet = ImageIO.read(new File(path, file));
        //this.spriteSheet = new BufferedImage(bi.getWidth(), bi.getHeight(), Buffered.Type_INT)
        
	}
	
	public ArrayList<BufferedImage> getAnimationFrames(int row, int length) {
		ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
		for(int i = 0; i <= length; i++) {
			BufferedImage frame = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
			frame.getGraphics().drawImage(this.spriteSheet,0 ,0 , 32, 32, i * 32, row * 32, i * 32 + 32, row * 32 + 32, null);
			frames.add(frame);
		}
		//Graphics g = grid.getGraphics();
		
		
		return frames;
	}
}
