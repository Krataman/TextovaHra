package Map;

import Items.Item;
import Items.ItemPool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map {

    private final List<Room> rooms;
    private HashMap<String, Item> itemsAndRooms;
    private final String start = "1";
    private String currentRoom = start;

    //region constructor
    public Map() {
        rooms = new ArrayList<>();
        itemsAndRooms = new HashMap<>();
        loadRooms();
        addItemsToRooms();
    }
    //endregion
    //region loadrooms
    /**
     * Načte informace o místnostech ze zadaného souboru a vytvoří instance třídy Map.Map.Lokalita
     * na základě těchto informací, které jsou následně přidány do kolekce rooms.
     *
     * @throws IOException pokud operace čtení ze souboru selže
     */
    public void loadRooms(){
        try(BufferedReader bf = new BufferedReader(new FileReader("dungeon.txt"))){
            String line;
            while((line = bf.readLine()) != null){
                String[] data = line.split(", ");

                rooms.add(new Room(data[0], data[1], data[2], data[3], data[4], data[5]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //endregion
    //region getRoomByID
    /**
     * Vyhledá a vrátí místnost podle zadaného ID.
     *
     * @param ID identifikátor místnosti, kterou chcete získat
     * @return instance třídy Map.Map.Lokalita reprezentující místnost, pokud je nalezena,
     *         nebo null, pokud místnost s daným identifikátorem neexistuje
     */
    public Room getRoom(String ID){
        try {
            int parsedID = Integer.parseInt(ID);
            if(parsedID >= 1) {
                return rooms.stream().filter(r -> r.getId().equalsIgnoreCase(ID)).findFirst().orElse(null);
            }
        } catch (NumberFormatException e){
            System.out.println("The room you are looking for must be a number!");
        }
        return null;
    }
    //endregion
    //region movetoNextRoom
    /**
     * Metoda přesouvá hráče do další místnosti na základě uživatelova vstupu a zaroven vypisuje item ktery je prirazen konkretni mistnosti.
     *
     * @param userInput uživatelův vstup, který určuje směr pohybu (číslo místnosti nebo zkratka směru)
     * @throws NumberFormatException pokud uživatelův vstup není platné číslo
     */
    public String moveToNextRoom(String userInput){

        try {
            switch (currentRoom) {
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9":
                    int roomIndex = Integer.parseInt(currentRoom) - 1;

                    if (userInput.equalsIgnoreCase(rooms.get(roomIndex).getSever()) && !rooms.get(roomIndex).getSever().equalsIgnoreCase("0")) {
                        currentRoom = rooms.get(roomIndex).getSever();
                    } else if (userInput.equalsIgnoreCase(rooms.get(roomIndex).getJih()) && !rooms.get(roomIndex).getJih().equalsIgnoreCase("0")) {
                        currentRoom = rooms.get(roomIndex).getJih();
                    } else if (userInput.equalsIgnoreCase(rooms.get(roomIndex).getZapad()) && !rooms.get(roomIndex).getZapad().equalsIgnoreCase("0")) {
                        currentRoom = rooms.get(roomIndex).getZapad();
                    } else if (userInput.equalsIgnoreCase(rooms.get(roomIndex).getVychod()) && !rooms.get(roomIndex).getVychod().equalsIgnoreCase("0")) {
                        currentRoom = rooms.get(roomIndex).getVychod();
                    } else if (userInput.equalsIgnoreCase("0")) {
                        System.exit(0);
                    } else {
                        return "Invalid direction!";
                    }
                    break;
            }

            return "\nMistnost po zmene: " + rooms.get(Integer.parseInt(currentRoom)-1) + "\n------------------------------";

        } catch (NumberFormatException e) {
            return "User input must be a number!";
        }
    }
    //endregion
    //region addItemsToRooms
    /**
     * Pri vytvoreni instance teto tridy, se kazde mistnosti priradi prave 1 nahodny item, ktery bude vygenerovan metedou ve tride ItemPool.
     */
    public void addItemsToRooms(){
        ItemPool p = new ItemPool();

        for(int i = 0; i < rooms.size(); i++){
            itemsAndRooms.put(rooms.get(i).getId(), p.generateRandomLootItem());
        }
    }
    //endregion
    //region randomRoom
    public Room startRoom(){
        Random r = new Random();

        int randomNum = r.nextInt(0, rooms.size() - 1);
        return rooms.get(randomNum);
    }
    //endregion
    //region getItemByRommID//Key
    public Item getItemByRommID(String roomID){
        return itemsAndRooms.get(roomID);
    }
    //endregion
    //region removeItemFromRoom
    public void removeItemFromRoom(String key){
        itemsAndRooms.remove(key);
    }
    //endregion
    //region toString
    @Override
    public String toString() {
        return "Map.Map{" +
                "rooms=" + rooms +
                '}';
    }
    //endregion

    public List<Room> getRooms() {
        return rooms;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }
}
