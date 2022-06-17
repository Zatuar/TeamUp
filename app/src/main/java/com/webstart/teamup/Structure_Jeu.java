package com.webstart.teamup;

public class Structure_Jeu {
    private String name, logo;
    private Integer id;

    public Structure_Jeu() {
    }

    public Structure_Jeu(String name, String logo, Integer id) {
        this.name = name;
        this.logo = logo;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
