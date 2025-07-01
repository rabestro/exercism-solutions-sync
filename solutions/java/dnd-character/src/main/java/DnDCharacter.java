import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a Dungeons & Dragons character with randomly generated ability scores and hitpoints.
 * The character's abilities are generated once upon creation and are immutable.
 */
class DnDCharacter {

    private static final Random random = ThreadLocalRandom.current();

    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;
    private final int hitpoints;

    /**
     * Creates a new DnD character with a unique, randomly generated set of ability scores.
     * All ability scores are calculated upon instantiation and then remain constant.
     */
    public DnDCharacter() {
        this.strength = generateAbility();
        this.dexterity = generateAbility();
        this.constitution = generateAbility();
        this.intelligence = generateAbility();
        this.wisdom = generateAbility();
        this.charisma = generateAbility();
        this.hitpoints = 10 + modifier(this.constitution);
    }

    /**
     * Calculates an ability score from a collection of dice rolls.
     * The standard method is to roll 4 6-sided dice and sum the highest 3.
     *
     * @param scores A collection of 4 integer dice rolls.
     * @return The calculated ability score (the sum of the 3 highest rolls).
     */
    public static int ability(Collection<Integer> scores) {
        return scores.stream().sorted().skip(1).mapToInt(Integer::intValue).sum();
    }

    /**
     * Generates a single, random ability score by rolling 4d6 and dropping the lowest roll.
     *
     * @return A random ability score between 3 and 18.
     */
    private static int generateAbility() {
        return ability(rollDice());
    }

    /**
     * Calculates the ability modifier for a given score based on the formula floor((score - 10) / 2).
     *
     * @param input The ability score (e.g., 14, 9).
     * @return The corresponding ability modifier.
     */
    public static int modifier(int input) {
        return Math.floorDiv(input - 10, 2);
    }

    /**
     * Simulates rolling four 6-sided dice.
     *
     * @return A list containing 4 random integers, each between 1 and 6 (inclusive).
     */
    public static List<Integer> rollDice() {
        return random.ints(4, 1, 7).boxed().toList();
    }

    /**
     * @return The character's Strength score.
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return The character's Dexterity score.
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * @return The character's Constitution score.
     */
    public int getConstitution() {
        return constitution;
    }

    /**
     * @return The character's Intelligence score.
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @return The character's Wisdom score.
     */
    public int getWisdom() {
        return wisdom;
    }

    /**
     * @return The character's Charisma score.
     */
    public int getCharisma() {
        return charisma;
    }

    /**
     * Gets the character's initial hitpoints.
     * This is calculated as 10 + the character's constitution modifier.
     *
     * @return The total initial hitpoints.
     * @see #getConstitution()
     * @see #modifier(int)
     */
    public int getHitpoints() {
        return hitpoints;
    }
}
