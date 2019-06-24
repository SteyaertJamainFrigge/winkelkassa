package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Supplier {

    private int id;
    private String name;
    private String address;
    private String email;
    private String telNr;
    private String contact;

    public Supplier(int id, String name, String address, String email, String telNr, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telNr = telNr;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
