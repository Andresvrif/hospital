package com.hospital.model;

public class Room {
    
    private Integer id;
    private Integer maxPatients;

    public Room(Integer maxPatients) {
        this.maxPatients = maxPatients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxPatients() {
        return maxPatients;
    }

    public void setMaxPatients(Integer maxPatients) {
        this.maxPatients = maxPatients;
    }

}
