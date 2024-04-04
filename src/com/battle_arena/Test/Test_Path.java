package com.battle_arena.Test;

import com.battle_arena.Individuum;
import com.battle_arena.Weapon;
import com.battle_arena.enviroment.Battlefield;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.goodies.Fighter;
import com.battle_arena.misc.Path;
import com.battle_arena.misc.Position;

public class Test_Path {

    public static void main(String[] args) throws OutOfBattlefieldDimensionException {
        test_path();
    }
    private static void test_path() throws OutOfBattlefieldDimensionException {
        //create battlefield, give two Positions
        Battlefield battlefield = new Battlefield(11, 11);
        Individuum individuum = new Fighter("Test",
                24,
                0,
                16,
                3,
                3,
                new Weapon(3, 6, "sword", 2 , 19),
                2);
        individuum.setBattlefield(battlefield);
        individuum.setPosition(new Position(2, 2));
        Path path = new Path(individuum, new Position(9,9));

        path.findPath(individuum.getPosition(), new Position(9,7));
        return;
    }


}
