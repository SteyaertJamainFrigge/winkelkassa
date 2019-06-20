package FriggeSteyaertJamain.be.winkelKassa.domain.login;

public class User {
    private int loginId;
    private String username;
    private String hashedPassword;
    private Role role;

    public User(int loginId, String username, String hashedPassword, Role role) {
        this.loginId = loginId;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
