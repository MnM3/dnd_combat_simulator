package com.battle_arena.Test;

import com.battle_arena.Individuum;
import com.battle_arena.Weapon;
import com.battle_arena.enviroment.Battlefield;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.goodies.Fighter;
import com.battle_arena.misc.Pathing.PathSolver;
import com.battle_arena.misc.Pathing.Position;

public class Test_Path {

	/*
	 * Test cases:
	 * 	- Get from A to B in a non robotic manner
	 *  - Try to get around obstacles
	 *  - handle not being able to reach goal with grace
	 *  - handle when goal is not 
	 */
	
    public static void main(String[] args) throws OutOfBattlefieldDimensionException {
        test_path();
    }
    private static void test_path() throws OutOfBattlefieldDimensionException {
        //create battlefield, give two Positions
        Battlefield battlefield = new Battlefield(10, 10);
        Individuum individuum = new Fighter("Test",
                24,
                0,
                16,
                3,
                3,
                new Weapon(3, 6, "sword", 2 , 19),
                2);

        individuum.setBattlefield(battlefield);
        individuum.setPosition(new Position(0, 9));
        //Refactor: Constructor seems to have deprecated information?
        PathSolver pathSolver = new PathSolver(individuum, new Position(4,4));
        pathSolver.findPath(individuum.getPosition(), new Position(4,4));
        
    }
    
    
    private static void test_path_with_obstacle() throws OutOfBattlefieldDimensionException {
        //create battlefield, give two Positions
        Battlefield battlefield = new Battlefield(10, 10);
        Individuum individuum = new Fighter("Test",
                24,
                0,
                16,
                3,
                3,
                new Weapon(3, 6, "sword", 2 , 19),
                2);
        Individuum ind2 = new Fighter("Test",
                24,
                0,
                16,
                3,
                3,
                new Weapon(3, 6, "sword", 2 , 19),
                2);
        individuum.setBattlefield(battlefield);
        individuum.setPosition(new Position(9, 2));
        ind2.setBattlefield(battlefield);
        ind2.setPosition(new Position (2,3));
        
        //Refactor: Constructor seems to have deprecated information?
        PathSolver path = new PathSolver(individuum, new Position(4,4));

        path.findPath(individuum.getPosition(), new Position(4,4));
        return;
    }
    
    
    
}
