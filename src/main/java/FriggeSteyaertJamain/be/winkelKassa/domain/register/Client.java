package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Client {
    private int id;
    private String name;
    private String familyName;
    private String email;
    private String telNumber;
    private String address;
    private String postAddress;

    public Client(int id, String name, String familyName, String email, String telNumber, String address, String postAddress) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.telNumber = telNumber;
        this.address = address;
        this.postAddress = postAddress;
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    private Client(ClientBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.familyName = builder.familyName;
        this.email = builder.email;
        this.telNumber = builder.telNumber;
        this.address = builder.address;
        this.postAddress = builder.postAddress;
    }

    public static class ClientBuilder{
        private int id;
        private String name;
        private String familyName;
        private String email;
        private String telNumber;
        private String address;
        private String postAddress;


        public ClientBuilder(int id){
            this.id = id;
        }

        public ClientBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ClientBuilder setFamilyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public ClientBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder setTelNumber(String telNumber) {
            this.telNumber = telNumber;
            return this;
        }

        public ClientBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public ClientBuilder setPostAddress(String postAddress) {
            this.postAddress = postAddress;
            return this;
        }

        public Client build(){
            return new Client(this);
        }
    }


}
