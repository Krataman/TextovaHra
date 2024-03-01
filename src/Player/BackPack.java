package Player;

import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class BackPack {
    private List<Item> inventory;
    private final int capacity = 5;
    private int carrying = 0;

    //region cons
    public BackPack(){
        inventory = new ArrayList<Item>();
    }
    //endregion
    //region pick up item
    public String pickUpItem(String yesORno, Item item) {

            if (yesORno.equalsIgnoreCase("Y") && item.isBringableOutOfRoom() && carrying <= capacity) {
                inventory.add(item);
                carrying++;
                return "Item BYL pridan do inventare!\n";
            } else {
                return "Item NEBYL pridan do inventare!\n";
            }

    }

    //endregion
    //region useItem

    /**
     * item je paramentr ktery reprezentuje kazdou polozku v seznamu inventory (lambda vyraz),
     * nemusi se jmenovat primo "item", muze byt napriklad jen "i".
     * @param itemName
     */
    public void useItem(String itemName){
        inventory.stream().filter(item -> item.getItemName().equalsIgnoreCase(itemName)).findFirst().orElse(null);
    }
    //endregion
    //region toString
    @Override
    public String toString() {
        return
                "INVENTORY: \n" + inventory;
    }
    //endregion
}
