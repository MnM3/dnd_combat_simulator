import com.battle_arena.Battle;
import com.battle_arena.Combat;
import com.battle_arena.Individuum;
import com.battle_arena.foe.RandomGoblin;
import com.battle_arena.goodies.GoodWife;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        List<Individuum> goodies = new ArrayList<>();
        List<Individuum> badies = new ArrayList<>();
        List<Individuum> badies2 = new ArrayList<>();

        GoodWife goodie = new GoodWife();

        goodies.add(goodie);

        for(int i = 0; i < 2; i++) {
            RandomGoblin random = new RandomGoblin();
            badies.add(random);
        }
        for(int i = 0; i < 2; i++) {
            RandomGoblin random = new RandomGoblin();
            badies2.add(random);
        }

        Battle battle = new Battle(goodies,badies);
        if(!Combat.checkAlldead(goodies)){
            Battle battle2 = new Battle(goodies, badies2);
        } else {
            System.out.println("The good guys already met their demise...");
        }
    }
}
