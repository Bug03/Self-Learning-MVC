package model;

public class Admin {

    int id;
    String username;
    String password;
    String name;
    String created;

    public Admin() {
    }

    public Admin(int id, String username, String password, String name, String created) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", created=" + created + '}';
    }
}
