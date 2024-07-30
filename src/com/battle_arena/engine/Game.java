package com.battle_arena.engine;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import com.battle_arena.Individuum;
import com.battle_arena.Weapon;
import com.battle_arena.enviroment.Battlefield;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.goodies.Fighter;
import com.battle_arena.misc.CoordinateConverter;
import com.battle_arena.misc.Pathing.Position;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -240020020294884579L;

    public  static final int WIDTH = 1280, HEIGHT = WIDTH/ 12 * 9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;

    public Game() throws OutOfBattlefieldDimensionException {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Let's bild a simulator", this);
        //TODO: Create a level class, that setups everything correctly, right now some responsibilities are reveresed
        //		We don't want to create a grid and pass it down to the Individuum and so on. We
        /////////// SETUP
        
        Grid grid = new Grid(10, 10, 15, 15, ID.Grid);
        
        CoordinateConverter converter = new CoordinateConverter(grid);
        
        Battlefield battlefield = converter.getBattlefield();
        
        Individuum individuum = new Fighter("Test",
                24,
                0,
                16,
                3,
                3,
                new Weapon(3, 6, "sword", 2 , 19),
                2);
        
        individuum.setBattlefield(battlefield);
        individuum.setPosition(new Position(3,3));
        
        /////////////////////
        handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32, ID.Player));
        handler.addObject(new Player (10, 10, ID.Player));
        IndividuumGameObject igo = new IndividuumGameObject(individuum, ID.Individuum);
        handler.addObject(igo);
        handler.addObject(grid);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        @SuppressWarnings("unused")
		int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        Toolkit.getDefaultToolkit().sync(); // only add when Os is Linux? it does not work
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

 

    public static void main(String args[]) 
    {
        try {
			new Game();
		} catch (OutOfBattlefieldDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
