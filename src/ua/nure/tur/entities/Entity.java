package ua.nure.tur.entities;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 6211999453714288955L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
