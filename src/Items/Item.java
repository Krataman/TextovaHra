package Items;

public class Item {
    private String itemName;
    private boolean isBringableOutOfRoom;
    private Rarity itemRarity;
    private double damage;

    public Item(String itemName, boolean isBringableOutOfRoom, Rarity itemRarity, double damage) {
        this.itemName = itemName;
        this.isBringableOutOfRoom = isBringableOutOfRoom;
        this.itemRarity = itemRarity;
        this.damage = damage;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isBringableOutOfRoom() {
        return isBringableOutOfRoom;
    }

    public Rarity getItemRarity() {
        return itemRarity;
    }

    @Override
    public String toString() {
        return
                "\n\uD835\uDC08\uD835\uDC2D\uD835\uDC1E\uD835\uDC26 \uD835\uDC0D\uD835\uDC1A\uD835\uDC26\uD835\uDC1E: " + itemName +
                "\n\uD835\uDC22\uD835\uDC2C\uD835\uDC01\uD835\uDC2B\uD835\uDC22\uD835\uDC27\uD835\uDC20\uD835\uDC1A\uD835\uDC1B\uD835\uDC25\uD835\uDC1E\uD835\uDC0E\uD835\uDC2E\uD835\uDC2D\uD835\uDC0E\uD835\uDC1F\uD835\uDC11\uD835\uDC28\uD835\uDC28\uD835\uDC26\uD835\uDC11\uD835\uDC1A\uD835\uDC2B\uD835\uDC22\uD835\uDC2D\uD835\uDC32: " + isBringableOutOfRoom +
                "\n\uD835\uDC11\uD835\uDC1A\uD835\uDC2B\uD835\uDC22\uD835\uDC2D\uD835\uDC32: " + itemRarity + "\nᴅᴀᴍᴀɢᴇ: " + damage + "\n===========================";

    }
}
