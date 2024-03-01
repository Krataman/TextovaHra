package Map;

public class Room {
    private final String id;
    private final String name;
    private final String sever;
    private final String jih;
    private final String zapad;
    private final String vychod;

    //region constructor
    public Room(String id, String name, String sever, String jih, String zapad, String vychod) {
        this.id = id;
        this.name = name;
        this.sever = sever;
        this.jih = jih;
        this.zapad = zapad;
        this.vychod = vychod;
    }
    //endregion
    //region gts//toString
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getSever() {
        return sever;
    }
    public String getJih() {
        return jih;
    }
    public String getZapad() {
        return zapad;
    }
    public String getVychod() {
        return vychod;
    }

    @Override
    public String toString() {
        return
                "\nID: " + id +
                "\nRoom: " + name +
                "\nS:" + sever + ", J:" + jih + ", Z:" + zapad + ", V:" + vychod;
    }
    //endregion
}
