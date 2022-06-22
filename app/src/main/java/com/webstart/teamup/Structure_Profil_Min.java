package com.webstart.teamup;

public class Structure_Profil_Min {
    private String name, photo;
    private String id;

    public Structure_Profil_Min() {
    }

    public Structure_Profil_Min(String name, String photo, String id) {
        this.name = name;
        this.photo = photo;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
