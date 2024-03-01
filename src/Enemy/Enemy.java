package Enemy;

public class Enemy {
    protected String name;
    protected double hp;
    protected int minDmg;
    protected int maxDmg;

    public Enemy(String name, double hp, int minDmg, int maxDmg) {
        this.name = name;
        this.hp = hp;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
    }

    @Override
    public String toString() {
        return
                "\nName: " + name +
                "\nHP: " + hp +
                "\nMin DMG: " + minDmg +
                "\nMax DMG: " + maxDmg + "\n";
    }
}
