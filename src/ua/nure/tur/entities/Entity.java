package ua.nure.tur.entities;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 6211999453714288955L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
