package models;

public class Admin extends User {

    private String role;

    public Admin(int id, String name, String email, String role) {
        super(id, name, email);
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString() + " | Role: " + role;
    }
}