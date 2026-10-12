public class Fighter {

    boolean isVulnerable() {
        return true;
    }
}

class Warrior extends Fighter {

    public int getDamagePoints(Fighter fighter) {
        return fighter.isVulnerable() ? 10 : 6;
    }

    @Override
    boolean isVulnerable() {
        return false;
    }

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }
}

class Wizard extends Fighter {

    private boolean isPreparedSpell;

    @Override
    boolean isVulnerable() {
        return !isPreparedSpell;
    }

    int getDamagePoints(Fighter warrior) {
        return isPreparedSpell ? 12 : 3;
    }

    void prepareSpell() {
        isPreparedSpell = true;
    }

    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }
}
