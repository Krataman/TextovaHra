import Items.Item;
import Items.ItemPool;
import Enemy.EnemyPool;
import Player.*;
import Map.MapWindow;
import Map.Map;

import java.util.Scanner;

public class Game {
    private Player player;
    private Map map;
    private EnemyPool ePool;
    private ItemPool pool;
    private BackPack backPack;
    Scanner sc = new Scanner(System.in);

    //region initialize
    private void initialize(){
        player = new Player();
        map = new Map();
        ePool = new EnemyPool();
        pool = new ItemPool();
        backPack = new BackPack();
        mapWindow();
    }
    //endregion
    //region LoadMapWindow
    private void mapWindow(){
        new MapWindow();
    }
    //endregion

    //region play
    public void play(){

        String in = "";
        initialize();

        /* // TEST GENEROVANI DMG NEPRITELE
        System.out.println(ePool.toString() + "\n\n");

        System.out.println(ePool.getEnemyByName("Infernal Demon"));
        System.out.println(ePool.enemyAttack(ePool.getEnemyByName("Infernal Demon")));
        */

        System.out.println("Momentalni mistnost: " + map.getRooms().get(0));

        //region hlavni cyklus
        while(!in.equalsIgnoreCase("0")){
            System.out.println("Zadejte cislo mistnost do ktere chcete jit: ");
            in = sc.next();

            System.out.println(map.moveToNextRoom(in)); // posune hrace do mistnosti a vypise item ktery je teto mistnosti prirazen

            //region pickup itemuu
            boolean cyklus2 = true;
            while(cyklus2 && map.getItemByRommID(map.getCurrentRoom()) != null){

                System.out.println("Chcete sebrat tento item?   [Y/N]");
                System.out.println(map.getItemByRommID(map.getCurrentRoom()));

                String userInput = sc.next();

                if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N")) {
                    System.out.println(backPack.pickUpItem(userInput, map.getItemByRommID(map.getCurrentRoom())));

                    if(userInput.equalsIgnoreCase("Y")){
                        map.removeItemFromRoom(map.getCurrentRoom());
                    }

                    cyklus2 = false;
                } else {
                    System.out.println("Musite zadat jednu z vyse uvedenych moznosti!");
                }

                System.out.println(backPack.toString());
            }
            //endregion
        }
        //endregion
        }

    //endregion

}
