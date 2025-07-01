import java.util.Collection;
import java.util.List;
import java.util.Random;

class DnDCharacter {
    private static final Random random = new Random();

    private final int strength = randomAbility();
    private final int dexterity = randomAbility();
    private final int constitution = randomAbility();
    private final int intelligence = randomAbility();
    private final int wisdom = randomAbility();
    private final int charisma = randomAbility();
    private final int hitpoints = 10 + this.modifier(constitution);

    public static int ability(Collection<Integer> scores) {
        return scores.stream().sorted().skip(1).mapToInt(Integer::intValue).sum();
    }

    private static int randomAbility() {
        return ability(rollDice());
    }

    int modifier(int input) {
        return (int) Math.floor((input - 10) / 2.);
    }

    int getStrength() {
        return strength;
    }

    int getDexterity() {
        return dexterity;
    }

    int getConstitution() {
        return constitution;
    }

    int getIntelligence() {
        return intelligence;
    }

    int getWisdom() {
        return wisdom;
    }

    int getCharisma() {
        return charisma;
    }

    int getHitpoints() {
        return hitpoints;
    }

    public static List<Integer> rollDice() {
        return random.ints(4, 1, 7).boxed().toList();
    }
}
