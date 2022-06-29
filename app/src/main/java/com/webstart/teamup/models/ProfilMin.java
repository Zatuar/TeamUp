package com.webstart.teamup.models;

public class ProfilMin {
    private String name, pictureProfil;
    private String id;

    public ProfilMin() {
    }

    public ProfilMin(String name, String pictureProfil, String id) {
        this.name = name;
        this.pictureProfil = pictureProfil;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureProfil() {
        return pictureProfil;
    }

    public void setPictureProfil(String photo) {
        this.pictureProfil = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
