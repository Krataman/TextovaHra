import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<Lokalita> rooms;
    private final String start = "1";
    private String currentRoom = start;

    //region constructor
    public Map() {
        rooms = new ArrayList<>();
    }
    //endregion
    //region loadrooms
    /**
     * Načte informace o místnostech ze zadaného souboru a vytvoří instance třídy Lokalita
     * na základě těchto informací, které jsou následně přidány do kolekce rooms.
     *
     * @param path cesta k souboru obsahujícímu informace o místnostech
     * @throws IOException pokud operace čtení ze souboru selže
     */
    public void loadRooms(String path){
        try(BufferedReader bf = new BufferedReader(new FileReader(path))){
            String line;
            while((line = bf.readLine()) != null){
                String[] data = line.split(" ");

                rooms.add(new Lokalita(data[0], data[1], data[2], data[3], data[4], data[5]));
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
     * @return instance třídy Lokalita reprezentující místnost, pokud je nalezena,
     *         nebo null, pokud místnost s daným identifikátorem neexistuje
     */
    public Lokalita getRoom(String ID){
        try {
            int parsedID = Integer.parseInt(ID);

            if(parsedID >= 1) {
                for (int i = 0; i < rooms.size(); i++) {
                    if (ID.equalsIgnoreCase(rooms.get(i).getId())) {
                        return rooms.get(i);
                    }
                }
            }
        } catch (NumberFormatException e){
            System.out.println("The room you are looking for must be a number!");
        }
        return null;
    }
    //endregion
    //region movetoNextRoom
    /**
     * Metoda přesouvá hráče do další místnosti na základě uživatelova vstupu.
     *
     * @param userInput uživatelův vstup, který určuje směr pohybu (číslo místnosti nebo zkratka směru)
     * @throws NumberFormatException pokud uživatelův vstup není platné číslo
     */
    public void moveToNextRoom(String userInput){
        try {
            int parsedUserInput = Integer.parseInt(userInput);

            switch (currentRoom) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    //region ...
                    int roomIndex = Integer.parseInt(currentRoom) - 1;


                    if (userInput.equalsIgnoreCase(rooms.get(roomIndex).getSever()) && !rooms.get(roomIndex).getSever().equalsIgnoreCase("0")) {
                        currentRoom = rooms.get(roomIndex).getSever();
                    } else if (userInput.equalsIgnoreCase(rooms.get(roomIndex).getJih()) && !rooms.get(roomIndex).getJih().equalsIgnoreCase("0")) {
                        currentRoom = rooms.get(roomIndex).getJih();
                    } else if (userInput.equalsIgnoreCase(rooms.get(roomIndex).getZapad()) && !rooms.get(roomIndex).getZapad().equalsIgnoreCase("0")) {
                        currentRoom = rooms.get(roomIndex).getZapad();
                    } else if (userInput.equalsIgnoreCase(rooms.get(roomIndex).getVychod()) && !rooms.get(roomIndex).getVychod().equalsIgnoreCase("0")) {
                        currentRoom = rooms.get(roomIndex).getVychod();
                    } else if (userInput.equalsIgnoreCase("0")){
                        System.exit(0);
                    } else {
                        System.out.println("Invalid direction!");
                    }

                    break;
                //endregion
            }
            System.out.println("𝑴𝒊𝒔𝒕𝒏𝒐𝒔𝒕 𝒑𝒐 𝒛𝒎𝒆𝒏𝒆: " + currentRoom);
        } catch (NumberFormatException e) {
            System.out.println("User input must be a number!");
        }
    }
    //endregion
    //region toString

    @Override
    public String toString() {
        return "Map{" +
                "rooms=" + rooms +
                '}';
    }

    //endregion

}
