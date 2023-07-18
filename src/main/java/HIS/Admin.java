package HIS;

public class Admin {
    private String username = "admin";
    private String password = "admin";
    private static Admin single_admin = null;
    private Admin(){}

    public static Admin getInstance() {
        if (single_admin == null)
            single_admin = new Admin();

        return single_admin;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
