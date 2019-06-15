package FriggeSteyaertJamain.be.winkelKassa.domain.login;

public class user {
    private int loginId;
    private String username;
    private String hashedPassword;
    private int roleId;
    private String roleName;
    private int weight;

    public user(int loginId, String username, String hashedPassword, int roleId, String roleName, int weight) {
        this.loginId = loginId;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.roleId = roleId;
        this.roleName = roleName;
        this.weight = weight;
    }

    public int getLoginId() {
        return loginId;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getWeight() {
        return weight;
    }
}
