package ua.nure.tur.entities;

public enum Role {

    ADMIN(0), CLIENT(1);

    private int id;

    Role(int id) {
        this.id = id;
    }

    public static Role getRole(int id) {
        return values()[id];
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
