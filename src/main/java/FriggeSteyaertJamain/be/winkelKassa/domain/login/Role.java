package FriggeSteyaertJamain.be.winkelKassa.domain.login;

public class Role {

    private int roleId;
    private String roleName;
    private int weight;

    public Role(int roleId, String roleName, int weight) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.weight = weight;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
