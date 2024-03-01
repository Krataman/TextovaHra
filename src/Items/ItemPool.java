package Items;
import Enemy.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ItemPool {
    List<Item> itemPool;
    Random r = new Random();

    //region const
    public ItemPool() {
        itemPool = new ArrayList<Item>();
        loadItems();
    }
    //endregion
    //region loadItems
    /**
     * Loads items from a text file and adds them to the item pool.
     * Each line in the text file should contain item information separated by commas.
     * The format for each line should be: itemName, isBringableOutOfRoom, itemRarity, damage
     * where:
     * - itemName is the name of the item (String).
     * - isBringableOutOfRoom specifies if the item can be brought out of a room (boolean).
     * - itemRarity specifies the rarity of the item (Rarity enum).
     * - damage specifies the damage value of the item (double).
     * <p>
     * The method reads each line, parses the information, and creates an Item object,
     * which is then added to the item pool.
     * <p>
     * If an IOException occurs during file reading, it will be caught and printed.
     */
    public void loadItems() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                String itemName = parts[0];
                boolean isBringableOutOfRoom = Boolean.parseBoolean(parts[1]);
                Rarity itemRarity = Rarity.valueOf(parts[2]);
                double damage = Double.parseDouble(parts[3]);

                itemPool.add(new Item(itemName, isBringableOutOfRoom, itemRarity, damage));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
    //region generateRandomLootItem
    /**
     * Generates a random loot item based on predefined rarity percentages.
     *
     * @return A randomly generated loot item.
     */
    public Item generateRandomLootItem(){
        int randomNum = r.nextInt(0, 100);

        if(randomNum >= 0 && randomNum <= 10){// 10% chance == LEGENDARY
            return itemsGeneratedBasedOnRarity(Rarity.LEGENDARY);
        }else if(randomNum > 10 && randomNum <= 15){// 5% chance == MYTHIC
            return itemsGeneratedBasedOnRarity(Rarity.MYTHIC);
        }else if(randomNum > 15 && randomNum <= 30){// 15% chance == RARE
            return itemsGeneratedBasedOnRarity(Rarity.RARE);
        }else if(randomNum > 30 && randomNum <= 60){// 30% chance == UNCOMMON
            return itemsGeneratedBasedOnRarity(Rarity.UNCOMMON);
        }else if (randomNum > 60 && randomNum <= 100){// 40% chance == COMMON
            return itemsGeneratedBasedOnRarity(Rarity.COMMON);
        }
        return null;
    }
    //endregion
    //region randomItem
    /**
     * Picks a random item based on the provided rarity.
     *
     * @param rarity The rarity of the item to be picked.
     * @return A randomly picked item with the specified rarity.
     */
    private Item itemsGeneratedBasedOnRarity(Rarity rarity){
        List<Item> pickedItems = new ArrayList<>();

        for(int i = 0; i < itemPool.size(); i++){
            if(itemPool.get(i).getItemRarity() == rarity){
                pickedItems.add(itemPool.get(i));
            }
        }

        int randomNum = r.nextInt(0, pickedItems.size());
        return pickedItems.get(randomNum);
    }
    //endregion
    //region toString
    @Override
    public String toString() {
        return
                "Item Pool: " + itemPool;
    }
    //endregion
}
