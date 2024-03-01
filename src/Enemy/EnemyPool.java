package Enemy;
import Player.Player;
import Items.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyPool {

    List<Enemy> enemyPool;
    Random r = new Random();

    //region con
    public EnemyPool() {
        enemyPool = new ArrayList<>();
        loadEnemies();
    }

    //endregion

    //region pickRandomEnemy
    /**
     * Picks a random enemy from the enemy pool.
     *
     * @return A randomly picked enemy from the enemy pool, or null if the enemy pool is empty.
     */
    public Enemy pickRandomEnemy(){

        if(!enemyPool.isEmpty()) {
            int randomNum = r.nextInt(0, enemyPool.size()); // -1 ???, byl problem v metode na vygenerovani nahodneho itemu
            return enemyPool.get(randomNum);
        }else{
            return null;
        }
    }
    //endregion
    //region fight
    public void fight(Player player){
        Enemy enemy = pickRandomEnemy();

        //region hlavni cyklus
        while(enemy.hp > 0 || player.getHp() > 0){
            System.out.println("Utocis prvni! Vyber zbran, kterou chces pouzit:       [napis nazev zbrane]");


        }
        //endregion
    }
    //endregion
    //region loadEnemies
    /**
     * Loads enemies from a text file and adds them to the enemy pool.
     * Each line in the text file should contain enemy information separated by commas.
     * The format for each line should be: name, hp, damage
     * where:
     * - name is the name of the enemy (String).
     * - hp is the health points of the enemy (double).
     * - damage is the damage value of the enemy (double).
     * <p>
     * The method reads each line, parses the information, and creates an Enemy object,
     * which is then added to the enemy pool.
     * <p>
     * If an IOException occurs during file reading, it will be caught and printed.
     */
    public void loadEnemies() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("enemy.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0];
                double hp = Double.parseDouble(parts[1]);
                int minDamage = Integer.parseInt(parts[2]);
                int maxDamage = Integer.parseInt(parts[3]);

                enemyPool.add(new Enemy(name, hp, minDamage, maxDamage));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
    //region enemyAttack
    public int enemyAttack(Enemy e) {

        int damageToReturn = 0;
        for(int i = 0; i < enemyPool.size(); i++){
            if(e.name.equalsIgnoreCase(enemyPool.get(i).name)){

                damageToReturn = r.nextInt(enemyPool.get(i).minDmg, enemyPool.get(i).maxDmg);

            }
        }
        return damageToReturn;
    }
    //endregion
    //region getEnemyByName
    public Enemy getEnemyByName(String name){
        return enemyPool.stream().filter(enm -> enm.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    //endregion

    @Override
    public String toString() {
        return "Enemy Pool: " + enemyPool;
    }
}


