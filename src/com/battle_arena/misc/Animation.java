package com.battle_arena.misc;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	private ArrayList<BufferedImage> frames;
	private SpriteSheetLoader ssl;
	private int row;
	private int length;
	private Boolean loop;
	private Animation transistionTo;
	
	public Animation(SpriteSheetLoader ssl, int row, int length) {
		this.frames = ssl.getAnimationFrames(row, length);
		this.row = row;
		this.length = length;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public ArrayList<BufferedImage> getFrames() {
		return frames;
	}

}
