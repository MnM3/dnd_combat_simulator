
import com.battle_arena.Battle;
import com.battle_arena.Combat;
import com.battle_arena.Individuum;
import com.battle_arena.Weapon;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.foe.RandomGoblin;
import com.battle_arena.foe.Random_Barbau_half;
import com.battle_arena.goodies.Fighter;
import com.battle_arena.goodies.GoodWife;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) throws OutOfBattlefieldDimensionException {
        List<Individuum> goodies = new ArrayList<>();
        List<Individuum> badies = new ArrayList<>();
        List<Individuum> badies2 = new ArrayList<>();
        List<Integer> deadCountsPerFight = new ArrayList<>();

        int foeCount = 3;
        int goodGuysWins = 0;
        int badGuysWins = 0;
        
        int numBattles = 1000;

        for(int f =0; f<numBattles; f++) {
            goodies = new ArrayList<>();
            badies = new ArrayList<>();
            GoodWife goodie = new GoodWife();
            Fighter fighter_1 = new Fighter("Hans", Fighter.health(2, 3), 1, 14, 3, 2, new Weapon(6, 1, "LongSword", 2, 4), 3);
            Fighter fighter_2 = new Fighter("Bernd", Fighter.health(2, 1), 1, 14, 3, 2, new Weapon(6, 1, "LongSword", 2, 4), 3);
            Fighter fighter_3 = new Fighter("Sebastian", Fighter.health(2, 0), 1, 14, 3, 2, new Weapon(6, 1, "LongSword", 2, 4), 3);


            goodies.add(goodie);
            goodies.add(fighter_1);
            goodies.add(fighter_2);
            goodies.add(fighter_3);

            for (int i = 0; i < foeCount; i++) {
                Random_Barbau_half random = new Random_Barbau_half();
                badies.add(random);
            }
            for (int i = 0; i < foeCount; i++) {
                RandomGoblin random = new RandomGoblin();
                badies2.add(random);
            }

            Battle battle = new Battle(goodies, badies);
            battle.fight_till_death();
            deadCountsPerFight.add(Combat.checkNumberOfDead(goodies));
            if (Combat.checkAlldead(goodies)) {
                badGuysWins++;
            } else {
                goodGuysWins++;
            }
        }

        int zero = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int[] deaths = new int[5];
        for (int number:deaths) {
            deaths[number] = 0;
        }

        for(int death : deadCountsPerFight) {
            deaths[death]++;
        }
        System.out.println(deadCountsPerFight);
        int z = 0;
        for(int number : deaths) {

            System.out.println(z+" deaths "+ number + "times");
            z++;
        }
        System.out.println("The good Guys won: "+ goodGuysWins + " times");
        System.out.println("The bad Guys won: "+ badGuysWins+ " times");
        /*if(!Combat.checkAlldead(goodies)){
            Battle battle2 = new Battle(goodies, badies2);
        } else {
            System.out.println("The good guys already met their demise...");
        }*/
    }
}
