package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Personnel {

    private int id;
    private String name;
    private String surname;
    private String address;
    private String township;
    private String email;
    private int loginId;
    private String telNr;
    private String function;

    public Personnel(int id, String name, String surname, String address, String township, String email, int loginId, String telNr, String function) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.township = township;
        this.email = email;
        this.loginId = loginId;
        this.telNr = telNr;
        this.function = function;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
