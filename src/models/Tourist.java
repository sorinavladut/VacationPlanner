package models;

import interfaces.Searchable;

public class Tourist extends User implements Searchable {

    private String passportNumber;

    public Tourist(int id, String name, String email, String passportNumber) {
        super(id, name, email);
        this.passportNumber = passportNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public boolean matches(String keyword) {
        String normalized = keyword.toLowerCase();
        return name.toLowerCase().contains(normalized) ||
                email.toLowerCase().contains(normalized) ||
                passportNumber.toLowerCase().contains(normalized);
    }

    @Override
    public String toString() {
        return super.toString() + " | Passport: " + passportNumber;
    }
}
